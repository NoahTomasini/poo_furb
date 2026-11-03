
package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class CartaoFidelidadeTest {

    private CartaoFidelidade cartaoFidelidade;

    @BeforeEach
    void setUp() {
        cartaoFidelidade = new CartaoFidelidade(500.0);
    }

    @Test
    @DisplayName("Deve dar 30% de desconto quando não houver sobremesas")
    void testCalcularDescontoSemSobremesa() {
        Pedido pedido = new Pedido();
        pedido.incluirPrato(
            new Prato(100.0, "File Simples", Categoria.PRINCIPAL)
        );

        double descontoEsperado = 30.0;
        double descontoAtual = cartaoFidelidade.calcularDesconto(pedido);

        assertEquals(descontoEsperado, descontoAtual, 0.001);
    }

    @Test
    @DisplayName("Deve dar desconto igual ao valor total das sobremesas")
    void testCalcularDescontoComSobremesa() {
        Pedido pedido = new Pedido();
        pedido.incluirPrato(
            new Prato(50.0, "Prato Principal", Categoria.PRINCIPAL)
        );
        pedido.incluirPrato(
            new Prato(15.0, "Pudim", Categoria.SOBREMESA)
        );
        pedido.incluirPrato(
            new Prato(10.0, "Gelatina", Categoria.SOBREMESA)
        );

        double descontoEsperado = 25.0;
        double descontoAtual = cartaoFidelidade.calcularDesconto(pedido);

        assertEquals(descontoEsperado, descontoAtual, 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar pedido vazio")
    void testCalcularDescontoPedidoVazio() {
        Pedido pedidoVazio = new Pedido();

        assertThrows(
            IllegalArgumentException.class,
            () -> cartaoFidelidade.calcularDesconto(pedidoVazio)
        );
    }

    @Test
    @DisplayName("Deve rejeitar pedido null")
    void testCalcularDescontoPedidoNulo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> cartaoFidelidade.calcularDesconto(null)
        );
    }

    @Test
    @DisplayName("Deve herdar corretamente o cálculo de troco da classe Cartao")
    void testCalcularTrocoHeranca() {
        assertEquals(0.0, cartaoFidelidade.calcularTroco(100.0), 0.001);
    }

    @Test
    @DisplayName("Deve validar o saldo através do construtor")
    void testConstrutorValidacao() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new CartaoFidelidade(-5.0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new CartaoFidelidade(0.0)
        );
    }

    @Test
    @DisplayName("Deve recuperar corretamente o saldo herdado")
    void testGetSaldoHeranca() {
        assertEquals(500.0, cartaoFidelidade.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar saldo inválido no setter")
    void testSetSaldoInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> cartaoFidelidade.setSaldo(0.0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> cartaoFidelidade.setSaldo(-50.0)
        );
    }
}
