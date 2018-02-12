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
import com.maishealth.maishealth.usuario.negocio.ServicosConsulta;

import java.util.ArrayList;

public class ListaConsFutMed extends AppCompatActivity {
    private ListView lista;
    private ArrayList<DadosConsMed> listaCons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cons_fut_med);

        lista = findViewById(R.id.lstConsFutMed);
        listaCons = preencher();

        AdpConsMed adp = new AdpConsMed(getApplicationContext(), listaCons);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DadosConsMed consMed = (DadosConsMed) parent.getItemAtPosition(position);

                GuiUtil.myToast(getApplicationContext(), "idCons:" + consMed.getIdCons());

                Intent intent = new Intent(getApplicationContext(), DetalhesConsFutMed.class);
                String idCons = Long.toString(consMed.getIdCons());
                intent.putExtra("idCons", idCons);
                startActivity(intent);
                finish();
            }
        });
    }

    private ArrayList<DadosConsMed> preencher() {
        ServicosConsulta servicosConsulta = new ServicosConsulta(getApplicationContext());

        return servicosConsulta.preencherMed();
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

    public void voltarConsFutMed(View view) {
        this.mudarTela(MenuMedicoActivity.class);
    }
}