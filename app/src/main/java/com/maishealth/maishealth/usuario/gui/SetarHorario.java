package com.maishealth.maishealth.usuario.gui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.HorarioMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

public class SetarHorario extends AppCompatActivity {
    private CheckBox checkBoxM, checkBoxT, checkBoxN;
    private EditText vagasM, vagasT, vagasN;
    private String diaSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setar_horario);

        TextView diaSemana=findViewById(R.id.textDia);

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
        setarTexto();

    }
    public void confirmarHorario(View view) throws Exception {
        ValidaCadastro validaCadastro = new ValidaCadastro();
        boolean valido = true;

        String vagasMS = vagasM.getText().toString();
        String vagasTS = vagasT.getText().toString();
        String vagasNS = vagasN.getText().toString();

        if (checkBoxM.isChecked()) {
            if (validaCadastro.isCampoVazio(vagasMS)) {
                vagasM.requestFocus();
                vagasM.setError(getString(R.string.campo_nao_preechido));
                valido=false;
            }
        }

        if (checkBoxT.isChecked()) {
            if (validaCadastro.isCampoVazio(vagasTS)) {
                vagasT.requestFocus();
                vagasT.setError(getString(R.string.campo_nao_preechido));
                valido=false;
            }
        }

        if (checkBoxN.isChecked()) {
            if (validaCadastro.isCampoVazio(vagasNS)) {
                vagasN.requestFocus();
                vagasN.setError(getString(R.string.campo_nao_preechido));
                valido=false;
            }
        }
        if (!(checkBoxM.isChecked()) && (!checkBoxT.isChecked()) && (!checkBoxN.isChecked())){
            valido = false;
        }
        if (!(validaCheckBoxEditText(checkBoxM, vagasMS))){
          valido = false;
        }
        if (!(validaCheckBoxEditText(checkBoxT, vagasTS))){
        valido = false;
        }
        if (!(validaCheckBoxEditText(checkBoxN, vagasNS))){
            valido = false;
        }
        if (valido) {
            cadastrar(vagasMS, vagasTS, vagasNS);
        } else {
        GuiUtil.myToast(this, getString(R.string.por_favar_checkbox));
        }
    }
    private boolean validaCheckBoxEditText(CheckBox checkBox, String editText){
        ValidaCadastro validaCadastro = new ValidaCadastro();
        return (checkBox.isChecked()) &&  !validaCadastro.isCampoVazio(editText);

    }
    private void cadastrar( String vagasMS, String vagasTS, String vagasNS)  {
        String manha = getString(R.string.manha);
        String tarde = getString(R.string.tarde);
        String noite = getString(R.string.noite);
        boolean atualizou = false;
        ValidaCadastro validaCadastro = new ValidaCadastro();
        ServicosMedico servicosMedico = new ServicosMedico(this);
        if ((!validaCadastro.isCampoVazio(vagasMS)) && (checkBoxM.isChecked())){
            long vagasML = Long.parseLong(vagasMS);
            servicosMedico.criarHorario(diaSet, manha, vagasML);
            atualizou = true;
        }
        if ((!validaCadastro.isCampoVazio(vagasTS)) && (checkBoxT.isChecked())){
            long vagasTL = Long.parseLong(vagasTS);
            servicosMedico.criarHorario(diaSet, tarde, vagasTL);
            atualizou = true;
        }
        if ((!validaCadastro.isCampoVazio(vagasTS))&& (checkBoxN.isChecked())){
            long vagasNL = Long.parseLong(vagasNS);
            servicosMedico.criarHorario(diaSet, noite, vagasNL);
            atualizou = true;
        }
        if (atualizou) {
            GuiUtil.myToast(this, getString(R.string.horario_atualizado));
        }
    }

    @SuppressLint("SetTextI18n")
    private void setarTexto(){
        String manha = getString(R.string.manha);
        String tarde = getString(R.string.tarde);
        String noite = getString(R.string.noite);
        ServicosMedico servicosMedico = new ServicosMedico(this);
        HorarioMedico horarioMedicoM = servicosMedico.getHorarioMedico(diaSet, manha);
        HorarioMedico horarioMedicoT = servicosMedico.getHorarioMedico(diaSet, tarde);
        HorarioMedico horarioMedicoN = servicosMedico.getHorarioMedico(diaSet, noite);
        if (horarioMedicoM != null){
            vagasM.setText(Long.toString(horarioMedicoM.getVagas()));
        }
        if (horarioMedicoT != null){
            vagasT.setText(Long.toString(horarioMedicoT.getVagas()));
        }
        if (horarioMedicoN != null){
            vagasN.setText(Long.toString(horarioMedicoN.getVagas()));
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
