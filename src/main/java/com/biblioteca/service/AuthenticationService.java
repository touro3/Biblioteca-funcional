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

    public Usuario authenticate(String nickname, String senha) {
        String sql = "SELECT u.MatriculaUsuario, u.NomeUsuario, u.TipoUsuarioMatricula, u.DataContratacao FROM autenticacao a INNER JOIN usuario u ON a.UsuarioID = u.MatriculaUsuario WHERE a.Nickname = ? AND a.Senha = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nickname);
            statement.setString(2, senha);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int usuarioId = resultSet.getInt("MatriculaUsuario");
                    String nomeUsuario = resultSet.getString("NomeUsuario");
                    String tipoUsuarioMatricula = resultSet.getString("TipoUsuarioMatricula");
                    Date dataContratacao = resultSet.getDate("DataContratacao");
                    // Retorna um objeto usuário com as informações carregadas
                    return new Usuario(usuarioId, nomeUsuario, tipoUsuarioMatricula, dataContratacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}