package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.content.SharedPreferences;

import com.maishealth.maishealth.infra.FormataData;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.HorarioMedico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.HorarioMedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;

import java.util.ArrayList;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_PACIENTE_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ServicosPaciente {
    private PacienteDAO pacienteDAO;
    private ConsultaDAO consultaDAO;
    private HorarioMedicoDAO horarioMedicoDAO;
    private SharedPreferences sharedPreferences;
    private ServicosConsulta servicosConsulta;

    public ServicosPaciente(Context context) {
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
        pacienteDAO = new PacienteDAO(context);
        consultaDAO = new ConsultaDAO(context);
        horarioMedicoDAO = new HorarioMedicoDAO(context);
        servicosConsulta = new ServicosConsulta(context);
    }

    private long cadastrarPaciente(Paciente paciente){ return pacienteDAO.inserirPaciente(paciente);
    }

    public long cadastrarPaciente(long idUsuario, String tipoSangue) {
        Paciente paciente = new Paciente();
        paciente.setIdUsuario(idUsuario);
        paciente.setTipoSangue(tipoSangue);

        return cadastrarPaciente(paciente);
    }

    private long marcarConsulta(Consulta consulta){ return consultaDAO.atualizarConsulta(consulta);
    }

    public long marcarConsulta( long idMedico, String data, String turno){
        long idPaciente = 0;
        long retorno = 0;
        Paciente paciente = pacienteDAO.getPaciente(sharedPreferences.getLong(ID_PACIENTE_PREFERENCES,idPaciente));
        idPaciente = paciente.getId();
        Consulta consulta = consultaDAO.getConsultaDisponivel(idMedico,data,turno);
        Consulta verificaConsulta = consultaDAO.getConsultaByPaciente(idMedico, data, turno, idPaciente);
        if (verificaConsulta == null) {
            consulta.setIdPaciente(idPaciente);
            consulta.setStatus(EnumStatusConsulta.EMANDAMENTO.toString());
            retorno = marcarConsulta(consulta);
        }else {
            retorno= 0;
        }
        return retorno;
    }

    public Paciente getPacienteById(long id) {
        return pacienteDAO.getPaciente(id);
    }

    public boolean reagendarConsulta(long idConsulta, String data, String diaSemana) {
        Consulta consultaAntiga = consultaDAO.getConsulta(idConsulta);
        long idmedico = consultaAntiga.getIdMedico();
        String turno = consultaAntiga.getTurno();
        Consulta consulta = consultaDAO.getConsultaDisponivel(idmedico, data, turno);
        if (consulta != null){
            consulta.setStatus(EnumStatusConsulta.EMANDAMENTO.toString());
            consulta.setIdPaciente(consultaAntiga.getIdPaciente());
            consultaDAO.atualizarConsulta(consulta);

            consultaAntiga.setStatus(EnumStatusConsulta.DISPONIVEL.toString());
            consultaAntiga.setIdPaciente(0);
            consultaDAO.atualizarConsulta(consultaAntiga);
            return true;

        }else {
            HorarioMedico horarioMedico = horarioMedicoDAO.getHorarioMedico(consultaAntiga.getIdMedico(), diaSemana,consultaAntiga.getTurno());
            if (horarioMedico != null) {
                servicosConsulta.gerarConsultas(consultaAntiga.getIdMedico(), consultaAntiga.getTurno(),data, diaSemana);
                consulta = consultaDAO.getConsultaDisponivel(idmedico, data, turno);
                consulta.setIdPaciente(consultaAntiga.getIdPaciente());
                consulta.setStatus(EnumStatusConsulta.EMANDAMENTO.toString());
                consultaDAO.atualizarConsulta(consulta);

                consultaAntiga.setStatus(EnumStatusConsulta.DISPONIVEL.toString());
                consultaAntiga.setIdPaciente(0);
                consultaDAO.atualizarConsulta(consultaAntiga);
                return true;
            }
        }
        return false;
    }

    public void cancelarConsulta(long idConsultaAntiga) {
        Consulta consulta = consultaDAO.getConsulta(idConsultaAntiga);

        if (consulta != null){

            String dataAntiga = consulta.getData();

            if (!FormataData.dataMaiorOuIgualQueAtual(dataAntiga)) {
                consulta.setIdPaciente(0);
                consulta.setStatus(EnumStatusConsulta.DISPONIVEL.toString());
                consultaDAO.atualizarConsulta(consulta);
            }else {
                consulta.setStatus(EnumStatusConsulta.CANCELADA.toString());
                consultaDAO.deleteConsulta(consulta.getId());
            }
        }
    }

    public ArrayList<Paciente> getPacientes() {
        return pacienteDAO.getPacientes();
    }
}
