package com.hamburgueriauni.pedido;

public class EstadoPago implements EstadoPedido {

    @Override
    public void pagar(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi pago.");
    }

    @Override
    public void iniciarPreparo(Pedido pedido) {
        System.out.println("[State] Pedido #" + pedido.getId() + " enviado para a cozinha.");
        pedido.setEstado(new EstadoPreparando());
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
        System.out.println("[State] Pedido #" + pedido.getId() + " cancelado após pagamento — reembolso gerado.");
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public StatusPedido getStatus() { return StatusPedido.PAGO; }
}
