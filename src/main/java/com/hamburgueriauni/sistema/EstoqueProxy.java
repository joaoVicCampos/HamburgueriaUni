package com.hamburgueriauni.sistema;

import com.hamburgueriauni.pedido.Pedido;

// Stub inicial — implementação completa do Proxy vem na Fase 8
public interface EstoqueProxy {
    boolean verificarDisponibilidade(Pedido pedido);
    void baixarEstoque(Pedido pedido);
}
