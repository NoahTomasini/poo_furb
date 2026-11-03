package com.example;

public class Dinheiro implements MetodoPagamento{
    private double valor;//valor pago
    public Dinheiro(double valor){
        setValor(valor);
    }
    public void setValor(double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("Valor invalido");
        }
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
    @Override
    public double calcularDesconto(Pedido pedido){
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido invalido");
        }
        double valor_pedido = pedido.calcularValorPedido();
        double valor_desconto = valor_pedido * 0.10;
        return valor_desconto;
    }
    @Override
    public double calcularTroco(double valor_pedido) {
        if (valor_pedido <= 0) {
            throw new IllegalArgumentException("Valor do Pedido invalido");
        }
        double valor_desconto = valor_pedido * 0.10;
        double valor_total = valor_pedido - valor_desconto;
        if (valor < valor_total) {
            throw new IllegalArgumentException("Troco invalido");
        }
        double troco = valor - valor_total;
        return troco;
    }
}
