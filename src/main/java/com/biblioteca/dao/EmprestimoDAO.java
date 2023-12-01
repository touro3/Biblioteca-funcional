package com.biblioteca.dao;

import com.biblioteca.model.Emprestimo;

import java.sql.*;

public class EmprestimoDAO {
    private Connection connection;

    public EmprestimoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimo (DataEmprestimo, DataDevolucao, ISBN, MatriculaUsuario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            statement.setDate(2, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            statement.setInt(3, emprestimo.getIsbn());
            statement.setInt(4, emprestimo.getMatriculaUsuario());
            statement.executeUpdate();

            // Handle the generated keys (if needed)
        }
    }

    public Emprestimo consultarEmprestimo(int id) throws SQLException {
        String sql = "SELECT * FROM emprestimo WHERE IdEmprestimo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Emprestimo(
                            resultSet.getInt("IdEmprestimo"),
                            resultSet.getDate("DataEmprestimo"),
                            resultSet.getDate("DataDevolucao"),
                            resultSet.getInt("ISBN"),
                            resultSet.getInt("MatriculaUsuario")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimo SET DataEmprestimo = ?, DataDevolucao = ?, ISBN = ?, MatriculaUsuario = ? WHERE IdEmprestimo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            statement.setDate(2, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            statement.setInt(3, emprestimo.getIsbn());
            statement.setInt(4, emprestimo.getMatriculaUsuario());
            statement.setInt(5, emprestimo.getIdEmprestimo());
            statement.executeUpdate();
        }
    }

    public void removerEmprestimo(int id) throws SQLException {
        String sql = "DELETE FROM emprestimo WHERE IdEmprestimo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
