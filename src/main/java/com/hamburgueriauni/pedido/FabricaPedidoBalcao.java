package com.hamburgueriauni.pedido;

public class FabricaPedidoBalcao extends FabricaDePedido {

    @Override
    public Pedido criarPedido(String cliente) {
        return new PedidoBalcao(cliente);
    }
}
