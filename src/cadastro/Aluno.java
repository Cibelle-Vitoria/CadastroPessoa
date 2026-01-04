package cadastro;

public class Aluno extends Pessoa {

    private String matricula;

    public Aluno(String nome, int idade, String matricula) {
        super(nome, idade);
        this.matricula = matricula;
    }
    // getter
    public String getMatricula() {
        return matricula;
    }

    // setter
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    @Override
    public String funcao() {
        return "Aluno";
    }

    @Override
    public String formatarParaArquivo() {
        return "Aluno;" + getNome() + ";" + getIdade() + ";" + matricula;
    }
}
