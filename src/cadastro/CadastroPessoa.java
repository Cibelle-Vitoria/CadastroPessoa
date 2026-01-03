package cadastro;

import java.io.BufferedWriter; //biblioteca para salvar arquivo
import java.io.FileWriter; //biblioteca para salvar arquivo
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CadastroPessoa {

    static ArrayList<Pessoa> pessoas = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Cadastrar Professor");
            System.out.println("3 - Listar Pessoas");
            System.out.println("4 - Alterar Pessoa");
            System.out.println("5 - Excluir Pessoa");
            System.out.println("6 - Salvar em Arquivo");
            System.out.println("0 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> cadastrarProfessor();
                case 3 -> listar();
                case 4 -> alterar();
                case 5 -> excluir();
                case 6 -> salvarArquivo();
            }

        } while (opcao != 0);
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
    }

    static void listar() {
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa p = pessoas.get(i);
            System.out.println(i + " - " + p.getTipo() + " | " + p.getNome() + " | " + p.getIdade());
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
        }
    }

    static void excluir() {
        listar();
        System.out.print("Índice: ");
        int i = scanner.nextInt();

        if (i >= 0 && i < pessoas.size()) {
            pessoas.remove(i);
        }
    }

    static void salvarArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("pessoas.txt"))) {
            for (Pessoa p : pessoas) {
                bw.write(p.toArquivo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar.");
        }
    }
}



