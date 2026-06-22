package com.hamburgueriauni.relatorio;

import com.hamburgueriauni.cardapio.ItemCardapio;

// Interface do padrão Visitor (visitante)
public interface Visitante {
    void visitar(ItemCardapio item);
}
