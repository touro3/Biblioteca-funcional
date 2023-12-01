package com.biblioteca;

import com.biblioteca.model.Usuario;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Autor;
import com.biblioteca.service.AuthenticationService;
import com.biblioteca.service.AutorService;
import com.biblioteca.service.LivroService;
import com.biblioteca.service.UsuarioService;
import com.biblioteca.dao.AutorDAO;
import com.biblioteca.dao.LivroDAO;
import com.biblioteca.dao.UsuarioDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import com.biblioteca.ui.UserInputHandler;
import org.jetbrains.annotations.NotNull;


public class Main {
    private static Connection connection;
    private static AuthenticationService authService;
    private static LivroService livroService;
    private static AutorService autorService;

    private static UsuarioService usuarioService;
    private static UserInputHandler userInputHandler;


    public static void main(String[] args) {
        // Assuming 'connection' is your established database connection
        AutorDAO autorDao = new AutorDAO(connection);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        LivroDAO livroDao = new LivroDAO(connection);
        LivroService livroService = new LivroService(connection);
        AuthenticationService authService = new AuthenticationService(connection);
        UsuarioService usuarioService = new UsuarioService(usuarioDAO);     // Carregar as configurações de conexão
        // Assuming 'usuario' is an instance of the Usuario class
        String tipoUsuarioMatricula = usuario.getTipoUsuarioMatricula();

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/database.properties"));
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
            authService = new AuthenticationService(connection);
            livroService = new LivroService(connection);
            autorService = new AutorService(connection);
            System.out.println("Conexão estabelecida com sucesso.");
            Usuario usuario = null;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Login");
                System.out.println("2. Cadastrar novo usuário");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.print("Nickname: ");
                        String nickname = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        usuario = authService.authenticate(nickname, senha);
                        if (usuario != null) {
                            System.out.println("Usuário autenticado com sucesso.");
                            redirecionarParaMenu(tipoUsuario);
                        } else {
                            System.out.println("Falha na autenticação. Tente novamente.");
                        }
                        break;
                    case 2:
                        // Lógica para cadastrar um novo usuário
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        return;
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
        String tipoUsuario = usuario.getTipoUsuarioMatricula(); // Call the instance method on the 'usuario' object

        switch (tipoUsuario) {
            case "Administrador":
                mostrarMenuAdministrador(scanner, livroService, userInputHandler, usuarioService);
                break;
            case "Bibliotecario":
                mostrarMenuBibliotecario();
                break;
            case "UsuarioAutenticado":
                mostrarMenuUsuarioAutenticado();
                break;
            case "UsuarioNaoAutenticado":
                mostrarMenuUsuarioNaoAutenticado();
                break;
            default:
                System.out.println("Tipo de usuário desconhecido.");
                break;
        }
    }

    private static void mostrarMenuAdministrador(@NotNull Scanner scanner, LivroService livroService, UserInputHandler userInputHandler, UsuarioService usuarioService) throws SQLException {
        int escolha = 0;
        userInputHandler = new UserInputHandler(scanner);
        livroService = new LivroService(connection);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioService = new UsuarioService(usuarioDAO);
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
                    break;
                case 2:
                    Livro livroToUpdate = userInputHandler.getLivroDetailsFromUser();
                    livroService.atualizarLivro(livroToUpdate);
                    break;
                case 3:
                    System.out.print("Informe o ISBN do livro a ser removido: ");
                    int isbnToRemove = scanner.nextInt();
                    scanner.nextLine();
                    livroService.excluirLivro(isbnToRemove);
                    break;
                case 4:
                    Autor newAutor = userInputHandler.getAutorDetailsFromUser();
                    autorService.adicionarAutor(newAutor);
                    break;
                case 5:
                    Autor autorToUpdate = userInputHandler.getAutorDetailsFromUser();
                    autorService.atualizarAutor(autorToUpdate);
                    break;
                case 6:
                    String cpfToRemove = userInputHandler.getCpfFromUser();
                    autorService.removerAutor(cpfToRemove);
                    break;
                case 7:
                    Usuario novoUsuario = userInputHandler.getUsuarioDetailsFromUser();
                    usuarioService.cadastrarUsuario(novoUsuario);
                    break;
                case 8:
                    //atualizarUsuario(scanner);
                    break;
                case 9:
                    //removerUsuario(scanner);
                    break;
                case 10:
                    //cadastrarEmprestimo(scanner);
                    break;
                case 11:
                    //atualizarEmprestimo(scanner);
                    break;
                case 12:
                    //removerEmprestimo(scanner);
                    break;
                case 13:
                    //cadastrarReserva(scanner);
                    break;
                case 14:
                    //atualizarReserva(scanner);
                    break;
                case 15:
                    //removerReserva(scanner);
                    break;
                case 16:
                    break; // Apenas sai do loop
                default:
                    System.out.println("Opção inválida.");
            }

        }
    while (escolha != 16);
    }

    private static void mostrarMenuBibliotecario() {
        int escolha = 0;
        Scanner scanner = new Scanner(System.in); // Assuming scanner is defined somewhere in your class

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
                    //cadastrarEmprestimo(scanner);
                    break;
                case 2:
                    //atualizarEmprestimo(scanner);
                    break;
                case 3:
                    //removerEmprestimo(scanner);
                    break;
                case 4:
                    //cadastrarReserva(scanner);
                    break;
                case 5:
                    //atualizarReserva(scanner);
                    break;
                case 6:
                    //removerReserva(scanner);
                    break;
                case 7:
                    break; // Just exits the loop
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolha != 7);

    }

    private static void mostrarMenuUsuarioAutenticado() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\nMenu Usuário Autenticado:");
            System.out.println("1. Consultar livros");
            System.out.println("2. Consultar autores");
            System.out.println("3. Consultar empréstimos");
            System.out.println("4. Consultar reservas");
            System.out.println("5. Realizar reserva");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    //consultarLivros(scanner);
                    break;
                case 2:
                    //consultarAutores(scanner);
                    break;
                case 3:
                    //consultarEmprestimos(scanner);
                    break;
                case 4:
                    //consultarReservas(scanner);
                    break;
                case 5:
                    //realizarReserva(scanner);
                    break;
                case 6:
                    break; // Just exits the loop
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolha != 6);

    }

    private static void mostrarMenuUsuarioNaoAutenticado() {
        Scanner scanner = new Scanner(System.in);
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
                    //consultarLivrosNaoAutenticado(scanner);
                    break;
                case 2:
                    //consultarAutoresNaoAutenticado(scanner);
                    break;
                case 3:
                    break; // Just exits the loop
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolha != 3);

    }

}
