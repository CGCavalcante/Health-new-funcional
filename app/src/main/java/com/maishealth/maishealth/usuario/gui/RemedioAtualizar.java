package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Medicamento;
import com.maishealth.maishealth.usuario.negocio.ServicosMedicamento;

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
}
