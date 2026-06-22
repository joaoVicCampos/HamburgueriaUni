package com.hamburgueriauni.pedido;

// Padrão State: interface que cada estado concreto do pedido deve implementar.
// Cada estado decide quais transições são válidas a partir dele.
public interface EstadoPedido {
    void pagar(Pedido pedido);
    void iniciarPreparo(Pedido pedido);
    void marcarPronto(Pedido pedido);
    void entregar(Pedido pedido);
    void cancelar(Pedido pedido);
    StatusPedido getStatus();
}
