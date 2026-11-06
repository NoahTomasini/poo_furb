package com.example;
//Noah Augusto Tomasini do Nascimento

public class Item {
    private double valor;
    private double peso;
    public Item(double valor, double peso) {
        setValor(valor);
        setPeso(peso);
    }
    public void setPeso(double peso) {
        if (peso<=0) {
            throw new IllegalArgumentException("peso invalido");
        }
        this.peso = peso;
    }
    public double getPeso() {
        return peso;
    }
    public void setValor(double valor) {
        if (valor<=0) {
            throw new IllegalArgumentException("valor invalido");
        }
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
}
