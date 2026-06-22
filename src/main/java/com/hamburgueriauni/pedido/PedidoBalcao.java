package com.hamburgueriauni.pedido;

public class PedidoBalcao extends Pedido {

    public PedidoBalcao(String cliente) {
        super(cliente, TipoPedido.BALCAO);
    }

    @Override
    public String getResumo() {
        return String.format("[BALCÃO] Pedido #%s para retirada no balcão. Cliente: %s", id, cliente);
    }
}
