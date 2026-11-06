package com.example;
//Noah Augusto Tomasini do Nascimento

public class Sedex implements TipoEntrega{
    public double calcularEntrega(double peso) {
        double valor_entrega = 0;
        if (peso>0.5) {
            peso = peso - 0.5;
            double peso_divido_100 = peso/0.1;
            valor_entrega = 14.50 + (1.50*peso_divido_100);
        }
        else if (peso<=0.5){
            valor_entrega = 12.50;
        }
        return valor_entrega;
    }

}
