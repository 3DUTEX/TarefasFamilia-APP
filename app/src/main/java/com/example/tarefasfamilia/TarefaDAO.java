package com.example.tarefasfamilia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long inverteEstado(TarefaModel tarefa){
        ContentValues values = new ContentValues();
        values.put("DESCRICAO", tarefa.getDescricao());
        values.put("RESPONSAVEL", tarefa.getResponsavel());
        if(tarefa.getStatus() == 0)
            values.put("STATUS", 1);
        else
            values.put("STATUS", 0);

        String id = "id=?";
        String[] args = {tarefa.getId()+""};
        return getWritableDatabase().update(TABELA, values, id, args);
    };

    //MÉTODO DE INSERIR
    public long inserir(TarefaModel tarefa){
        //associa coluna da tabela com os valores do Dto
        ContentValues values = new ContentValues();
        values.put("DESCRICAO", tarefa.getDescricao());
        values.put("RESPONSAVEL", tarefa.getResponsavel());
        values.put("STATUS", tarefa.getStatus());

        //retorna o número de linhas afetadas (long)
        long nLinhas = getWritableDatabase().insert(TABELA, null, values);

        return nLinhas;
    };

    public ArrayList<TarefaModel> consultar(String tipo, String responsavel){
        String comando = "";

        switch (tipo){
            case "todos":
                if(responsavel == "Responsável")
                    comando = "SELECT * FROM " + TABELA;
                else
                    comando = "SELECT * FROM " + TABELA + " WHERE RESPONSAVEL = '" + responsavel + "'";
                break;

            case "pendente":
                if(responsavel == "Responsável")
                    comando = "SELECT * FROM " + TABELA + " WHERE STATUS = 0";
                else
                    comando = "SELECT * FROM " + TABELA + " WHERE STATUS = 0 AND RESPONSAVEL = '" + responsavel + "'";
                break;
            case "concluido":
                if(responsavel == "Responsável")
                    comando = "SELECT * FROM " + TABELA + " WHERE STATUS = 1";
                else
                    comando = "SELECT * FROM " + TABELA + " WHERE STATUS = 1 AND RESPONSAVEL = '" + responsavel + "'";
                break;
        }

        //procura no BD e retornar "Cursor"
        Cursor cursor = getReadableDatabase().rawQuery(comando, null);

        ArrayList<TarefaModel> arrayListTarefa = new ArrayList<>();

        //laço passando pelos registros, criando contanto e adicionando no vetor
        while(cursor.moveToNext()){
            TarefaModel tarefaModel = new TarefaModel();
            //valoriza dtoContato com a respectiva coluna do cursor - primeira coluna é Id então precisa ser "getInt"
            tarefaModel.setId(cursor.getInt(0));
            //segunda coluna é Nome então precisa ser "getString" - 2 parâmetro é o indíce da coluna
            tarefaModel.setDescricao(cursor.getString(1));
            tarefaModel.setResponsavel(cursor.getString(2));
            tarefaModel.setStatus(cursor.getInt(3));

            arrayListTarefa.add(tarefaModel);
        }

        return arrayListTarefa;
    };
}
