package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.AdpConsMed;
import com.maishealth.maishealth.usuario.dominio.DadosConsMed;

import java.util.ArrayList;

public class ListaConsMed extends AppCompatActivity {
    ListView lista;
    ArrayList<DadosConsMed> listaCons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cons_med);

        lista = findViewById(R.id.lstConsMed);
        listaCons = new ArrayList<DadosConsMed>();

        listaCons.add(new DadosConsMed(1, 1, "paciente1", "data1", "turno1"));
        listaCons.add(new DadosConsMed(2, 2, "paciente2", "data2", "turno2"));
        listaCons.add(new DadosConsMed(3, 3, "paciente3", "data3", "turno3"));

        AdpConsMed adp = new AdpConsMed(getApplicationContext(), listaCons);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DadosConsMed consMed = (DadosConsMed) parent.getItemAtPosition(position);

                GuiUtil.myToast(getApplicationContext(), "idCons:" + consMed.getIdCons());

                Intent intent = new Intent(getApplicationContext(), DetalhesConsMed.class);
                startActivity(intent);
            }
        });
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

    public void voltarConsMed(View view) {
        this.mudarTela(MenuMedicoActivity.class);
    }
}
