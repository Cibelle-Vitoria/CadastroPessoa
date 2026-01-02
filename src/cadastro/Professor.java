package cadastro;

public class Professor extends Pessoa {

    private String disciplina;

    public Professor(String nome, int idade, String disciplina) {
        super(nome, idade);
        this.disciplina = disciplina;
    }

    @Override
    public String getTipo() {
        return "Professor";
    }

    @Override
    public String toArquivo() {
        return super.toArquivo() + ";" + disciplina;
    }
}

