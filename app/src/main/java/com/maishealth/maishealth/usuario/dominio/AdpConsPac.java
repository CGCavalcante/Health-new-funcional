package com.maishealth.maishealth.usuario.dominio;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maishealth.maishealth.R;

import java.util.List;

public class AdpConsPac extends BaseAdapter {
    private Context context;
    private List<DadosConsPac> lista;

    public AdpConsPac(Context context, List<DadosConsPac> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_consulta_paciente, null);

        TextView nome = view.findViewById(R.id.NomeMed);
        TextView espec = view.findViewById(R.id.EspecMed);
        TextView data = view.findViewById(R.id.DataPac);
        TextView turno = view.findViewById(R.id.TurnoPac);

        nome.setText(lista.get(position).getNomeMed().toString());
        espec.setText(lista.get(position).getEspecMed().toString());
        data.setText(lista.get(position).getData().toString());
        turno.setText(lista.get(position).getTurno().toString());

        return view;
    }
}
