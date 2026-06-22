package com.hamburgueriauni.pagamento;

// Strategy: quanto mais itens no pedido, maior o desconto (escala progressiva)
public class DescontoProgressivo implements EstrategiaDesconto {

    private final int quantidadeItens;

    public DescontoProgressivo(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    @Override
    public double aplicar(double valorTotal) {
        double percentual = calcularPercentual();
        double desconto   = valorTotal * percentual;
        System.out.printf("[Strategy] Desconto progressivo (%d itens → %.0f%%): -R$%.2f%n",
                quantidadeItens, percentual * 100, desconto);
        return valorTotal - desconto;
    }

    private double calcularPercentual() {
        if (quantidadeItens >= 5) return 0.20;
        if (quantidadeItens >= 3) return 0.10;
        if (quantidadeItens >= 2) return 0.05;
        return 0.0;
    }

    @Override
    public String descricao() {
        return String.format("Desconto progressivo (%d itens)", quantidadeItens);
    }
}
