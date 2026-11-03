package com.example;

public interface MetodoPagamento {
    double calcularDesconto(Pedido pedido);
    double calcularTroco(double valor_pedido);
//class interface = define como devem ser as classes implementadas
//TODOS OS METODOS DE UMA INTERFACE SÃO PUBLIC E ABSTRACT
}
