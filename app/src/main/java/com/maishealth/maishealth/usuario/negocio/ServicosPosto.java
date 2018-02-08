package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.database.Cursor;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.DadosMedico;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicoPostoDAO;
import com.maishealth.maishealth.usuario.persistencia.PessoaDAO;
import com.maishealth.maishealth.usuario.persistencia.PostoDAO;
import com.maishealth.maishealth.usuario.persistencia.UsuarioDAO;

import java.util.ArrayList;

/**
 * Created by Wenderson de Souza on 31/01/2018.
 */

public class ServicosPosto {
    private PessoaDAO pessoaDAO;
    private MedicoDAO medicoDAO;
    private PostoDAO postoDAO;
    private MedicoPostoDAO medicoPostoDAO;

    public ServicosPosto(Context context) {
        medicoDAO = new MedicoDAO(context);
        postoDAO = new PostoDAO(context);
        medicoPostoDAO = new MedicoPostoDAO(context);
        pessoaDAO = new PessoaDAO(context);
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

    /*private ArrayList<DadosMedico> nomeEspecMedico(ArrayList<String> nomes, ArrayList<String> especs) {
        ArrayList<DadosMedico> nomeEspec = new ArrayList<DadosMedico>();

        long tamanho = nomes.size();
        for (int i = 0; i < tamanho; i++) {
            String nome = nomes.get(i);
            String espec = especs.get(i);

            nomeEspec.add(new DadosMedico(i + 1, nome, espec, R.drawable.user_avatar));

        }
        return nomeEspec;
    }

    public ArrayList<DadosMedico> returnNomeMedicos(long id) {
        ArrayList<Medico> medicos = medicoPostoDAO.getMedicosByPosto(id);

        ArrayList<String> pessoasMedico = getPessoaByMedico(medicos);
        ArrayList<String> especMedico = getEspecByMedico(medicos);

        ArrayList<DadosMedico> nomeEspec = nomeEspecMedico(pessoasMedico, especMedico);

        return nomeEspec;
    }*/

    public ArrayList<DadosMedico> medicosEspec(String espec){
        ArrayList<Medico> medicos = medicoDAO.getMedicoByEspecialidade(espec);
        ArrayList<String> pessoasMedico = getPessoaByMedico(medicos);

        ArrayList<DadosMedico> nomeEspec = setarDadosMedico(medicos,pessoasMedico);

        return nomeEspec;
    }

    public ArrayList<DadosMedico> setarDadosMedico(ArrayList<Medico> medicos,ArrayList<String> pessoasMedico){
        ArrayList<DadosMedico> nomeEspec = new ArrayList<DadosMedico>();
        long tamanho = medicos.size();
        for (int i = 0; i < tamanho; i++) {
            Medico medico = medicos.get(i);
            String nome = pessoasMedico.get(i);
            String espec = medico.getEspecialidade();

            nomeEspec.add(new DadosMedico(i + 1, nome, espec, R.drawable.user_avatar,medico.getId()));
        }
        return nomeEspec;
    }

}
