package com.hamburgueriauni.pagamento;

public class SemDesconto implements EstrategiaDesconto {

    @Override
    public double aplicar(double valorTotal) { return valorTotal; }

    @Override
    public String descricao() { return "Sem desconto"; }
}
