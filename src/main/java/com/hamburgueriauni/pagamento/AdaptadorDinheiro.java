package com.hamburgueriauni.pagamento;

// Padrão Adapter: traduz GatewayDinheiro (incompatível) para a interface MetodoPagamento
public class AdaptadorDinheiro implements MetodoPagamento {

    private final GatewayDinheiro gateway;
    private final double valorPago;

    public AdaptadorDinheiro(GatewayDinheiro gateway, double valorPago) {
        this.gateway   = gateway;
        this.valorPago = valorPago;
    }

    @Override
    public boolean processarPagamento(double valor) {
        if (valorPago < valor) {
            System.out.println("  [AdaptadorDinheiro] Valor insuficiente. Faltam R$"
                    + String.format("%.2f", valor - valorPago));
            return false;
        }
        gateway.calcularTroco(valorPago, valor);
        return gateway.registrarPagamentoCaixa(valor);
    }

    @Override
    public String getNomeMetodo() { return "Dinheiro"; }
}
