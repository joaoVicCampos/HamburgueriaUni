package com.hamburgueriauni.pedido;

public class PedidoMesa extends Pedido {

    private final int numeroMesa;

    public PedidoMesa(String cliente, int numeroMesa) {
        super(cliente, TipoPedido.MESA);
        this.numeroMesa = numeroMesa;
    }

    public int getNumeroMesa() { return numeroMesa; }

    @Override
    public String getResumo() {
        return String.format("[MESA %d] Pedido #%s. Cliente: %s", numeroMesa, id, cliente);
    }
}
