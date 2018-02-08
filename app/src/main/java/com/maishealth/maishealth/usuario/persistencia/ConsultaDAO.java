package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Consulta;

public class ConsultaDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public ConsultaDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirConsulta(Consulta consulta){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_CONSULTA;

        String colunaIdPaciente = DataBase.ID_EST_PACIENTE_CON;
        long idPaciente = consulta.getIdPaciente();
        values.put(colunaIdPaciente, idPaciente);

        String colunaIdMedico = DataBase.ID_EST_MEDICO_CON;
        long idMedico = consulta.getIdMedico();
        values.put(colunaIdMedico, idMedico);

        String colunaData = DataBase.DATA;
        String data = consulta.getData();
        values.put(colunaData, data);

        String colunaTurno = DataBase.EST_TURNO;
        String turno = consulta.getTurno();
        values.put(colunaTurno, turno);

        String colunaStatus = DataBase.STATUS_CONSULTA;
        String status = consulta.getStatus();
        values.put(colunaStatus, status);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarConsulta(Consulta consulta){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_CONSULTA;


        String colunaPaciente = DataBase.ID_EST_PACIENTE_CON;
        long idPaciente = consulta.getIdPaciente();
        values.put(colunaPaciente, idPaciente);

        String colunaIdDataHorario = DataBase.ID_EST_MEDICO_CON;
        long idDataHorario = consulta.getIdMedico();
        values.put(colunaIdDataHorario, idDataHorario);

        String colunaData = DataBase.DATA;
        String data = consulta.getData();
        values.put(colunaData, data);

        String colunaTurno = DataBase.EST_TURNO;
        String turno = consulta.getTurno();
        values.put(colunaTurno, turno);

        String colunaStatus = DataBase.STATUS_CONSULTA;
        String status = consulta.getStatus();
        values.put(colunaStatus, status);

        String whereClause = DataBase.ID_CONSULTA + " = ? ";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(consulta.getId());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;
    }

    private Consulta criarConsulta(Cursor cursor){

        String colunaIdConsulta = DataBase.ID_CONSULTA;
        int indexColunaIdConsulta = cursor.getColumnIndex(colunaIdConsulta);
        long idConsulta = cursor.getInt(indexColunaIdConsulta);

        String colunaIdPaciente = DataBase.ID_EST_PACIENTE_CON;
        int indexColunaIdPaciente = cursor.getColumnIndex(colunaIdPaciente);
        long idPaciente = cursor.getInt(indexColunaIdPaciente);

        String colunaIdDataHorario = DataBase.ID_EST_MEDICO_CON;
        int indexColunaIdDataHorario = cursor.getColumnIndex(colunaIdDataHorario);
        long idDataHorario = cursor.getInt(indexColunaIdDataHorario);

        String colunaData = DataBase.DATA;
        int indexColunaData = cursor.getColumnIndex(colunaData);
        String data = cursor.getString(indexColunaData);

        String colunaTurno = DataBase.EST_TURNO;
        int indexColunaTurno = cursor.getColumnIndex(colunaTurno);
        String turno = cursor.getString(indexColunaTurno);

        String colunaStatus = DataBase.STATUS_CONSULTA;
        int indexColunaStatus = cursor.getColumnIndex(colunaStatus);
        String status = cursor.getString(indexColunaStatus);

        Consulta consulta = new Consulta();

        consulta.setIdPaciente(idPaciente);
        consulta.setIdMedico(idDataHorario);
        consulta.setStatus(status);
        consulta.setId(idConsulta);
        consulta.setData(data);
        consulta.setTurno(turno);

        return consulta;
    }

    private Consulta getConsulta(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Consulta consulta = null;

        if(cursor.moveToNext()){
            consulta = criarConsulta(cursor);
        }

        cursor.close();
        liteDatabase.close();

        return consulta;
    }

    public Consulta getConsulta(long idMedico, String data, String turno) {
        String query = "SELECT * FROM " + DataBase.TABELA_CONSULTA +
                " WHERE " + DataBase.ID_EST_MEDICO_CON + " LIKE ?" +
                " AND " + DataBase.DATA + " LIKE ?" +
                    " AND " + DataBase.EST_TURNO + " LIKE ?";

        String idMedicoString = Long.toString(idMedico);

        String[] argumentos = {idMedicoString, data, turno};

        return this.getConsulta(query, argumentos);
    }

}
