package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosConsulta;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

public class DetalhesHistPac extends AppCompatActivity {
    private String idConsS;
    private ServicosMedico servicosMedico;
    private ServicosPessoa servicosPessoa;
    private ServicosConsulta servicosConsulta;
    private ServicosPaciente servicosPaciente;

    private Consulta consulta;
    private TextView dataCons;
    private TextView turnoCons;
    private String data;
    private String turno;

    private Medico medico;
    private Pessoa pessoaMed;
    private String nomemed;
    private String espec;
    private String crm;
    private TextView nomeMed;
    private TextView especMed;
    private TextView crmMed;

    private Paciente paciente;
    private Pessoa pessoaPac;
    private String nomepac;
    private TextView nomePac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_hist_pac);

        Intent intent = getIntent();
        idConsS = intent.getStringExtra("idCons");
        final long idcons = Long.parseLong(idConsS);

        servicosMedico = new ServicosMedico(getApplicationContext());
        servicosPessoa = new ServicosPessoa(getApplicationContext());
        servicosConsulta = new ServicosConsulta(getApplicationContext());
        servicosPaciente = new ServicosPaciente(getApplicationContext());

        consulta = servicosConsulta.getConsultaById(idcons);
        turno = consulta.getTurno();
        data = consulta.getData();

        final long idmed = consulta.getIdMedico();
        medico = servicosMedico.getMedico(idmed);
        espec = medico.getEspecialidade();
        crm = medico.getCrm();
        final long idUserMed = medico.getIdUsuario();
        pessoaMed = servicosPessoa.searchPessoaByIdUsuario(idUserMed);
        nomemed = pessoaMed.getNome();

        final long idpac = consulta.getIdPaciente();
        paciente = servicosPaciente.getPacienteById(idpac);
        final long idUserPac = paciente.getIdUsuario();
        pessoaPac = servicosPessoa.searchPessoaByIdUsuario(idUserPac);
        nomepac = pessoaPac.getNome();

        turnoCons = findViewById(R.id.turnoH);
        dataCons = findViewById(R.id.dataH);

        nomeMed = findViewById(R.id.medH);
        especMed = findViewById(R.id.especMedH);
        crmMed = findViewById(R.id.crmMedH);

        nomePac = findViewById(R.id.pacH);

        dataCons.setText("Data: " + data);
        turnoCons.setText("Turno: " + turno);

        nomeMed.setText("Nome: " + nomemed);
        especMed.setText("Especialidade: " + espec);
        crmMed.setText("CRM: " + crm);

        nomePac.setText("Nome: " + nomepac);

    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(ListaHistPac.class);
    }

}

