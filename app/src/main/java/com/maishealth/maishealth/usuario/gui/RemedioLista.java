package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.RemedioAdaptador;
import com.maishealth.maishealth.usuario.dominio.RemedioDados;
import com.maishealth.maishealth.usuario.negocio.ServicosMedicamento;

import java.util.ArrayList;

public class RemedioLista extends AppCompatActivity {
    ArrayList<RemedioDados> remedioDados;
    private ListView listaRemedios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedio_lista);

        listaRemedios = findViewById(R.id.listaMedicamentos);
        remedioDados = preencher();

        RemedioAdaptador remedioAdaptador = new RemedioAdaptador(getApplicationContext(), remedioDados);

        listaRemedios.setAdapter(remedioAdaptador);

        listaRemedios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RemedioDados remedio = (RemedioDados) parent.getItemAtPosition(position);

                Intent passar = new Intent(getApplicationContext(), RemedioDetalhes.class);
                String idRemedio = Long.toString(remedio.getIdRemedio());
                passar.putExtra("idmedicamento", idRemedio);

                startActivity(passar);
                finish();
            }
        });


    }

    private ArrayList<RemedioDados> preencher() {
        ServicosMedicamento servicosMedicamento = new ServicosMedicamento(getApplicationContext());
        return servicosMedicamento.preencher();
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
