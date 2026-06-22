package com.hamburgueriauni.pagamento;

// Sistema de caixa físico com API própria (incompatível com MetodoPagamento)
public class GatewayDinheiro {

    public double calcularTroco(double valorPago, double valorTotal) {
        double troco = valorPago - valorTotal;
        System.out.println("  [GatewayDinheiro] Valor pago: R$" + String.format("%.2f", valorPago)
                + " | Troco: R$" + String.format("%.2f", troco));
        return troco;
    }

    public boolean registrarPagamentoCaixa(double valor) {
        System.out.println("  [GatewayDinheiro] Registrando R$" + String.format("%.2f", valor) + " no caixa.");
        return true;
    }
}
