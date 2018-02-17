package com.maishealth.maishealth.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Adaptador;
import com.maishealth.maishealth.usuario.dominio.DadosMedico;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Usuario;
import com.maishealth.maishealth.usuario.negocio.ServicosConsulta;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;
import com.maishealth.maishealth.usuario.negocio.ServicosPosto;
import com.maishealth.maishealth.usuario.persistencia.UsuarioDAO;

import java.io.Serializable;
import java.util.ArrayList;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ListaMedicos extends AppCompatActivity {
    ArrayList<DadosMedico> lista;
    private ListView listaMedicos;
    private String especialidade;
    private String data;
    private String turno;
    private String diaSemana;
    private SharedPreferences sharedPreferences;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);
        Context context = getApplicationContext();
        usuarioDAO = new UsuarioDAO(context);
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);

        listaMedicos = findViewById(R.id.lstMedicos);
        Intent intent = getIntent();
        especialidade = intent.getStringExtra("espec");
        data = intent.getStringExtra("data");
        turno = intent.getStringExtra("turno");
        diaSemana = intent.getStringExtra("diaS");

        lista = preencher(especialidade);

        Adaptador adaptador = new Adaptador(getApplication(), lista);

        listaMedicos.setAdapter(adaptador);

        listaMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DadosMedico obj = (DadosMedico) parent.getItemAtPosition(position);

                Intent passar = new Intent(getApplicationContext(), DetalhesMedico.class);
                String idMedico = Long.toString( obj.getIdmedico());
                passar.putExtra("idmedico", idMedico);
                passar.putExtra("data1", data);
                passar.putExtra("turno1", turno );
                passar.putExtra("diaSemana1", diaSemana);

                startActivity(passar);
                finish();
            }
        });

    }

    private ArrayList<DadosMedico> preencher(String espec) {
        ServicosPosto servicosPosto = new ServicosPosto(getApplicationContext());
        ServicosPaciente servicosPaciente = new ServicosPaciente(getApplicationContext());

        long idUsuario = 0;
        Usuario usuario = usuarioDAO.getUsuario(sharedPreferences.getLong(ID_USER_PREFERENCES, idUsuario));
        idUsuario = usuario.getId();

        Paciente paciente1 = servicosPaciente.getPacienteByIdUsuario(idUsuario);

        return servicosPosto.medicosEspec(paciente1, espec);
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        intent.putExtra("espec", especialidade);
        startActivity(intent);
        finish();
    }

    public void voltar(View view) {
        this.mudarTela(CalendarioDialog.class);
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(CalendarioDialog.class);
    }
}
