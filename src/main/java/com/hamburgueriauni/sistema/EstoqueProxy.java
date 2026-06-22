package com.hamburgueriauni.sistema;

import com.hamburgueriauni.pedido.Pedido;

// Padrão Proxy: Subject — interface implementada pelo serviço real e pelo proxy
public interface EstoqueProxy {
    boolean verificarDisponibilidade(Pedido pedido);
    void baixarEstoque(Pedido pedido);
    int getQuantidade(String nomeIngrediente);
}
