/**
 * Representa uma pessoa com nome e idade.
 */
public class Pessoa {

    private String nome;
    private int idade;

    /**
     * Cria uma nova pessoa.
     *
     * @param nome nome da pessoa
     * @param idade idade da pessoa
     */
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    /**
     * Exibe os dados da pessoa no console.
     */
    public void apresentar() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}
