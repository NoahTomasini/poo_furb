package com.example.lista01.exe02;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        Pessoa p1 = new Pessoa();
        System.out.print("\nDigite sua altura: ");
        p1.altura = myScanner.nextDouble();
        System.out.print("\nDigite seu peso: ");
        p1.peso = myScanner.nextDouble();
        System.out.printf("\nSeu IMC é: %.2f", p1.calcularIMC());
        myScanner.close();
    }
}
