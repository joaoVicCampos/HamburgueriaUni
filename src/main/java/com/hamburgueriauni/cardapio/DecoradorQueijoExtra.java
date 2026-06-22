package com.hamburgueriauni.cardapio;

// Decorator concreto: adiciona uma fatia extra de queijo (+R$3,00)
public class DecoradorQueijoExtra extends DecoradorItemCardapio {

    public DecoradorQueijoExtra(ItemCardapio decorado) {
        super(decorado);
        System.out.println("[Decorator] + Queijo Extra adicionado a: " + decorado.getNome());
    }

    @Override public double getPreco()     { return decorado.getPreco() + 3.00; }
    @Override public String getDescricao() { return decorado.getDescricao() + " + Queijo Extra"; }
}
