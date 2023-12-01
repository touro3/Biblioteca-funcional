package com.biblioteca.model;

import java.sql.Date;

public class Emprestimo {
    private int idEmprestimo;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private int isbn;
    private int matriculaUsuario;

    // Construtor padrão
    public Emprestimo() {
    }

    // Construtor com todos os campos
    public Emprestimo(int idEmprestimo, Date dataEmprestimo, Date dataDevolucao, int isbn, int matriculaUsuario) {
        this.idEmprestimo = idEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.isbn = isbn;
        this.matriculaUsuario = matriculaUsuario;
    }

    // Getter para idEmprestimo
    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    // Setter para idEmprestimo
    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    // Getter para dataEmprestimo
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    // Setter para dataEmprestimo
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    // Getter para dataDevolucao
    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    // Setter para dataDevolucao
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    // Getter para isbn
    public int getIsbn() {
        return isbn;
    }

    // Setter para isbn
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    // Getter para matriculaUsuario
    public int getMatriculaUsuario() {
        return matriculaUsuario;
    }

    // Setter para matriculaUsuario
    public void setMatriculaUsuario(int matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }
}
