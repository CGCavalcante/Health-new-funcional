package com.maishealth.maishealth.usuario.dominio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.DadosMedico;

import java.util.List;

/**
 * Created by Wenderson de Souza on 01/02/2018.
 */

public class Adaptador extends BaseAdapter {

    Context contexto;
    List<DadosMedico> listaMedicos;

    public Adaptador(Context contexto, List<DadosMedico> listaMedicos) {
        this.contexto = contexto;
        this.listaMedicos = listaMedicos;
    }

    @Override
    public int getCount() {
        return listaMedicos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMedicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaMedicos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;

        LayoutInflater inflater = LayoutInflater.from(contexto);
        vista = inflater.inflate(R.layout.lista_medico_by_posto, null);

        ImageView imagem = (ImageView) vista.findViewById(R.id.imagem);
        TextView nomeMedico = (TextView) vista.findViewById(R.id.nomeMedico);
        TextView especMedico = (TextView) vista.findViewById(R.id.especMedico);

        nomeMedico.setText(listaMedicos.get(position).getNome().toString());
        especMedico.setText(listaMedicos.get(position).getEspecialidade().toString());
        imagem.setImageResource(listaMedicos.get(position).getImagem());

        return vista;
    }
}
