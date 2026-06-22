package com.hamburgueriauni.pedido;

// Padrão Command: interface que encapsula uma operação sobre um Pedido com suporte a undo
public interface ComandoPedido {
    void executar();
    void desfazer();
    String descricao();
}
