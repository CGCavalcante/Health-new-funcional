package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.gui.MenuMedicoActivity;
import com.maishealth.maishealth.usuario.gui.SetarHorario;

public class HorarioMedicoAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_medico);

    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    private void mudarTela(Class tela, String dia) {
        Intent intent = new Intent(this, tela);

        intent.putExtra("dia", dia);
        startActivity(intent);
        finish();
    }

    public void horSegunda(View view) {
        mudarTela(SetarHorario.class, "Segunda");
    }

    public void horTerca(View view) {
        mudarTela(SetarHorario.class, "Terca");
    }

    public void horQuarta(View view) {
        mudarTela(SetarHorario.class, "Quarta");
    }

    public void horQuinta(View view) {
        mudarTela(SetarHorario.class, "Quinta");
    }

    public void horSexta(View view) {
        mudarTela(SetarHorario.class, "Sexta");
    }

    public void voltarMenuMed(View view) {
        this.mudarTela(MenuMedicoActivity.class);
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(MenuMedicoActivity.class);
    }
}
