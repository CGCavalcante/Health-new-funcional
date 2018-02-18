package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Avaliacao;

import java.util.ArrayList;


public class AvaliacaoDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;


    public AvaliacaoDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirRecomendacao(Avaliacao avaliacao) {
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela= DataBase.TABELA_RECOMENDACAO;

        String colunaIdPaciente=DataBase.ID_EST_PACIENTE_REC;
        long idPaciente = avaliacao.getIdPaciente();
        values.put(colunaIdPaciente, idPaciente);

        String colunaIdMedico = DataBase.ID_EST_MEDICO_REC;
        long idMedico = avaliacao.getIdMedico();
        values.put(colunaIdMedico, idMedico);

        String colunaNota = DataBase.NOTA;
        double nota = avaliacao.getNota();
        values.put(colunaNota, nota);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;

    }

    public long atualizarRecomendacao(Avaliacao avaliacao) {
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_RECOMENDACAO;

        String colunaIdPaciente=DataBase.ID_EST_PACIENTE_REC;
        long idPaciente = avaliacao.getIdPaciente();
        values.put(colunaIdPaciente, idPaciente);

        String colunaIdMedico = DataBase.ID_EST_MEDICO_REC;
        long idMedico = avaliacao.getIdMedico();
        values.put(colunaIdMedico, idMedico);

        String colunaNota = DataBase.NOTA;
        double nota = avaliacao.getNota();
        values.put(colunaNota, nota);

        String whereClause = DataBase.ID_RECOMENDACAO + " = ? ";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(avaliacao.getId());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;

    }

    private Avaliacao criarRecomendacao(Cursor cursor) {

         String colunaIdPaciente= DataBase.ID_EST_PACIENTE_REC;
         int indexColunaIdPaciente = cursor.getColumnIndex(colunaIdPaciente);
         long idPaciente = cursor.getLong(indexColunaIdPaciente);

         String colunaIdMedico = DataBase.ID_EST_MEDICO_REC;
         int indexColunaIdMedico = cursor.getColumnIndex(colunaIdMedico);
         long idMedico = cursor.getLong(indexColunaIdMedico);

         String colunaNota = DataBase.NOTA;
         int indexColunaNota = cursor.getColumnIndex(colunaNota);
         double nota = cursor.getDouble(indexColunaNota);

        String colunaIdRecomendacao = DataBase.ID_RECOMENDACAO;
        int indexColunaIdRecomendacao = cursor.getColumnIndex(colunaIdRecomendacao);
        long idRecomendacao = cursor.getInt(indexColunaIdRecomendacao);

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setIdPaciente(idPaciente);
        avaliacao.setIdMedico(idMedico);
        avaliacao.setNota(nota);
        avaliacao.setId(idRecomendacao);

        return avaliacao;
     }

    private Avaliacao getRecomendacao(String query, String[] argumentos) {
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Avaliacao avaliacao = null;

        if(cursor.moveToNext()){
            avaliacao = criarRecomendacao(cursor);
        }

        cursor.close();
        liteDatabase.close();

        return avaliacao;

    }

    public Avaliacao getRecomendacaoByMedicoPaciente(long idMedico, long idPaciente) {
        String query = " SELECT * FROM " + DataBase.TABELA_RECOMENDACAO +
                " WHERE " + DataBase.ID_EST_PACIENTE_REC    + " LIKE ? " +
                    " AND " + DataBase.ID_EST_MEDICO_REC + " LIKE ? ";


        String idMedicoString   = Long.toString(idMedico);
        String idPacienteString   = Long.toString(idPaciente);
        String[] argumentos  = {idPacienteString,idMedicoString,};

        return this.getRecomendacao(query, argumentos);

    }

}

