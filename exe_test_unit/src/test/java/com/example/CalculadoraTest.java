package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    @Test
    void testSomar() {
        Calculadora calculadora = new Calculadora();
        int valor1 = 32;
        int valor2 = 10;

        int esperado = 42;
        int resultado = calculadora.somar(valor1, valor2);
        assertEquals(esperado, resultado);
    }

	@Test
	void testMultiplicar() {
		Calculadora calculadora1 = new Calculadora();
		double valor1 = 1.0;
		double valor2 = 12;
	
		double resultado = calculadora1.multiplicar(valor1, valor2);
		double esperado = 12.0;
		double tolerancia = 0;
		assertEquals(esperado, resultado, tolerancia);
	}
}