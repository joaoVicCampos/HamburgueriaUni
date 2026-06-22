package com.hamburgueriauni.cardapio;

import com.hamburgueriauni.ingrediente.Ingrediente;
import com.hamburgueriauni.relatorio.Visitante;
import com.hamburgueriauni.relatorio.Visitavel;

import java.util.ArrayList;
import java.util.List;

// Padrão Composite: Leaf — item simples e indivisível do cardápio
// Padrão Prototype: implementa clonar() para criar cópias personalizadas
public class CardapioFolha implements ItemCardapio, Visitavel {

    private String nome;
    private double preco;
    private String descricao;
    private final List<Ingrediente> ingredientes;

    public CardapioFolha(String nome, double preco, String descricao) {
        this.nome        = nome;
        this.preco       = preco;
        this.descricao   = descricao;
        this.ingredientes = new ArrayList<>();
    }

    // Construtor de cópia usado pelo Prototype
    private CardapioFolha(CardapioFolha origem) {
        this.nome        = origem.nome;
        this.preco       = origem.preco;
        this.descricao   = origem.descricao;
        this.ingredientes = new ArrayList<>(origem.ingredientes);
    }

    // Padrão Prototype: retorna uma cópia independente deste item
    public CardapioFolha clonar() {
        System.out.println("[Prototype] Clonando item: " + nome);
        return new CardapioFolha(this);
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
        this.preco += ingrediente.getPreco();
    }

    @Override public String getNome()      { return nome; }
    @Override public double getPreco()     { return preco; }
    @Override public String getDescricao() { return descricao; }

    public void setNome(String nome)           { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public List<Ingrediente> getIngredientes() { return ingredientes; }

    @Override
    public void exibir(String indent) {
        System.out.printf("%s- %-25s R$%.2f  |  %s%n", indent, nome, preco, descricao);
    }

    @Override
    public void aceitar(Visitante visitante) {
        visitante.visitar(this);
    }
}
