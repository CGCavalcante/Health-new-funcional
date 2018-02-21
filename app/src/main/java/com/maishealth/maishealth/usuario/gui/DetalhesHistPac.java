package com.maishealth.maishealth.usuario.gui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.dominio.Avaliacao;
import com.maishealth.maishealth.usuario.negocio.ServicosConsulta;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosAvaliacao;

public class DetalhesHistPac extends AppCompatActivity {
    private RatingBar estrelinha;
    private long idMed;
    private long idPac;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_hist_pac);


        Intent intent = getIntent();
        String idConsS=intent.getStringExtra("idCons");
        final long idcons = Long.parseLong(idConsS);

        ServicosMedico servicosMedico=new ServicosMedico(getApplicationContext());
        ServicosPessoa servicosPessoa=new ServicosPessoa(getApplicationContext());
        ServicosConsulta servicosConsulta=new ServicosConsulta(getApplicationContext());
        ServicosPaciente servicosPaciente=new ServicosPaciente(getApplicationContext());

        Consulta consulta=servicosConsulta.getConsultaById(idcons);
        String turno=consulta.getTurno();
        String data=consulta.getData();

        idMed = consulta.getIdMedico();
        Medico medico=servicosMedico.getMedico(idMed);
        String espec=medico.getEspecialidade();
        String crm=medico.getCrm();
        final long idUserMed = medico.getIdUsuario();
        Pessoa pessoaMed=servicosPessoa.searchPessoaByIdUsuario(idUserMed);
        String nomemed=pessoaMed.getNome();

        idPac = consulta.getIdPaciente();
        Paciente paciente=servicosPaciente.getPacienteById(idPac);
        final long idUserPac = paciente.getIdUsuario();
        Pessoa pessoaPac=servicosPessoa.searchPessoaByIdUsuario(idUserPac);
        String nomepac=pessoaPac.getNome();

        TextView turnoCons=findViewById(R.id.turnoH);
        TextView dataCons=findViewById(R.id.dataH);

        TextView nomeMed=findViewById(R.id.medH);
        TextView especMed=findViewById(R.id.especMedH);
        TextView crmMed=findViewById(R.id.crmMedH);

        TextView nomePac=findViewById(R.id.pacH);

        dataCons.setText("Data: " + data);
        turnoCons.setText("Turno: " + turno);

        nomeMed.setText("Nome: " + nomemed);
        especMed.setText("Especialidade: " + espec);
        crmMed.setText("CRM: " + crm);

        nomePac.setText("Nome: " + nomepac);

        addListenerOnRatingBar();
        addListenerOnButton();
    }

    private void addListenerOnRatingBar() {
        estrelinha = findViewById(R.id.estrelinha);
        final TextView txtValorAvaliacao = findViewById(R.id.exemplo);

        //se o valor de avaliação mudar,
        //exiba o valor de avaliação atual no resultado (textview) automaticamente
        estrelinha.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float avaliacao, boolean fromUser) {
                txtValorAvaliacao.setText(String.valueOf(avaliacao));
            }
        });
    }

    private void addListenerOnButton() {
        estrelinha =findViewById(R.id.estrelinha);

    }

    public void confirmarAvaliacao(View view){
        int valor =(int) estrelinha.getRating();

        ServicosAvaliacao servicosAvaliacao = new ServicosAvaliacao(getApplicationContext());
        Avaliacao verificaAvaliacao;
        verificaAvaliacao=servicosAvaliacao.getRecomendacao(idMed, idPac);

        if (verificaAvaliacao == null) {
            insereAvaliacao(valor);
        }else {
            atualiazarAvaliacao(valor, verificaAvaliacao);
        }
    }
    public void insereAvaliacao(double valor){
        ServicosAvaliacao servicosAvaliacao = new ServicosAvaliacao(this);
        servicosAvaliacao.criarRecomendacao(idMed, valor);
        GuiUtil.myToast(this, "Obrigado por avaliar!");
    }

    public void atualiazarAvaliacao(double valor, Avaliacao verificaAvaliacao){
        ServicosAvaliacao servicosAvaliacao = new ServicosAvaliacao(this);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(valor);
        avaliacao.setId(verificaAvaliacao.getId());
        avaliacao.setIdMedico(verificaAvaliacao.getIdMedico());
        avaliacao.setIdPaciente(verificaAvaliacao.getIdPaciente());
        servicosAvaliacao.atualizarRecomendacao(avaliacao);
        GuiUtil.myToast(this, "Avaliação atualizada com sucesso!\nObrigado por avaliar!");
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    public void cancelarAvaliacao(View view) {
        this.mudarTela(ListaHistPac.class);
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(ListaHistPac.class);
    }

}

