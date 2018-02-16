package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;

public class RemedioMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedio_menu);
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(MenuMedicoActivity.class);
    }

    public void sairMenuRemedio(View view) {
        this.mudarTela(MenuMedicoActivity.class);
    }

    public void cadastroRemedio(View view) {
        this.mudarTela(RemedioCadastrar.class);
    }

    public void listaRemedio(View view) {
        this.mudarTela(RemedioLista.class);
    }


}
