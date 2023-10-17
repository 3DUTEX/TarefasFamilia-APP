package com.example.tarefasfamilia;

import androidx.annotation.NonNull;

public class TarefaModel {
    private int id;
    private String descricao, responsavel;

    private int status;

    @NonNull
    @Override
    public String toString() {
        String estadoTarefa = "";
        if(this.status == 1){
            estadoTarefa = "Concluido";
        }else{
            estadoTarefa = "Pendente";
        }
        return this.descricao + " | " + estadoTarefa + " | " +this.responsavel;
    }


    public TarefaModel(String descricao, String responsavel, int status){
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.status = status;
    }

    public TarefaModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
