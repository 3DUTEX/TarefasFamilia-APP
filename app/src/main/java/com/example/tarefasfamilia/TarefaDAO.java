package com.example.tarefasfamilia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TarefaDAO extends SQLiteOpenHelper {

    private final String TABELA = "TBL_TAREFAS";
    public TarefaDAO(@Nullable Context context) {
        super(context, "DB_TarefasFamilia", null, 1);
    }

    //CRIANDO TABELA DO BD
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String comando = "CREATE TABLE " + TABELA + "(" +
                "ID INTEGER PRIMARY KEY," +
                "DESCRICAO VARCHAR(100)," +
                "RESPONSAVEL VARCHAR(30)," +
                "STATUS BIT)";

        sqLiteDatabase.execSQL(comando);
    }

    //MÉTODO DE INSERIR
    public long inserir(TarefaModel tarefa){
        //associa coluna da tabela com os valores do Dto
        ContentValues values = new ContentValues();
        values.put("DESCRICAO", tarefa.getDescricao());
        values.put("RESPONSAVEL", tarefa.getResponsavel());
        values.put("STATUS", tarefa.isStatus());

        //retorna o número de linhas afetadas (long)
        long nLinhas = getWritableDatabase().insert(TABELA, null, values);

        return nLinhas;
    };

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
