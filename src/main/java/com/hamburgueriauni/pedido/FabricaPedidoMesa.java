package com.hamburgueriauni.pedido;

public class FabricaPedidoMesa extends FabricaDePedido {

    private final int numeroMesa;

    public FabricaPedidoMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    @Override
    public Pedido criarPedido(String cliente) {
        return new PedidoMesa(cliente, numeroMesa);
    }
}
