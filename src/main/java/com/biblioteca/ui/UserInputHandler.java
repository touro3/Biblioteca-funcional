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
        System.out.println("Informe os dados do livro:");
        System.out.print("ISBN: ");
        int isbn = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
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
        scanner.nextLine();
        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();

        return new Autor(cpf, nome, nacionalidade);
    }

    public Emprestimo getEmprestimoDetailsFromUser() {
        System.out.println("Informe os detalhes do empréstimo:");
        System.out.print("Data do empréstimo (yyyy-mm-dd): ");
        Date dataEmprestimo = Date.valueOf(scanner.nextLine());
        System.out.print("Data da devolução (yyyy-mm-dd): ");
        Date dataDevolucao = Date.valueOf(scanner.nextLine());
        System.out.print("ISBN do livro: ");
        int isbn = scanner.nextInt();
        System.out.print("Matrícula do usuário: ");
        int matriculaUsuario = scanner.nextInt();
        scanner.nextLine();

        return new Emprestimo(0, dataEmprestimo, dataDevolucao, isbn, matriculaUsuario); // O idEmprestimo é definido como 0, pois é gerado pelo banco de dados
    }

    public Usuario getUsuarioDetailsFromUser() {
        System.out.println("Informe os dados do usuário para cadastro:");
        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo de Matrícula (Ex: (1)Aluno,(2)Professor,(3)Funcionário): ");
        String tipoMatricula = scanner.nextLine();
        System.out.print("ID do Tipo de Usuário: Administrador(1) Bibliotecario(2), Usuario Autenticado(3),Usuario Nao Autenticado(4)");
        int tipoUsuarioId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Data de contratação (yyyy-mm-dd): ");
        String inputDataContratacao = scanner.nextLine();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dataContratacao = null;
        try {
            java.util.Date parsed = format.parse(inputDataContratacao);
            dataContratacao = new Date(parsed.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Usuario(matricula, nome, tipoMatricula, dataContratacao, tipoUsuarioId);
    }

    public String getCpfFromUser() {
        System.out.print("Informe o CPF: ");
        return scanner.nextLine();
    }

    public int getMatriculaFromUser() {
        System.out.print("Informe a matrícula do usuário: ");
        return scanner.nextInt();
    }

    public int getISBNFromUser(){
            System.out.print("ISBN do livro: ");
            return scanner.nextInt();
    }

    public Reserva getReservaDetailsFromUser() {
        System.out.println("Informe os detalhes da reserva:");
        System.out.print("Data da reserva (yyyy-mm-dd): ");
        Date dataReserva = Date.valueOf(scanner.nextLine());
        System.out.print("ISBN do livro: ");
        int isbn = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Matrícula do usuário: ");
        int matriculaUsuario = scanner.nextInt();
        scanner.nextLine();

        return new Reserva(0, dataReserva, isbn, matriculaUsuario);
    }
    public String getNicknameFromUser() {
        System.out.print("Crie um nickname: ");
        return scanner.nextLine();
    }
    public String getSenhaFromUser() {
        System.out.print("Crie uma senha: ");
        return scanner.nextLine();
    }

}