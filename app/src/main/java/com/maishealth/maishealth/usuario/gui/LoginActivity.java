package com.maishealth.maishealth.usuario.gui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.negocio.Servicos;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.DEFAULT_IS_MEDICO_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.DEFAULT_LOGIN_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.DEFAULT_PASSWORD_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.IS_MEDICO_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.LOGIN_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.PASSWORD_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class LoginActivity extends AppCompatActivity {
    public static final String EM_CONSTRUCAO = "Em construcao";
    private EditText edtEmailLogin, edtSenhaLogin;
    private SharedPreferences sharedPreferences;
    private ImageButton btnEmergencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(TITLE_PREFERENCES, MODE_PRIVATE);
        edtEmailLogin = findViewById(R.id.emailx);
        edtSenhaLogin = findViewById(R.id.senhax);
        btnEmergencia = findViewById(R.id.bt_emerg_2);

        btnEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "102"));

                if (ActivityCompat.checkSelfPermission(LoginActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        return;
                }
                startActivity(callIntent);

            }
        });
        String email = sharedPreferences.getString(LOGIN_PREFERENCES, DEFAULT_LOGIN_PREFERENCES);
        String senha = sharedPreferences.getString(PASSWORD_PREFERENCES, DEFAULT_PASSWORD_PREFERENCES);
        if(!email.equals(DEFAULT_LOGIN_PREFERENCES) && !senha.equals(DEFAULT_PASSWORD_PREFERENCES)){
            this.logar(email, senha);
        }
    }

    public void validarLogin(View view) {
        String email = edtEmailLogin.getText().toString();
        String senha = edtSenhaLogin.getText().toString();

        ValidaCadastro validaCadastro = new ValidaCadastro();
        boolean valido = true;

        if (!validaCadastro.isSenhaValida(senha)) {
            edtSenhaLogin.requestFocus();
            edtSenhaLogin.setError(getString(R.string.error_invalid_password));
            valido = false;
        }

        if (!validaCadastro.isEmail(email)) {
            edtEmailLogin.requestFocus();
            edtEmailLogin.setError(getString(R.string.error_invalid_email));
            valido = false;
        }

        if (valido){
            this.logar(email, senha);
        }
    }

    private void logar(String email, String senha){
        Servicos servicos = new Servicos(getApplicationContext());

        try{
            servicos.login(email, senha);
            boolean isMedico = sharedPreferences.getBoolean(IS_MEDICO_PREFERENCES, DEFAULT_IS_MEDICO_PREFERENCES);

            if(isMedico){
                this.mudarTela(MenuMedicoActivity.class);
            } else{
                this.mudarTela(MenuPaciente.class);
            }
        } catch (Exception e) {
            GuiUtil.myToast(this, e);
        }
    }

    public void tela1Cadastro(View view) {
        this.mudarTela(Cadastrar.class);
    }

    public void emConstrucao(View view) {
        GuiUtil.myToast(this, EM_CONSTRUCAO);
    }

    private void mudarTela(Class proximaTela){
        Intent intent = new Intent( LoginActivity.this, proximaTela);
        startActivity(intent);
        finish();
    }

    public void teste(View view) {
        this.mudarTela(ListaMedicos.class);
    }
}
