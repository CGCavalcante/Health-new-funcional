package com.maishealth.maishealth.usuario.dominio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maishealth.maishealth.R;

import java.util.List;

public class RemedioAdaptador extends BaseAdapter {
    Context context;
    List<RemedioDados> remedios;

    public RemedioAdaptador(Context context, List<RemedioDados> remedios) {
        this.context = context;
        this.remedios = remedios;
    }

    @Override
    public int getCount() {
        return remedios.size();
    }

    @Override
    public Object getItem(int position) {
        return remedios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return remedios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;

        LayoutInflater inflater = LayoutInflater.from(context);
        vista = inflater.inflate(R.layout.remediovisualisacao, null);

        TextView nome = vista.findViewById(R.id.nomeRemedioVisual);

        nome.setText(remedios.get(position).getNome().toString());

        return vista;
    }
}
