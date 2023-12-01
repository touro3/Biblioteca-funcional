package com.biblioteca.dao;

import com.biblioteca.model.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Connection connection;
    public AutorDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarAutor(Autor autor) throws SQLException {
        String sql = "INSERT INTO autor (cpf, nome, nacionalidade) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, autor.getCpf());
            statement.setString(2, autor.getNome());
            statement.setString(3, autor.getNacionalidade());
            statement.executeUpdate();
        }
    }

    public void atualizarAutor(Autor autor) throws SQLException {
        String sql = "UPDATE autor SET nome = ?, nacionalidade = ? WHERE cpf = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getNacionalidade());
            statement.setInt(3, autor.getCpf());
            statement.executeUpdate();
        }
    }

    public void removerAutor(String cpf) throws SQLException {
        String sql = "DELETE FROM autor WHERE cpf = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            statement.executeUpdate();
        }
    }

    public List<Autor> buscarAutores() throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autor";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int cpf = resultSet.getInt("cpf");
                String nome = resultSet.getString("nome");
                String nacionalidade = resultSet.getString("nacionalidade");
                Autor autor = new Autor(cpf, nome, nacionalidade);
                autores.add(autor);
            }
        }
        return autores;
    }
}
