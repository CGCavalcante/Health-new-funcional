package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.content.SharedPreferences;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Recomendacao;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;
import com.maishealth.maishealth.usuario.persistencia.RecomendacaoDAO;

import java.util.ArrayList;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_PACIENTE_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ServicosRecomendacao {
    private PacienteDAO pacienteDAO;
    private ConsultaDAO consultaDAO;
    private MedicoDAO medicoDAO;
    private ServicosPessoa servicosPessoa;
    private SharedPreferences sharedPreferences;
    private RecomendacaoDAO recomendacaoDAO;

    public  ServicosRecomendacao (Context context){
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
        pacienteDAO = new PacienteDAO(context);
        medicoDAO = new MedicoDAO(context);
        servicosPessoa = new ServicosPessoa(context);
        consultaDAO = new ConsultaDAO(context);
        recomendacaoDAO = new RecomendacaoDAO(context);

    }
    private void criarConsulta ( Recomendacao recomendacao ){recomendacaoDAO.inserirRecomendacao(recomendacao);}

    public void criarRecomendacao (long idConsulta, long idMedico, int nota){

        long idPaciente = 0;
        Paciente paciente = pacienteDAO.getPaciente(sharedPreferences.getLong(ID_PACIENTE_PREFERENCES,idPaciente));
        Recomendacao recomendacao = recomendacaoDAO.getRecomendacaoMedico( idConsulta, idPaciente );
        if (recomendacao != null){
            recomendacao.setIdConsulta(idConsulta);
            recomendacao.setIdMedico(idMedico);
            recomendacao.setIdPaciente(paciente.getId());
            recomendacao.setNota(nota);

        }

    }

    public ArrayList<Recomendacao> getRecomendacao (long idMedico){
        return recomendacaoDAO.getRecomendacaoByMedico(idMedico);
    }
}


