package com.biblioteca.dao;

import com.biblioteca.model.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarReserva(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reserva (DataReserva, ISBN, MatriculaUsuario) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(reserva.getDataReserva().getTime()));
            statement.setInt(2, reserva.getIsbn());
            statement.setInt(3, reserva.getMatriculaUsuario());
            statement.executeUpdate();
        }
    }

    public Reserva buscarReserva(int idReserva) throws SQLException {
        String sql = "SELECT * FROM reserva WHERE IdReserva = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idReserva);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Reserva(
                            resultSet.getInt("IdReserva"),
                            resultSet.getDate("DataReserva"),
                            resultSet.getInt("ISBN"),
                            resultSet.getInt("MatriculaUsuario")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarReserva(Reserva reserva) throws SQLException {
        String sql = "UPDATE reserva SET DataReserva = ?, ISBN = ?, MatriculaUsuario = ? WHERE IdReserva = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(reserva.getDataReserva().getTime()));
            statement.setInt(2, reserva.getIsbn());
            statement.setInt(3, reserva.getMatriculaUsuario());
            statement.setInt(4, reserva.getIdReserva());
            statement.executeUpdate();
        }
    }

    public void removerReserva(int idReserva) throws SQLException {
        String sql = "DELETE FROM reserva WHERE IdReserva = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idReserva);
            statement.executeUpdate();
        }
    }
    public List<Reserva> buscarTodasReservas() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                reservas.add(new Reserva(
                        resultSet.getInt("IdReserva"),
                        resultSet.getDate("DataReserva"),
                        resultSet.getInt("ISBN"),
                        resultSet.getInt("MatriculaUsuario")
                ));
            }
        }
        return reservas;
    }

}
