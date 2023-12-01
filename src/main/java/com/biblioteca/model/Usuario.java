package com.biblioteca.model;

import java.util.Date;

public class Usuario {
    private int matriculaUsuario;
    private String nomeUsuario;
    private String tipoUsuarioMatricula; // Isto pode ser um ID ou uma String do tipo de usuário
    private Date dataContratacao;

    // Construtor
    public Usuario(int matriculaUsuario, String nomeUsuario, String tipoUsuarioMatricula, Date dataContratacao) {
        this.matriculaUsuario = matriculaUsuario;
        this.nomeUsuario = nomeUsuario;
        this.tipoUsuarioMatricula = tipoUsuarioMatricula;
        this.dataContratacao = dataContratacao;
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

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    // Você pode adicionar mais métodos aqui conforme necessário
}
