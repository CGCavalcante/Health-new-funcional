package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.content.SharedPreferences;

import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.HorarioMedico;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.HorarioMedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;

import java.util.ArrayList;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_MEDICO_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;
/**
 * Classe responsável pelos serviços do Medico
 */

public class ServicosMedico {
    private MedicoDAO medicoDAO;
    private ConsultaDAO consultaDAO;
    private HorarioMedicoDAO horarioMedicoDAO;
    private SharedPreferences sharedPreferences;

    public ServicosMedico(Context context) {
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
        medicoDAO = new MedicoDAO(context);
        consultaDAO = new ConsultaDAO(context);
        horarioMedicoDAO = new HorarioMedicoDAO(context);    }

    private long cadastrarMedico(Medico medico){
        return medicoDAO.inserirMedico(medico);
    }

    public long cadastrarMedico(String crm, String estado, String especialidade, long idUsuario) {
        Medico medico = new Medico();
        medico.setCrm(crm);
        medico.setEstado(estado);
        medico.setEspecialidade(especialidade);
        medico.setIdUsuario(idUsuario);

        return cadastrarMedico(medico);
    }

    public long criarHorario(HorarioMedico horarioMedico) {
        return horarioMedicoDAO.inserirHorarioMedico(horarioMedico);
    }

    public void criarHorario(String dia, String turno, long vagas)  {
        long idMedico = 0;
        Medico medico = medicoDAO.getMedico(sharedPreferences.getLong(ID_MEDICO_PREFERENCES, idMedico));
        idMedico = medico.getId();
        HorarioMedico horarioMedico = horarioMedicoDAO.getHorarioMedico(medico.getId(), dia,turno);

        if (horarioMedico == null) {
            HorarioMedico horarioMedicoIns = new HorarioMedico();
            horarioMedicoIns.setIdMedico(medico.getId());
            horarioMedicoIns.setTurno(turno);
            horarioMedicoIns.setVagas(vagas);
            horarioMedicoIns.setDiaSemana(dia);
            criarHorario(horarioMedicoIns);
        }else {
            horarioMedico.setVagas(vagas);
            atualizarHorario(horarioMedico);
        }
    }

    private long atualizarHorario(HorarioMedico horarioMedico) {
        return horarioMedicoDAO.atualizaHorarioMedico(horarioMedico);
    }

    public HorarioMedico getHorarioMedico(String dia, String turno){
        long idMedico = 0;
        Medico medico = medicoDAO.getMedico(sharedPreferences.getLong(ID_MEDICO_PREFERENCES, idMedico));
        HorarioMedico horarioMedico = horarioMedicoDAO.getHorarioMedico(medico.getId(), dia,turno);
        return horarioMedico;
    }

    public Medico getMedico(long id) {
        Medico medico = medicoDAO.getMedico(id);
        return medico;
    }

    public ArrayList<Medico> getMedicoByEspec(String espec){
        ArrayList<Medico> listaMedicos = new ArrayList<>();
        listaMedicos = medicoDAO.getMedicoByEspecialidade(espec);
        return listaMedicos;
    }
}
