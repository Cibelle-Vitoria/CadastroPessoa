package cadastro;

import javax.swing.*; // importar os componentes JFrame JButton JLabel JTextArea JOptionPane
import java.awt.*; //FlowLayout
import java.awt.event.*;//ActionListener ActionEvent
import java.util.ArrayList;//ArrayList
import java.io.*; //FileWriter PrintWriter IOException

public class CadastroPessoa extends JFrame {  //classe principal da interface


//declaração dos componentes da interface
    private JLabel lblTipo, lblNome, lblIdade, lblInfo;//títulos do texto //info -> campo de texto que vai guardar matrícula ou disciplina
    private JTextField txtNome, txtIdade, txtInfo; //campos para digitar texto
    private JRadioButton rbAluno, rbProfessor; // o usuário vai escolher uma opção
    private ButtonGroup grupo; // vai agrupar os JRadiobutton e permitir que só um fique marcado
    private JButton btnCadastrar, btnListar, btnAlterar, btnExcluir, btnSalvar; //ação
    private JTextArea area; //area para exibir a lista

    private ArrayList<Pessoa> pessoas = new ArrayList<>(); //Lista que armazena Aluno e Professor

    public CadastroPessoa() { //O construtor é responsável por inicializar a interface gráfica, criando e configurando todos os componentes da janela

        super("Cadastro de Pessoas"); //título
        setLayout(new FlowLayout()); //organizar
//identificação btn -> jbutton, txt -> JTextField, rb ->JRadioButton, lbl->label

        lblTipo = new JLabel("Tipo:"); //texto fixo na tela
        rbAluno = new JRadioButton("Aluno");  //rbAluno e rbProfessor são aqueles botões redondos pro usuário escolher
        rbProfessor = new JRadioButton("Professor");

        grupo = new ButtonGroup(); //criar o buttongroup para o  usuário escolher apenas uma opção
        grupo.add(rbAluno);
        grupo.add(rbProfessor);

        add(lblTipo);
        add(rbAluno);
        add(rbProfessor);

        // criar componente e adicionar a janela
        lblNome = new JLabel("Nome:"); //usuário digita o nome
        txtNome = new JTextField(15);
        add(lblNome);
        add(txtNome); //adiciona a tela

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
        btnCadastrar = new JButton("Cadastrar");//botão clicável e o texto que vai aparecer nele
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
        area.setEditable(false); //Impede que o usuário clique lá dentro e apague os nomes manualmente. A lista só muda através dos botões.
        add(new JScrollPane(area));//barras de rolagem laterais

        // CADASTRAR
        btnCadastrar.addActionListener(new ActionListener() { //evento vai rodar quando o botão for clicado
            public void actionPerformed(ActionEvent e) {
                    //faz leitura do que for digitado
                String nome = txtNome.getText();
                String idadeTxt = txtIdade.getText();

               //evita erro se o campo ficar vazio
                if (nome.isEmpty() || idadeTxt.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha nome e idade.");
                    return;
                }
                  //coverte texto em número
                int idade = Integer.parseInt(idadeTxt);

                //Cria objetos diferentes usando a mesma lista/polimorfismo  o usuário escolheu aluno ou professor, vai criar aluno ou professor e adicionar lista de pessoas
                if (rbAluno.isSelected()) {
                    pessoas.add(new Aluno(nome, idade, txtInfo.getText())); //verifica seleção se está marcado ou não, vai criar objeto aluno ou professor e adicionar a lista pessoas
                } else if (rbProfessor.isSelected()) {
                    pessoas.add(new Professor(nome, idade, txtInfo.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione Aluno ou Professor."); //vai mostrar a janela pronta no centro da tela
                    return;
                }
           //Esse trecho exibe no JTextArea as informações do último cadastro realizado, utilizando dados armazenados naArrayList.
                area.append(
                        (pessoas.size() - 1) + " - " +
                                pessoas.get(pessoas.size() - 1).funcao() +
                                " | " + nome + "\n"
                );
              //limpar interface
                txtNome.setText("");
                txtIdade.setText("");
                txtInfo.setText("");
                grupo.clearSelection();
            }
        });

        // LISTAR
        //percorre a lista de pessoas cadastradas e exibe no JTextArea o índice, o tipo e o nome de cada registro ao clicar no botão Listar.
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
        //solicita o índice do registro a ser alterado, recupera o objeto correspondente da ArrayList e atualiza seus dados utilizando métodos setters, conforme os conceitos de encapsulamento e collections
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String indiceTxt = JOptionPane.showInputDialog("Índice para alterar:");//abre uma caixa de pergunta
                if (indiceTxt == null) return; //Se o usuário clicar no botão Cancelar da janela, o programa para a execução desse método

                int i = Integer.parseInt(indiceTxt); //Como o usuário digitou em uma caixa de texto recebe como  String, essa parte traduz string para Int

                if (i >= 0 && i < pessoas.size()) { //para garantir que o número digitado está dentro da lista

                    Pessoa p = pessoas.get(i);

                    String novoNome = JOptionPane.showInputDialog("Novo nome:", p.getNome());
                    String novaIdadeTxt = JOptionPane.showInputDialog("Nova idade:", p.getIdade());

                    int novaIdade = Integer.parseInt(novaIdadeTxt);

                    p.setNome(novoNome); //pega o valor que o usuário digitou e coloca dentro da variável privada nome ou idade
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

                String indiceTxt = JOptionPane.showInputDialog("Índice para excluir:"); //abre uma caixa de pergunta
                if (indiceTxt == null) return;

                int i = Integer.parseInt(indiceTxt);

                if (i >= 0 && i < pessoas.size()) { //verificar se o número digitado é válido
                    pessoas.remove(i); // remover i
                    JOptionPane.showMessageDialog(null, "Excluído!");
                }
            }
        });

        // SALVAR
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter("pessoas.txt")); //FileWriter cria ou abre o arquivo, PrintWriter permite usar println para escrever de forma organizada
                    for (Pessoa p : pessoas) { //percorre a lista,

                        pw.println(p.formatarParaArquivo()); //p é tratado como pessoa se  o objeto específico for um Aluno, vai usar a regra do Aluno (gravando nome, idade e matrícula), se for professor grava disciplina
                    }
                    pw.close(); //garante que os dados realmente sejam salvos
                    JOptionPane.showMessageDialog(null, "Arquivo salvo!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar.");
                }
            }
        });

        // CONFIGURAÇÕES
        setSize(520, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // finaliza quando é apertado x na janela
        setVisible(true); //torna a janela visível
    }

    public static void main(String[] args) {
        new CadastroPessoa();
    }
}
