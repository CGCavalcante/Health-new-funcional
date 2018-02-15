package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.content.SharedPreferences;

import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Avaliacao;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;
import com.maishealth.maishealth.usuario.persistencia.AvaliacaoDAO;

import java.util.ArrayList;
import java.util.List;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_PACIENTE_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ServicosAvaliacao {
    private PacienteDAO pacienteDAO;
    private ConsultaDAO consultaDAO;
    private MedicoDAO medicoDAO;
    private ServicosPessoa servicosPessoa;
    private SharedPreferences sharedPreferences;
    private AvaliacaoDAO avaliacaoDAO;

    public ServicosAvaliacao(Context context) {
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
        pacienteDAO = new PacienteDAO(context);
        medicoDAO = new MedicoDAO(context);
        servicosPessoa = new ServicosPessoa(context);
        consultaDAO = new ConsultaDAO(context);
        avaliacaoDAO = new AvaliacaoDAO(context);

    }

    private long criarRecomendacao(Avaliacao avaliacao) {
        return avaliacaoDAO.inserirRecomendacao(avaliacao);
    }

    public long criarRecomendacao (long idMedico, int nota){

        long idPaciente =0;
        Paciente paciente = pacienteDAO.getPaciente(sharedPreferences.getLong(ID_PACIENTE_PREFERENCES,idPaciente));
        idPaciente = paciente.getId();
        Avaliacao verificaAvaliacao = avaliacaoDAO.getRecomendacaoByMedicoPaciente(idMedico, idPaciente);
        Avaliacao avaliacao = new Avaliacao();
        if (verificaAvaliacao == null) {

            avaliacao.setIdMedico(idMedico);
            avaliacao.setIdPaciente(paciente.getId());
            avaliacao.setNota(nota);
        }
        return criarRecomendacao(avaliacao);
    }

    public Avaliacao getRecomendacao(long idMedico, long idPaciente) {
        return avaliacaoDAO.getRecomendacaoByMedicoPaciente(idMedico, idPaciente);
    }

    public ArrayList<Avaliacao> getRecomendacaoByMedico(long idMedico) {
        return avaliacaoDAO.getRecomendacaoByMedico(idMedico);
    }

    public ArrayList<Avaliacao> getRecomendacoes() {
        return avaliacaoDAO.getRecomendacoes();
    }

    public ArrayList<Avaliacao> getRecomendacaoByPacienteAndEspec(long idPaciente, List<Medico> medicos) {
        ArrayList<Avaliacao> medicosAvaliados = new ArrayList<>();
        for (Medico medico : medicos) {
            medicosAvaliados.add(avaliacaoDAO.getRecomendacaoByMedicoPaciente(medico.getId(), idPaciente));
        }
        return medicosAvaliados;
    }
}


