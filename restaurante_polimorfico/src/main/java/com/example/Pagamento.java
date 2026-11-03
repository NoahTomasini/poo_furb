package com.example;

public class Pagamento {
    private Pedido pedido;
    private MetodoPagamento metodo;
    public Pagamento(Pedido pedido, MetodoPagamento metodo){
        setPedido(pedido);
        setMetodo(metodo);
    }
    public double calcularTotalPagar(){
        double valor_pedido = pedido.calcularValorPedido();
        double desconto_metodo = metodo.calcularDesconto(pedido);
        double valor_total = valor_pedido - desconto_metodo;
        if (valor_total < 0){
            throw new IllegalArgumentException("Total invalido");
        }
        return valor_total;
    }
    public void setPedido(Pedido pedido) {
        if (pedido == null){
            throw new IllegalArgumentException("Pedido invalido");
        }
        this.pedido = pedido;
    }
    public void setMetodo(MetodoPagamento metodo) {
        if (metodo == null){
            throw new IllegalArgumentException("Metodo de Pagamento invalido");
        }
        this.metodo = metodo;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public MetodoPagamento getMetodo() {
        return metodo;
    }
}
