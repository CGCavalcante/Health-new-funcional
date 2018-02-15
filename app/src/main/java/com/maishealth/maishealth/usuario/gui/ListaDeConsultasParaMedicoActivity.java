package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.maishealth.maishealth.R;

public class ListaDeConsultasParaMedicoActivity extends AppCompatActivity {

    //isso aq dos nomes e descricoes foram feitas para os testes, mas vcs q tem q adptar para na vdd
    //vir os nomes dos PACIENTES
    int[] IMAGES2 ={R.drawable.ic_event_38dp};
    String[] NAMES2 = {"Paciente João","Paciente Guilherme","Copi"};
    String[] DESCRIPTION2={"Pré","doente","marc"};

    @Override
    public void onBackPressed(){this.retornoMenuMedico();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_consultas_para_medico);

        ListView listView= findViewById(R.id.listView);
        ListaDeConsultasParaMedicoActivity.CustomAdapter customAdapter=new ListaDeConsultasParaMedicoActivity.CustomAdapter();

        listView.setAdapter(customAdapter);

    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    public void voltaMenuMedico(View view) {
        this.mudarTela(MenuMedicoActivity.class);
    }

    private void retornoMenuMedico() {
        this.mudarTela(MenuMedicoActivity.class);
    }

    //falta metodo para mostrar detalhes da consulta atual do medico(pegar o cliq da pessoa e mostrar essa tela)
    public void telaConsultaAtualMedico(View view) {
        this.mudarTela(ConsultaAtualMedActivity.class);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //return 0;
            return NAMES2.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        //@SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView_name = view.findViewById(R.id.textView_name);
            TextView textView_description = view.findViewById(R.id.textView_descriptions);


            textView_name.setText(NAMES2[i]);
            textView_description.setText(DESCRIPTION2[i]);

            return view;
        }
    }
}
