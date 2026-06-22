package com.hamburgueriauni.pagamento;

// Interface alvo do padrão Adapter
public interface MetodoPagamento {
    boolean processarPagamento(double valor);
    String getNomeMetodo();
}
