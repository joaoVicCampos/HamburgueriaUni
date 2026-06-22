package com.hamburgueriauni.cardapio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// Padrão Iterator: percorre todos os itens folha do Cardápio (DFS) sem expor a estrutura interna.
// Permite navegar pelo cardápio de forma uniforme, independente da profundidade da árvore Composite.
public class IteradorCardapio implements Iterator<CardapioFolha> {

    private final List<CardapioFolha> folhas = new ArrayList<>();
    private int posicao = 0;

    public IteradorCardapio(CardapioCategoria raiz) {
        achatarArvore(raiz);
    }

    private void achatarArvore(ItemCardapio item) {
        if (item instanceof CardapioFolha folha) {
            folhas.add(folha);
        } else if (item instanceof CardapioCategoria categoria) {
            for (ItemCardapio filho : categoria.getFilhos()) {
                achatarArvore(filho);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return posicao < folhas.size();
    }

    @Override
    public CardapioFolha next() {
        if (!hasNext()) throw new NoSuchElementException("Fim do cardápio.");
        return folhas.get(posicao++);
    }

    public void reiniciar()       { posicao = 0; }
    public int totalItens()       { return folhas.size(); }
    public int posicaoAtual()     { return posicao; }
}
