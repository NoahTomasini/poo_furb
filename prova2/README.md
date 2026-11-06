---Classe Item:
    ---Precisa getters e setters
    ---Não permite zero ou menor que zero.---

---Classe Pedido:
    ---tipoEntrega getter e setter
    ---met calcularValorPedido deve retornar o valor do pedido sem considerar ---o valor da entrega
    ---met incluirItem e removerItem devem verificar se não é nulo e lançar ---exceção caso seja
    ---met calcularValorTotal deve retornar o valor do pedido com a entrega
    ---met calcularPrecoEntrega deve retornar o preco da entrega para o pedido
    ---met calcularPesoPedido deve retornar o valor total do peso---

---Classe Pac:
    ---peso ultrapassa o limite de peso lançar exceção própria com o nome ---PesoExcedidoException---, filha de IllegarArgumentException
    ---Até 1kg - R$10
    ---Até 2kg - R$15
    ---Mais de 2kg - não aceitar esse tipo de entrega.---

---Classe Sedex:
    ---Até 500g - R$12,50
    ---Acima - cobrará R$14,50 + 1,50 para cada 100g adicionais.---

---Classe Retirada:
    ---Sem custo---

---GERAL:
    ---NOME COMO COMENTARIO NO COMEÇO---
    ---lançar IllegalArgumentException sempre que uma validação detectar ---valores incorretos, tendo como padrão a mensagem campo invalido, sendo ---campo o nome do atributo
    ---Erros de compilação e sintaxe irão descontar 1 ponto cada.---
