
package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class PratoTest {

    private Prato prato;

    private final String NOME_VALIDO = "Risoto de Alho Poro";
    private final double VALOR_VALIDO = 45.0;
    private final Categoria CATEGORIA_VALIDA = Categoria.PRINCIPAL;

    @BeforeEach
    void setUp() {
        prato = new Prato(
            VALOR_VALIDO,
            NOME_VALIDO,
            CATEGORIA_VALIDA
        );
    }

    @Test
    @DisplayName("Deve definir e recuperar corretamente o nome")
    void testGetSetNome() {
        String novoNome = "Lasanha";

        prato.setNome(novoNome);

        assertEquals(novoNome, prato.getNome());
    }

    @Test
    @DisplayName("Deve rejeitar nome vazio, nulo ou contendo apenas espaços")
    void testSetNomeInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> prato.setNome("")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> prato.setNome(null)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> prato.setNome("   ")
        );
    }

    @Test
    @DisplayName("Deve definir e recuperar corretamente o valor")
    void testGetSetValor() {
        double novoValor = 50.0;

        prato.setValor(novoValor);

        assertEquals(novoValor, prato.getValor(), 0.001);
    }

    @Test
    @DisplayName("Deve rejeitar valor menor ou igual a zero")
    void testSetValorInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> prato.setValor(0.0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> prato.setValor(-10.5)
        );
    }

    @Test
    @DisplayName("Deve definir e recuperar corretamente a categoria")
    void testGetSetCategoria() {
        Categoria novaCategoria = Categoria.SOBREMESA;

        prato.setCategoria(novaCategoria);

        assertEquals(novaCategoria, prato.getCategoria());
    }

    @Test
    @DisplayName("Deve lançar CategoriaInvalidaException para categoria nula")
    void testSetCategoriaInvalida() {
        assertThrows(
            CategoriaInvalidaException.class,
            () -> prato.setCategoria(null)
        );
    }

    @Test
    @DisplayName("Deve inicializar corretamente os atributos pelo construtor")
    void testConstrutor() {
        assertAll(
            () -> assertEquals(NOME_VALIDO, prato.getNome()),
            () -> assertEquals(VALOR_VALIDO, prato.getValor(), 0.001),
            () -> assertEquals(CATEGORIA_VALIDA, prato.getCategoria())
        );
    }

    @Test
    @DisplayName("Deve rejeitar nome inválido no construtor")
    void testConstrutorNomeInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Prato(VALOR_VALIDO, null, CATEGORIA_VALIDA)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new Prato(VALOR_VALIDO, "", CATEGORIA_VALIDA)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new Prato(VALOR_VALIDO, "   ", CATEGORIA_VALIDA)
        );
    }

    @Test
    @DisplayName("Deve rejeitar valor inválido no construtor")
    void testConstrutorValorInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Prato(0.0, NOME_VALIDO, CATEGORIA_VALIDA)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new Prato(-5.0, NOME_VALIDO, CATEGORIA_VALIDA)
        );
    }

    @Test
    @DisplayName("Deve rejeitar categoria nula no construtor")
    void testConstrutorCategoriaInvalida() {
        assertThrows(
            CategoriaInvalidaException.class,
            () -> new Prato(VALOR_VALIDO, NOME_VALIDO, null)
        );
    }
}
