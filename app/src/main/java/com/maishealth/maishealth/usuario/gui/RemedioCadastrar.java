package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.negocio.ServicosMedicamento;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

public class RemedioCadastrar extends AppCompatActivity {
    private EditText nomeRemedio, fornecedor;

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

        nomeRemedio = findViewById(R.id.nomeMedicamento);
        fornecedor = findViewById(R.id.fornecedorMedicamento);

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

            ServicosMedicamento servicosMedicamento=new ServicosMedicamento(this);
            servicosMedicamento.cadastrarMedicamento(nomeRemedioString, fornecedorString);

        }

    }

}
