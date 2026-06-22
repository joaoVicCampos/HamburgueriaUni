package com.hamburgueriauni.relatorio;

import com.hamburgueriauni.cardapio.CardapioCategoria;
import com.hamburgueriauni.cardapio.CardapioFolha;

// Padrão Visitor: coleta estatísticas de preço sem modificar as classes do cardápio
public class RelatorioPrecos implements Visitante {

    private double total       = 0;
    private double menorPreco  = Double.MAX_VALUE;
    private double maiorPreco  = Double.MIN_VALUE;
    private String itemMaisBarato  = "";
    private String itemMaisCaro    = "";
    private int contadorItens  = 0;

    @Override
    public void visitar(CardapioFolha folha) {
        double preco = folha.getPreco();
        total += preco;
        contadorItens++;

        if (preco < menorPreco) { menorPreco = preco; itemMaisBarato = folha.getNome(); }
        if (preco > maiorPreco) { maiorPreco = preco; itemMaisCaro   = folha.getNome(); }
    }

    @Override
    public void visitar(CardapioCategoria categoria) {
        // Não acumula preço de categorias — só de folhas
    }

    public void imprimirRelatorio() {
        System.out.println("\n=== Relatório de Preços (Visitor) ===");
        System.out.printf("  Total de itens:   %d%n", contadorItens);
        System.out.printf("  Soma dos preços:  R$%.2f%n", total);
        System.out.printf("  Preço médio:      R$%.2f%n", contadorItens > 0 ? total / contadorItens : 0);
        System.out.printf("  Mais barato:      %s (R$%.2f)%n", itemMaisBarato, menorPreco);
        System.out.printf("  Mais caro:        %s (R$%.2f)%n", itemMaisCaro,   maiorPreco);
    }
}
