package com.exe01;

public class ContaBancaria{
    private String titular;
    private double saldo;
    
    public void setTitular(String titular) {
        this.titular = titular;
    }
   
    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public void sacarSaldo(double valor) {
        if (valor<=0){
            throw new IllegalArgumentException("Valor de saque inválido");
        }
        //separando exceções para respostas mais específicas
        else if (valor>saldo){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        saldo -= valor;
    }

    public void depositarSaldo(double valor) {
        if (valor<=0){
            throw new IllegalArgumentException("Valor de depósito inválido");
        }
        saldo += valor;
    }

    public void transferirSaldo(ContaBancaria destino, double valor) {
        this.sacarSaldo(valor);
        destino.depositarSaldo(valor);
    }
}