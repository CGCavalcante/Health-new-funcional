package com.maishealth.maishealth.usuario.persistencia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.DataHorario;

public class DataHorarioDAO {
    /*private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public DataHorarioDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long InserirDataHorario(DataHorario dataHorario) {
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_DATA_HORARIO;

        String colunaIdHorarioMedico = DataBase.ID_EST_HORARIO_MED;
        long idHorarioMedico = dataHorario.getIdHorarioMedico();
        values.put(colunaIdHorarioMedico, idHorarioMedico);

        String colunaData = DataBase.DATA;
        String data = dataHorario.getData();
        values.put(colunaData, data);

        String colunaContador = DataBase.CONTADOR_REGRESIVO;
        long contador = dataHorario.getContador();
        values.put(colunaContador, contador);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;

    }

    public long atualizarDataHorario(DataHorario dataHorario) {
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_DATA_HORARIO;

        String colunaIdHorarioMedico = DataBase.ID_EST_HORARIO_MED;
        long idHorarioMedico = dataHorario.getIdHorarioMedico();
        values.put(colunaIdHorarioMedico, idHorarioMedico);

        String colunaData = DataBase.DATA;
        String data = dataHorario.getData();
        values.put(colunaData, data);

        String colunaContador = DataBase.CONTADOR_REGRESIVO;
        long contador = dataHorario.getContador();
        values.put(colunaContador, contador);

        String whereClause = DataBase.ID_DATA_HORARIO + " = ? ";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(dataHorario.getId());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;
    }

    private DataHorario criarDataHorario(Cursor cursor) {

        String colunaId = DataBase.ID_DATA_HORARIO;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long idDataHorario = cursor.getInt(indexColunaId);

        String colunaIdHorarioMed = DataBase.ID_EST_HORARIO_MED;
        int indexColunaIdHorarioMed = cursor.getColumnIndex(colunaIdHorarioMed);
        long IdHorarioMed = cursor.getInt(indexColunaIdHorarioMed);

        String colunaData = DataBase.DATA;
        int indexColunaData = cursor.getColumnIndex(colunaData);
        String data = cursor.getString(indexColunaData);

        String colunaContador = DataBase.CONTADOR_REGRESIVO;
        int indexContador = cursor.getColumnIndex(colunaContador);
        long contador = cursor.getInt(indexContador);

        DataHorario dataHorario = new DataHorario();
        dataHorario.setId(idDataHorario);
        dataHorario.setIdHorarioMedico(IdHorarioMed);
        dataHorario.setData(data);
        dataHorario.setContador(contador);

        return dataHorario;
    }

    private DataHorario getDataHorario(String query, String[] argumentos) {
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        DataHorario dataHorario = null;

        if (cursor.moveToNext()) {
            dataHorario = criarDataHorario(cursor);
        }
        cursor.close();
        liteDatabase.close();

        return dataHorario;
    }

    public DataHorario getDataHorario(long idHorarioMed, String data) {
        String query = " SELECT * FROM " + DataBase.TABELA_DATA_HORARIO +
                " WHERE " + DataBase.DATA + " LIKE ? " +
                " AND " + DataBase.ID_EST_HORARIO_MED + " LIKE ?";

        String idHorarioMedString = Long.toString(idHorarioMed);

        String[] argumentos = {data, idHorarioMedString};

        return this.getDataHorario(query, argumentos);

    }*/
}
