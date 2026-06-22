package com.hamburgueriauni.cardapio;

import com.hamburgueriauni.relatorio.Visitante;
import com.hamburgueriauni.relatorio.Visitavel;

import java.util.ArrayList;
import java.util.List;

// Padrão Composite: Composite — categoria que agrupa outros itens ou subcategorias
public class CardapioCategoria implements ItemCardapio, Visitavel {

    private final String nome;
    private final String descricao;
    private final List<ItemCardapio> filhos;

    public CardapioCategoria(String nome, String descricao) {
        this.nome      = nome;
        this.descricao = descricao;
        this.filhos    = new ArrayList<>();
    }

    public void adicionar(ItemCardapio item) {
        filhos.add(item);
    }

    public void remover(ItemCardapio item) {
        filhos.remove(item);
    }

    public List<ItemCardapio> getFilhos() {
        return filhos;
    }

    @Override public String getNome()      { return nome; }
    @Override public String getDescricao() { return descricao; }

    // Preço da categoria é a soma de todos os itens filhos
    @Override
    public double getPreco() {
        return filhos.stream().mapToDouble(ItemCardapio::getPreco).sum();
    }

    @Override
    public void exibir(String indent) {
        System.out.println(indent + "=== " + nome.toUpperCase() + " ===  (" + descricao + ")");
        for (ItemCardapio filho : filhos) {
            filho.exibir(indent + "  ");
        }
    }

    @Override
    public void aceitar(Visitante visitante) {
        visitante.visitar(this);
        for (ItemCardapio filho : filhos) {
            if (filho instanceof Visitavel) {
                ((Visitavel) filho).aceitar(visitante);
            }
        }
    }
}
