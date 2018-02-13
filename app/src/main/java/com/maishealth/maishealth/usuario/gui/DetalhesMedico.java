package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosConsulta;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

public class DetalhesMedico extends AppCompatActivity {
    private String data;
    private String turno;
    private long idmedico;
    private ServicosConsulta servicosConsulta;
    private ServicosPaciente servicosPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_medico);

        ImageView fotoMedico=findViewById(R.id.fotoMedico);
        TextView dataCons=findViewById(R.id.datacons);
        TextView turnoCons=findViewById(R.id.turnocons);
        TextView nomeMedico=findViewById(R.id.txtNomeMedico);
        TextView especMedico=findViewById(R.id.txtEspecMedico);
        TextView crm=findViewById(R.id.idTxtCrm);

        Intent intent = getIntent();
        data =  intent.getStringExtra("data1");
        turno  =  intent.getStringExtra("turno1");
        String idmedicoS=intent.getStringExtra("idmedico");
        String diaSemana=intent.getStringExtra("diaSemana1");
        idmedico = Long.parseLong(idmedicoS);

        ServicosMedico servicosMedico=new ServicosMedico(getApplicationContext());
        ServicosPessoa servicosPessoa=new ServicosPessoa(getApplicationContext());
        servicosConsulta = new ServicosConsulta(getApplicationContext());
        servicosPaciente = new ServicosPaciente(getApplicationContext());

        Medico medico=servicosMedico.getMedico(idmedico);
        Pessoa pessoaMedico=servicosPessoa.searchPessoaByIdUsuario(medico.getIdUsuario());
        String nomeMedicoString=pessoaMedico.getNome();
        String especString=medico.getEspecialidade();
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

            } else {
                GuiUtil.myToast(DetalhesMedico.this, "Paciente já possui consulta marcada neste horario");
            }

        }
    }
}
