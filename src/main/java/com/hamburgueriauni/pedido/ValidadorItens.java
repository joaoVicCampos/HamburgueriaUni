package com.hamburgueriauni.pedido;

// Chain of Responsibility: verifica se o pedido possui ao menos um item
public class ValidadorItens extends ManipuladorPedido {

    @Override
    public boolean processar(Pedido pedido) {
        System.out.println("[Chain] ValidadorItens: verificando itens do pedido #" + pedido.getId());
        if (pedido.getItens().isEmpty()) {
            System.out.println("[Chain] BLOQUEADO — Pedido #" + pedido.getId() + " não possui itens.");
            return false;
        }
        System.out.println("[Chain] OK — " + pedido.getItens().size() + " item(ns) encontrado(s).");
        return passarParaProximo(pedido);
    }
}
