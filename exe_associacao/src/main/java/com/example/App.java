package com.example;

public class App {
    public static void main( String[] args ) {
        Funcionario f1 = new Funcionario();
        Funcionario f2 = new Funcionario();
        f1.setNome("Juliano Korz");
        f1.setRamal("4901");
        f2.setNome("Marco Polo");
        f2.setRamal("3362");

        Departamento d1 = new Departamento();
        d1.setNome("Contabilidade");

        d1.setGerente(f1);
        System.out.printf("Departamento: %s\nGerente: %s\nAtendente: %s", d1.getNome(), d1.getGerente().getNome(), f2.getNome() );
    }
}