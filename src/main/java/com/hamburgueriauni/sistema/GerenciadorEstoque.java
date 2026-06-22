package com.hamburgueriauni.sistema;

import com.hamburgueriauni.ingrediente.Ingrediente;
import com.hamburgueriauni.pedido.Pedido;

import java.util.HashMap;
import java.util.Map;

// Padrão Proxy — RealSubject: serviço real de gerenciamento de estoque
public class GerenciadorEstoque implements EstoqueProxy {

    private final Map<String, Integer> estoque = new HashMap<>();

    public GerenciadorEstoque() {
        // Estoque inicial
        estoque.put("pão brioche",          50);
        estoque.put("blend bovino 150g",    30);
        estoque.put("queijo cheddar",       40);
        estoque.put("molho da casa",        60);
        estoque.put("bacon",                25);
        estoque.put("pão integral vegano",  20);
        estoque.put("burger de grão-de-bico", 15);
        estoque.put("queijo vegano de castanha", 15);
        estoque.put("maionese vegana",      30);
        estoque.put("pão australiano",      20);
        estoque.put("blend wagyu 180g",     10);
        estoque.put("brie francês",         12);
        estoque.put("aioli de trufas",      18);
    }

    @Override
    public boolean verificarDisponibilidade(Pedido pedido) {
        return pedido.getItens().stream().allMatch(item -> {
            String chave = item.getNome().toLowerCase();
            return estoque.getOrDefault(chave, 0) > 0;
        });
    }

    @Override
    public void baixarEstoque(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            String chave = item.getNome().toLowerCase();
            estoque.computeIfPresent(chave, (k, v) -> Math.max(0, v - 1));
        });
    }

    @Override
    public int getQuantidade(String nomeIngrediente) {
        return estoque.getOrDefault(nomeIngrediente.toLowerCase(), 0);
    }

    public void reporEstoque(String nomeIngrediente, int quantidade) {
        estoque.merge(nomeIngrediente.toLowerCase(), quantidade, Integer::sum);
    }
}
