package com.hamburgueriauni.pagamento;

// Padrão Strategy: interface para algoritmos de desconto intercambiáveis
public interface EstrategiaDesconto {
    double aplicar(double valorTotal);
    String descricao();
}
