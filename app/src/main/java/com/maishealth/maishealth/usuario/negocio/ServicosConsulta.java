package com.maishealth.maishealth.usuario.negocio;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.maishealth.maishealth.infra.FormataData;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.DadosConsMed;
import com.maishealth.maishealth.usuario.dominio.DadosConsPac;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.HorarioMedico;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.HorarioMedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;

import java.util.ArrayList;
import java.util.Calendar;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_MEDICO_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_PACIENTE_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ServicosConsulta {

    private HorarioMedicoDAO horarioMedicoDAO;
    private PacienteDAO pacienteDAO;
    private ConsultaDAO consultaDAO;
    private MedicoDAO medicoDAO;
    private ServicosPessoa servicosPessoa;
    private SharedPreferences sharedPreferences;
    private long idMedico;
    private long idPaciente;

    public ServicosConsulta(Context context) {
        consultaDAO = new ConsultaDAO(context);
        horarioMedicoDAO = new HorarioMedicoDAO(context);
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
        pacienteDAO = new PacienteDAO(context);
        consultaDAO = new ConsultaDAO(context);
        medicoDAO = new MedicoDAO(context);
        servicosPessoa = new ServicosPessoa(context);

    }

    private void cadastrarConsulta (Consulta consulta){
        consultaDAO.inserirConsulta(consulta);
    }

    public void cadastrarConsulta (long idMedico, String data, String turno){
        Consulta consulta = new Consulta();
        consulta.setTurno(turno);
        consulta.setData(data);
        consulta.setIdMedico(idMedico);
        consulta.setStatus(EnumStatusConsulta.DISPONIVEL.toString());
        cadastrarConsulta(consulta);
    }

    public void atualizarConsulta (long idMedico, String data, String turno ,long idPaciente){
        Consulta consulta = consultaDAO.getConsultaDisponivel(idMedico, data, turno);
        if (consulta != null){
            consulta.setTurno(turno);
            consulta.setData(data);
            consulta.setIdMedico(idMedico);
            consulta.setIdPaciente(idPaciente);
            consulta.setStatus(EnumStatusConsulta.EMANDAMENTO.toString());
            consultaDAO.atualizarConsulta(consulta);
        }

    }

    public void gerarConsultas(long idMedico, String turno, String data, String diaSemana){
        HorarioMedico horarioMedico = horarioMedicoDAO.getHorarioMedico(idMedico, diaSemana, turno);
        if (horarioMedico != null){
            long vagas = horarioMedico.getVagas() ;
            int contador = 0;
            while (contador < vagas ){
                cadastrarConsulta(idMedico,data,turno);
                contador++;
            }
        }
    }

   /* public ArrayList<Consulta> getConsultasPassadas(){

    }*/

    public Consulta getConsulta(long idMedico, String turno, String data){
        return consultaDAO.getConsultaDisponivel(idMedico, data, turno);
    }

    private ArrayList<Consulta> getConsultasDoDiaMedico(){
        String data;
        Calendar cal = Calendar.getInstance();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH);
        int ano = cal.get(Calendar.YEAR);
        FormataData formataData = new FormataData();
        data = formataData.corrigeData(ano, mes, dia);

        long idMedico = 0;
        Medico medico = medicoDAO.getMedico(sharedPreferences.getLong(ID_MEDICO_PREFERENCES, idMedico));
        idMedico = medico.getId();
        return consultaDAO.getConsultasAtuais(idMedico,data);
    }
    private ArrayList<Consulta> getConsultasPendetes(){
        long idPaciente = 0;
        Paciente paciente = pacienteDAO.getPaciente(sharedPreferences.getLong(ID_PACIENTE_PREFERENCES,idPaciente));
        idPaciente = paciente.getId();
        return  consultaDAO.getConsultaEmAndamento(idPaciente);
    }

    private ArrayList<Consulta> getConsultasConcluidas(){
        long idPaciente = 0;
        Paciente paciente = pacienteDAO.getPaciente(sharedPreferences.getLong(ID_PACIENTE_PREFERENCES,idPaciente));
        idPaciente = paciente.getId();
        return  consultaDAO.getConsultaConcluidas(idPaciente);
    }

    public ArrayList<DadosConsMed> preencherMed() {
        ArrayList<Consulta> consultas = getConsultasDoDiaMedico();
        ArrayList<DadosConsMed> consMeds = new ArrayList<>();

        DadosConsMed dados;
        int tamanho = consultas.size();
        for (int i = 0; i < tamanho; i++) {
            Consulta consulta = consultas.get(i);
            Paciente paciente = pacienteDAO.getPaciente(consulta.getIdPaciente());
            Pessoa pessoa = servicosPessoa.searchPessoaByIdUsuario(paciente.getIdUsuario());
            String data = consulta.getData();
            String turno = consulta.getTurno();

            dados = new DadosConsMed(i + 1, consulta.getId(), pessoa.getNome(), data, turno);

            consMeds.add(dados);

        }

        return consMeds;
    }

    public ArrayList<DadosConsPac> preencherConsPac() {
        ArrayList<Consulta> consultas = getConsultasPendetes();
        return getConsPacs(consultas);
    }

    public ArrayList<DadosConsPac> preencherHistPac() {
        ArrayList<Consulta> consultas = getConsultasConcluidas();
        return getConsPacs(consultas);
    }

    @NonNull
    private ArrayList<DadosConsPac> getConsPacs(ArrayList<Consulta> consultas) {
        ArrayList<DadosConsPac> consPacs = new ArrayList<>();

        DadosConsPac dados;
        int tamanho = consultas.size();
        for (int i = 0; i < tamanho; i++) {
            Consulta consulta = consultas.get(i);
            Medico medico = medicoDAO.getMedico(consulta.getIdMedico());
            Pessoa pessoa = servicosPessoa.searchPessoaByIdUsuario(medico.getIdUsuario());
            String data = consulta.getData();
            String turno = consulta.getTurno();

            dados = new DadosConsPac(i + 1, consulta.getId(), pessoa.getNome(), medico.getEspecialidade(), data, turno);

            consPacs.add(dados);

        }
        return consPacs;
    }

    public Consulta getConsultaById(long idcons) {
        Consulta consulta = consultaDAO.getConsulta(idcons);

        return consulta;
    }


}
