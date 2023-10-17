package com.example.tarefasfamilia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerResponsavel;
    RadioButton radioPendente, radioRealizados, radioTodos;

    Button buttonNovaTarefa;

    ListView listViewTarefas;

    ArrayList<TarefaModel> arrayListTarefa = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        associa();

        TarefaDAO tarefaDAO = new TarefaDAO(MainActivity.this);

        //pegar as informação do BD
        arrayListTarefa = tarefaDAO.consultarTodos();

        //passa as informações pro adapter
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayListTarefa);

        //atualiza listView
        listViewTarefas.setAdapter(adapter);

        buttonNovaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InserirTarefa.class);
                startActivity(intent);
            }
        });
    }

    private void associa() {
        spinnerResponsavel = findViewById(R.id.spinnerResponsavel);
        radioPendente = findViewById(R.id.radioPendente);
        radioRealizados = findViewById(R.id.radioRealizados);
        radioTodos = findViewById(R.id.radioTodos);
        buttonNovaTarefa = findViewById(R.id.buttonNovaTarefa);
        listViewTarefas = findViewById(R.id.listViewTarefas);
    }
}