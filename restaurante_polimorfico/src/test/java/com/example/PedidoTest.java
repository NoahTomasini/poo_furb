
package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

class PedidoTest {

    private Pedido pedido;
    private Prato prato1;
    private Prato prato2;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();

        prato1 = new Prato(30.0, "Pizza", Categoria.PRINCIPAL);
        prato2 = new Prato(15.0, "Pudim", Categoria.SOBREMESA);
    }

    @Test
    @DisplayName("Deve incluir pratos na lista do pedido")
    void testIncluirPrato() {
        pedido.incluirPrato(prato1);
        pedido.incluirPrato(prato2);

        ArrayList<Prato> pratos = pedido.getPratos();

        assertEquals(2, pratos.size());
        assertTrue(pratos.contains(prato1));
        assertTrue(pratos.contains(prato2));
    }

    @Test
    @DisplayName("Deve rejeitar a inclusão de prato nulo")
    void testIncluirPratoNulo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> pedido.incluirPrato(null)
        );
    }

    @Test
    @DisplayName("Deve remover um prato do pedido")
    void testRemoverPrato() {
        pedido.incluirPrato(prato1);
        pedido.incluirPrato(prato2);

        pedido.removerPrato(prato1);

        assertEquals(1, pedido.getPratos().size());
        assertFalse(pedido.getPratos().contains(prato1));
        assertTrue(pedido.getPratos().contains(prato2));
    }

    @Test
    @DisplayName("Deve rejeitar a remoção de prato nulo")
    void testRemoverPratoNulo() {
        pedido.incluirPrato(prato1);

        assertThrows(
            IllegalArgumentException.class,
            () -> pedido.removerPrato(null)
        );
    }

    @Test
    @DisplayName("Deve impedir a remoção do último prato")
    void testRemoverUltimoPrato() {
        pedido.incluirPrato(prato1);

        assertThrows(
            IllegalArgumentException.class,
            () -> pedido.removerPrato(prato1)
        );

        assertEquals(1, pedido.getPratos().size());
    }

    @Test
    @DisplayName("Deve calcular corretamente o valor total do pedido")
    void testCalcularValorPedido() {
        pedido.incluirPrato(prato1);
        pedido.incluirPrato(prato2);

        double esperado = 45.0;
        double atual = pedido.calcularValorPedido();

        assertEquals(esperado, atual, 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar o cálculo de valor de um pedido vazio")
    void testCalcularValorPedidoVazio() {
        assertThrows(
            IllegalArgumentException.class,
            () -> pedido.calcularValorPedido()
        );
    }

    @Test
    @DisplayName("Deve rejeitar o acesso à lista de um pedido vazio")
    void testGetPratosPedidoVazio() {
        assertThrows(
            IllegalArgumentException.class,
            () -> pedido.getPratos()
        );
    }

    @Test
    @DisplayName("Deve retornar uma cópia da lista de pratos")
    void testGetPratos() {
        pedido.incluirPrato(prato1);
        pedido.incluirPrato(prato2);

        ArrayList<Prato> pratos = pedido.getPratos();

        assertNotNull(pratos);
        assertEquals(2, pratos.size());
        assertTrue(pratos.contains(prato1));
        assertTrue(pratos.contains(prato2));

        //modificar a copia não deve alterar a lista original
        pratos.clear();

        assertEquals(2, pedido.getPratos().size());
    }
}
