package com.maishealth.maishealth.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.Servicos;
import com.maishealth.maishealth.usuario.negocio.ServicosConsulta;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_PACIENTE_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class DetalhesMedico extends AppCompatActivity {
    private ImageView fotoMedico;
    private TextView nomeMedico;
    private TextView dataCons;
    private TextView turnoCons;
    private TextView crm;
    private String data;
    private String turno;
    private String idmedicoS;
    private long idmedico;
    private String diaSemana;
    private Medico medico;
    private Pessoa pessoaMedico;
    private String nomeMedicoString;
    private String especString;
    private ServicosConsulta servicosConsulta;
    private ServicosPaciente servicosPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_medico);

        fotoMedico = findViewById(R.id.fotoMedico);
        dataCons = findViewById(R.id.datacons);
        turnoCons = findViewById(R.id.turnocons);
        nomeMedico  = findViewById(R.id.txtNomeMedico);
        TextView especMedico=findViewById(R.id.txtEspecMedico);
        crm = findViewById(R.id.idTxtCrm);

        Intent intent = getIntent();
        data =  intent.getStringExtra("data1");
        turno  =  intent.getStringExtra("turno1");
        idmedicoS = intent.getStringExtra("idmedico");
        diaSemana = intent.getStringExtra("diaSemana1");
        idmedico = Long.parseLong(idmedicoS);

        ServicosMedico servicosMedico=new ServicosMedico(getApplicationContext());
        ServicosPessoa servicosPessoa=new ServicosPessoa(getApplicationContext());
        servicosConsulta = new ServicosConsulta(getApplicationContext());
        servicosPaciente = new ServicosPaciente(getApplicationContext());

        medico = servicosMedico.getMedico(idmedico);
        pessoaMedico = servicosPessoa.searchPessoaByIdUsuario(medico.getIdUsuario());
        nomeMedicoString = pessoaMedico.getNome();
        especString = medico.getEspecialidade();
        String crmString=medico.getCrm();

        dataCons.setText(data);
        turnoCons.setText(turno);
        nomeMedico.setText(nomeMedicoString);
        especMedico.setText(especString);
        crm.setText(crmString);

        // gera consultas para o dia selecionado pelo paciente
        // caso a data e turno selecionados não estejam no banco
        Consulta verificaConsulta  =  servicosConsulta.getConsulta(idmedico, turno, data);
        if (verificaConsulta == null) {
            servicosConsulta.gerarConsultas(idmedico, turno, data, diaSemana);
        }
    }

    public  void  confirmarMarcarConsulta(View view) {

        Consulta consulta=servicosConsulta.getConsulta(idmedico, turno, data);
        if (consulta != null) {
            // marca consulta para o paciente
            if (servicosPaciente.marcarConsulta(idmedico, data, turno) != 0) {
                GuiUtil.myToast(DetalhesMedico.this, "Consulta Marcada com sucesso!");
                this.mudarTela(MenuPaciente.class);

            } else {
                GuiUtil.myToast(DetalhesMedico.this, "Paciente já possui consulta marcada neste horario!");
            }
        }
    }

    public void cancelarMarcarConsulta(View view){
        mudarTela(MenuPaciente.class);

    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
}
