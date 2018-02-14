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
    private long criarRecomendacao(Recomendacao recomendacao ){ return recomendacaoDAO.inserirRecomendacao(recomendacao);}

    public long criarRecomendacao (long idMedico, int nota){

        long idPaciente =0;
        Paciente paciente = pacienteDAO.getPaciente(sharedPreferences.getLong(ID_PACIENTE_PREFERENCES,idPaciente));
        idPaciente = paciente.getId();
        Recomendacao verificaRecomendacao = recomendacaoDAO.getRecomendacaoByMedicoPaciente( idMedico, idPaciente );
        Recomendacao recomendacao = new Recomendacao();
        if (verificaRecomendacao == null){

            recomendacao.setIdMedico(idMedico);
            recomendacao.setIdPaciente( paciente.getId());
            recomendacao.setNota(nota);
        }
        return criarRecomendacao(recomendacao);
    }

    public Recomendacao getRecomendacao(long idMedico, long idPaciente){ return recomendacaoDAO.getRecomendacaoByMedicoPaciente(idMedico,idPaciente);}

    public ArrayList<Recomendacao> getRecomendacao (long idMedico){
        return recomendacaoDAO.getRecomendacaoByMedico(idMedico);
    }
}


