package com.biblioteca.service;

import com.biblioteca.dao.LivroDAO;
import com.biblioteca.model.Livro;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.biblioteca.model.Livro;
// Importações necessárias

public class LivroService {
    private final LivroDAO livroDAO;

    public LivroService(Connection connection) {
        this.livroDAO = new LivroDAO(connection);
    }

    public void cadastrarLivro(Livro livro) {
        livroDAO.adicionarLivro(livro);
    }
    public void atualizarLivro(Livro livro)
    {
        livroDAO.atualizarLivro(livro);
    }

    public void excluirLivro(int isbn) {
        livroDAO.removerLivro(isbn);
    }

    public Livro consultarLivro(int isbn) throws SQLException, SQLException {
        return livroDAO.buscarLivroPorIsbn(isbn);
}
    public List<Livro> consultarTodosLivros() throws SQLException {
        return livroDAO.buscarTodosLivros();
    }
}

