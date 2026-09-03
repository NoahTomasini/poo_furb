package com.example.lista01.exe02;

public class Pessoa {
    double peso;
    double altura;

    public double calcularIMC(){
        double resultado = peso/(altura*altura);

        return resultado;
    }
}

