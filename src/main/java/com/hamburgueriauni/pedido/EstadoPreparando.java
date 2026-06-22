package com.hamburgueriauni.pedido;

public class EstadoPreparando implements EstadoPedido {

    @Override
    public void pagar(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi pago.");
    }

    @Override
    public void iniciarPreparo(Pedido pedido) {
        throw new IllegalStateException("Pedido já está sendo preparado.");
    }

    @Override
    public void marcarPronto(Pedido pedido) {
        System.out.println("[State] Pedido #" + pedido.getId() + " finalizado na cozinha — pronto para entrega.");
        pedido.setEstado(new EstadoPronto());
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new IllegalStateException("Pedido ainda não está pronto.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Não é possível cancelar pedido em preparo.");
    }

    @Override
    public StatusPedido getStatus() { return StatusPedido.PREPARANDO; }
}
