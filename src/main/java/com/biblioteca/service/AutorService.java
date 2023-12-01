package com.biblioteca.service;

import com.biblioteca.dao.AutorDAO;
import com.biblioteca.model.Autor;
import com.biblioteca.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AutorService {
    private AutorDAO autorDAO;

    public AutorService(Connection connection) {
        this.autorDAO = new AutorDAO(connection);
    }

    public void adicionarAutor(Autor autor) {
        try {
            autorDAO.adicionarAutor(autor);
        } catch (SQLException e) {
            // Trate a exceção adequadamente
            // Log.error("Erro ao adicionar autor: ", e);
            throw new RuntimeException("Erro ao adicionar autor", e);
        }
    }

    public void atualizarAutor(Autor autor) {
        try {
            autorDAO.atualizarAutor(autor);
        } catch (SQLException e) {
            // Trate a exceção adequadamente
            throw new RuntimeException("Erro ao atualizar autor", e);
        }
    }

    public void removerAutor(String cpf) {
        try {
            autorDAO.removerAutor(cpf);
        } catch (SQLException e) {
            // Trate a exceção adequadamente
            throw new RuntimeException("Erro ao remover autor", e);
        }
    }

    public List<Autor> buscarAutores() {
        try {
            return autorDAO.buscarAutores();
        } catch (SQLException e) {
            // Trate a exceção adequadamente
            throw new RuntimeException("Erro ao buscar autores", e);
        }
    }

}
