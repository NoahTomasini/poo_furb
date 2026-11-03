package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class CartaoTest {

    private Cartao cartao;
    private final double SALDO_INICIAL = 200.0;

    @BeforeEach
    void setUp() {
        cartao = new Cartao(SALDO_INICIAL);
    }

    @Test
    @DisplayName("Deve calcular o desconto de 5% sobre o valor do pedido")
    void testCalcularDesconto() {
        Pedido pedido = new Pedido();
        pedido.incluirPrato(
            new Prato(100.0, "Sequencia de Sushi", Categoria.PRINCIPAL)
        );

        double descontoEsperado = 5.0;
        double descontoAtual = cartao.calcularDesconto(pedido);

        assertEquals(descontoEsperado, descontoAtual, 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar pedido nulo ao calcular desconto")
    void testCalcularDescontoPedidoNulo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> cartao.calcularDesconto(null)
        );
    }

    @Test
    @DisplayName("Deve rejeitar pedido vazio ao calcular desconto")
    void testCalcularDescontoPedidoVazio() {
        Pedido pedido = new Pedido();

        assertThrows(
            IllegalArgumentException.class,
            () -> cartao.calcularDesconto(pedido)
        );
    }

    @Test
    @DisplayName("Deve garantir que o troco seja sempre zero")
    void testCalcularTroco() {
        double valorPedido = 50.0;
        double troco = cartao.calcularTroco(valorPedido);

        assertEquals(0.0, troco, 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar valor do pedido igual a zero no calculo do troco")
    void testCalcularTrocoValorZero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> cartao.calcularTroco(0.0)
        );
    }

    @Test
    @DisplayName("Deve rejeitar valor do pedido negativo no calculo do troco")
    void testCalcularTrocoValorNegativo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> cartao.calcularTroco(-50.0)
        );
    }

    @Test
    @DisplayName("Deve definir e recuperar corretamente o saldo")
    void testGetSetSaldo() {
        double novoSaldo = 350.0;

        cartao.setSaldo(novoSaldo);

        assertEquals(novoSaldo, cartao.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar saldo inválido no setter")
    void testSetSaldoInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> cartao.setSaldo(0.0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> cartao.setSaldo(-50.0)
        );
    }

    @Test
    @DisplayName("Deve validar saldo no construtor")
    void testConstrutorComParametroValidacao() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Cartao(-10.0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new Cartao(0.0)
        );
    }
}
