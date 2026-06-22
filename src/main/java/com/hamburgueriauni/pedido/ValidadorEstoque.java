package com.hamburgueriauni.pedido;

import com.hamburgueriauni.sistema.EstoqueProxy;

// Chain of Responsibility: consulta o estoque via Proxy antes de confirmar o pedido
public class ValidadorEstoque extends ManipuladorPedido {

    private final EstoqueProxy estoque;

    public ValidadorEstoque(EstoqueProxy estoque) {
        this.estoque = estoque;
    }

    @Override
    public boolean processar(Pedido pedido) {
        System.out.println("[Chain] ValidadorEstoque: consultando disponibilidade...");
        boolean disponivel = estoque.verificarDisponibilidade(pedido);
        if (!disponivel) {
            System.out.println("[Chain] BLOQUEADO — Ingredientes insuficientes no estoque.");
            return false;
        }
        System.out.println("[Chain] OK — Estoque disponível para o pedido.");
        return passarParaProximo(pedido);
    }
}
