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

public class RemedioCadastrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedio_cadastrar);

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
        this.mudarTela(RemedioMenu.class);
    }

    public void onClickInserirMedicamento(View view){
        boolean valido = true;

        ValidaCadastro validaCadastro = new ValidaCadastro();

        EditText nomeRemedio=findViewById(R.id.nomeMedicamento);
        EditText fornecedor=findViewById(R.id.fornecedorMedicamento);

        String nomeRemedioString = nomeRemedio.getText().toString();
        String fornecedorString = fornecedor.getText().toString();

        if (validaCadastro.isCampoVazio(nomeRemedioString)  && validaCadastro.isCampoVazio(fornecedorString))
        {
            nomeRemedio.requestFocus();
            nomeRemedio.setError(getString(R.string.campo_obrigatorio));
            fornecedor.requestFocus();
            fornecedor.setError(getString(R.string.campo_obrigatorio));
            valido = false;
        }

        if (validaCadastro.isCampoVazio(nomeRemedioString)){

            nomeRemedio.requestFocus();
            nomeRemedio.setError(getString(R.string.campo_obrigatorio));
            valido = false;

        }

        if (validaCadastro.isCampoVazio(fornecedorString)){

            fornecedor.requestFocus();
            fornecedor.setError(getString(R.string.campo_obrigatorio));
            valido = false;

        }

        if (valido) {
            cadastrar(nomeRemedioString, fornecedorString);
        }

    }
    public  void  cadastrar(String nomeRemedioString, String fornecedorString){
        ServicosMedicamento servicosMedicamento=new ServicosMedicamento(this);
        String nomeMedIns = nomeRemedioString.toUpperCase();
        String fornecIns = fornecedorString.toUpperCase();
        Medicamento medicamento = servicosMedicamento.getMedicamentoByName(nomeMedIns, fornecIns);

        if (medicamento == null){
            cadastrarMedicNovo(nomeMedIns,fornecedorString);
        }else {
            GuiUtil.myToast(this, "Medicamento já está existe!\n Não foi possivel cadastrar!");
        }

    }

    public void cadastrarMedicNovo(String nomeMedIns, String fornecedorString){
        ServicosMedicamento servicosMedicamento=new ServicosMedicamento(this);
        servicosMedicamento.cadastrarMedicamento(nomeMedIns, fornecedorString);
        GuiUtil.myToast(getApplicationContext(), "Medicamento Cadastrado com Sucesso!");
        mudarTela(RemedioMenu.class);
    }

}
