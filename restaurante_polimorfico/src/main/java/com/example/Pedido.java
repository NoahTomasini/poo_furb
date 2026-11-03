package com.example;
import java.util.ArrayList;

public class Pedido {
    private ArrayList<Prato> pratos = new ArrayList<>();
    public double calcularValorPedido(){
        double valor_pedido = 0;
        for(Prato prato: getPratos()){
            valor_pedido += prato.getValor();
        }
        if (pratos.isEmpty()) {
            throw new IllegalArgumentException("Pedido invalido");
        }
        return valor_pedido;
    }
    public void incluirPrato(Prato prato){
        if (prato == null){
            throw new IllegalArgumentException("Prato invalido.");
        }
        pratos.add(prato);
    }
    public void removerPrato(Prato prato){
        if (prato == null){
            throw new IllegalArgumentException("Prato invalido.");
        }
        if (pratos.contains(prato) && pratos.size() == 1) {
        //1...* = deve ter ao menos um prato
            throw new IllegalArgumentException("Pedido deve possuir ao menos um prato");
        }
        pratos.remove(prato);
    }
    public ArrayList<Prato> getPratos(){
         if (pratos.isEmpty()) {
            throw new IllegalArgumentException("Pedido deve possuir ao menos um prato");
        }
        return new ArrayList<>(pratos);
        //impede que outras listas utilizem
        //a primeira sem validação
        //enviando uma cópia
    }
}
