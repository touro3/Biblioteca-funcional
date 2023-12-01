package com.biblioteca.model;

public class Autor {
    private int cpf;
    private String nome;
    private String nacionalidade;

    public Autor(int cpf, String nome, String nacionalidade) {
        this.cpf = cpf;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    // Getters and Setters
    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
