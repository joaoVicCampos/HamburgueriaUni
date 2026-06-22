package com.hamburgueriauni.cardapio;

// Decorator concreto: adiciona molho especial da casa (+R$2,00)
public class DecoradorMolhoEspecial extends DecoradorItemCardapio {

    public DecoradorMolhoEspecial(ItemCardapio decorado) {
        super(decorado);
        System.out.println("[Decorator] + Molho Especial adicionado a: " + decorado.getNome());
    }

    @Override public double getPreco()     { return decorado.getPreco() + 2.00; }
    @Override public String getDescricao() { return decorado.getDescricao() + " + Molho Especial"; }
}
