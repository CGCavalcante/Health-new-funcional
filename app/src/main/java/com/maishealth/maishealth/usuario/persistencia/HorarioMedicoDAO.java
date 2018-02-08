package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.HorarioMedico;
import com.maishealth.maishealth.infra.DataBase;

public class HorarioMedicoDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public HorarioMedicoDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirHorarioMedico(HorarioMedico horarioMedico) {
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_HORARIO_MEDICO;

        String colunaDiaSemana = DataBase.DIA_DA_SEMANA;
        String diaSemana = horarioMedico.getDiaSemana();
        values.put(colunaDiaSemana, diaSemana);

        String colunaVagas = DataBase.VAGAS;
        long vagas = horarioMedico.getVagas();
        values.put(colunaVagas, vagas);

        String colunaTurno = DataBase.TURNO;
        String turno = horarioMedico.getTurno();
        values.put(colunaTurno, turno);

        String colunaIdMedico = DataBase.ID_EST_MEDICO;
        long idMedico = horarioMedico.getIdMedico();
        values.put(colunaIdMedico, idMedico);

        long id = liteDatabase.insert(tabela,null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizaHorarioMedico(HorarioMedico horarioMedico) {
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_HORARIO_MEDICO;

        String colunaDiaSemana = DataBase.DIA_DA_SEMANA;
        String diaSemana = horarioMedico.getDiaSemana();
        values.put(colunaDiaSemana, diaSemana);

        String colunaVagas = DataBase.VAGAS;
        long vagas = horarioMedico.getVagas();
        values.put(colunaVagas, vagas);

        String colunaTurno = DataBase.TURNO;
        String turno = horarioMedico.getTurno();
        values.put(colunaTurno, turno);

        String colunaIdMedico = DataBase.ID_EST_MEDICO;
        long idMedico = horarioMedico.getIdMedico();
        values.put(colunaIdMedico, idMedico);

        String whereClause = DataBase.ID_HOR_MEDICO + " = ? ";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(horarioMedico.getIdHorarioMedico());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;

    }

    private HorarioMedico criarHorarioMedico(Cursor cursor) {
        String colunaId = DataBase.ID_HOR_MEDICO;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaDiaSemana = DataBase.DIA_DA_SEMANA;
        int indexColunaDiaSemana = cursor.getColumnIndex(colunaDiaSemana);
        String diaSemana = cursor.getString(indexColunaDiaSemana);

        String colunaVagas = DataBase.VAGAS;
        int indexColunaVagas = cursor.getColumnIndex(colunaVagas);
        long vagas = cursor.getInt(indexColunaVagas);

        String colunaTurno = DataBase.TURNO;
        int indexColunaTurno = cursor.getColumnIndex(colunaTurno);
        String turno = cursor.getString(indexColunaTurno);

        String colunaIdMedico = DataBase.ID_EST_MEDICO;
        int indexColunaIdMedico = cursor.getColumnIndex(colunaIdMedico);
        long idMedico = cursor.getInt(indexColunaIdMedico);

        HorarioMedico horarioMedico = new HorarioMedico();
        horarioMedico.setIdHorarioMedico(id);
        horarioMedico.setDiaSemana(diaSemana);
        horarioMedico.setVagas(vagas);
        horarioMedico.setTurno(turno);
        horarioMedico.setIdMedico(idMedico);

        return horarioMedico;

    }

    private HorarioMedico getHorarioMedico(String query, String[] argumentos) {
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        HorarioMedico horarioMedico = null;

        if (cursor.moveToNext()) {
            horarioMedico = criarHorarioMedico(cursor);
        }

        cursor.close();
        liteDatabase.close();

        return horarioMedico;
    }

    public HorarioMedico getHorarioMedico(long idMedico, String diaSemana, String turno) {
        String query = " SELECT * FROM " + DataBase.TABELA_HORARIO_MEDICO +
                " WHERE " + DataBase.ID_EST_MEDICO + " LIKE ?" +
                " AND " + DataBase.DIA_DA_SEMANA + " LIKE ?" +
                " AND " + DataBase.TURNO + " LIKE ?";

        String idMedicoString = Long.toString(idMedico);

        String[] argumentos = {idMedicoString, diaSemana, turno};
        return this.getHorarioMedico(query, argumentos);

    }
}
