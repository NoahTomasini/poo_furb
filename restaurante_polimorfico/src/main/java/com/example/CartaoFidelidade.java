package com.example;

import java.util.ArrayList;

public class CartaoFidelidade extends Cartao{
    public CartaoFidelidade(double saldo){
        super(saldo);
    }
    @Override
    public double calcularDesconto(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido invalido");
        }
        double valor_desconto = 0;
        ArrayList<Prato> pratos = pedido.getPratos();
        for (Prato prato: pratos){
            if (prato.getCategoria() == Categoria.SOBREMESA){
                valor_desconto += prato.getValor();
            }
        }
        if (valor_desconto <= 0.0){
            valor_desconto = pedido.calcularValorPedido() * 0.30;
        }
        return valor_desconto;
    }
}
