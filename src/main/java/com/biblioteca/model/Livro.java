package com.biblioteca.model;

public class Livro {
    private int isbn;
    private String titulo;
    private int ano;
    private String editora;
    private String categoria;
    private String autor;

    // Construtor com todos os campos
    public Livro(int isbn, String titulo, int ano, String editora, String categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
        this.editora = editora;
        this.categoria = categoria;
    }

    // Getter para ISBN
    public int getIsbn() {
        return isbn;
    }

    // Getter para título
    public String getTitulo() {
        return titulo;
    }

    // Getter para ano
    public int getAno() {
        return ano;
    }

    // Getter para editora
    public String getEditora() {
        return editora;
    }

    // Getter para categoria
    public String getCategoria() {
        return categoria;
    }

    // Setters caso você também precise modificar os valores dos campos
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getAutor() {
        return this.autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "isbn=" + isbn +
                ", título='" + titulo + '\'' +
                ", ano=" + ano +
                ", editora='" + editora + '\'' +
                ", categoria='" + categoria + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
