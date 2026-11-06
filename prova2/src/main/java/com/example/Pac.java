package com.example;
//Noah Augusto Tomasini do Nascimento

public class Pac implements TipoEntrega {
    @Override
    public double calcularEntrega(double peso) {
        double valor_entrega = 0;
        if (peso>2.0) {
            throw new PesoExcedidoException("entrega invalido");
        }
        if (peso<=1.0) {
            valor_entrega = 10.0;
        }
        if (peso>1) {
            valor_entrega = 15.0;
        }
        return valor_entrega;
    }
}
