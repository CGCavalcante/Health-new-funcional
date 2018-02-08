package com.maishealth.maishealth.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper; // Cria banco de dados

import static com.maishealth.maishealth.usuario.persistencia.ConstantePopularBanco.INSERIR_MEDICO;
import static com.maishealth.maishealth.usuario.persistencia.ConstantePopularBanco.INSERIR_MEDICO_POSTO;
import static com.maishealth.maishealth.usuario.persistencia.ConstantePopularBanco.INSERIR_PACIENTE;
import static com.maishealth.maishealth.usuario.persistencia.ConstantePopularBanco.INSERIR_PESSOA;
import static com.maishealth.maishealth.usuario.persistencia.ConstantePopularBanco.INSERIR_POSTO;
import static com.maishealth.maishealth.usuario.persistencia.ConstantePopularBanco.INSERIR_USUARIO;
import static com.maishealth.maishealth.usuario.persistencia.ConstantePopularBanco.INSERIR_HORARIO_MEDICO;

/**
 * Classe responsável por criar tabelas e o banco de dados
 */

public class DataBase extends SQLiteOpenHelper {
    //TABELA PESSOA
    public static final String TABELA_PESSOA = "pessoa";
    public static final String ID_PESSOA = "id_pessoa";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_SEXO = "sexo";
    public static final String PESSOA_DATANASC = "data_nasc";
    public static final String CPF = "cpf";
    public static final String ID_EST_USUARIO_PE = "id_est_usuario";
    // TABELA USUÁRIO
    public static final String TABELA_USUARIO = "usuario";
    public static final String ID_USUARIO = "id_usuario";
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_SENHA = "senha";
    //TABELA PACIENTE
    public static final String TABELA_PACIENTE = "paciente";
    public static final String ID_PACIENTE = "id_paciente";
    public static final String PACIENTE_SANGUE = "tipo_sangue";
    public static final String ID_EST_USUARIO_PA = "id_est_usuario";
    //TABELA MEDICO
    public static final String TABELA_MEDICO = "medico";
    public static final String ID_MEDICO = "id_medico";
    public static final String CRM = "crm";
    public static final String ESTADO = "estado";
    public static final String ESPECIALIDADE = "especialidade";
    public static final String ID_EST_USUARIO_ME = "id_est_usuario";
    //TABELA POSTO
    public static final String TABELA_POSTO = "posto";
    public static final String ID_POSTO = "id_posto";
    public static final String POSTO_NOME = "nome";
    public static final String POSTO_LOCAL = "local";
    public static final String ID_EST_USUARIO = "id_est_usuario";
    //TABELA MEDICO-POSTO
    public static final String TABELA_MEDICO_POSTO = "medico_posto";
    public static final String ID_EST_MEDICO_ME_POS = "id_medico";
    public static final String ID_EST_POSTO_ME_POS = "id_posto";
    //TABELA TURNO-MEDICO
    public static final String TABELA_HORARIO_MEDICO = "horario_medico";
    public static final String ID_HOR_MEDICO = "id_hor_medico";
    public static final String DIA_DA_SEMANA = "dia_da_semana";
    public static final String VAGAS = "vagas";
    public static final String TURNO = "turno";
    public static final String ID_EST_MEDICO = "id_est_medico";
    //TABELA CONSULTA
    public static final String TABELA_CONSULTA  = "consulta";
    public static final String ID_CONSULTA = "id_consulta";
    public static final String ID_EST_MEDICO_CON = "id_medico";
    public static final String ID_EST_PACIENTE_CON = "id_paciente";
    public static final String DATA = "data";
    public static final String EST_TURNO = "turno";
    public static final String STATUS_CONSULTA = "status_consulta";

   /* //TABELA DATA-TURNO
    public static final String TABELA_DATA_HORARIO = "data_horario";
    public static final String ID_DATA_HORARIO = "id_data_horario";
    public static final String ID_EST_HORARIO_MED = "id_horario_medico";
    public static final String DATA = "data";
    public static final String CONTADOR_REGRESIVO = "contador"; // Quantidade de Vagas
    //TABELA CONSULTA
    public static final String TABELA_CONSULTA = "consulta";
    public static final String ID_CONSULTA = "id_consulta";
    public static final String ID_EST_PACIENTE_CON = "id_paciente";
    public static final String ID_EST_DATA_HORARIO = "id_data_horario";
    public static final String STATUS_CONSULTA = "status_consulta";*/

    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "dbmaishealth";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE " + TABELA_DATA_HORARIO + " (" +
                ID_DATA_HORARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ID_EST_HORARIO_MED + " INTEGER, " +
                DATA + " TEXT NOT NULL, " +
                CONTADOR_REGRESIVO + " INTEGER); ");*/

