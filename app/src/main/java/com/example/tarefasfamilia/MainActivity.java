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

        ArrayList arrayListView = new ArrayList();
        arrayListView.add("Responsável");
        arrayListView.add("João");
        arrayListView.add("Lucas");
        arrayListView.add("Clara");
        arrayListView.add("Joana");

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayListView);

        spinnerResponsavel.setAdapter(adapter);

        TarefaDAO tarefaDAO = new TarefaDAO(MainActivity.this);

        radioTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String responsavel = spinnerResponsavel.getSelectedItem().toString();
                arrayListTarefa = tarefaDAO.consultar("todos", responsavel);
                atualizaListView();
            }
        });

        radioPendente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String responsavel = spinnerResponsavel.getSelectedItem().toString();
                arrayListTarefa = tarefaDAO.consultar("pendente", responsavel);
                atualizaListView();
            }
        });

        radioRealizados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String responsavel = spinnerResponsavel.getSelectedItem().toString();
                arrayListTarefa = tarefaDAO.consultar("concluido", responsavel);
                atualizaListView();
            }
        });

        //vai para tela de nova tarefa
        buttonNovaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InserirTarefa.class);
                startActivity(intent);
            }
        });
    }

    private void atualizaListView() {
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayListTarefa);
        //atualiza listView
        listViewTarefas.setAdapter(adapter);
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