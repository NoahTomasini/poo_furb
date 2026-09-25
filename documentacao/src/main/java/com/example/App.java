package com.example;
/**
 * Classe principal da aplicação.
 */
public class App {

    /**
     * Ponto de entrada da aplicação.
     *
     * @param args argumentos recebidos pela linha de comando
     */
    public static void main(String[] args) {

        int numero = 10;

        Pessoa pessoa = new Pessoa("João", 25);

        System.out.println("Número: " + numero);
        pessoa.apresentar();
    }
}
