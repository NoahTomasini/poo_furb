package com.example;

public class Cartao implements MetodoPagamento {
    private double saldo;//saldo do cartão, provavelmente
    public Cartao(double saldo){
        setSaldo(saldo);
    }
    public void setSaldo(double saldo) {
        if (saldo <= 0.0){
            throw new IllegalArgumentException("Saldo invalido");
        }
        this.saldo = saldo;
    }
    public double getSaldo() {
        return saldo;
    }
    @Override 
    public double calcularDesconto(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido invalido");
        }
        double valor_pedido = pedido.calcularValorPedido();
        double valor_desconto = valor_pedido * 0.05;
        return valor_desconto;
    }
    @Override 
    public double calcularTroco(double valor_pedido) {
        if (valor_pedido <= 0) {
            throw new IllegalArgumentException("ValorPedido invalido");
        }
        return 0.0;
    }
}
