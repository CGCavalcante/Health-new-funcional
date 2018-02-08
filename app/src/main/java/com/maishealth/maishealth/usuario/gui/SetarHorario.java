package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

public class SetarHorario extends AppCompatActivity {
    private TextView diaSemana;
    private CheckBox checkBoxM, checkBoxT, checkBoxN;
    private EditText vagasM, vagasT, vagasN;
    private String diaSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setar_horario);

        diaSemana = findViewById(R.id.textDia);

        Intent intent = getIntent();
        final String dia = intent.getStringExtra("dia");
        diaSet = dia;


        diaSemana.setText(dia);

        checkBoxM = findViewById(R.id.chkM);
        checkBoxT = findViewById(R.id.chkT);
        checkBoxN = findViewById(R.id.chkN);

        vagasM = findViewById(R.id.vagasM);
        vagasT = findViewById(R.id.vagasT);
        vagasN = findViewById(R.id.vagasN);

    }
    public void confirmarHorario(View view)  {
        ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
        ValidaCadastro validaCadastro = new ValidaCadastro();
        boolean valido = true;
        boolean jaPreenchido = false;

        String vagasMS = vagasM.getText().toString();
        String vagasTS = vagasT.getText().toString();
        String vagasNS = vagasN.getText().toString();

        String manha = "Manhã";
        String tarde = "Tarde";
        String noite = "Noite";

        if (validaCadastro.isCampoVazio(vagasMS) || !checkBoxM.isChecked()) {
            vagasM.requestFocus();
            vagasM.setError("Campo não preechido");
            valido = false;
        }

        if (validaCadastro.isCampoVazio(vagasTS) || !checkBoxT.isChecked()) {
            vagasT.requestFocus();
            vagasT.setError("Campo não preechido");
            valido = false;
        }

        if (validaCadastro.isCampoVazio(vagasNS) || !checkBoxN.isChecked()) {
            vagasN.requestFocus();
            vagasN.setError("Campo não preechido");
            valido = false;
        }

            if (valido) {

                try {
                    long vagasML = Long.parseLong(vagasMS);
                    servicosMedico.criarHorario(diaSet, manha, vagasML);
                } catch (Exception e) {
                    jaPreenchido = true;
                }

                try {
                    long vagasTL = Long.parseLong(vagasTS);
                    servicosMedico.criarHorario(diaSet, tarde, vagasTL);
                } catch (Exception e) {
                    jaPreenchido = true;
                }

                try {
                    long vagasNL = Long.parseLong(vagasNS);
                    servicosMedico.criarHorario(diaSet, noite, vagasNL);
                } catch (Exception e) {
                    jaPreenchido = true;
                }
                if (jaPreenchido){
                    GuiUtil.myToast(this, "Este horário já foi preenchido!");
                }else {
                    GuiUtil.myToast(this, "Horário inserido com sucesso!");
                    this.voltarHoraMed(view);
                }

            }
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    public void voltarHoraMed(View view) {
        this.mudarTela(HorarioMedicoAct.class);
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(HorarioMedicoAct.class);
    }
}
