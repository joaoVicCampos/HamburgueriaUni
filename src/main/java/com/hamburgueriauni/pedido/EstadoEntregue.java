package com.hamburgueriauni.pedido;

public class EstadoEntregue implements EstadoPedido {

    @Override public void pagar(Pedido pedido)         { throw new IllegalStateException("Pedido já foi entregue."); }
    @Override public void iniciarPreparo(Pedido pedido){ throw new IllegalStateException("Pedido já foi entregue."); }
    @Override public void marcarPronto(Pedido pedido)  { throw new IllegalStateException("Pedido já foi entregue."); }
    @Override public void entregar(Pedido pedido)      { throw new IllegalStateException("Pedido já foi entregue."); }
    @Override public void cancelar(Pedido pedido)      { throw new IllegalStateException("Pedido já foi entregue."); }

    @Override
    public StatusPedido getStatus() { return StatusPedido.ENTREGUE; }
}
