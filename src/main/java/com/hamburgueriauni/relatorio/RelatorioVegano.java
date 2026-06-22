package com.hamburgueriauni.relatorio;

import com.hamburgueriauni.cardapio.CardapioCategoria;
import com.hamburgueriauni.cardapio.CardapioFolha;

import java.util.ArrayList;
import java.util.List;

// Padrão Visitor: identifica e lista todos os itens 100% veganos do cardápio
public class RelatorioVegano implements Visitante {

    private final List<String> itensVeganos = new ArrayList<>();

    @Override
    public void visitar(CardapioFolha folha) {
        boolean todosVeganos = folha.getIngredientes().stream().allMatch(i -> i.isVegano());
        if (todosVeganos && !folha.getIngredientes().isEmpty()) {
            itensVeganos.add(String.format("%-25s R$%.2f", folha.getNome(), folha.getPreco()));
        }
    }

    @Override
    public void visitar(CardapioCategoria categoria) {
        // Navegação pela estrutura feita pelo aceitar() do Composite
    }

    public void imprimirRelatorio() {
        System.out.println("\n=== Relatório de Itens Veganos (Visitor) ===");
        if (itensVeganos.isEmpty()) {
            System.out.println("  Nenhum item 100% vegano encontrado.");
        } else {
            System.out.println("  Itens veganos disponíveis:");
            itensVeganos.forEach(i -> System.out.println("    • " + i));
            System.out.println("  Total: " + itensVeganos.size() + " item(ns)");
        }
    }
}
