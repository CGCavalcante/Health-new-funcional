package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.Adaptador;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.DadosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPosto;

import java.util.ArrayList;

public class ReagendarListaMedicos extends AppCompatActivity {
    ListView listaMedicos;
    ArrayList<DadosMedico> lista;
    private String data;
    private String diaSemana;

    private String idConsS;
    private String especialidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reagendar_lista_medicos);
        listaMedicos = (ListView) findViewById(R.id.lstRMedicos);

        Intent intent = getIntent();
        data = intent.getStringExtra("data");
        diaSemana = intent.getStringExtra("diaS");

        idConsS = intent.getStringExtra("idCons");
        especialidade = intent.getStringExtra("espec");

        lista = new ArrayList<>();

        Adaptador adaptador = new Adaptador(getApplication(), lista);

        listaMedicos.setAdapter(adaptador);

        listaMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DadosMedico obj = (DadosMedico) parent.getItemAtPosition(position);

                Intent passar = new Intent(getApplicationContext(), MenuPaciente.class);
                String idMedico = Long.toString(obj.getIdmedico());
                passar.putExtra("idmedico", idMedico);
                passar.putExtra("data1", data);
                passar.putExtra("diaSemana1", diaSemana);

                passar.putExtra("idCons", idConsS);
                passar.putExtra("espec", especialidade);

                startActivity(passar);
                finish();
            }
        });

    }

    /*private ArrayList<DadosMedico> preencher(String espec) {
        ServicosPosto servicosPosto = new ServicosPosto(getApplicationContext());

        return servicosPosto.medicosEspec(espec);
    }*/

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    public void voltar(View view) {
        this.mudarTela(ReagendarDia.class);
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(ReagendarDia.class);
    }
}
