package com.hamburgueriauni.pedido;

import com.hamburgueriauni.cardapio.ItemCardapio;

// Command concreto: adiciona um item ao pedido; desfazer remove o mesmo item
public class ComandoAdicionarItem implements ComandoPedido {

    private final Pedido pedido;
    private final ItemCardapio item;

    public ComandoAdicionarItem(Pedido pedido, ItemCardapio item) {
        this.pedido = pedido;
        this.item   = item;
    }

    @Override
    public void executar() {
        pedido.adicionarItem(item);
        System.out.println("[Command] Item adicionado: " + item.getNome() + " → Pedido #" + pedido.getId());
    }

    @Override
    public void desfazer() {
        pedido.removerItem(item);
        System.out.println("[Command] Undo — Item removido: " + item.getNome() + " ← Pedido #" + pedido.getId());
    }

    @Override
    public String descricao() {
        return "Adicionar item '" + item.getNome() + "' ao pedido #" + pedido.getId();
    }
}
