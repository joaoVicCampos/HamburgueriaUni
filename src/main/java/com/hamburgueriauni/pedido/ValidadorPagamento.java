package com.hamburgueriauni.pedido;

import com.hamburgueriauni.pagamento.MetodoPagamento;

// Chain of Responsibility: processa o pagamento antes de liberar o pedido para a cozinha
public class ValidadorPagamento extends ManipuladorPedido {

    private final MetodoPagamento metodoPagamento;

    public ValidadorPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    @Override
    public boolean processar(Pedido pedido) {
        double total = pedido.calcularTotal();
        System.out.println("[Chain] ValidadorPagamento: processando R$" + String.format("%.2f", total)
                + " via " + metodoPagamento.getNomeMetodo());

        boolean pago = metodoPagamento.processarPagamento(total);
        if (!pago) {
            System.out.println("[Chain] BLOQUEADO — Pagamento recusado.");
            return false;
        }

        pedido.pagar();
        System.out.println("[Chain] OK — Pagamento aprovado.");
        return passarParaProximo(pedido);
    }
}
