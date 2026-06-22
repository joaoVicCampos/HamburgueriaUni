package com.hamburgueriauni.pedido;

public class FabricaPedidoDelivery extends FabricaDePedido {

    private final String enderecoEntrega;

    public FabricaPedidoDelivery(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    @Override
    public Pedido criarPedido(String cliente) {
        return new PedidoDelivery(cliente, enderecoEntrega);
    }
}
