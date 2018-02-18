package com.maishealth.maishealth.usuario.gui;

import android.annotation.SuppressLint;
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

public class DetalhesConsFutMed extends AppCompatActivity {

    private String data;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_cons_fut_med);

        Intent intent = getIntent();
        String idConsS=intent.getStringExtra("idCons");
        final long idcons = Long.parseLong(idConsS);

        ServicosMedico servicosMedico=new ServicosMedico(getApplicationContext());
        ServicosPessoa servicosPessoa=new ServicosPessoa(getApplicationContext());
        ServicosConsulta servicosConsulta=new ServicosConsulta(getApplicationContext());
        ServicosPaciente servicosPaciente=new ServicosPaciente(getApplicationContext());

        Consulta consulta=servicosConsulta.getConsultaById(idcons);
        String turno=consulta.getTurno();
        data = consulta.getData();

        final long idmed = consulta.getIdMedico();
        Medico medico=servicosMedico.getMedico(idmed);
        String espec=medico.getEspecialidade();
        String crm=medico.getCrm();
        final long idUserMed = medico.getIdUsuario();
        Pessoa pessoaMed=servicosPessoa.searchPessoaByIdUsuario(idUserMed);
        String nomemed=pessoaMed.getNome();

        final long idpac = consulta.getIdPaciente();
        Paciente paciente=servicosPaciente.getPacienteById(idpac);
        final long idUserPac = paciente.getIdUsuario();
        Pessoa pessoaPac=servicosPessoa.searchPessoaByIdUsuario(idUserPac);
        String nomepac=pessoaPac.getNome();

        TextView turnoCons=findViewById(R.id.turnoFut);
        TextView dataCons=findViewById(R.id.dataFut);

        TextView nomeMed=findViewById(R.id.medFut);
        TextView especMed=findViewById(R.id.especMedFut);
        TextView crmMed=findViewById(R.id.crmMedFut);

        TextView nomePac=findViewById(R.id.pacFut);

        dataCons.setText("Data: " + data);
        turnoCons.setText("Turno: " + turno);

        nomeMed.setText("Nome: " + nomemed);
        especMed.setText("Especialidade: " + espec);
        crmMed.setText("CRM: " + crm);

        nomePac.setText("Nome: " + nomepac);
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        intent.putExtra("data", data);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(ListaConsFutMed.class);
    }

    public void sairConsultaFut(View view) {
        this.mudarTela(ListaConsFutMed.class);
    }
}
