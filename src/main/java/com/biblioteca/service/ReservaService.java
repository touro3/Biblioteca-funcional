package com.biblioteca.service;

import com.biblioteca.dao.ReservaDAO;
import com.biblioteca.model.Reserva;
import java.sql.SQLException;

public class ReservaService {
    private ReservaDAO reservaDAO;

    public ReservaService(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
    }

    public void cadastrarReserva(Reserva reserva) throws SQLException {
        reservaDAO.adicionarReserva(reserva);
        // Additional business logic can be implemented here
    }

    public Reserva consultarReserva(int idReserva) throws SQLException {
        return reservaDAO.buscarReserva(idReserva);
        // Additional business logic can be implemented here
    }

    public void editarReserva(Reserva reserva) throws SQLException {
        reservaDAO.atualizarReserva(reserva);
        // Additional business logic can be implemented here
    }

    public void excluirReserva(int idReserva) throws SQLException {
        reservaDAO.removerReserva(idReserva);
        // Additional business logic can be implemented here
    }

    // Additional methods as needed
}

