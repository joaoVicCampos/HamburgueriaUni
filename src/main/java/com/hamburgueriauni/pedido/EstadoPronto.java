package com.hamburgueriauni.pedido;

public class EstadoPronto implements EstadoPedido {

    @Override
    public void pagar(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi pago.");
    }

    @Override
    public void iniciarPreparo(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi preparado.");
    }

    @Override
    public void marcarPronto(Pedido pedido) {
        throw new IllegalStateException("Pedido já está pronto.");
    }

    @Override
    public void entregar(Pedido pedido) {
        System.out.println("[State] Pedido #" + pedido.getId() + " entregue ao cliente " + pedido.getCliente() + ". Bom apetite!");
        pedido.setEstado(new EstadoEntregue());
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Não é possível cancelar pedido já pronto.");
    }

    @Override
    public StatusPedido getStatus() { return StatusPedido.PRONTO; }
}
