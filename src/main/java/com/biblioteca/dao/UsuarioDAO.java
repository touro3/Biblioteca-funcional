package com.biblioteca.dao;

import com.biblioteca.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (matricula, nome, tipo, data_contratacao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getMatriculaUsuario());
            statement.setString(2, usuario.getNomeUsuario());
            statement.setString(3, usuario.getTipoUsuarioMatricula());
            statement.setDate(4, new java.sql.Date(usuario.getDataContratacao().getTime()));
            statement.executeUpdate();
        }
    }

    public Usuario buscarUsuario(int matricula) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE matricula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, matricula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Usuario(
                            resultSet.getInt("matricula"),
                            resultSet.getString("nome"),
                            resultSet.getString("tipo"),
                            resultSet.getDate("data_contratacao")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, tipo = ?, data_contratacao = ? WHERE matricula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNomeUsuario());
            statement.setString(2, usuario.getTipoUsuarioMatricula());
            statement.setDate(3, new java.sql.Date(usuario.getDataContratacao().getTime()));
            statement.setInt(4, usuario.getMatriculaUsuario());
            statement.executeUpdate();
        }
    }

    public void removerUsuario(int matricula) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE matricula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, matricula);
            statement.executeUpdate();
        }
    }
}
