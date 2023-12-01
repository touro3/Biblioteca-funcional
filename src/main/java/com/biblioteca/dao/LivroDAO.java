package com.biblioteca.dao;

import java.sql.Connection;
import com.biblioteca.model.Livro;
import com.biblioteca.service.LivroService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// Importações necessárias

public class LivroDAO {
    private Connection connection;

    public LivroDAO(Connection connection) {
        this.connection = connection;
    }


    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO livro (isbn, titulo, ano, editora, categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, livro.getIsbn());
            statement.setString(2, livro.getTitulo());
            statement.setInt(3, livro.getAno());
            statement.setString(4, livro.getEditora());
            statement.setString(5, livro.getCategoria());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding book", e);
        }
    }

    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, ano = ?, editora = ?, categoria = ? WHERE isbn = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livro.getTitulo());
            statement.setInt(2, livro.getAno());
            statement.setString(3, livro.getEditora());
            statement.setString(4, livro.getCategoria());
            statement.setInt(5, livro.getIsbn());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error refreshing book", e);
        }
    }

    public void removerLivro(int isbn) {
        String sql = "DELETE FROM livro WHERE isbn = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, isbn);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book", e);
        }
    }

    // Métodos para buscar livros, etc.
}

// DAOs similares para Autor, Usuario, Emprestimo, Reserva...
