package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.database.Cursor;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.DadosMedico;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.dominio.Recomendacao;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicoPostoDAO;
import com.maishealth.maishealth.usuario.persistencia.PessoaDAO;
import com.maishealth.maishealth.usuario.persistencia.PostoDAO;
import com.maishealth.maishealth.usuario.persistencia.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Classe responsável pelos serviços de Posto
 */

public class ServicosPosto {
    private PessoaDAO pessoaDAO;
    private MedicoDAO medicoDAO;
    private PostoDAO postoDAO;
    private MedicoPostoDAO medicoPostoDAO;
    private SlopeOne slopeOne;

    public ServicosPosto(Context context) {
        medicoDAO = new MedicoDAO(context);
        postoDAO = new PostoDAO(context);
        medicoPostoDAO = new MedicoPostoDAO(context);
        pessoaDAO = new PessoaDAO(context);
        slopeOne = new SlopeOne(context);
    }

    private ArrayList<String> getPessoaByMedico(ArrayList<Medico> medicos) {
        ArrayList<String> pessoasMedico = new ArrayList<String>();
        for (Medico medico :medicos){
            long idUsuario = medico.getIdUsuario();
            Pessoa pessoa = pessoaDAO.getPessoaByIdUsuario(idUsuario);
            pessoasMedico.add(pessoa.getNome());

        }
        return pessoasMedico;
    }

    private ArrayList<String> getEspecByMedico(ArrayList<Medico> medicos) {
        ArrayList<String> especMedico = new ArrayList<String>();
        for (Medico medico : medicos){
            String espec = medico.getEspecialidade();
            especMedico.add(espec);

        }
        return especMedico;

    }

    public ArrayList<DadosMedico> medicosEspec(Paciente paciente, String espec) {
        ArrayList<Recomendacao> idmedicos = slopeOne.listaRecomendacao1(paciente, espec);

        ArrayList<DadosMedico> nomeEspec = setarDadosMedico(idmedicos);

        return nomeEspec;
    }

    private ArrayList<DadosMedico> setarDadosMedico(ArrayList<Recomendacao> idmedicos) {
        ArrayList<DadosMedico> nomeEspec = new ArrayList<DadosMedico>();

        for (Recomendacao rec : idmedicos) {
            Medico medico = medicoDAO.getMedico(rec.getIdmed());
            Pessoa pessoa = pessoaDAO.getPessoaByIdUsuario(medico.getIdUsuario());

            String nome = pessoa.getNome();
            String espec = medico.getEspecialidade();
            String nota = Double.toString(rec.getNota());

            int j = 1;
            nomeEspec.add(new DadosMedico(j, nome, espec, nota, medico.getId()));
            j++;
        }
        return nomeEspec;
    }

}
