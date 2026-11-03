package com.example;
public class CategoriaInvalidaException extends IllegalArgumentException {
    public CategoriaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
