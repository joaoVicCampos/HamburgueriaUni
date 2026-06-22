package com.hamburgueriauni.pagamento;

// Sistema externo de PIX com API própria (incompatível com MetodoPagamento)
public class GatewayPix {

    public String gerarQrCode(double valor) {
        return "QR_PIX_" + String.format("%.0f", valor * 100);
    }

    public boolean confirmarPagamentoPix(String qrCode) {
        System.out.println("  [GatewayPix] Confirmando pagamento PIX: " + qrCode);
        return qrCode != null && qrCode.startsWith("QR_PIX_");
    }
}
