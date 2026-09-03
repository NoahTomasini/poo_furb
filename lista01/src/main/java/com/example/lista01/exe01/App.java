package com.example.lista01.exe01;

public class App{
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();
        pessoa.peso = 78;
        pessoa.altura = 1.75;

        System.out.printf("O IMC é:%.2f", pessoa.calcularIMC());
    }
}
