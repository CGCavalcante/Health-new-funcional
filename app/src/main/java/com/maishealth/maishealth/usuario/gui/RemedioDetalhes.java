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
import com.maishealth.maishealth.usuario.dominio.Medicamento;
import com.maishealth.maishealth.usuario.negocio.ServicosMedicamento;

public class RemedioDetalhes extends AppCompatActivity {
    AlertDialog aviso;
    private long idRemdedio;
    private String idR;
    private ServicosMedicamento servicosMedicamento;
    private TextView nomeRemedio;
    private TextView fornecedorRemedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedio_detalhes);

        Intent intent = getIntent();
        idR = intent.getStringExtra("idmedicamento");
        idRemdedio = Long.parseLong(idR);

        servicosMedicamento = new ServicosMedicamento(getApplicationContext());
        Medicamento medicamento = servicosMedicamento.getMedicamento(idRemdedio);

        final String nome = medicamento.getNomeMedicamento();
        final String fornecedor = medicamento.getFornecedor();

        nomeRemedio = findViewById(R.id.nomeRemedio);
        fornecedorRemedio = findViewById(R.id.fornecedorRemedio);

        nomeRemedio.setText(nome.toString());
        fornecedorRemedio.setText(fornecedor.toString());

    }

    @SuppressLint("WrongViewCast")
    public void confirmarAcao(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Aviso");
        builder.setMessage("Deseja deletar o medicamento?");

        builder.setPositiveButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                Toast.makeText(RemedioDetalhes.this, "Medicamento Deletado", Toast.LENGTH_SHORT).show();
                servicosMedicamento.excluirMedicamento(idRemdedio);
                mudarTela(RemedioLista.class);
            }
        });
        aviso = builder.create();
        aviso.show();
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        intent.putExtra("idmedicamento", idR);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(RemedioLista.class);
    }

    public void sairDetslheRemedio(View view) {
        this.mudarTela(RemedioLista.class);
    }

    public void atualizaRemedio(View view) {
        this.mudarTela(RemedioAtualizar.class);
    }
}
