package com.example;

import java.util.ArrayList;
//GERADO POR IA PARA VALIDAR TUDO
/**
 * Aplicacao simples para testar as classes do exercicio Restaurante Polimorfico.
 * Coloque este arquivo no mesmo pacote das demais classes: com.example.
 * Execute o metodo main para ver os testes no console.
 */
public class AppTestesRestaurante {

    private static int aprovados = 0;
    private static int reprovados = 0;

    @FunctionalInterface
    private interface AcaoTeste {
        void executar();
    }

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println(" TESTES - RESTAURANTE POLIMORFICO");
        System.out.println("==============================================\n");

        testarPrato();
        testarPedido();
        testarDinheiro();
        testarCartao();
        testarCartaoFidelidade();
        testarPagamento();

        System.out.println("\n==============================================");
        System.out.println(" RESULTADO FINAL");
        System.out.println("==============================================");
        System.out.println("Testes aprovados: " + aprovados);
        System.out.println("Testes reprovados: " + reprovados);
        System.out.println("Total executado:  " + (aprovados + reprovados));

        if (reprovados == 0) {
            System.out.println("\nTudo certo! Todos os testes passaram.");
        } else {
            System.out.println("\nExistem testes que falharam. Confira a mensagem de cada um acima.");
        }
    }

    private static void testarPrato() {
        titulo("PRATO");

        teste("Criar prato valido - entrada", () ->
                new Prato(15.0, "Salada", Categoria.ENTRADA));
        teste("Criar prato valido - principal", () ->
                new Prato(45.0, "Massa", Categoria.PRINCIPAL));
        teste("Criar prato valido - sobremesa", () ->
                new Prato(20.0, "Pudim", Categoria.SOBREMESA));

        esperarExcecao("Rejeitar valor igual a zero", IllegalArgumentException.class,
                () -> new Prato(0.0, "Salada", Categoria.ENTRADA));
        esperarExcecao("Rejeitar valor negativo", IllegalArgumentException.class,
                () -> new Prato(-10.0, "Salada", Categoria.ENTRADA));
        esperarExcecao("Rejeitar nome null", IllegalArgumentException.class,
                () -> new Prato(10.0, null, Categoria.ENTRADA));
        esperarExcecao("Rejeitar nome vazio", IllegalArgumentException.class,
                () -> new Prato(10.0, "", Categoria.ENTRADA));
        esperarExcecao("Rejeitar nome com apenas espacos (usa isBlank)", IllegalArgumentException.class,
                () -> new Prato(10.0, "   ", Categoria.ENTRADA));
        esperarExcecao("Rejeitar categoria null com excecao especifica", CategoriaInvalidaException.class,
                () -> new Prato(10.0, "Salada", null));

        esperarExcecao("Enum rejeita texto que nao existe", IllegalArgumentException.class,
                () -> Categoria.valueOf("LANCHE"));

        teste("Getters retornam os valores corretos", () -> {
            Prato prato = new Prato(22.50, "Torta", Categoria.SOBREMESA);
            verificar(quaseIgual(22.50, prato.getValor()), "Valor retornado incorretamente");
            verificar("Torta".equals(prato.getNome()), "Nome retornado incorretamente");
            verificar(prato.getCategoria() == Categoria.SOBREMESA, "Categoria retornada incorretamente");
        });

        teste("Setters aceitam novos valores validos", () -> {
            Prato prato = new Prato(10.0, "Prato", Categoria.ENTRADA);
            prato.setValor(12.0);
            prato.setNome("Nova entrada");
            prato.setCategoria(Categoria.PRINCIPAL);
            verificar(quaseIgual(12.0, prato.getValor()), "setValor nao atualizou o valor");
            verificar("Nova entrada".equals(prato.getNome()), "setNome nao atualizou o nome");
            verificar(prato.getCategoria() == Categoria.PRINCIPAL, "setCategoria nao atualizou a categoria");
        });

        esperarExcecao("Setter rejeita valor zero", IllegalArgumentException.class, () -> {
            Prato prato = pratoValido();
            prato.setValor(0.0);
        });
        esperarExcecao("Setter rejeita nome vazio", IllegalArgumentException.class, () -> {
            Prato prato = pratoValido();
            prato.setNome("");
        });
        esperarExcecao("Setter rejeita categoria null", CategoriaInvalidaException.class, () -> {
            Prato prato = pratoValido();
            prato.setCategoria(null);
        });
    }

    private static void testarPedido() {
        titulo("PEDIDO");

        teste("Incluir pratos e somar valores sem desconto", () -> {
            Pedido pedido = pedidoComPratos(); // 80 + 20 = 100
            verificar(quaseIgual(100.0, pedido.calcularValorPedido()),
                    "O pedido deveria somar 100.0, sem aplicar desconto");
        });

        esperarExcecao("Pedido vazio nao pode ser calculado", IllegalArgumentException.class, () -> {
            Pedido pedido = new Pedido();
            pedido.calcularValorPedido();
        });
        esperarExcecao("Nao aceitar inclusao de prato null", IllegalArgumentException.class, () -> {
            Pedido pedido = new Pedido();
            pedido.incluirPrato(null);
        });
        esperarExcecao("Nao aceitar remocao de prato null", IllegalArgumentException.class, () -> {
            Pedido pedido = pedidoComPratos();
            pedido.removerPrato(null);
        });

        teste("Remover um prato quando ainda resta outro", () -> {
            Pedido pedido = pedidoComPratos();
            Prato primeiro = pedido.getPratos().get(0);
            pedido.removerPrato(primeiro);
            verificar(pedido.getPratos().size() == 1, "Deveria restar exatamente um prato");
        });

        // Este teste verifica a multiplicidade 1..* do UML.
        esperarExcecao("Impedir a remocao do ultimo prato (multiplicidade 1..*)",
                IllegalArgumentException.class, () -> {
                    Pedido pedido = new Pedido();
                    Prato unico = new Prato(10.0, "Prato unico", Categoria.PRINCIPAL);
                    pedido.incluirPrato(unico);
                    pedido.removerPrato(unico);
                });

        // Este teste verifica se getPratos() devolve uma copia, e nao a lista interna.
        teste("Getter devolve copia e protege a lista interna", () -> {
            Pedido pedido = pedidoComPratos();
            ArrayList<Prato> listaRecebida = pedido.getPratos();
            listaRecebida.clear();
            verificar(pedido.getPratos().size() == 2,
                    "A lista interna foi alterada por meio do getter; retorne new ArrayList<>(pratos)");
        });
    }

    private static void testarDinheiro() {
        titulo("DINHEIRO");

        teste("Criar pagamento em dinheiro com valor positivo", () -> new Dinheiro(100.0));
        esperarExcecao("Rejeitar valor pago igual a zero", IllegalArgumentException.class,
                () -> new Dinheiro(0.0));
        esperarExcecao("Rejeitar valor pago negativo", IllegalArgumentException.class,
                () -> new Dinheiro(-1.0));
        esperarExcecao("Setter de Dinheiro rejeita valor zero", IllegalArgumentException.class, () -> {
            Dinheiro dinheiro = new Dinheiro(10.0);
            dinheiro.setValor(0.0);
        });
        esperarExcecao("Setter de Dinheiro rejeita valor negativo", IllegalArgumentException.class, () -> {
            Dinheiro dinheiro = new Dinheiro(10.0);
            dinheiro.setValor(-5.0);
        });

        teste("Desconto em dinheiro deve ser 10%", () -> {
            Pedido pedido = pedidoComPratos();
            Dinheiro dinheiro = new Dinheiro(100.0);
            verificar(quaseIgual(10.0, dinheiro.calcularDesconto(pedido)),
                    "Desconto esperado: 10.0");
        });

        esperarExcecao("calcularDesconto deve rejeitar pedido null", IllegalArgumentException.class,
                () -> new Dinheiro(100.0).calcularDesconto(null));

        teste("Troco em dinheiro considera desconto de 10%", () -> {
            Dinheiro dinheiro = new Dinheiro(100.0);
            verificar(quaseIgual(10.0, dinheiro.calcularTroco(100.0)),
                    "Para pedido de 100 e pagamento de 100, o troco esperado e 10.0");
        });

        teste("Pagamento exato em dinheiro retorna troco zero", () -> {
            Dinheiro dinheiro = new Dinheiro(90.0);
            verificar(quaseIgual(0.0, dinheiro.calcularTroco(100.0)),
                    "Para pedido de 100, o total com desconto e 90.0");
        });

        esperarExcecao("Rejeitar pagamento em dinheiro insuficiente", IllegalArgumentException.class,
                () -> new Dinheiro(50.0).calcularTroco(100.0));
        esperarExcecao("Rejeitar valor do pedido zero no calculo do troco", IllegalArgumentException.class,
                () -> new Dinheiro(20.0).calcularTroco(0.0));
    }

    private static void testarCartao() {
        titulo("CARTAO");

        teste("Criar cartao com saldo positivo", () -> new Cartao(100.0));
        esperarExcecao("Rejeitar saldo zero", IllegalArgumentException.class, () -> new Cartao(0.0));
        esperarExcecao("Rejeitar saldo negativo", IllegalArgumentException.class, () -> new Cartao(-1.0));
        esperarExcecao("Setter de Cartao rejeita saldo zero", IllegalArgumentException.class, () -> {
            Cartao cartao = new Cartao(10.0);
            cartao.setSaldo(0.0);
        });
        esperarExcecao("Setter de Cartao rejeita saldo negativo", IllegalArgumentException.class, () -> {
            Cartao cartao = new Cartao(10.0);
            cartao.setSaldo(-5.0);
        });

        teste("Desconto no cartao deve ser 5%", () -> {
            Cartao cartao = new Cartao(100.0);
            verificar(quaseIgual(5.0, cartao.calcularDesconto(pedidoComPratos())),
                    "Desconto esperado: 5.0");
        });

        esperarExcecao("calcularDesconto deve rejeitar pedido null", IllegalArgumentException.class,
                () -> new Cartao(100.0).calcularDesconto(null));

        teste("Troco no cartao deve ser sempre zero", () -> {
            Cartao cartao = new Cartao(100.0);
            verificar(quaseIgual(0.0, cartao.calcularTroco(100.0)), "Troco esperado: 0.0");
        });

        esperarExcecao("Rejeitar valor do pedido zero no calculo do troco", IllegalArgumentException.class,
                () -> new Cartao(100.0).calcularTroco(0.0));
    }

    private static void testarCartaoFidelidade() {
        titulo("CARTAO FIDELIDADE");

        teste("Cartao fidelidade herda de Cartao", () -> {
            CartaoFidelidade fidelidade = new CartaoFidelidade(100.0);
            verificar(fidelidade instanceof Cartao, "CartaoFidelidade deveria herdar de Cartao");
            verificar(fidelidade instanceof MetodoPagamento,
                    "CartaoFidelidade deveria ser um MetodoPagamento por heranca");
        });

        teste("Com sobremesa, desconto deve ser 100% dos itens SOBREMESA", () -> {
            Pedido pedido = pedidoComPratos(); // principal 80 + sobremesa 20
            CartaoFidelidade fidelidade = new CartaoFidelidade(100.0);
            verificar(quaseIgual(20.0, fidelidade.calcularDesconto(pedido)),
                    "Desconto esperado: somente os 20.0 da sobremesa");
        });

        teste("Sem sobremesa, desconto deve ser 30% do pedido", () -> {
            Pedido pedido = new Pedido();
            pedido.incluirPrato(new Prato(60.0, "Hamburguer", Categoria.PRINCIPAL));
            pedido.incluirPrato(new Prato(40.0, "Entrada", Categoria.ENTRADA));
            CartaoFidelidade fidelidade = new CartaoFidelidade(100.0);
            verificar(quaseIgual(30.0, fidelidade.calcularDesconto(pedido)),
                    "Desconto esperado: 30.0");
        });

        esperarExcecao("Cartao fidelidade deve rejeitar pedido null", IllegalArgumentException.class,
                () -> new CartaoFidelidade(100.0).calcularDesconto(null));

        teste("Pedido so com sobremesa pode ter total final zero", () -> {
            Pedido pedido = new Pedido();
            pedido.incluirPrato(new Prato(20.0, "Pudim", Categoria.SOBREMESA));
            Pagamento pagamento = new Pagamento(pedido, new CartaoFidelidade(100.0));
            verificar(quaseIgual(0.0, pagamento.calcularTotalPagar()),
                    "O total deve ser zero quando todo o pedido e sobremesa");
        });
    }

    private static void testarPagamento() {
        titulo("PAGAMENTO");

        teste("Pagamento em dinheiro aplica desconto de 10%", () -> {
            Pagamento pagamento = new Pagamento(pedidoComPratos(), new Dinheiro(100.0));
            verificar(quaseIgual(90.0, pagamento.calcularTotalPagar()), "Total esperado: 90.0");
        });

        teste("Pagamento com cartao aplica desconto de 5%", () -> {
            Pagamento pagamento = new Pagamento(pedidoComPratos(), new Cartao(100.0));
            verificar(quaseIgual(95.0, pagamento.calcularTotalPagar()), "Total esperado: 95.0");
        });

        teste("Pagamento com fidelidade desconta as sobremesas", () -> {
            Pagamento pagamento = new Pagamento(pedidoComPratos(), new CartaoFidelidade(100.0));
            verificar(quaseIgual(80.0, pagamento.calcularTotalPagar()), "Total esperado: 80.0");
        });

        teste("Pagamento com fidelidade sem sobremesa aplica 30%", () -> {
            Pedido pedido = new Pedido();
            pedido.incluirPrato(new Prato(100.0, "Prato principal", Categoria.PRINCIPAL));
            Pagamento pagamento = new Pagamento(pedido, new CartaoFidelidade(100.0));
            verificar(quaseIgual(70.0, pagamento.calcularTotalPagar()), "Total esperado: 70.0");
        });

        esperarExcecao("Construtor de Pagamento rejeita pedido null", IllegalArgumentException.class,
                () -> new Pagamento(null, new Dinheiro(100.0)));
        esperarExcecao("Construtor de Pagamento rejeita metodo null", IllegalArgumentException.class,
                () -> new Pagamento(pedidoComPratos(), null));

        esperarExcecao("setPedido rejeita null", IllegalArgumentException.class, () -> {
            Pagamento pagamento = new Pagamento(pedidoComPratos(), new Cartao(100.0));
            pagamento.setPedido(null);
        });
        esperarExcecao("setMetodo rejeita null", IllegalArgumentException.class, () -> {
            Pagamento pagamento = new Pagamento(pedidoComPratos(), new Cartao(100.0));
            pagamento.setMetodo(null);
        });
    }

    private static Pedido pedidoComPratos() {
        Pedido pedido = new Pedido();
        pedido.incluirPrato(new Prato(80.0, "Prato principal", Categoria.PRINCIPAL));
        pedido.incluirPrato(new Prato(20.0, "Sobremesa", Categoria.SOBREMESA));
        return pedido;
    }

    private static Prato pratoValido() {
        return new Prato(10.0, "Prato de teste", Categoria.PRINCIPAL);
    }

    private static void teste(String nome, AcaoTeste acao) {
        try {
            acao.executar();
            aprovados++;
            System.out.println("[APROVADO] " + nome);
        } catch (Throwable erro) {
            reprovados++;
            System.out.println("[REPROVADO] " + nome);
            System.out.println("            Motivo: " + mensagemErro(erro));
        }
    }

    private static void esperarExcecao(String nome, Class<? extends Throwable> tipoEsperado,
                                       AcaoTeste acao) {
        try {
            acao.executar();
            reprovados++;
            System.out.println("[REPROVADO] " + nome);
            System.out.println("            Motivo: era esperada a excecao " + tipoEsperado.getSimpleName()
                    + ", mas nenhuma excecao foi lancada.");
        } catch (Throwable erro) {
            if (tipoEsperado.isInstance(erro)) {
                aprovados++;
                System.out.println("[APROVADO] " + nome + " (" + erro.getClass().getSimpleName() + ")");
            } else {
                reprovados++;
                System.out.println("[REPROVADO] " + nome);
                System.out.println("            Motivo: esperava " + tipoEsperado.getSimpleName()
                        + ", mas recebeu " + erro.getClass().getSimpleName()
                        + " - " + mensagemErro(erro));
            }
        }
    }

    private static void verificar(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

    private static boolean quaseIgual(double esperado, double obtido) {
        return Math.abs(esperado - obtido) < 0.000001;
    }

    private static String mensagemErro(Throwable erro) {
        String mensagem = erro.getMessage();
        return mensagem == null ? "Sem mensagem" : mensagem;
    }

    private static void titulo(String texto) {
        System.out.println("\n--- " + texto + " ---");
    }
}
