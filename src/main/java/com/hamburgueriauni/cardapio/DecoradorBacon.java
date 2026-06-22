package com.hamburgueriauni.cardapio;

// Decorator concreto: adiciona bacon ao item (+R$4,00)
public class DecoradorBacon extends DecoradorItemCardapio {

    public DecoradorBacon(ItemCardapio decorado) {
        super(decorado);
        System.out.println("[Decorator] + Bacon adicionado a: " + decorado.getNome());
    }

    @Override public double getPreco()     { return decorado.getPreco() + 4.00; }
    @Override public String getDescricao() { return decorado.getDescricao() + " + Bacon"; }
}
