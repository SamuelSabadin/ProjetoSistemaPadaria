public abstract class Pessoa {
    private String nome;
    private int idade;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        if(idade<0)
        {
            System.out.println("Idade não pode ser negativa");
        throw new IllegalArgumentException();
        }
        else
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        if(idade<0)
        {
            System.out.println("Idade incorreta");
            throw new IllegalArgumentException();
        }
        else
            this.idade = idade;
    }
}
