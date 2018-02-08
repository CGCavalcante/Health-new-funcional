package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

public class DetalhesMedico extends AppCompatActivity {
    private ImageView fotoMedico;
    private TextView nomeMedico;
    private TextView especMedico;
    private TextView dataCons;
    private TextView turnoCons;
    private TextView crm;
    private String data;
    private String turno;
    private String idmedicoS;
    private String diaSemana;
    private Medico medico;
    private Pessoa pessoaMedico;
    private String nomeMedicoString;
    private String especString;
    private String crmString;
    private ServicosMedico servicosMedico;
    private ServicosPessoa servicosPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_medico);

        fotoMedico = findViewById(R.id.fotoMedico);
        //nomeMedico = findViewById(R.id.nomeMedico);
        //especMedico =  findViewById(R.id.especMedico);
        dataCons = findViewById(R.id.datacons);
        turnoCons = findViewById(R.id.turnocons);
        nomeMedico  = findViewById(R.id.txtNomeMedico);
        especMedico = findViewById(R.id.txtEspecMedico);
        crm = findViewById(R.id.idTxtCrm);


        Intent intent = getIntent();
        data =  intent.getStringExtra("data1");
        turno =  intent.getStringExtra("turno1");
        idmedicoS = intent.getStringExtra("idmedico");
        diaSemana = intent.getStringExtra("diaSemana1");
        long idmedico = Long.parseLong(idmedicoS);

        servicosMedico = new ServicosMedico(getApplicationContext());
        servicosPessoa = new ServicosPessoa(getApplicationContext());

        medico = servicosMedico.getMedico(idmedico);
        pessoaMedico = servicosPessoa.searchPessoaByIdUsuario(medico.getIdUsuario());
        nomeMedicoString = pessoaMedico.getNome().toString();
        especString = medico.getEspecialidade().toString();
        crmString = medico.getCrm().toString();

        GuiUtil.myToast(getApplicationContext(), "data" + data + "\nturno" + turno + "\nidmedico" + idmedico + "\ndiaSemana " + diaSemana);

        dataCons.setText(data);
        turnoCons.setText(turno);
        nomeMedico.setText(nomeMedicoString);
        especMedico.setText(especString);
        crm.setText(crmString);


        /*DadosMedico obj = (DadosMedico) getIntent().getExtras().getSerializable("objeto");
        nomeMedico.setText(obj.getNome());
        especMedico.setText(obj.getEspecialidade());
        fotoMedico.setImageResource(obj.getImagem());*/
    }
}
