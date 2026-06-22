package com.hamburgueriauni.cardapio;

// Interface base para Composite e Decorator
public interface ItemCardapio {
    String getNome();
    double getPreco();
    String getDescricao();
    void exibir(String indent);
}
