package com.example.tarefasfamilia;

public class TarefaModel {
    private int id;
    private String descricao, responsavel;

    private boolean status;

    public TarefaModel(String descricao, String responsavel, Boolean status){
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
