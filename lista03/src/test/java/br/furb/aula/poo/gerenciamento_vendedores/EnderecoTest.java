package br.furb.aula.poo.gerenciamento_vendedores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnderecoTest {

    @Test @DisplayName("Construtor: logradouro invalido deve lancar excecao")
    void testConstrutorLogradouro() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> new Endereco(null, 1, "89123456"));
        assertTrue(excecao.getMessage().equalsIgnoreCase("Logradouro inválido")); // 🛠️ Ajustado acento
    }

    @Test @DisplayName("Construtor: numero invalido deve lancar excecao")
    void testConstrutorNumero() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> new Endereco("R", 0, "89123456"));
        assertTrue(excecao.getMessage().equalsIgnoreCase("Número inválido")); // 🛠️ Ajustado acento
    }

    @Test @DisplayName("Construtor: cep invalido deve lancar excecao")
    void testConstrutorCep() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> new Endereco("Rua das palmeiras", 1, null));
        assertTrue(excecao.getMessage().equalsIgnoreCase("CEP inválido")); // 🛠️ Ajustado acento
    }

    @Test @DisplayName("Setter: logradouro nulo, vazio ou branco deve lancar excecao")
    void testSetterLogradouro() {
        Endereco endereco = new Endereco("Rua", 1, "89123456");
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> endereco.setLogradouro(null));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> endereco.setLogradouro(""));
        IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class, () -> endereco.setLogradouro("   "));
        
        assertTrue(ex1.getMessage().equalsIgnoreCase("Logradouro inválido"), "Verificacao logradouro nulo falhou");
        assertTrue(ex2.getMessage().equalsIgnoreCase("Logradouro inválido"), "Verificacao logradouro vazio falhou");
        assertTrue(ex3.getMessage().equalsIgnoreCase("Logradouro inválido"), "Verificacao logradouro em branco falhou");
    }

    @Test @DisplayName("Setter: numero zero ou negativo deve lancar excecao")
    void testSetterNumero() {
        Endereco endereco = new Endereco("Rua", 1, "89123456");
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> endereco.setNumero(0));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> endereco.setNumero(-10));
        
        assertTrue(ex1.getMessage().equalsIgnoreCase("Número inválido"), "Verificacao numero igual a zero falhou");
        assertTrue(ex2.getMessage().equalsIgnoreCase("Número inválido"), "Verificacao numero negativo falhou");
    }

    @Test @DisplayName("Setter: cep nulo, vazio ou branco deve lancar excecao")
    void testSetterBairro() {
        Endereco endereco = new Endereco("Rua", 1, "89123456");
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> endereco.setCep(null));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> endereco.setCep(""));
        IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class, () -> endereco.setCep("  "));
        
        assertTrue(ex1.getMessage().equalsIgnoreCase("CEP inválido"), "Verificacao Cep nulo falhou");
        assertTrue(ex2.getMessage().equalsIgnoreCase("CEP inválido"), "Verificacao Cep vazio falhou");
        assertTrue(ex3.getMessage().equalsIgnoreCase("CEP inválido"), "Verificacao Cep em branco falhou");
    }

    @Test @DisplayName("getLogradouro: deve retornar o logradouro definido corretamente")
    void testGetLogradouro() {
        String logradouroEsperado = "Rua Alfandega";
        Endereco enderecoParaTeste = new Endereco(logradouroEsperado, 100, "89123456");

        assertEquals(logradouroEsperado, enderecoParaTeste.getLogradouro(),
                "O getter getLogradouro nao retornou o valor esperado que foi definido no construtor");
    }

    @Test @DisplayName("getCep: deve retornar o CEP definido corretamente")
    void testGetCep() {
        String cepEsperado = "12345999";
        Endereco enderecoParaTeste = new Endereco("Rua Valida", 10, cepEsperado);

        assertEquals(cepEsperado, enderecoParaTeste.getCep(),
                "O getter getCep falhou em recuperar o cep do endereco");
    }

    @Test @DisplayName("getNumero: deve retornar o numero definido corretamente")
    void testGetNumero() {
        int numeroEsperado = 500;
        Endereco enderecoParaTeste = new Endereco("Rua Valida", 1, "89123456");

        enderecoParaTeste.setNumero(numeroEsperado);

        assertEquals(numeroEsperado, enderecoParaTeste.getNumero(),
                "O getter getNumero nao retornou o valor que foi alterado via setter");
    }
}
