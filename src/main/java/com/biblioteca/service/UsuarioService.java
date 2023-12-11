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
    }

    public Usuario consultarUsuario(int matricula) throws SQLException {
        return usuarioDAO.buscarUsuario(matricula);
    }

    public void editarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.atualizarUsuario(usuario);
    }

    public void excluirUsuario(int matricula) throws SQLException {
        usuarioDAO.removerUsuario(matricula);
    }
}
