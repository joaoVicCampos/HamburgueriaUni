package com.hamburgueriauni.pedido;

// Command concreto: cancela o pedido; usa Memento para salvar o estado antes de cancelar,
// permitindo que o desfazer restaure o pedido ao estado anterior.
public class ComandoCancelarPedido implements ComandoPedido {

    private final Pedido pedido;
    private final HistoricoPedido historico;
    private PedidoMemento mementoAntesDoCancelamento;

    public ComandoCancelarPedido(Pedido pedido, HistoricoPedido historico) {
        this.pedido    = pedido;
        this.historico = historico;
    }

    @Override
    public void executar() {
        mementoAntesDoCancelamento = pedido.criarMemento();
        historico.salvar(mementoAntesDoCancelamento);
        pedido.cancelar();
        System.out.println("[Command] Pedido #" + pedido.getId() + " cancelado. Estado anterior salvo no histórico.");
    }

    @Override
    public void desfazer() {
        if (mementoAntesDoCancelamento == null) {
            throw new IllegalStateException("Nenhum estado salvo para restaurar.");
        }
        pedido.restaurarMemento(mementoAntesDoCancelamento);
        System.out.println("[Command] Undo — Cancelamento revertido. Pedido #" + pedido.getId() + " restaurado.");
    }

    @Override
    public String descricao() {
        return "Cancelar pedido #" + pedido.getId();
    }
}
