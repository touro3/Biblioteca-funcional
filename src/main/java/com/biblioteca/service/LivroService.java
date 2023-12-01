package com.biblioteca.service;

import com.biblioteca.dao.LivroDAO;
import com.biblioteca.model.Livro;
import java.sql.Connection;
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
        // Outras regras de negócio, se necessário
    }

    public void atualizarLivro(Livro livro)
    {
        livroDAO.atualizarLivro(livro);
    }

    public void excluirLivro(int isbn) {
        livroDAO.removerLivro(isbn);
        // Outras regras de negócio, se necessário
    }

    // Suppose you have a Livro object named livro



    // Outros métodos conforme necessário
}

// Serviços similares para Autor, Usuario, Emprestimo, Reserva...
