package com.hamburgueriauni.pedido;

public class EstadoNovo implements EstadoPedido {

    @Override
    public void pagar(Pedido pedido) {
        System.out.println("[State] Pagamento registrado para pedido #" + pedido.getId());
        pedido.setEstado(new EstadoPago());
    }

    @Override
    public void iniciarPreparo(Pedido pedido) {
        throw new IllegalStateException("Pedido precisa ser pago antes de ir para a cozinha.");
    }

    @Override
    public void marcarPronto(Pedido pedido) {
        throw new IllegalStateException("Pedido ainda não está em preparo.");
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new IllegalStateException("Pedido ainda não está pronto.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("[State] Pedido #" + pedido.getId() + " cancelado antes do pagamento.");
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public StatusPedido getStatus() { return StatusPedido.NOVO; }
}
