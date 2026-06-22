package com.hamburgueriauni.cardapio;

// Padrão Decorator: decorador abstrato que envolve um ItemCardapio e delega chamadas a ele.
// Subclasses sobrescrevem apenas o que precisam modificar (preço, descrição).
public abstract class DecoradorItemCardapio implements ItemCardapio {

    protected final ItemCardapio decorado;

    protected DecoradorItemCardapio(ItemCardapio decorado) {
        this.decorado = decorado;
    }

    @Override public String getNome()      { return decorado.getNome(); }
    @Override public double getPreco()     { return decorado.getPreco(); }
    @Override public String getDescricao() { return decorado.getDescricao(); }

    @Override
    public void exibir(String indent) {
        System.out.printf("%s- %-25s R$%.2f  |  %s%n", indent, getNome(), getPreco(), getDescricao());
    }
}
