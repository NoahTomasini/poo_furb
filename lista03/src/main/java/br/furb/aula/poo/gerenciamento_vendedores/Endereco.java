package br.furb.aula.poo.gerenciamento_vendedores;

public class Endereco {
    private String logradouro;
    private int numero;
    private String cep;

    public Endereco(String logradouro, int numero, String cep) {
        setLogradouro(logradouro);
        setNumero(numero);
        setCep(cep);
    }

    public void setLogradouro(String logradouro) {
        if (logradouro == null || logradouro.trim().isEmpty()) {
            throw new IllegalArgumentException("Logradouro inválido");
        }
        this.logradouro = logradouro;
    }

    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Número inválido");
        }
        this.numero = numero;
    }

    public void setCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new IllegalArgumentException("CEP inválido");
        }
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }
}
