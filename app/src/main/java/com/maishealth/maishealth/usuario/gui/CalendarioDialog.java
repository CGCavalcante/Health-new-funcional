package com.maishealth.maishealth.usuario.gui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.FormataData;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarioDialog extends AppCompatActivity {
    private final String[] listaHorarioMedico = {"Manhã", "Tarde", "Noite"};
    Button btnClick;
    TextView textData;
    private Spinner spinnerHorarioMedico;
    private String data;
    private String turno;
    private int dayOfWeek;
    private String espec;
    private String diaSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendario_dialog);

        Intent intent = getIntent();
        espec = intent.getStringExtra("espec");

        spinnerHorarioMedico = findViewById(R.id.editTextInicioHorMed);

        btnClick = (Button) findViewById(R.id.btndata);
        textData = (TextView) findViewById(R.id.data);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegarData();
            }
        });

        // inicia os valores do spinner:
        spinnerHorarioMedico.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaHorarioMedico));
        spinnerHorarioMedico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void pegarData() {

        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        DatePickerDialog dp = new DatePickerDialog(CalendarioDialog.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Recupera a data no formato dd/MM/YYYY
                FormataData formataData = new FormataData();
                data = formataData.corrigeData(year, month, dayOfMonth);
                textData.setText(data);

                // recupera o dia da semana para validar se é um dia comercial.
                GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth-1);
                dayOfWeek =date.get(date.DAY_OF_WEEK);
                diaSemana = formataData.getDiaSemana(dayOfWeek);

            }
        }, ano, mes, dia);

        dp.show();

    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        intent.putExtra("data", data);
        intent.putExtra("turno", turno);
        intent.putExtra("espec", espec);
        intent.putExtra("diaS", diaSemana);

        startActivity(intent);
        finish();
    }

    public void voltarMenuMed1(View view) {
        this.mudarTela(ListaEspecialidade.class);
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(ListaEspecialidade.class);
    }

    public void marcaConsulta(View view) {
        ValidaCadastro validaCadastro = new ValidaCadastro();
        boolean valido = true;
        if (!validaCadastro.isDataNoPassado(data)){
            textData.requestFocus();
            textData.setError("Data inválida!");
            valido = false;
        }
        if (dayOfWeek == 6 || dayOfWeek == 7){
            textData.requestFocus();
            textData.setError("Data inválida!");
            valido = false;
        }
        if (valido){
            turno = (String) spinnerHorarioMedico.getSelectedItem();
            this.mudarTela(ListaMedicos.class);
        }
    }
}