        db.execSQL("CREATE TABLE " + TABELA_CONSULTA + "(" +
                ID_CONSULTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ID_EST_MEDICO_CON + " INTEGER, " +
                ID_EST_PACIENTE_CON + " INTEGER, " +
                DATA + " INTEGER, " +
                EST_TURNO + " TEXT NOT NULL ," +
                STATUS_CONSULTA + " TEXT); ");

        db.execSQL("CREATE TABLE " + TABELA_HORARIO_MEDICO + " (" +
                ID_HOR_MEDICO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DIA_DA_SEMANA + " TEXT NOT NULL, " +
                VAGAS + " INTEGER," +
                TURNO + " TEXT NOT NULL," +
                ID_EST_MEDICO + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABELA_USUARIO + " (" +
                ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USUARIO_EMAIL + " TEXT NOT NULL UNIQUE, " +
                USUARIO_SENHA + " TEXT NOT NULL);");
                
        db.execSQL("CREATE TABLE " + TABELA_PESSOA + " (" +
                ID_PESSOA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PESSOA_NOME + " TEXT NOT NULL, " +
                PESSOA_SEXO + " TEXT NOT NULL, " +
                PESSOA_DATANASC + " TEXT NOT NULL, " +
                CPF + " TEXT NOT NULL, " +
                ID_EST_USUARIO_PE + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABELA_PACIENTE + " (" +
                ID_PACIENTE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PACIENTE_SANGUE + " TEXT NOT NULL, " +
                ID_EST_USUARIO_PA + " INTEGER);");
    
        db.execSQL("CREATE TABLE " + TABELA_MEDICO + " (" +
                ID_MEDICO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CRM + " TEXT NOT NULL, " +
                ESTADO + " TEXT NOT NULL, " +
				ESPECIALIDADE + " TEXT NOT NULL, " +
                ID_EST_USUARIO_ME + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABELA_POSTO + " (" +
                ID_POSTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                POSTO_NOME + " TEXT NOT NULL, " +
                POSTO_LOCAL + " TEXT NOT NULL, " +
                ID_EST_USUARIO + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABELA_MEDICO_POSTO + " (" +
                ID_EST_MEDICO_ME_POS + " INTEGER, " +
                ID_EST_POSTO_ME_POS + " INTEGER);");

        db.execSQL(INSERIR_USUARIO);
        db.execSQL(INSERIR_PESSOA);
        db.execSQL(INSERIR_PACIENTE);
        db.execSQL(INSERIR_MEDICO);
        db.execSQL(INSERIR_POSTO);
        db.execSQL(INSERIR_MEDICO_POSTO);
        db.execSQL(INSERIR_HORARIO_MEDICO);
    }

    //Atualização da tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query1 = "DROP TABLE IF EXISTS " + TABELA_USUARIO;
        db.execSQL(query1);

        String query2 = "DROP TABLE IF EXISTS " + TABELA_PESSOA;
        db.execSQL(query2);

        String query3 = "DROP TABLE IF EXISTS " + TABELA_PACIENTE;
        db.execSQL(query3);

        String query4 = "DROP TABLE IF EXISTS " + TABELA_MEDICO;
        db.execSQL(query4);

        String query5 = "DROP TABLE IF EXISTS " + TABELA_CONSULTA;
        db.execSQL(query5);

        String query6 = "DROP TABLE IF EXISTS " + TABELA_POSTO;
        db.execSQL(query6);

        String query7 = "DROP TABLE IF EXISTS " + TABELA_MEDICO_POSTO;
        db.execSQL(query7);

        String query14 = "DROP TABLE IF EXISTS " + TABELA_HORARIO_MEDICO;
        db.execSQL(query14);

        /*String query15 = "DROP TABLE IF EXISTS " + TABELA_DATA_HORARIO;
        db.execSQL(query15);*/

        this.onCreate(db);
    }
}
