package com.biblioteca.model;

import java.util.Date;

import java.util.Date;

public class Usuario {
    private int matriculaUsuario;
    private String nomeUsuario;
    private String tipoUsuarioMatricula; // Professor, aluno ou funcionário
    private int tipoUsuario; // Chave estrangeira para a tabela tipoUsuario
    private Date dataContratacao;

    // Construtor atualizado
    public Usuario(int matriculaUsuario, String nomeUsuario, String tipoUsuarioMatricula, Date dataContratacao, int tipoUsuario) {
        this.matriculaUsuario = matriculaUsuario;
        this.nomeUsuario = nomeUsuario;
        this.tipoUsuarioMatricula = tipoUsuarioMatricula;
        this.dataContratacao = dataContratacao;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters
    public int getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(int matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTipoUsuarioMatricula() {
        return tipoUsuarioMatricula;
    }

    public void setTipoUsuarioMatricula(String tipoUsuarioMatricula) {
        this.tipoUsuarioMatricula = tipoUsuarioMatricula;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

}