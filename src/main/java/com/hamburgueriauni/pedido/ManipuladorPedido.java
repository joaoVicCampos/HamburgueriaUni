package com.hamburgueriauni.pedido;

// Padrão Chain of Responsibility: elo abstrato da cadeia de validação de pedidos
public abstract class ManipuladorPedido {

    private ManipuladorPedido proximo;

    public ManipuladorPedido setProximo(ManipuladorPedido proximo) {
        this.proximo = proximo;
        return proximo; // permite encadeamento fluente: a.setProximo(b).setProximo(c)
    }

    public abstract boolean processar(Pedido pedido);

    protected boolean passarParaProximo(Pedido pedido) {
        if (proximo != null) {
            return proximo.processar(pedido);
        }
        return true;
    }
}
