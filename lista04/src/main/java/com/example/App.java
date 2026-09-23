package com.example;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner MyScanner = new Scanner(System.in);
        System.out.print("Digite seu salário: R$ ");
        double salario = MyScanner.nextDouble();

        FaixaIRPF faixaEncontrada = null;
        for (FaixaIRPF faixa : FaixaIRPF.values()) {
            if (salario >= faixa.getSalarioMinimo()
                    && salario <= faixa.getSalarioMaximo()) {
                faixaEncontrada = faixa;
                break;
            }
        }

        if (faixaEncontrada != null) {
            System.out.println("Salário: R$ " + salario);
            System.out.println("Faixa: " + faixaEncontrada);
        }
        else {
            System.out.println("Salário inválido.");
        }
        MyScanner.close();
    }
}
