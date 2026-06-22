package com.hamburgueriauni.pagamento;

// Sistema externo de cartão com API própria (incompatível com MetodoPagamento)
public class GatewayCartao {

    public String autorizarTransacao(double valor, int parcelas) {
        String codigo = "TXN_" + System.currentTimeMillis();
        System.out.println("  [GatewayCartao] Autorizando R$" + String.format("%.2f", valor)
                + " em " + parcelas + "x → código: " + codigo);
        return codigo;
    }

    public boolean capturarTransacao(String codigoAutorizacao) {
        return codigoAutorizacao != null && codigoAutorizacao.startsWith("TXN_");
    }
}
