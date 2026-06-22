package com.hamburgueriauni.pagamento;

// Padrão Adapter: traduz GatewayPix (incompatível) para a interface MetodoPagamento
public class AdaptadorPix implements MetodoPagamento {

    private final GatewayPix gateway;

    public AdaptadorPix(GatewayPix gateway) {
        this.gateway = gateway;
    }

    @Override
    public boolean processarPagamento(double valor) {
        String qrCode = gateway.gerarQrCode(valor);
        return gateway.confirmarPagamentoPix(qrCode);
    }

    @Override
    public String getNomeMetodo() { return "PIX"; }
}
