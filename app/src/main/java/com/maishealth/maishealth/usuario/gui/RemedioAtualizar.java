package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Medicamento;
import com.maishealth.maishealth.usuario.negocio.ServicosMedicamento;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

public class RemedioAtualizar extends AppCompatActivity {
    private long idRemdedio;
    private EditText nomeRemedio;
    private EditText fornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedio_atualizar);

        Intent intent = getIntent();
        String idR=intent.getStringExtra("idmedicamento");
        idRemdedio = Long.parseLong(idR);

        setarTexto();

    }

    private void setarTexto(){
        ServicosMedicamento servicosMedicamento=new ServicosMedicamento(getApplicationContext());
        Medicamento medicamento = servicosMedicamento.getMedicamento(idRemdedio);
        if (medicamento != null) {
            nomeRemedio =findViewById(R.id.nomeMedicamentoA);
            fornecedor=findViewById(R.id.fornecedorMedicamentoA);
            String nomeR = medicamento.getNomeMedicamento();
            String fornecedorR = medicamento.getFornecedor();
            nomeRemedio.setText(nomeR);
            fornecedor.setText(fornecedorR);
        }
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(RemedioMenu.class);
    }

    public void sairMenuRemedio(View view) {
        this.mudarTela(RemedioLista.class);
    }


    public void onClickAtualizarMedicamento(View view){
        boolean valido = true;

        ValidaCadastro validaCadastro = new ValidaCadastro();
        String nomeRemedioString = nomeRemedio.getText().toString();
        String fornecedorString = fornecedor.getText().toString();

        if (validaCadastro.isCampoVazio(nomeRemedioString)  && validaCadastro.isCampoVazio(fornecedorString))
        {
            nomeRemedio.requestFocus();
            nomeRemedio.setError("Campo obrigatório!");
            fornecedor.requestFocus();
            fornecedor.setError("Campo obrigatório!");
            valido = false;
        }

        if (validaCadastro.isCampoVazio(nomeRemedioString)){

            nomeRemedio.requestFocus();
            nomeRemedio.setError("Campo obrigatório!");
            valido = false;
        }

        if (validaCadastro.isCampoVazio(fornecedorString)){

            fornecedor.requestFocus();
            fornecedor.setError("Campo obrigatório!");
            valido = false;
        }

        if (valido) {
            atualizarMed(nomeRemedioString, fornecedorString);
        }

    }

    public void atualizarMed( String nomeRemedioString, String fornecedorString){
        ServicosMedicamento servicosMedicamento=new ServicosMedicamento(this);
        String nomeMedIns = nomeRemedioString.toUpperCase();
        String fornecIns = fornecedorString.toUpperCase();
        Medicamento medicamento = servicosMedicamento.getMedicamento(idRemdedio);

        if (medicamento != null) {
            atualizarMedByName(medicamento, nomeMedIns, fornecIns);

        }else {
            GuiUtil.myToast(this, "Não há nada para atualizar!");
        }

    }

    public void atualizarMedByName(Medicamento medicamentoAtualizar, String nomeMedIns, String fornecIns){
        ServicosMedicamento servicosMedicamento=new ServicosMedicamento(this);
        Medicamento medicamento = servicosMedicamento.getMedicamentoByName(nomeMedIns, fornecIns);
        if (medicamento == null){
            long idRemedio = medicamentoAtualizar.getId();
            servicosMedicamento.atualizarMedicamento(idRemedio, nomeMedIns, fornecIns);
            GuiUtil.myToast(this, "Medicamento Atualizado com Sucesso!");
            mudarTela(RemedioLista.class);
        }else {
            GuiUtil.myToast(this, "Já existe este Medicamento para esse Fornecedor!");
        }

    }

}
