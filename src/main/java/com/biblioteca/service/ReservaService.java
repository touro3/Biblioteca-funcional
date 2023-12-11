package com.biblioteca.service;

import com.biblioteca.dao.ReservaDAO;
import com.biblioteca.model.Reserva;
import java.sql.SQLException;
import java.util.List;

public class ReservaService {
    private ReservaDAO reservaDAO;

    public ReservaService(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
    }

    public void cadastrarReserva(Reserva reserva) throws SQLException {
        reservaDAO.adicionarReserva(reserva);
    }

    public Reserva consultarReserva(int idReserva) throws SQLException {
        return reservaDAO.buscarReserva(idReserva);
    }

    public void editarReserva(Reserva reserva) throws SQLException {
        reservaDAO.atualizarReserva(reserva);
    }

    public void excluirReserva(int idReserva) throws SQLException {
        reservaDAO.removerReserva(idReserva);
    }
    public List<Reserva> consultarTodasReservas() throws SQLException {
        return reservaDAO.buscarTodasReservas();
    }
}

