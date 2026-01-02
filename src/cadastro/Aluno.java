package cadastro;

public class Aluno extends Pessoa {

    private String matricula;

    public Aluno(String nome, int idade, String matricula) {
        super(nome, idade);
        this.matricula = matricula;
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }

    @Override
    public String toArquivo() {
        return super.toArquivo() + ";" + matricula;
    }
}
