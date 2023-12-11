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
        String sql = "INSERT INTO usuario (MatriculaUsuario, NomeUsuario, TipoUsuarioMatricula, TipoUsuario, DataContratacao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getMatriculaUsuario());
            statement.setString(2, usuario.getNomeUsuario());
            statement.setString(3, usuario.getTipoUsuarioMatricula());
            statement.setInt(4, usuario.getTipoUsuario());
            statement.setDate(5, new java.sql.Date(usuario.getDataContratacao().getTime()));
            statement.executeUpdate();
        }
    }

    public Usuario buscarUsuario(int matricula) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE MatriculaUsuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, matricula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Usuario(
                            resultSet.getInt("MatriculaUsuario"),
                            resultSet.getString("NomeUsuario"),
                            resultSet.getString("TipoUsuarioMatricula"),
                            resultSet.getDate("DataContratacao"),
                            resultSet.getInt("TipoUsuario")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET NomeUsuario = ?, TipoUsuarioMatricula = ?, TipoUsuario = ?, DataContratacao = ? WHERE MatriculaUsuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNomeUsuario());
            statement.setString(2, usuario.getTipoUsuarioMatricula());
            statement.setInt(3, usuario.getTipoUsuario());
            statement.setDate(4, new java.sql.Date(usuario.getDataContratacao().getTime()));
            statement.setInt(5, usuario.getMatriculaUsuario());
            statement.executeUpdate();
        }
    }

    public void removerUsuario(int matricula) throws SQLException {
        String sql = "DELETE FROM usuario WHERE MatriculaUsuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, matricula);
            statement.executeUpdate();
        }
    }
}
