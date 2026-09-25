package br.furb.aula.poo.gerenciamento_vendedores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendedorTest {
    private final Endereco enderecoValidoParaTeste = new Endereco("Rua Alfandega", 100, "89123456");

    @Test
    @DisplayName("Validacoes de Nome: nulo, vazio e branco")
    void testValidacoesNome() {
        Vendedor vendedorParaTeste = new Vendedor("Valido", 1500.0, 0.0, enderecoValidoParaTeste);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> new Vendedor(null, 1500.0, 0.0, enderecoValidoParaTeste), "Construtor deveria falhar com nome nulo");
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> vendedorParaTeste.setNome(""), "setNome deveria falhar com string vazia");
        IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class, () -> vendedorParaTeste.setNome("   "), "setNome deveria falhar com string em branco");

        // 🛠️ Mensagens ajustadas para bater com "Nome inválido" da classe oficial
        assertTrue(ex1.getMessage().equalsIgnoreCase("Nome inválido"), "Mensagem incorreta para nome nulo");
        assertTrue(ex2.getMessage().equalsIgnoreCase("Nome inválido"), "Mensagem incorreta para nome vazio");
        assertTrue(ex3.getMessage().equalsIgnoreCase("Nome inválido"), "Mensagem incorreta para nome em branco");
    }

    @Test
    @DisplayName("Validacoes de SalarioBase: minimo R$ 1.412,00")
    void testValidacoesSalario() {
        Vendedor vendedorParaTeste = new Vendedor("Valido", 1500.0, 0.0, enderecoValidoParaTeste);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> new Vendedor("Vendedor", 1411.99, 0.0, enderecoValidoParaTeste), "Construtor deveria impedir salario abaixo de 1412.00");
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> vendedorParaTeste.setSalarioBase(1000.0), "setSalarioBase deveria impedir valor abaixo do minimo");

        // 🛠️ Mensagens ajustadas para bater com "Salário inválido" da classe oficial
        assertTrue(ex1.getMessage().equalsIgnoreCase("Salário inválido"), "Mensagem incorreta para salarioBase no construtor");
        assertTrue(ex2.getMessage().equalsIgnoreCase("Salário inválido"), "Mensagem incorreta para salarioBase no setter");
    }

    @Test
    @DisplayName("Validacoes de ValorVendas: nao pode ser negativo")
    void testValidacoesVendas() {
        Vendedor vendedorParaTeste = new Vendedor("Valido", 1500.0, 100.0, enderecoValidoParaTeste);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> new Vendedor("Vendedor", 1500.0, -0.01, enderecoValidoParaTeste), "Construtor deveria impedir vendas negativas");
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> vendedorParaTeste.setValorVendas(-50.0), "setValorVendas deveria impedir valor negativo");

        // 🛠️ Mensagens ajustadas para bater com "Valor de vendas inválido" da classe oficial
        assertTrue(ex1.getMessage().equalsIgnoreCase("Valor de vendas inválido"), "Mensagem incorreta para valorVendas no construtor");
        assertTrue(ex2.getMessage().equalsIgnoreCase("Valor de vendas inválido"), "Mensagem incorreta para valorVendas no setter");
    }

    @Test
    @DisplayName("Validacoes de Endereco: nao pode ser nulo")
    void testValidacoesEndereco() {
        Vendedor vendedorParaTeste = new Vendedor("Valido", 1500.0, 0.0, enderecoValidoParaTeste);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> new Vendedor("Vendedor", 1500.0, 0.0, null), "Construtor deveria impedir endereco nulo");
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> vendedorParaTeste.setEndereco(null), "setEndereco deveria impedir valor nulo");

        // 🛠️ Mensagens ajustadas para bater com "Endereço inválido" da classe oficial
        assertTrue(ex1.getMessage().equalsIgnoreCase("Endereço inválido"), "Mensagem incorreta para endereco no construtor");
        assertTrue(ex2.getMessage().equalsIgnoreCase("Endereço inválido"), "Mensagem incorreta para endereco no setter");
    }

    @Test
    @DisplayName("calcularComissao: deve retornar 10% do valor de vendas")
    void testCalcularComissao() {
        Vendedor vendedorParaTeste = new Vendedor("Ana", 2000.0, 5000.0, enderecoValidoParaTeste);

        double comissaoEsperada = 500.0;
        assertEquals(comissaoEsperada, vendedorParaTeste.calcularComissao(), 0.001,
                "A comissao calculada esta incorreta. Deveria ser 10% do valorVendas");
    }

    @Test
    @DisplayName("calcularSalario: deve retornar salarioBase + comissao")
    void testCalcularSalarioTotal() {
        Vendedor vendedorParaTeste = new Vendedor("Ana", 2000.0, 5000.0, enderecoValidoParaTeste);

        double salarioTotalEsperado = 2500.0;
        assertEquals(salarioTotalEsperado, vendedorParaTeste.calcularSalario(), 0.001,
                "O calculo do salario total (base + comissao) esta incorreto");
    }

    @Test @DisplayName("getNome: deve retornar o nome do vendedor corretamente")
    void testGetNomeVendedor() {
        String nomeEsperado = "Carlos Silva";
        Vendedor vendedorParaTeste = new Vendedor(nomeEsperado, 2000.0, 500.0, enderecoValidoParaTeste);

        assertEquals(nomeEsperado, vendedorParaTeste.getNome(),
                "O getter getNome nao retornou o nome que foi definido no construtor");
    }

    @Test @DisplayName("getSalarioBase: deve retornar o salario base definido")
    void testGetSalarioBaseVendedor() {
        double salarioEsperado = 3500.0;
        Vendedor vendedorParaTeste = new Vendedor("Ana", 1500.0, 0.0, enderecoValidoParaTeste);

        vendedorParaTeste.setSalarioBase(salarioEsperado);

        assertEquals(salarioEsperado, vendedorParaTeste.getSalarioBase(), 0.001,
                "O getter getSalarioBase nao retornou o valor atualizado pelo setter");
    }

    @Test @DisplayName("getValorVendas: deve retornar o valor de vendas atualizado")
    void testGetValorVendasVendedor() {
        double vendasEsperadas = 12500.50;
        Vendedor vendedorParaTeste = new Vendedor("Ana", 1500.0, 0.0, enderecoValidoParaTeste);

        vendedorParaTeste.setValorVendas(vendasEsperadas);

        assertEquals(vendasEsperadas, vendedorParaTeste.getValorVendas(), 0.001,
                "O getter getValorVendas falhou em recuperar o volume");
    }
}
