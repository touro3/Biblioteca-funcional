package com.biblioteca;

import com.biblioteca.model.Usuario;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Autor;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Reserva;
import com.biblioteca.service.AuthenticationService;
import com.biblioteca.service.AutorService;
import com.biblioteca.service.LivroService;
import com.biblioteca.service.EmprestimoService;
import com.biblioteca.service.UsuarioService;
import com.biblioteca.service.ReservaService;
import com.biblioteca.dao.AutorDAO;
import com.biblioteca.dao.LivroDAO;
import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.dao.ReservaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import com.biblioteca.ui.UserInputHandler;
public class Main {
    private static Connection connection;
    private static AuthenticationService authService;
    private static LivroService livroService;
    private static AutorService autorService;
    private static UsuarioService usuarioService;
    private static UserInputHandler userInputHandler;
    private static EmprestimoService emprestimoService;
    private static ReservaService reservaService;

    public static void main(String[] args) {
        AutorDAO autorDao = new AutorDAO(connection);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        LivroDAO livroDao = new LivroDAO(connection);
        LivroService livroService = new LivroService(connection);
        AuthenticationService authService = new AuthenticationService(connection);
        UsuarioService usuarioService = new UsuarioService(usuarioDAO);
        reservaService = new ReservaService(new ReservaDAO(connection));

        Usuario usuario = null;

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/database.properties"));
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
            authService = new AuthenticationService(connection);
            emprestimoService = new EmprestimoService(connection);
            livroService = new LivroService(connection);
            autorService = new AutorService(connection);
            System.out.println("Conexão estabelecida com sucesso.");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Login");
                System.out.println("2. Autentique seu usuário");
                System.out.println("3. Sair");
                System.out.println("4. Entre como Usuário não autenticado");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                switch (opcao) {
                    case 1:
                        System.out.print("Nickname: ");
                        String nickname = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        usuario = authService.authenticate(nickname, senha);

                        if (usuario != null) {
                            System.out.println("Usuário autenticado com sucesso.");
                            redirecionarParaMenu(usuario,scanner);
                        } else {
                            System.out.println("Falha na autenticação. Tente novamente.");
                        }
                        break;
                    case 2:
                        userInputHandler = new UserInputHandler(scanner);
                        int matricula = userInputHandler.getMatriculaFromUser();
                        scanner.nextLine();
                        nickname = userInputHandler.getNicknameFromUser();
                        senha = userInputHandler.getSenhaFromUser();
                        boolean success = authService.registerUser(matricula, nickname, senha);
                        if (success) {
                            System.out.println("Usuário cadastrado com sucesso.");
                        } else {
                            System.out.println("Falha ao cadastrar usuário.");
                        }
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        return;
                    case 4:
                        userInputHandler = new UserInputHandler(scanner);
                        mostrarMenuUsuarioNaoAutenticado(scanner,userInputHandler, livroService, autorService);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível ler o arquivo de propriedades.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao conectar-se ao banco de dados.");
            e.printStackTrace();
        }

    }
    private static void redirecionarParaMenu(Usuario usuario, Scanner scanner) throws SQLException {
        int tipoUsuario = usuario.getTipoUsuario();

        switch (tipoUsuario) {
            case 1:
                mostrarMenuAdministrador(scanner, livroService, userInputHandler, usuarioService, emprestimoService, reservaService);
                break;
            case 2:
                mostrarMenuBibliotecario(scanner, emprestimoService,reservaService);
                break;
            case 3:
                mostrarMenuUsuarioAutenticado(autorService,livroService,emprestimoService,reservaService,scanner);
                break;
            case 4:
                mostrarMenuUsuarioNaoAutenticado(scanner,userInputHandler, livroService, autorService);
                break;
            default:
                System.out.println("Tipo de usuário desconhecido.");
                break;
        }
    }
    private static void mostrarMenuAdministrador(Scanner scanner, LivroService livroService, UserInputHandler userInputHandler, UsuarioService usuarioService, EmprestimoService emprestimoService, ReservaService reservaService) throws SQLException {
        int escolha = 0;
        userInputHandler = new UserInputHandler(scanner);
        livroService = new LivroService(connection);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioService = new UsuarioService(usuarioDAO);
        emprestimoService = new EmprestimoService(connection);

        do{
            System.out.println("\nMenu Administrador:");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Atualizar livro");
            System.out.println("3. Remover livro");
            System.out.println("4. Cadastrar autor");
            System.out.println("5. Atualizar autor");
            System.out.println("6. Remover autor");
            System.out.println("7. Cadastrar usuário");
            System.out.println("8. Atualizar usuário");
            System.out.println("9. Remover usuário");
            System.out.println("10. Cadastrar empréstimo");
            System.out.println("11. Atualizar empréstimo");
            System.out.println("12. Remover empréstimo");
            System.out.println("13. Cadastrar reserva");
            System.out.println("14. Atualizar reserva");
            System.out.println("15. Remover reserva");
            System.out.println("16. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    Livro newLivro = userInputHandler.getLivroDetailsFromUser();
                    livroService.cadastrarLivro(newLivro);
                    System.out.println("Livro cadastrado com sucesso.");
                    break;
                case 2:
                    Livro livroToUpdate = userInputHandler.getLivroDetailsFromUser();
                    livroService.atualizarLivro(livroToUpdate);
                    System.out.println("Livro atualizado com sucesso.");
                    break;
                case 3:
                    int isbnToRemove = userInputHandler.getISBNFromUser();
                    livroService.excluirLivro(isbnToRemove);
                    System.out.println("Livro removido com sucesso.");
                    break;
                case 4:
                    Autor newAutor = userInputHandler.getAutorDetailsFromUser();
                    autorService.adicionarAutor(newAutor);
                    System.out.println("Autor cadastrado com sucesso.");
                    break;
                case 5:
                    Autor autorToUpdate = userInputHandler.getAutorDetailsFromUser();
                    autorService.atualizarAutor(autorToUpdate);
                    System.out.println("Autor atualizado com sucesso.");
                    break;
                case 6:
                    String cpfToRemove = userInputHandler.getCpfFromUser();
                    autorService.removerAutor(cpfToRemove);
                    System.out.println("Autor removido com sucesso.");
                    break;
                case 7:
                    Usuario novoUsuario = userInputHandler.getUsuarioDetailsFromUser();
                    usuarioService.cadastrarUsuario(novoUsuario);
                    System.out.println("Usuário cadastrado com sucesso.");
                    break;
                case 8:
                    Usuario usuarioToUpdate = userInputHandler.getUsuarioDetailsFromUser();
                    usuarioService.editarUsuario(usuarioToUpdate);
                    System.out.println("Usuário atualizado com sucesso.");
                    break;
                case 9:
                    int matriculaParaRemover = userInputHandler.getMatriculaFromUser();
                    usuarioService.excluirUsuario(matriculaParaRemover);
                    System.out.println("Usuário removido com sucesso.");
                    break;
                case 10:
                    Emprestimo novoEmprestimo = userInputHandler.getEmprestimoDetailsFromUser();
                    emprestimoService.cadastrarEmprestimo(novoEmprestimo);
                    System.out.println("Empréstimo cadastrado com sucesso.");
                    break;
                case 11:
                    Emprestimo emprestimoToUpdate = userInputHandler.getEmprestimoDetailsFromUser();
                    emprestimoService.atualizarEmprestimo(emprestimoToUpdate);
                    System.out.println("Empréstimo atualizado com sucesso.");
                    break;
                case 12:
                    int emprestimoToRemove = userInputHandler.getISBNFromUser();
                    emprestimoService.excluirEmprestimo(emprestimoToRemove);
                    System.out.println("Empréstimo removido com sucesso.");
                    break;
                case 13:
                    Reserva novaReserva = userInputHandler.getReservaDetailsFromUser();
                    reservaService.cadastrarReserva(novaReserva);
                    System.out.println("Reserva cadastrada com sucesso.");
                    break;
                case 14:
                    Reserva reservaToUpdate = userInputHandler.getReservaDetailsFromUser();
                    reservaService.editarReserva(reservaToUpdate);
                    System.out.println("Reserva atualizada com sucesso.");
                    break;
                case 15:
                    int reservaToRemove = userInputHandler.getISBNFromUser();
                    reservaService.excluirReserva(reservaToRemove);
                    System.out.println("Reserva removida com sucesso.");
                    break;
                case 16:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        while (escolha != 16);
    }

    private static void mostrarMenuBibliotecario(Scanner scanner,EmprestimoService emprestimoService, ReservaService reservaService) throws SQLException {
        int escolha = 0;
        userInputHandler = new UserInputHandler(scanner);
        livroService = new LivroService(connection);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioService = new UsuarioService(usuarioDAO);
        emprestimoService = new EmprestimoService(connection);
        do {
            System.out.println("\nMenu Bibliotecário:");
            System.out.println("1. Cadastrar empréstimo");
            System.out.println("2. Atualizar empréstimo");
            System.out.println("3. Remover empréstimo");
            System.out.println("4. Cadastrar reserva");
            System.out.println("5. Atualizar reserva");
            System.out.println("6. Remover reserva");
            System.out.println("7. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    Emprestimo novoEmprestimo = userInputHandler.getEmprestimoDetailsFromUser();
                    emprestimoService.cadastrarEmprestimo(novoEmprestimo);
                    System.out.println("Empréstimo cadastrado com sucesso.");
                    break;
                case 2:
                    Emprestimo emprestimoToUpdate = userInputHandler.getEmprestimoDetailsFromUser();
                    emprestimoService.atualizarEmprestimo(emprestimoToUpdate);
                    System.out.println("Empréstimo atualizado com sucesso.");
                    break;
                case 3:
                    int emprestimoToRemove = userInputHandler.getISBNFromUser();
                    emprestimoService.excluirEmprestimo(emprestimoToRemove);
                    System.out.println("Empréstimo removido com sucesso.");
                    break;
                case 4:
                    Reserva novaReserva = userInputHandler.getReservaDetailsFromUser();
                    reservaService.cadastrarReserva(novaReserva);
                    System.out.println("Reserva cadastrada com sucesso.");
                    break;
                case 5:
                    Reserva reservaToUpdate = userInputHandler.getReservaDetailsFromUser();
                    reservaService.editarReserva(reservaToUpdate);
                    System.out.println("Reserva atualizada com sucesso.");
                    break;
                case 6:
                    int reservaToRemove = userInputHandler.getISBNFromUser();
                    reservaService.excluirReserva(reservaToRemove);
                    System.out.println("Reserva removida com sucesso.");
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolha != 7);

    }

    private static void mostrarMenuUsuarioAutenticado(AutorService autorService, LivroService livroService, EmprestimoService emprestimoService, ReservaService reservaService, Scanner scanner) throws SQLException {
        int escolha;
        scanner = new Scanner(System.in);
        userInputHandler = new UserInputHandler(scanner);
        livroService = new LivroService(connection);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioService = new UsuarioService(usuarioDAO);
        emprestimoService = new EmprestimoService(connection);

        do {
            System.out.println("\nMenu Usuário Autenticado:");
            System.out.println("1. Consultar livros");
            System.out.println("2. Consultar autores");
            System.out.println("3. Consultar empréstimos");
            System.out.println("4. Consultar reservas");
            System.out.println("5. Realizar reserva");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    List<Livro> livros = livroService.consultarTodosLivros();
                    livros.forEach(System.out::println);
                    break;
                case 2:
                    List<Autor> autores = autorService.consultarTodosAutores();
                    autores.forEach(System.out::println);
                    break;
                case 3:
                    List<Emprestimo> emprestimos = emprestimoService.consultarTodosEmprestimos();
                    emprestimos.forEach(System.out::println);
                    break;
                case 4:
                    List<Reserva> reservas = reservaService.consultarTodasReservas();
                    reservas.forEach(System.out::println);
                    break;
                case 5:
                    Reserva reserva = userInputHandler.getReservaDetailsFromUser();
                    reservaService.cadastrarReserva(reserva);
                    System.out.println("Reserva realizada com sucesso.");
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolha != 6);
    }

    private static void mostrarMenuUsuarioNaoAutenticado(Scanner scanner,UserInputHandler userInputHandler, LivroService livroService, AutorService autorService) throws SQLException {
        int escolha = 0;
        userInputHandler = new UserInputHandler(scanner);
        livroService = new LivroService(connection);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioService = new UsuarioService(usuarioDAO);
        emprestimoService = new EmprestimoService(connection);
        do {
            System.out.println("\nMenu Usuário Não Autenticado:");
            System.out.println("1. Consultar livros");
            System.out.println("2. Consultar autores");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    List<Livro> livros = livroService.consultarTodosLivros();
                    livros.forEach(System.out::println);
                    break;
                case 2:
                    List<Autor> autores = autorService.consultarTodosAutores();
                    autores.forEach(System.out::println);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolha != 3);
    }
}