package com.maishealth.maishealth.usuario.negocio;


import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Medicamento;
import com.maishealth.maishealth.usuario.dominio.RemedioDados;
import com.maishealth.maishealth.usuario.persistencia.MedicamentoDAO;

import java.util.ArrayList;

public class ServicosMedicamento {
    private final MedicamentoDAO medicamentoDAO;

    public ServicosMedicamento (Context context){
        medicamentoDAO = new MedicamentoDAO(context);
    }

    private void cadastrarMedicamento (Medicamento medicamento){ medicamentoDAO.inserirMedicamtento(medicamento);}

    public void cadastrarMedicamento (String nomeMedicamento, String fornecedor){
        Medicamento medicamento = new Medicamento();
        medicamento.setNomeMedicamento(nomeMedicamento);
        medicamento.setFornecedor(fornecedor);
        cadastrarMedicamento(medicamento);
    }

    public void  atualizarMedicamento (long idMedicamento, String nomeMedicamento, String fornecedor){
        Medicamento medicamento = medicamentoDAO.getMedicamento(idMedicamento);
        if (medicamento != null){
            medicamento.setFornecedor(fornecedor);
            medicamento.setNomeMedicamento(nomeMedicamento);
            medicamentoDAO.atualizarMedicamento(medicamento);
        }
    }

    public Medicamento getMedicamento (long idMedicamento){
        return medicamentoDAO.getMedicamento(idMedicamento);
    }

    private ArrayList<Medicamento> getMedicamentos(){
        return medicamentoDAO.getMedicamentos();
    }

    public void excluirMedicamento(long idMedicamento){
        Medicamento medicamento = getMedicamento(idMedicamento);
        if (medicamento != null){
            medicamentoDAO.deleteMedicamento(idMedicamento);
        }
    }

    public ArrayList<RemedioDados> preencher() {
        ArrayList<Medicamento> remedios = getMedicamentos();
        ArrayList<RemedioDados> remedioDados = new ArrayList<>();
        int j = 1;
        for (Medicamento i : remedios) {
            RemedioDados remedio = new RemedioDados(j, i.getNomeMedicamento(), i.getId());
            j++;

            remedioDados.add(remedio);
        }
        return remedioDados;
    }

    public Medicamento getMedicamentoByName (String nomeMedicamento, String fornec){
        return medicamentoDAO.getMedicamentoByName(nomeMedicamento, fornec);
    }

}
