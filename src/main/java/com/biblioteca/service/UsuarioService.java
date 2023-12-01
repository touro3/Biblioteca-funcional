package com.biblioteca.service;

import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.model.Usuario;
import java.sql.SQLException;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.adicionarUsuario(usuario);
        // Additional business logic can be implemented here
    }

    public Usuario consultarUsuario(int matricula) throws SQLException {
        return usuarioDAO.buscarUsuario(matricula);
        // Additional business logic can be implemented here
    }

    public void editarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.atualizarUsuario(usuario);
        // Additional business logic can be implemented here
    }

    public void excluirUsuario(int matricula) throws SQLException {
        usuarioDAO.removerUsuario(matricula);
        // Additional business logic can be implemented here
    }

    // Additional methods as needed
}
