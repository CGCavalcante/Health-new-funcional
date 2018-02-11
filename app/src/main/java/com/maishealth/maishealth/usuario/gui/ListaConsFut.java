package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.maishealth.maishealth.R;

public class ListaConsFut extends AppCompatActivity {
    TextView dataConsFut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cons_fut);

        Intent intent = getIntent();
        final String dataFut = intent.getStringExtra("data");

        dataConsFut = findViewById(R.id.txtdataFut);
        dataConsFut.setText(dataFut);
    }
}
