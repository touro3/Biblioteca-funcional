package com.biblioteca.ui;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Reserva;
import com.biblioteca.model.Usuario;
import com.biblioteca.model.Autor;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.sql.Date;
public class UserInputHandler {
    private Scanner scanner;
        public UserInputHandler(Scanner scanner) {
            this.scanner = scanner;
        }
        public Livro getLivroDetailsFromUser() {
            System.out.println("Informe os dados do livro para atualizar:");
            System.out.print("ISBN: ");
            int isbn = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline
            System.out.print("Editora: ");
            String editora = scanner.nextLine();
            System.out.print("Categoria: ");
            String categoria = scanner.nextLine();

            return new Livro(isbn, titulo, ano, editora, categoria);
        }
    public Autor getAutorDetailsFromUser() {
        System.out.println("Informe os dados do autor:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        int cpf = scanner.nextInt();
        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();

        return new Autor(cpf, nome, nacionalidade);
    }
    public Emprestimo getEmprestimoDetailsFromUser()
    {
        System.out.println("Informe os dados do empréstimo:");

        return null;
    }

    public String getCpfFromUser() {
        System.out.print("Informe o CPF do autor a ser removido: ");
        return scanner.nextLine();
    }

    public Usuario getUsuarioDetailsFromUser() {
        System.out.println("Informe os dados do usuário para cadastro:");
        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo (Ex: Administrador, Bibliotecário...): ");
        String tipo = scanner.nextLine();
        System.out.print("Data de contratação (yyyy-mm-dd): ");
        String inputDataContratacao = scanner.nextLine();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dataContratacao = null;
        try {
            java.util.Date parsed = format.parse(inputDataContratacao);
            dataContratacao = new Date(parsed.getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception as you see fit
        }

        // Supondo que você tenha um construtor na classe Usuario que aceita esses parâmetros
        return new Usuario(matricula, nome, tipo, dataContratacao);
    }
    }

