package com.hamburgueriauni.relatorio;

import com.hamburgueriauni.cardapio.CardapioCategoria;
import com.hamburgueriauni.cardapio.CardapioFolha;

// Padrão Visitor: interface do visitante com um método por tipo concreto visitável
public interface Visitante {
    void visitar(CardapioFolha folha);
    void visitar(CardapioCategoria categoria);
}
