package com.maishealth.maishealth.usuario.persistencia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Medicamento;

import java.util.ArrayList;

public class MedicamentoDAO {
    private SQLiteDatabase liteDatabase;
    private final DataBase dataBaseHelper;

    public MedicamentoDAO(Context context){dataBaseHelper = new DataBase(context);}

    public void inserirMedicamtento(Medicamento medicamento){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_MEDICAMENTO;

        String colunaNome = DataBase.NOME_MEDICAMENTO;
        String nome = medicamento.getNomeMedicamento();
        values.put(colunaNome, nome);

        String colunaFornecedor = DataBase.FORNECEDOR;
        String fornecedor = medicamento.getFornecedor();
        values.put(colunaFornecedor, fornecedor);

        long id = liteDatabase.insert(tabela, null, values);
        liteDatabase.close();

    }

    public void atualizarMedicamento (Medicamento medicamento){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_MEDICAMENTO;

        String colunaNome = DataBase.NOME_MEDICAMENTO;
        String nome = medicamento.getNomeMedicamento();
        values.put(colunaNome, nome);

        String colunaFornecedor = DataBase.FORNECEDOR;
        String fornecedor = medicamento.getFornecedor();
        values.put(colunaFornecedor, fornecedor);

        String whereClause = DataBase.ID_MEDICAMENTO + " = ? ";
        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(medicamento.getId());

        long id = liteDatabase.update(tabela, values, whereClause, paramentros);

        liteDatabase.close();


    }

    private Medicamento criarMedicamento (Cursor cursor){

        String colunaIdMedicamento = DataBase.ID_MEDICAMENTO;
        int indexColunaIdMedicamento = cursor.getColumnIndex(colunaIdMedicamento);
        long idMedicamento = cursor.getLong(indexColunaIdMedicamento);

        String colunaNomeMedicamento = DataBase.NOME_MEDICAMENTO;
        int indexColunaNomeMedicamento = cursor.getColumnIndex(colunaNomeMedicamento);
        String nome = cursor.getString(indexColunaNomeMedicamento);

        String colunaFornecedor = DataBase.FORNECEDOR;
        int indexColunaFornecedor = cursor.getColumnIndex(colunaFornecedor);
        String fornecedor = cursor.getString(indexColunaFornecedor);

        Medicamento medicamento = new Medicamento();

        medicamento.setId(idMedicamento);
        medicamento.setNomeMedicamento(nome);
        medicamento.setFornecedor(fornecedor);

        return medicamento;

    }

    private Medicamento getMedicamento(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Medicamento medicamento = null;

        if(cursor.moveToNext()){
            medicamento = criarMedicamento(cursor);
        }

        cursor.close();
        liteDatabase.close();

        return medicamento;

    }

    public Medicamento getMedicamento (long idMedicamento){
        String query = " SELECT * FROM " + DataBase.TABELA_MEDICAMENTO +
                " WHERE " + DataBase.ID_MEDICAMENTO + " LIKE ? ";

        String idMedicamentoString = Long.toString(idMedicamento);

        String[] argumentos = {idMedicamentoString};

        return getMedicamento(query, argumentos);

    }
    public Medicamento getMedicamentoByName (String nome, String fornec){
        String query = " SELECT * FROM " + DataBase.TABELA_MEDICAMENTO +
                " WHERE " + DataBase.NOME_MEDICAMENTO + " LIKE ? "+
                " AND " + DataBase.FORNECEDOR + " LIKE ? ";


        String[] argumentos = {nome, fornec};

        return getMedicamento(query, argumentos);

    }

    public ArrayList<Medicamento> getMedicamentos (){
        liteDatabase = dataBaseHelper.getReadableDatabase();
        ArrayList<Medicamento> listaMedicamento = new ArrayList<>();

        String query = "SELECT * FROM " + DataBase.TABELA_MEDICAMENTO +
                " ORDER BY " + DataBase.NOME_MEDICAMENTO + " ASC ";

        Cursor cursor = liteDatabase.rawQuery(query, null);

        String idColunaMedicamento = DataBase.ID_MEDICAMENTO;
        int indexColunaIdMedicamento = cursor.getColumnIndex(idColunaMedicamento);

        Medicamento medicamento;

        while (cursor.moveToNext()){

            long idMedicamento = cursor.getLong(indexColunaIdMedicamento);
            medicamento = getMedicamento(idMedicamento);
            listaMedicamento.add(medicamento);

        }
        cursor.close();
        liteDatabase.close();

        return listaMedicamento;

    }

    public void deleteMedicamento(long idMedicamento){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        String idMedicamentoString   = Long.toString(idMedicamento);
        String table = DataBase.TABELA_MEDICAMENTO;
        String where = DataBase.ID_MEDICAMENTO + " = " + idMedicamentoString;

        liteDatabase.delete(table, where, null );
        liteDatabase.close();
    }



}
