package br.furb.aula.poo.gerenciamento_vendedores;

import java.util.ArrayList;

public class Vendedor {
    private String nome;
    private double salarioBase;
    private double valorVendas;
    private Endereco endereco; 
    public static ArrayList<Vendedor> vendedores = new ArrayList<>();

    public Vendedor(String nome, double salarioBase, double valorVendas, Endereco endereco) {
        setNome(nome);
        setSalarioBase(salarioBase);
        setValorVendas(valorVendas);
        setEndereco(endereco);

        vendedores.add(this);
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 1412.00) {
            throw new IllegalArgumentException("Salário inválido");
        }
        this.salarioBase = salarioBase;
    }

    public void setValorVendas(double valorVendas) {
        if (valorVendas < 0) {
            throw new IllegalArgumentException("Valor de vendas inválido");
        }
        this.valorVendas = valorVendas;
    }

    public void setEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço inválido");
        }
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getValorVendas() {
        return valorVendas;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public double calcularComissao() {
        return valorVendas * 0.10;
    }

    public double calcularSalario() {
        return salarioBase + calcularComissao();
    }
}
