package com.biblioteca.service;

import com.biblioteca.dao.EmprestimoDAO;
import com.biblioteca.model.Emprestimo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmprestimoService {
    private EmprestimoDAO emprestimoDAO;

    public EmprestimoService(Connection connection) {
        this.emprestimoDAO = new EmprestimoDAO(connection);
    }

    public void cadastrarEmprestimo(Emprestimo emprestimo) throws SQLException {
        emprestimoDAO.adicionarEmprestimo(emprestimo);
    }

    public Emprestimo consultarEmprestimo(int id) throws SQLException {
        return emprestimoDAO.consultarEmprestimo(id);

    }

    public void atualizarEmprestimo(Emprestimo emprestimo) throws SQLException {
        emprestimoDAO.atualizarEmprestimo(emprestimo);
    }

    public void excluirEmprestimo(int id) throws SQLException {
        emprestimoDAO.removerEmprestimo(id);
    }
    public List<Emprestimo> consultarTodosEmprestimos() throws SQLException {
        return emprestimoDAO.buscarTodosEmprestimos();
    }
}
