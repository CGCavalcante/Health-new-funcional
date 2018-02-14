package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class DetalhesConsMed extends AppCompatActivity {
    private String idConsS;
    private long idConsLomg;
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
        setContentView(R.layout.activity_detalhes_cons_med);

        Intent intent = getIntent();
        idConsS = intent.getStringExtra("idCons");
        idConsLomg = Long.parseLong(idConsS);

        servicosMedico = new ServicosMedico(getApplicationContext());
        servicosPessoa = new ServicosPessoa(getApplicationContext());
        servicosConsulta = new ServicosConsulta(getApplicationContext());
        servicosPaciente = new ServicosPaciente(getApplicationContext());

        consulta = servicosConsulta.getConsultaById(idConsLomg);
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

        turnoCons = findViewById(R.id.turnoD);
        dataCons = findViewById(R.id.dataD);

        nomeMed = findViewById(R.id.medD);
        especMed = findViewById(R.id.especMedD);
        crmMed = findViewById(R.id.crmMedD);

        nomePac = findViewById(R.id.pacD);

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
        this.mudarTela(ListaConsMed.class);
    }

    public void concluirConsulta(View view) {
        servicosConsulta.concluirConsulta(idConsLomg);

        this.mudarTela(MenuMedicoActivity.class);
    }
}
