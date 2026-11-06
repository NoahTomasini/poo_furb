package com.example;
import java.util.ArrayList;
//Noah Augusto Tomasini do Nascimento

public class Pedido {
    private TipoEntrega tipoEntrega;
    private ArrayList<Item> itens = new ArrayList<>();
    public Pedido(TipoEntrega tipoEntrega){
        setTipoEntrega(tipoEntrega);
    }

    //set tipoEntrega
    public void setTipoEntrega(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    //get tipoEntrega
    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }
    
    //tal qual o set de itens
    public void incluirItem(Item item) {
        if (item==null) {
            throw new IllegalArgumentException("item invalido");
        }
        itens.add(item);
    }

    //get itens
    public ArrayList<Item> getItens() {
        return itens;
    }
    //reset itens
    public void removerItem(Item item) {
        if (item==null) {
            throw new IllegalArgumentException("item invalido");
        }
        itens.remove(item);
    }

    public double calcularPesoPedido() {
        double peso_total = 0;
        for (Item item: itens) {
            peso_total += item.getPeso();
        }
        return peso_total;
    }

    public double calcularPrecoEntrega() {
        double preco_entrega = tipoEntrega.calcularEntrega(0);
        return preco_entrega;
    }

    public double calcularValorPedido() {
        double valor_total = 0;
        for (Item item: itens) {
            valor_total += item.getValor();
        }
        return valor_total;
    }

    public double calcularValorTotal() {
        double entrega = calcularPrecoEntrega();
        double pedido = calcularValorPedido();
        return (entrega+pedido);
    }
}
