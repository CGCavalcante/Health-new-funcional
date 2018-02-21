package com.maishealth.maishealth.usuario.dominio;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maishealth.maishealth.R;

import java.util.List;
/**
 * Classe responsável por adptar os dados para listview das consultas para o médico
 */

public class AdpConsMed extends BaseAdapter {
    private Context context;
    private List<DadosConsMed> lista;

    public AdpConsMed(Context context, List<DadosConsMed> lista) {
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
        view = inflater.inflate(R.layout.item_consulta_medico, null);

        TextView nome = view.findViewById(R.id.NomePac);
        TextView data = view.findViewById(R.id.DataMed);
        TextView turno = view.findViewById(R.id.TurnoMed);

        nome.setText(lista.get(position).getNomePac().toString());
        data.setText(lista.get(position).getData().toString());
        turno.setText(lista.get(position).getTurno().toString());

        return view;
    }
}
