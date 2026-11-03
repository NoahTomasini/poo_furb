
package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class PagamentoTest {

    private Pedido pedido;
    private Pagamento pagamento;
    private MetodoPagamento metodoMock;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.incluirPrato(
            new Prato(100.0, "Prato Teste", Categoria.PRINCIPAL)
        );

        // Utilizando Dinheiro como implementação de MetodoPagamento
        metodoMock = new Dinheiro(150.0);
        pagamento = new Pagamento(pedido, metodoMock);
    }

    @Test
    @DisplayName("Deve calcular o total subtraindo o desconto do pedido")
    void testCalcularTotalPagar() {
        // 100 - 10% = 90
        double valorEsperado = 90.0;

        assertEquals(
            valorEsperado,
            pagamento.calcularTotalPagar(),
            0.001
        );
    }

    @Test
    @DisplayName("Deve definir e recuperar corretamente pedido e método")
    void testGetSetAtributos() {
        Pedido novoPedido = new Pedido();
        novoPedido.incluirPrato(
            new Prato(50.0, "Novo Prato", Categoria.ENTRADA)
        );

        MetodoPagamento novoMetodo = new Cartao(200.0);

        pagamento.setPedido(novoPedido);
        pagamento.setMetodo(novoMetodo);

        assertSame(novoPedido, pagamento.getPedido());
        assertSame(novoMetodo, pagamento.getMetodo());
    }

    @Test
    @DisplayName("Deve rejeitar pedido nulo no setter")
    void testSetPedidoInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> pagamento.setPedido(null)
        );
    }

    @Test
    @DisplayName("Deve rejeitar método de pagamento nulo no setter")
    void testSetMetodoInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> pagamento.setMetodo(null)
        );
    }

    @Test
    @DisplayName("Deve validar pedido e método no construtor")
    void testConstrutorValidacao() {
        assertAll(
            () -> assertThrows(
                IllegalArgumentException.class,
                () -> new Pagamento(null, metodoMock)
            ),
            () -> assertThrows(
                IllegalArgumentException.class,
                () -> new Pagamento(pedido, null)
            )
        );
    }

    @Test
    @DisplayName("Deve calcular total corretamente com Dinheiro")
    void testCalcularTotalComDinheiro() {
        pagamento.setMetodo(new Dinheiro(150.0));

        // 100 - 10% = 90
        assertEquals(90.0, pagamento.calcularTotalPagar(), 0.001);
    }

    @Test
    @DisplayName("Deve calcular total corretamente com Cartão")
    void testCalcularTotalComCartao() {
        pagamento.setMetodo(new Cartao(200.0));

        // 100 - 5% = 95
        assertEquals(95.0, pagamento.calcularTotalPagar(), 0.001);
    }

    @Test
    @DisplayName("Fidelidade: deve aplicar 30% sem sobremesas")
    void testCalcularTotalComFidelidadeSemSobremesa() {
        Pedido pedidoSemSobremesa = new Pedido();
        pedidoSemSobremesa.incluirPrato(
            new Prato(100.0, "Jantar Especial", Categoria.PRINCIPAL)
        );

        pagamento.setPedido(pedidoSemSobremesa);
        pagamento.setMetodo(new CartaoFidelidade(200.0));

        // 100 - 30% = 70
        assertEquals(70.0, pagamento.calcularTotalPagar(), 0.001);
    }

    @Test
    @DisplayName("Fidelidade: deve descontar o valor das sobremesas")
    void testCalcularTotalComFidelidadeComSobremesa() {
        Pedido pedidoComSobremesa = new Pedido();

        pedidoComSobremesa.incluirPrato(
            new Prato(60.0, "Lasanha", Categoria.PRINCIPAL)
        );
        pedidoComSobremesa.incluirPrato(
            new Prato(15.0, "Pudim", Categoria.SOBREMESA)
        );
        pedidoComSobremesa.incluirPrato(
            new Prato(10.0, "Sorvete", Categoria.SOBREMESA)
        );

        pagamento.setPedido(pedidoComSobremesa);
        pagamento.setMetodo(new CartaoFidelidade(200.0));

        // 85 - 25 = 60
        assertEquals(60.0, pagamento.calcularTotalPagar(), 0.001);
    }

    @Test
    @DisplayName("Fidelidade: deve permitir total zero quando o pedido tiver somente sobremesas")
    void testCalcularTotalComFidelidadeSomenteSobremesa() {
        Pedido pedidoSomenteSobremesa = new Pedido();

        pedidoSomenteSobremesa.incluirPrato(
            new Prato(15.0, "Pudim", Categoria.SOBREMESA)
        );
        pedidoSomenteSobremesa.incluirPrato(
            new Prato(10.0, "Sorvete", Categoria.SOBREMESA)
        );

        pagamento.setPedido(pedidoSomenteSobremesa);
        pagamento.setMetodo(new CartaoFidelidade(200.0));

        // 25 - 25 = 0
        assertEquals(0.0, pagamento.calcularTotalPagar(), 0.001);
    }
}
