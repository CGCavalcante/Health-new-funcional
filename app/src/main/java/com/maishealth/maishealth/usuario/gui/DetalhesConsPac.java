package com.maishealth.maishealth.usuario.gui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosConsulta;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

public class DetalhesConsPac extends AppCompatActivity {

    private long idconsLong;
    private ServicosPaciente servicosPaciente;

    private Consulta consulta;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_cons_pac);

        Intent intent = getIntent();
        String idConsS=intent.getStringExtra("idCons");
        idconsLong = Long.parseLong(idConsS);

        ServicosMedico servicosMedico=new ServicosMedico(getApplicationContext());
        ServicosPessoa servicosPessoa=new ServicosPessoa(getApplicationContext());
        ServicosConsulta servicosConsulta=new ServicosConsulta(getApplicationContext());
        servicosPaciente = new ServicosPaciente(getApplicationContext());

        consulta = servicosConsulta.getConsultaById(idconsLong);
        String turno=consulta.getTurno();
        String data=consulta.getData();

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

        TextView turnoCons=findViewById(R.id.turnoEA);
        TextView dataCons=findViewById(R.id.dataEA);

        TextView nomeMed=findViewById(R.id.medEA);
        TextView especMed=findViewById(R.id.especMedEA);
        TextView crmMed=findViewById(R.id.crmMedEA);

        TextView nomePac=findViewById(R.id.pacEA);

        dataCons.setText("Data: " + data);
        turnoCons.setText("Turno: " + turno);

        nomeMed.setText("Nome: " + nomemed);
        especMed.setText("Especialidade: " + espec);
        crmMed.setText("CRM: " + crm);

        nomePac.setText("Nome: " + nomepac);

    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        String idCons = Long.toString(consulta.getId());
        intent.putExtra("idCons", idCons);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(ListaConsPac.class);
    }

    public void reagendarDiaConsulta(View view) {
        this.mudarTela(ReagendarDia.class);
    }

    public void sairConsPac(View view) {
        this.mudarTela(ListaConsPac.class);
    }

    @SuppressLint("WrongViewCast")
    public void confirmarAcao(final View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Aviso");
        builder.setMessage("Deseja cancelar a consulta médica?");

        builder.setPositiveButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                Toast.makeText(DetalhesConsPac.this, "Consulta Cancelada", Toast.LENGTH_SHORT).show();
                servicosPaciente.cancelarConsulta(idconsLong);
                sairConsPac(view);
            }
        });
        AlertDialog aviso=builder.create();
        aviso.show();
    }

}
