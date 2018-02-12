package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.content.SharedPreferences;

import com.maishealth.maishealth.infra.FormataData;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_PACIENTE_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ServicosPaciente {
    private PacienteDAO pacienteDAO;
    private ConsultaDAO consultaDAO;
    private SharedPreferences sharedPreferences;

    public ServicosPaciente(Context context) {
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
        pacienteDAO = new PacienteDAO(context);
        consultaDAO = new ConsultaDAO(context);
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

    public void reagendarConsulta ( long idConsultaAntiga,long idMedico, String data, String turno ){
        Consulta consulta = consultaDAO.getConsultaDisponivel(idMedico, data, turno);
        if (consulta != null){
            long idPaciente = 0;
            Paciente paciente = pacienteDAO.getPaciente(sharedPreferences.getLong(ID_PACIENTE_PREFERENCES,idPaciente));
            idPaciente = paciente.getId();
            consulta.setTurno(turno);
            consulta.setData(data);
            consulta.setIdMedico(idMedico);
            consulta.setIdPaciente(idPaciente);
            consulta.setStatus(EnumStatusConsulta.EMANDAMENTO.toString());
            consultaDAO.atualizarConsulta(consulta);

            Consulta disponibilizar = consultaDAO.getConsulta(idConsultaAntiga);
            disponibilizar.setIdPaciente(0);
            disponibilizar.setStatus(EnumStatusConsulta.DISPONIVEL.toString());
            consultaDAO.atualizarConsulta(disponibilizar);
        }

    }

    public void cancelarConsulta ( long idConsultaAntiga,long idMedico, String data, String turno ){
        Consulta consulta = consultaDAO.getConsulta(idConsultaAntiga);

        if (consulta != null){

            String dataAntiga = consulta.getData();

            if (!FormataData.dataMaiorOuIgualQueAtual(dataAntiga)) {
                consulta.setIdPaciente(0);
                consulta.setStatus(EnumStatusConsulta.DISPONIVEL.toString());
                consultaDAO.atualizarConsulta(consulta);
            }else {
                consulta.setStatus(EnumStatusConsulta.CANCELADA.toString());
                consultaDAO.atualizarConsulta(consulta);
            }
        }

    }

}
