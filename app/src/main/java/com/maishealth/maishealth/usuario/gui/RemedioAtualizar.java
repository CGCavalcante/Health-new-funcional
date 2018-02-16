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
    private String idR;
    private ServicosMedicamento servicosMedicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedio_atualizar);

        Intent intent = getIntent();
        idR = intent.getStringExtra("idmedicamento");
        idRemdedio = Long.parseLong(idR);

        servicosMedicamento = new ServicosMedicamento(getApplicationContext());
        Medicamento medicamento = servicosMedicamento.getMedicamento(idRemdedio);

        GuiUtil.myToast(getApplicationContext(), "idmedicamento" + idR);
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


    public void onClickAtualizarMedicamento(View view){
        boolean valido = true;

        ValidaCadastro validaCadastro = new ValidaCadastro();

        EditText nomeRemedio =findViewById(R.id.nomeMedicamentoA);
        EditText fornecedor=findViewById(R.id.fornecedorMedicamentoA);

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
            Medicamento medicamento = servicosMedicamento.getMedicamento(idRemdedio);
            if (medicamento != null) {
                servicosMedicamento.atualizarMedicamento(idRemdedio, nomeRemedioString, fornecedorString);
                GuiUtil.myToast (this,"Medicamento Atualizado com Sucesso!");
                this.mudarTela(RemedioMenu.class);
            }

        }

    }

}
