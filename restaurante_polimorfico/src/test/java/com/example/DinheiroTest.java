package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class DinheiroTest {

    private Dinheiro pagamentoDinheiro;
    private final double VALOR_PAGAMENTO = 100.0;

    @BeforeEach
    void setUp() {
        pagamentoDinheiro = new Dinheiro(VALOR_PAGAMENTO);
    }

    @Test
    @DisplayName("Deve calcular o desconto de 10% sobre o valor do pedido")
    void testCalcularDesconto() {
        Pedido pedido = new Pedido();
        pedido.incluirPrato(
            new Prato(50.0, "Prato 1", Categoria.PRINCIPAL)
        );
        pedido.incluirPrato(
            new Prato(50.0, "Prato 2", Categoria.PRINCIPAL)
        );

        double descontoEsperado = 10.0;
        double descontoAtual = pagamentoDinheiro.calcularDesconto(pedido);

        assertEquals(descontoEsperado, descontoAtual, 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar pedido nulo ao calcular desconto")
    void testCalcularDescontoPedidoNulo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> pagamentoDinheiro.calcularDesconto(null)
        );
    }

    @Test
    @DisplayName("Deve rejeitar pedido vazio ao calcular desconto")
    void testCalcularDescontoPedidoVazio() {
        Pedido pedido = new Pedido();

        assertThrows(
            IllegalArgumentException.class,
            () -> pagamentoDinheiro.calcularDesconto(pedido)
        );
    }

    @Test
    @DisplayName("Deve calcular o troco considerando o desconto de 10%")
    void testCalcularTroco() {
        double valorPedido = 85.50;

        // 85,50 - 10% = 76,95
        // 100,00 - 76,95 = 23,05
        double trocoEsperado = 23.05;
        double trocoAtual = pagamentoDinheiro.calcularTroco(valorPedido);

        assertEquals(trocoEsperado, trocoAtual, 0.001);
    }

    @Test
    @DisplayName("Deve retornar troco zero quando o pagamento for exato")
    void testCalcularTrocoExato() {
        // Pedido de 100,00 com 10% de desconto = 90,00
        pagamentoDinheiro.setValor(90.0);

        double troco = pagamentoDinheiro.calcularTroco(100.0);

        assertEquals(0.0, troco, 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar pagamento insuficiente")
    void testPagamentoInsuficiente() {
        pagamentoDinheiro.setValor(50.0);

        assertThrows(
            IllegalArgumentException.class,
            () -> pagamentoDinheiro.calcularTroco(100.0)
        );
    }

    @Test
    @DisplayName("Deve rejeitar valor do pedido igual a zero no cálculo do troco")
    void testCalcularTrocoValorZero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> pagamentoDinheiro.calcularTroco(0.0)
        );
    }

    @Test
    @DisplayName("Deve rejeitar valor do pedido negativo no cálculo do troco")
    void testCalcularTrocoValorNegativo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> pagamentoDinheiro.calcularTroco(-50.0)
        );
    }

    @Test
    @DisplayName("Deve definir e recuperar corretamente o valor")
    void testGetSetValor() {
        pagamentoDinheiro.setValor(200.0);

        assertEquals(200.0, pagamentoDinheiro.getValor(), 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar valor de pagamento inválido no setter")
    void testSetValorInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> pagamentoDinheiro.setValor(0.0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> pagamentoDinheiro.setValor(-1.0)
        );
    }

    @Test
    @DisplayName("Deve validar o valor no construtor")
    void testConstrutorValidacao() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Dinheiro(0.0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new Dinheiro(-10.0)
        );
    }
}
