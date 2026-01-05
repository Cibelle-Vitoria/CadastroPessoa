package cadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class CadastroPessoa extends JFrame {

    private JLabel lblTipo, lblNome, lblIdade, lblInfo;
    private JTextField txtNome, txtIdade, txtInfo;
    private JRadioButton rbAluno, rbProfessor;
    private ButtonGroup grupo;
    private JButton btnCadastrar, btnListar, btnAlterar, btnExcluir, btnSalvar;
    private JTextArea area;

    private ArrayList<Pessoa> pessoas = new ArrayList<>();

    public CadastroPessoa() {

        super("Cadastro de Pessoas");
        setLayout(new FlowLayout());

        // Tipo
        lblTipo = new JLabel("Tipo:");
        rbAluno = new JRadioButton("Aluno");
        rbProfessor = new JRadioButton("Professor");

        grupo = new ButtonGroup();
        grupo.add(rbAluno);
        grupo.add(rbProfessor);

        add(lblTipo);
        add(rbAluno);
        add(rbProfessor);

        // Nome
        lblNome = new JLabel("Nome:");
        txtNome = new JTextField(15);
        add(lblNome);
        add(txtNome);

        // Idade
        lblIdade = new JLabel("Idade:");
        txtIdade = new JTextField(5);
        add(lblIdade);
        add(txtIdade);

        // Matrícula / Disciplina
        lblInfo = new JLabel("Matrícula / Disciplina:");
        txtInfo = new JTextField(15);
        add(lblInfo);
        add(txtInfo);

        // Botões
        btnCadastrar = new JButton("Cadastrar");
        btnListar = new JButton("Listar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnSalvar = new JButton("Salvar");

        add(btnCadastrar);
        add(btnListar);
        add(btnAlterar);
        add(btnExcluir);
        add(btnSalvar);

        // Área
        area = new JTextArea(10, 35);
        area.setEditable(false);
        add(new JScrollPane(area));

        // CADASTRAR
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nome = txtNome.getText();
                String idadeTxt = txtIdade.getText();

                if (nome.isEmpty() || idadeTxt.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha nome e idade.");
                    return;
                }

                int idade = Integer.parseInt(idadeTxt);

                if (rbAluno.isSelected()) {
                    pessoas.add(new Aluno(nome, idade, txtInfo.getText()));
                } else if (rbProfessor.isSelected()) {
                    pessoas.add(new Professor(nome, idade, txtInfo.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione Aluno ou Professor.");
                    return;
                }

                area.append(
                        (pessoas.size() - 1) + " - " +
                                pessoas.get(pessoas.size() - 1).funcao() +
                                " | " + nome + "\n"
                );

                txtNome.setText("");
                txtIdade.setText("");
                txtInfo.setText("");
                grupo.clearSelection();
            }
        });

        // LISTAR
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.setText("");
                for (int i = 0; i < pessoas.size(); i++) {
                    Pessoa p = pessoas.get(i);
                    area.append(i + " - " + p.funcao() + " | " + p.getNome() + "\n");
                }
            }
        });

        // ALTERAR
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String indiceTxt = JOptionPane.showInputDialog("Índice para alterar:");
                if (indiceTxt == null) return;

                int i = Integer.parseInt(indiceTxt);

                if (i >= 0 && i < pessoas.size()) {

                    Pessoa p = pessoas.get(i);

                    String novoNome = JOptionPane.showInputDialog("Novo nome:", p.getNome());
                    String novaIdadeTxt = JOptionPane.showInputDialog("Nova idade:", p.getIdade());

                    int novaIdade = Integer.parseInt(novaIdadeTxt);

                    p.setNome(novoNome);
                    p.setIdade(novaIdade);

                    JOptionPane.showMessageDialog(null, "Cadastro alterado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Índice inválido.");
                }
            }
        });

        // EXCLUIR
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String indiceTxt = JOptionPane.showInputDialog("Índice para excluir:");
                if (indiceTxt == null) return;

                int i = Integer.parseInt(indiceTxt);

                if (i >= 0 && i < pessoas.size()) {
                    pessoas.remove(i);
                    JOptionPane.showMessageDialog(null, "Excluído!");
                }
            }
        });

        // SALVAR
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter("pessoas.txt"));
                    for (Pessoa p : pessoas) {
                        pw.println(p.formatarParaArquivo());
                    }
                    pw.close();
                    JOptionPane.showMessageDialog(null, "Arquivo salvo!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar.");
                }
            }
        });

        // CONFIGURAÇÕES
        setSize(520, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CadastroPessoa();
    }
}
