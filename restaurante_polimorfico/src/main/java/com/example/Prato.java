package com.example;

public class Prato {
    private double valor;
    private String nome;
    private Categoria categoria;
    public Prato(double valor, String nome, Categoria categoria){
        setValor(valor);
        setNome(nome);
        setCategoria(categoria);
    }
    public void setCategoria(Categoria categoria) {
        if (categoria == null){
            throw new CategoriaInvalidaException("Categoria invalido");
        }
        this.categoria = categoria;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome invalido");
        }
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setValor(double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("Valor invalido");
        }
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
}
