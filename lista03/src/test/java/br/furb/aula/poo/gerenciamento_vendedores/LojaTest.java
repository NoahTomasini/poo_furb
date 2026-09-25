package br.furb.aula.poo.gerenciamento_vendedores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class LojaTest {

    @Test @DisplayName("Construtor: nome nulo, vazio ou branco deve lancar excecao")
    void testConstrutorNomeInvalido() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> new Loja(null), "Construtor deveria falhar com nome nulo");
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> new Loja(""), "Construtor deveria falhar com nome vazio");
        IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class, () -> new Loja("   "), "Construtor deveria falhar com nome em branco");

        // 🛠️ Mensagens ajustadas com acentuação oficial da regra de negócio ("Nome inválido")
        assertTrue(ex1.getMessage().equalsIgnoreCase("Nome inválido"), "Mensagem incorreta para nome nulo");
        assertTrue(ex2.getMessage().equalsIgnoreCase("Nome inválido"), "Mensagem incorreta para nome vazio");
        assertTrue(ex3.getMessage().equalsIgnoreCase("Nome inválido"), "Mensagem incorreta para nome em branco");
    }

    @Test @DisplayName("Setter: setNome nulo, vazio ou branco deve lancar excecao")
    void testSetterNomeInvalido() {
        Loja lojaParaTeste = new Loja("Loja Valida");

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> lojaParaTeste.setNome(null), "setNome deveria falhar com nulo");
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> lojaParaTeste.setNome(""), "setNome deveria falhar com vazio");
        IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class, () -> lojaParaTeste.setNome("  "), "setNome deveria falhar com espaços");

        assertTrue(ex1.getMessage().equalsIgnoreCase("Nome inválido"), "Mensagem do setter incorreta para nulo");
        assertTrue(ex2.getMessage().equalsIgnoreCase("Nome inválido"), "Mensagem do setter incorreta para vazio");
        assertTrue(ex3.getMessage().equalsIgnoreCase("Nome inválido"), "Mensagem do setter incorreta para espaços");
    }

    @Test @DisplayName("adicionarVendedor: deve lancar excecao para objeto nulo")
    void testAdicionarVendedorNulo() {
        Loja lojaParaTeste = new Loja("Loja Teste");
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> lojaParaTeste.adicionarVendedor(null), "Nao deve ser possivel adicionar um vendedor nulo");
        assertTrue(excecao.getMessage().equalsIgnoreCase("Vendedor inválido"), "Mensagem deveria ser 'Vendedor inválido'");
    }

    @Test @DisplayName("removerVendedor: deve lancar excecao para nulo ou lista vazia")
    void testRemoverVendedorInvalido() {
        Loja lojaParaTeste = new Loja("Loja Teste");
        Endereco enderecoGenerico = new Endereco("Rua", 1, "89123456");
        Vendedor vendedorGenerico = new Vendedor("V", 1500.0, 0, enderecoGenerico);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> lojaParaTeste.removerVendedor(null), "Remover nulo deve falhar");
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> lojaParaTeste.removerVendedor(vendedorGenerico), "Remover de lista vazia deve falhar");

        assertTrue(ex1.getMessage().equalsIgnoreCase("Vendedor inválido"), "Mensagem incorreta para remocao de nulo");
        assertTrue(ex2.getMessage().equalsIgnoreCase("Vendedor inválido"), "Mensagem incorreta para remocao em lista vazia");
    }

    @Test @DisplayName("getVendedores: deve retornar uma copia fiel do conteudo (Encapsulamento)")
    void testGetVendedores() {
        Loja lojaParaTeste = new Loja("Loja Matriz");
        Endereco enderecoExemplo = new Endereco("Rua Alfandega", 100, "89123456");
        Vendedor vendedorInserido = new Vendedor("Vendedor Original", 2000.0, 500.0, enderecoExemplo);

        lojaParaTeste.adicionarVendedor(vendedorInserido);

        List<Vendedor> listaRetornada = lojaParaTeste.getVendedores();

        assertEquals(1, listaRetornada.size(), "A copia da lista deveria conter exatamente 1 vendedor");
        assertEquals(vendedorInserido, listaRetornada.get(0), "O vendedor dentro da copia deve ser o mesmo objeto");
    }

    @Test @DisplayName("calcularMediaComissao: deve retornar a media correta das comissoes")
    void testCalcularMediaComissao() {
        Loja lojaParaTeste = new Loja("Loja Teste");
        Endereco end = new Endereco("Rua", 1, "89123456");

        lojaParaTeste.adicionarVendedor(new Vendedor("Ana", 1500.0, 1000.0, end));    // Comissão: 100.0
        lojaParaTeste.adicionarVendedor(new Vendedor("Getulio", 1500.0, 3000.0, end)); // Comissão: 300.0

        assertEquals(200.0, lojaParaTeste.calcularMediaComissao(), 0.001, "A media das comissoes esta incorreta");
    }

    @Test @DisplayName("vendedorMaiorVenda: deve retornar o vendedor com maior valorVendas")
    void testVendedorMaiorVenda() {
        Loja lojaParaTeste = new Loja("Loja Teste");
        Endereco end = new Endereco("Rua", 1, "89123456");

        Vendedor vBaixo = new Vendedor("Baixo", 1500.0, 1000.0, end);
        Vendedor vAlto = new Vendedor("Alto", 1500.0, 9000.0, end);

        lojaParaTeste.adicionarVendedor(vBaixo);
        lojaParaTeste.adicionarVendedor(vAlto);

        assertEquals(vAlto, lojaParaTeste.vendedorMaiorVenda(), "O metodo deveria retornar o vendedor com 9000 em vendas");
    }

    @Test @DisplayName("calcularFolhaPagamento: deve retornar a soma de todos os salarios totais")
    void testCalcularFolhaPagamento() {
        Loja lojaParaTeste = new Loja("Loja Teste");
        Endereco end = new Endereco("Rua", 1, "89123456");

        lojaParaTeste.adicionarVendedor(new Vendedor("Ana", 2000.0, 5000.0, end));    // Salário Total: 2000 + 500 = 2500
        lojaParaTeste.adicionarVendedor(new Vendedor("Pedro", 1500.0, 1000.0, end));  // Salário Total: 1500 + 100 = 1600

        double folhaEsperada = 4100.0; // 2500 + 1600
        assertEquals(folhaEsperada, lojaParaTeste.calcularFolhaPagamento(), 0.001, "O cálculo da folha salarial falhou");
    }
}
