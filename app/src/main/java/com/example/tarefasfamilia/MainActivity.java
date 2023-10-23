package com.example.tarefasfamilia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerResponsavel;
    RadioButton radioPendente, radioRealizados, radioTodos;

    Button buttonNovaTarefa;

    ListView listViewTarefas;

    ArrayList<TarefaModel> arrayListTarefa = new ArrayList<>();


    TarefaModel tarefa;

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

        listViewTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int posicao, long id) {
                AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
                msg.setMessage("Deseja inverter o estado da tarefa?");
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tarefa = arrayListTarefa.get(posicao);
                        long nLinhasAfetadas = tarefaDAO.inverteEstado(tarefa);

                        if(nLinhasAfetadas > 0){
                            consulta(tarefaDAO);
                            Toast.makeText(MainActivity.this, "Tarefa alterada com sucesso!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Vixi, algo deu errado :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                msg.setNegativeButton("Não", null);
                msg.show();

                return false;
            }
        });

        spinnerResponsavel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                consulta(tarefaDAO);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

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

    private void consulta(TarefaDAO tarefaDAO) {
        String responsavel = spinnerResponsavel.getSelectedItem().toString();
        ;
        if(radioPendente.isChecked())
            arrayListTarefa = tarefaDAO.consultar("pendente", responsavel);
        else if(radioRealizados.isChecked())
            arrayListTarefa = tarefaDAO.consultar("concluido", responsavel);
        else{
            arrayListTarefa = tarefaDAO.consultar("todos", responsavel);
        }
        atualizaListView();
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