package com.hamburgueriauni.pedido;

import com.hamburgueriauni.config.ConfiguracaoRestaurante;

public class PedidoDelivery extends Pedido {

    private final String enderecoEntrega;
    private final double taxaEntrega;

    public PedidoDelivery(String cliente, String enderecoEntrega) {
        super(cliente, TipoPedido.DELIVERY);
        this.enderecoEntrega = enderecoEntrega;
        this.taxaEntrega = ConfiguracaoRestaurante.getInstancia().getTaxaEntregaDelivery();
    }

    @Override
    public double calcularTotal() {
        return super.calcularTotal() + taxaEntrega;
    }

    public String getEnderecoEntrega() { return enderecoEntrega; }
    public double getTaxaEntrega()     { return taxaEntrega; }

    @Override
    public String getResumo() {
        return String.format("[DELIVERY] Pedido #%s para %s. Taxa: R$%.2f", id, enderecoEntrega, taxaEntrega);
    }
}
