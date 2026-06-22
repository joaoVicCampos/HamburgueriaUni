package com.hamburgueriauni.pedido;

import com.hamburgueriauni.cardapio.ItemCardapio;

// Command concreto: remove um item do pedido; desfazer o adiciona de volta
public class ComandoRemoverItem implements ComandoPedido {

    private final Pedido pedido;
    private final ItemCardapio item;

    public ComandoRemoverItem(Pedido pedido, ItemCardapio item) {
        this.pedido = pedido;
        this.item   = item;
    }

    @Override
    public void executar() {
        pedido.removerItem(item);
        System.out.println("[Command] Item removido: " + item.getNome() + " → Pedido #" + pedido.getId());
    }

    @Override
    public void desfazer() {
        pedido.adicionarItem(item);
        System.out.println("[Command] Undo — Item restaurado: " + item.getNome() + " ← Pedido #" + pedido.getId());
    }

    @Override
    public String descricao() {
        return "Remover item '" + item.getNome() + "' do pedido #" + pedido.getId();
    }
}
