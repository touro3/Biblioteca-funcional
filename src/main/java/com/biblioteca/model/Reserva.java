package com.biblioteca.model;

import java.sql.Date;

public class Reserva {
    private int idReserva;
    private Date dataReserva;
    private int isbn;
    private int matriculaUsuario;

    // Construtor com todos os campos
    public Reserva(int idReserva, Date dataReserva, int isbn, int matriculaUsuario) {
        this.idReserva = idReserva;
        this.dataReserva = dataReserva;
        this.isbn = isbn;
        this.matriculaUsuario = matriculaUsuario;
    }

    // Getter para idReserva
    public int getIdReserva() {
        return idReserva;
    }

    // Setter para idReserva
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    // Getter para dataReserva
    public Date getDataReserva() {
        return dataReserva;
    }

    // Setter para dataReserva
    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
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

