package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.EnumEspecialidade;

public class ListaEspecialidade extends AppCompatActivity {
    ListView listaEspec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_especialidade);

        listaEspec = (ListView) findViewById(R.id.lstEspec);
        String[] especs = EnumEspecialidade.enumEspecialidadeLista();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, especs);

        listaEspec.setAdapter(adapter);

        listaEspec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String espec = (String) parent.getItemAtPosition(position);

                Intent proximo = new Intent(getApplicationContext(), CalendarioDialog.class);
                proximo.putExtra("espec", espec);
                startActivity(proximo);
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
        this.mudarTela(MenuPaciente.class);
    }

    public void voltarPac(View view) {
        this.mudarTela(MenuPaciente.class);
    }

}
