package com.hamburgueriauni.pagamento;

import com.hamburgueriauni.config.ConfiguracaoRestaurante;

// Strategy: desconto para clientes cadastrados no programa de fidelidade
public class DescontoFidelidade implements EstrategiaDesconto {

    private final double percentual;

    public DescontoFidelidade() {
        this.percentual = ConfiguracaoRestaurante.getInstancia().getDescontoFidelidade();
    }

    @Override
    public double aplicar(double valorTotal) {
        double desconto = valorTotal * percentual;
        System.out.printf("[Strategy] Desconto fidelidade (%.0f%%): -R$%.2f%n", percentual * 100, desconto);
        return valorTotal - desconto;
    }

    @Override
    public String descricao() {
        return String.format("Desconto fidelidade (%.0f%%)", percentual * 100);
    }
}
