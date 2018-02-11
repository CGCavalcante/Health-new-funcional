package com.maishealth.maishealth.usuario.gui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.FormataData;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.gui.CalendarioDialog;
import com.maishealth.maishealth.usuario.gui.ListaEspecialidade;
import com.maishealth.maishealth.usuario.gui.ListaMedicos;
import com.maishealth.maishealth.usuario.gui.MenuMedicoActivity;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ConsultasFuturas extends AppCompatActivity {
    Button btnClick;
    TextView textData;
    private String data;
    private int dayOfWeek;
    private String diaSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_futuras);


        btnClick = (Button) findViewById(R.id.btndataFut);
        textData = (TextView) findViewById(R.id.dataFut);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegarData();
            }
        });
    }

    private void pegarData() {

        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        DatePickerDialog dp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Recupera a data no formato dd/MM/YYYY
                FormataData formataData = new FormataData();
                data = formataData.corrigeData(year, month, dayOfMonth);
                textData.setText(data);

                // recupera o dia da semana para validar se é um dia comercial.
                GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth - 1);
                dayOfWeek = date.get(date.DAY_OF_WEEK);
                diaSemana = formataData.getDiaSemana(dayOfWeek);

            }
        }, ano, mes, dia);

        dp.show();

    }

    public void consultaFutura(View view) {
        ValidaCadastro validaCadastro = new ValidaCadastro();
        boolean valido = true;
        if (!validaCadastro.isDataNoPassado(data)) {
            textData.requestFocus();
            textData.setError("Data inválida!");
            valido = false;
        }
        if (dayOfWeek == 6 || dayOfWeek == 7) {
            textData.requestFocus();
            textData.setError("Data inválida!");
            valido = false;
        }
        if (valido) {
            GuiUtil.myToast(this, "Data Valida = " + valido);
            this.mudarTela(ListaConsFut.class);
        }
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        intent.putExtra("data", data);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(MenuMedicoActivity.class);
    }


}
