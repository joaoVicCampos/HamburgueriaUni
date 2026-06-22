package com.hamburgueriauni.pedido;

public class EstadoCancelado implements EstadoPedido {

    @Override public void pagar(Pedido pedido)         { throw new IllegalStateException("Pedido cancelado."); }
    @Override public void iniciarPreparo(Pedido pedido){ throw new IllegalStateException("Pedido cancelado."); }
    @Override public void marcarPronto(Pedido pedido)  { throw new IllegalStateException("Pedido cancelado."); }
    @Override public void entregar(Pedido pedido)      { throw new IllegalStateException("Pedido cancelado."); }
    @Override public void cancelar(Pedido pedido)      { throw new IllegalStateException("Pedido já foi cancelado."); }

    @Override
    public StatusPedido getStatus() { return StatusPedido.CANCELADO; }
}
