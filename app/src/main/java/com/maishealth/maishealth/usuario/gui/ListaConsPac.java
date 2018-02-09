package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.AdpConsPac;
import com.maishealth.maishealth.usuario.dominio.DadosConsPac;
import com.maishealth.maishealth.usuario.negocio.ServicosConsulta;

import java.util.ArrayList;

public class ListaConsPac extends AppCompatActivity {
    ListView lista;
    ArrayList<DadosConsPac> listaCons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cons_pac);

        lista = findViewById(R.id.lstConsPac);
        listaCons = preencher();
        GuiUtil.myToast(getApplicationContext(), "tamanho:" + listaCons.size());

        listaCons.add(new DadosConsPac(1, 1, "medico1", "espec1", "data1", "turno1"));
        listaCons.add(new DadosConsPac(2, 2, "medico2", "espec2", "data2", "turno2"));
        listaCons.add(new DadosConsPac(3, 3, "medico2", "espec3", "data3", "turno3"));

        AdpConsPac adp = new AdpConsPac(getApplicationContext(), listaCons);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DadosConsPac consPac = (DadosConsPac) parent.getItemAtPosition(position);

                GuiUtil.myToast(getApplicationContext(), "idCons:" + consPac.getIdCons());

                Intent intent = new Intent(getApplicationContext(), DetalhesConsPac.class);
                startActivity(intent);

            }
        });
    }

    private ArrayList<DadosConsPac> preencher() {
        ServicosConsulta servicosConsulta = new ServicosConsulta(getApplicationContext());

        return servicosConsulta.preencherPac();
    }


    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(MenuPaciente.class);
    }

    public void voltarConsPac(View view) {
        this.mudarTela(MenuPaciente.class);
    }
}
