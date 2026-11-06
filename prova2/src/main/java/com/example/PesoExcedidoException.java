package com.example;

public class PesoExcedidoException extends IllegalArgumentException{
    public PesoExcedidoException(String mensagem) {
        super(mensagem);
    }
}
