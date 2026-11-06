package com.example;
//Noah Augusto Tomasini do Nascimento

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Sedex sedex = new Sedex();
        Pac pac = new Pac();
        Retirada retirada = new Retirada();
        Item item = new Item(1, 1.5);
        Item item1 = new Item(1, 1.0);
        Pedido pedido = new Pedido(sedex);
        pedido.incluirItem(item);
        pedido.incluirItem(item1);
        System.out.println(pedido.calcularPesoPedido());
        System.out.println(pedido.calcularValorTotal());
    }
}