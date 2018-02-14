package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Recomendacao;

import java.util.ArrayList;


public class RecomendacaoDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;


    public RecomendacaoDAO(Context context){dataBaseHelper = new DataBase(context);}

    public long inserirRecomendacao (Recomendacao recomendacao) {
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela= DataBase.TABELA_RECOMENDACAO;

        String colunaIdPaciente=DataBase.ID_EST_PACIENTE_REC;
        long idPaciente=recomendacao.getIdPaciente();
        values.put(colunaIdPaciente, idPaciente);

        String colunaIdMedico = DataBase.ID_EST_MEDICO_REC;
        long idMedico = recomendacao.getIdMedico();
        values.put(colunaIdMedico, idMedico);

        String colunaNota = DataBase.NOTA;
        long nota = recomendacao.getNota();
        values.put(colunaNota, nota);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;

    }

    public long atualizarRecomendacao (Recomendacao recomendacao){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_RECOMENDACAO;

        String colunaIdPaciente=DataBase.ID_EST_PACIENTE_REC;
        long idPaciente=recomendacao.getIdPaciente();
        values.put(colunaIdPaciente, idPaciente);

        String colunaIdMedico = DataBase.ID_EST_MEDICO_REC;
        long idMedico = recomendacao.getIdMedico();
        values.put(colunaIdMedico, idMedico);

        String colunaNota = DataBase.NOTA;
        long nota = recomendacao.getNota();
        values.put(colunaNota, nota);

        String whereClause = DataBase.ID_RECOMENDACAO + " = ? ";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(recomendacao.getId());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;

    }

     private Recomendacao criarRecomendacao (Cursor cursor){

         String colunaIdPaciente= DataBase.ID_EST_PACIENTE_REC;
         int indexColunaIdPaciente = cursor.getColumnIndex(colunaIdPaciente);
         long idPaciente = cursor.getLong(indexColunaIdPaciente);

         String colunaIdMedico = DataBase.ID_EST_MEDICO_REC;
         int indexColunaIdMedico = cursor.getColumnIndex(colunaIdMedico);
         long idMedico = cursor.getLong(indexColunaIdMedico);

         String colunaNota = DataBase.NOTA;
         int indexColunaNota = cursor.getColumnIndex(colunaNota);
         int nota = cursor.getInt(indexColunaNota);

         Recomendacao recomendacao = new Recomendacao();

         recomendacao.setIdPaciente(idPaciente);
         recomendacao.setIdMedico(idMedico);
         recomendacao.setNota(nota);

         return recomendacao;
     }
    private Recomendacao getRecomendacao (String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Recomendacao recomendacao = null;

        if(cursor.moveToNext()){
            recomendacao = criarRecomendacao (cursor);
        }

        cursor.close();
        liteDatabase.close();

        return recomendacao;

    }

    public  Recomendacao getRecomendacao(long idRecomendacao){
        String query = " SELECT * FROM " + DataBase.TABELA_RECOMENDACAO +
                " WHERE " + DataBase.ID_RECOMENDACAO + " LIKE ? ";

        String idRecomendacaoString   = Long.toString(idRecomendacao);
        String[] argumentos  = {idRecomendacaoString};

        return this.getRecomendacao(query, argumentos);

    }
    public  Recomendacao getRecomendacaoByPaciente(long idPaciente){
        String query = " SELECT * FROM " + DataBase.TABELA_RECOMENDACAO +
                " WHERE " + DataBase.ID_EST_PACIENTE_REC + " LIKE ? ";

        String idPacienteString   = Long.toString(idPaciente);
        String[] argumentos  = {idPacienteString};

        return this.getRecomendacao(query, argumentos);

    }

    public  Recomendacao getRecomendacaoByMedicoPaciente(long idMedico, long idPaciente){
        String query = " SELECT * FROM " + DataBase.TABELA_RECOMENDACAO +
                " WHERE " + DataBase.ID_EST_PACIENTE_REC    + " LIKE ? " +
                    " AND " + DataBase.ID_EST_MEDICO_REC + " LIKE ? ";


        String idMedicoString   = Long.toString(idMedico);
        String idPacienteString   = Long.toString(idPaciente);
        String[] argumentos  = {idPacienteString,idMedicoString,};

        return this.getRecomendacao(query, argumentos);

    }


    public  Recomendacao getRecomendacaoMedico(long idMedico){
        String query = " SELECT * FROM " + DataBase.TABELA_RECOMENDACAO +
                " WHERE " + DataBase.ID_EST_MEDICO_REC + " LIKE ? ";

        String idMedicoString   = Long.toString(idMedico);
        String[] argumentos  = {idMedicoString};

        return this.getRecomendacao(query, argumentos);

    }

    public ArrayList<Recomendacao> getRecomendacaoByMedico(long idMedico){
        liteDatabase = dataBaseHelper.getReadableDatabase();
        ArrayList<Recomendacao> listaRecomendacao = new ArrayList<>();

        String query = " SELECT * FROM " + DataBase.TABELA_RECOMENDACAO +
                " WHERE " + DataBase.ID_EST_MEDICO_REC + " LIKE ? ";

        String idMedicoString   = Long.toString(idMedico);
        String[] argumentos  = {idMedicoString};

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        String colunaId   = DataBase.ID_RECOMENDACAO;
        int indexColunaId = cursor.getColumnIndex(colunaId);

        Recomendacao recomendacao;

        while (cursor.moveToNext()){
            long idRecomendacao  = cursor.getInt(indexColunaId);
            recomendacao = getRecomendacao(idRecomendacao);
            listaRecomendacao.add(recomendacao);
        }
        cursor.close();

        return listaRecomendacao;

    }


}

