package com.example.tarefasfamilia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerResponsavel;
    RadioButton radioPendente, radioRealizados, radioTodos;

    Button buttonNovaTarefa;

    ListView listViewTarefas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        associa();

        
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