package com.biblioteca.dao;

import java.sql.Connection;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Autor;
import com.biblioteca.service.AutorService;
import com.biblioteca.dao.AutorDAO;
import com.biblioteca.service.LivroService;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public Livro buscarLivroPorIsbn(int isbn) throws SQLException {
        String sql = "SELECT * FROM livro WHERE isbn = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Livro(
                        resultSet.getInt("isbn"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("ano"),
                        resultSet.getString("editora"),
                        resultSet.getString("categoria")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding book", e);
        }
        return null;
    }

    public List<Livro> buscarTodosLivros() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Livro livro = new Livro(
                        resultSet.getInt("isbn"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("ano"),
                        resultSet.getString("editora"),
                        resultSet.getString("categoria")
                );
                livros.add(livro);
            }
        }
        return livros;
    }
    }

