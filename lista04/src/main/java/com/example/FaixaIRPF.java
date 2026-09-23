package com.example;

public enum FaixaIRPF {

    PRIMEIRA(0, 2428.80),
    SEGUNDA(2428.81, 2826.65),
    TERCEIRA(2826.66, 3751.05),
    QUARTA(3751.06, 4664.68),
    QUINTA(4664.69, Double.MAX_VALUE);

    private double salarioMinimo;
    private double salarioMaximo;

    FaixaIRPF(double salarioMinimo, double salarioMaximo) {
        this.salarioMinimo = salarioMinimo;
        this.salarioMaximo = salarioMaximo;
    }

    public double getSalarioMinimo() {
        return salarioMinimo;
    }

    public double getSalarioMaximo() {
        return salarioMaximo;
    }
}