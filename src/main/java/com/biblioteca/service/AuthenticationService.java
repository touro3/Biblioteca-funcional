package com.biblioteca.service;

import com.biblioteca.model.Usuario;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {
    private final Connection connection;

    public AuthenticationService(Connection connection) {
        this.connection = connection;
    }
    public void cadastrarAutenticacao(int matriculaUsuario, String nickname, String senha) {
        String sql = "INSERT INTO autenticacao (UsuarioID, Nickname, Senha) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, matriculaUsuario);
            statement.setString(2, nickname);
            statement.setString(3, senha);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário cadastrado na autenticação com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario authenticate(String nickname, String senha) {
        String sql = "SELECT u.MatriculaUsuario, u.NomeUsuario, u.TipoUsuarioMatricula, u.TipoUsuario, u.DataContratacao "
                + "FROM autenticacao a "
                + "INNER JOIN usuario u ON a.UsuarioID = u.MatriculaUsuario "
                + "WHERE a.Nickname = ? AND a.Senha = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nickname);
            statement.setString(2, senha);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int usuarioId = resultSet.getInt("MatriculaUsuario");
                    String nomeUsuario = resultSet.getString("NomeUsuario");
                    String tipoUsuarioMatricula = resultSet.getString("TipoUsuarioMatricula");
                    int tipoUsuario = resultSet.getInt("TipoUsuario");
                    java.sql.Date dataContratacaoSql = resultSet.getDate("DataContratacao");

                    java.util.Date dataContratacao = null;
                    if (dataContratacaoSql != null) {
                        dataContratacao = new java.util.Date(dataContratacaoSql.getTime());
                    }
                    return new Usuario(usuarioId, nomeUsuario, tipoUsuarioMatricula, dataContratacao, tipoUsuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerUser(int matriculaUsuario, String nickname, String senha) {
        String sql = "INSERT INTO autenticacao (UsuarioID, Nickname, Senha) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, matriculaUsuario);
            statement.setString(2, nickname);
            statement.setString(3, senha);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}