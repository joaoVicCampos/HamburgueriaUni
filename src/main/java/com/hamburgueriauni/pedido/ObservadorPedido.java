package com.hamburgueriauni.pedido;

// Padrão Observer: interface que todos os observadores de pedido devem implementar
public interface ObservadorPedido {
    void atualizar(Pedido pedido, StatusPedido anterior, StatusPedido novo);
}
