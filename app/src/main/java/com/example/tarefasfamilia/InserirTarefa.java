package com.example.tarefasfamilia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InserirTarefa extends AppCompatActivity {

    EditText editTextDesc;
    Spinner spinnerResponsavel;
    Button buttonCadastrar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_tarefa);

        TarefaDAO tarefaDAO = new TarefaDAO(InserirTarefa.this);

        associa();



        ArrayList arrayListView = new ArrayList();
        arrayListView.add("Responsável");
        arrayListView.add("João");
        arrayListView.add("Lucas");
        arrayListView.add("Clara");
        arrayListView.add("Joana");

        ArrayAdapter adapter = new ArrayAdapter(InserirTarefa.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayListView);

        spinnerResponsavel.setAdapter(adapter);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc = editTextDesc.getText().toString();
                String responsavel = spinnerResponsavel.getSelectedItem().toString();

                if(desc.isEmpty()){
                    Toast.makeText(InserirTarefa.this, "O campo descrição não pode estar vazio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(responsavel == "Responsável"){
                    Toast.makeText(InserirTarefa.this, "O responsável não foi selecionado", Toast.LENGTH_SHORT).show();
                    return;
                }

                TarefaModel tarefa = new TarefaModel(desc, responsavel, 0);

                long linhasAfetadas = tarefaDAO.inserir(tarefa);

                if(linhasAfetadas > 0){
                    Toast.makeText(InserirTarefa.this, "Tarefa cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InserirTarefa.this, "Erro ao cadastrar tarefa, contate o administrador :(", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(InserirTarefa.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void associa() {
        editTextDesc= findViewById(R.id.editTextDesc);
        spinnerResponsavel = findViewById(R.id.spinnerResponsavel);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
    }
}