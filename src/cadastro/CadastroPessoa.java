package cadastro;

import java.io.*; // gravar arquivo
import java.util.ArrayList;
import java.util.Scanner;

public class CadastroPessoa {

    static ArrayList<Pessoa> pessoas = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int op = -1;

        while (op != 0) {
            System.out.println("\n--- SISTEMA DE CADASTRO ---");
            System.out.println("1 - Novo Aluno");
            System.out.println("2 - Novo Professor");
            System.out.println("3 - Listar Cadastros");
            System.out.println("4 - Alterar Cadastro");
            System.out.println("5 - Excluir Cadastro");
            System.out.println("6 - Salvar em Arquivo");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            op = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (op) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    cadastrarProfessor();
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    alterar();
                    break;
                case 5:
                    excluir();
                    break;
                case 6:
                    salvarArquivo();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    static void cadastrarAluno() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        pessoas.add(new Aluno(nome, idade, matricula));
        System.out.println("Aluno cadastrado!");
    }

    static void cadastrarProfessor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Disciplina: ");
        String disciplina = scanner.nextLine();

        pessoas.add(new Professor(nome, idade, disciplina));
        System.out.println("Professor cadastrado!");
    }

    static void listar() {
        if (pessoas.isEmpty()) {
            System.out.println("Nenhum cadastro encontrado.");
            return;
        }

        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa p = pessoas.get(i);
            System.out.println(i + " - " + p.funcao()
                    + " | " + p.getNome()
                    + " | " + p.getIdade());
        }
    }

    static void alterar() {
        listar();
        System.out.print("Índice: ");
        int i = scanner.nextInt();
        scanner.nextLine();

        if (i >= 0 && i < pessoas.size()) {
            System.out.print("Novo nome: ");
            pessoas.get(i).setNome(scanner.nextLine());

            System.out.print("Nova idade: ");
            pessoas.get(i).setIdade(scanner.nextInt());
            scanner.nextLine();

            System.out.println("Cadastro alterado!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    static void excluir() {
        listar();
        System.out.print("Índice: ");
        int i = scanner.nextInt();
        scanner.nextLine();

        if (i >= 0 && i < pessoas.size()) {
            pessoas.remove(i);
            System.out.println("Cadastro excluído!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    static void salvarArquivo() {
        try {
            FileWriter fw = new FileWriter("pessoas.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (Pessoa p : pessoas) {
                pw.println(p.formatarParaArquivo());
            }

            pw.close();
            fw.close();
            System.out.println("Dados salvos em pessoas.txt!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar.");
        }
    }
}



