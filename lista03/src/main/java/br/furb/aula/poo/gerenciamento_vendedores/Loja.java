package br.furb.aula.poo.gerenciamento_vendedores;
import java.util.ArrayList;

public class Loja {
    private String nome;
    public ArrayList<Vendedor> vendedores_loja = new ArrayList<>();

    public Loja(String nome) {
        setNome(nome);
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    public ArrayList<Vendedor> getVendedores() {
        return vendedores_loja;
    }

    public void adicionarVendedor(Vendedor vendedor) {
        if (vendedor == null) {
            throw new IllegalArgumentException("Vendedor inválido");
        }
        vendedores_loja.add(vendedor);
    }

    public void removerVendedor(Vendedor vendedor) {
        if (vendedor == null || vendedores_loja.isEmpty()) {
            throw new IllegalArgumentException("Vendedor inválido");    
        }
        vendedores_loja.remove(vendedor);
    }

    public double calcularMediaComissao() {
        if (vendedores_loja.isEmpty()) {
            return 0;
        }
        double comissao_total = 0;
        for (Vendedor vendedor : vendedores_loja) {
            comissao_total += vendedor.calcularComissao(); 
        }
        return comissao_total / vendedores_loja.size();
    }

    public Vendedor vendedorMaiorVenda() {
        if (vendedores_loja.isEmpty()) {
            throw new IllegalArgumentException("Maior venda inválida");
        }
        double maior_venda = -1;
        Vendedor maior_vendedor = null;
        
        for (Vendedor vendedor : vendedores_loja) {
            if (vendedor.getValorVendas() > maior_venda) {
                maior_vendedor = vendedor;
                maior_venda = vendedor.getValorVendas();
            }
        }
        return maior_vendedor;
    }

    public double calcularFolhaPagamento() {
        if (vendedores_loja.isEmpty()) {
            return 0;
        }
        double folha_pagamento = 0;
        for (Vendedor vendedor : vendedores_loja) {
            folha_pagamento += vendedor.calcularSalario(); 
        }
        return folha_pagamento;
    }
}
