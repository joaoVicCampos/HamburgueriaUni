package com.hamburgueriauni.pagamento;

// Padrão Adapter: traduz GatewayCartao (incompatível) para a interface MetodoPagamento
public class AdaptadorCartao implements MetodoPagamento {

    private final GatewayCartao gateway;
    private final int parcelas;

    public AdaptadorCartao(GatewayCartao gateway, int parcelas) {
        this.gateway  = gateway;
        this.parcelas = parcelas;
    }

    @Override
    public boolean processarPagamento(double valor) {
        String autorizacao = gateway.autorizarTransacao(valor, parcelas);
        return gateway.capturarTransacao(autorizacao);
    }

    @Override
    public String getNomeMetodo() { return "Cartão (" + parcelas + "x)"; }
}
