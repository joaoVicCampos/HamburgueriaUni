package com.hamburgueriauni.lanche;

import com.hamburgueriauni.ingrediente.Ingrediente;

// Padrão Builder: define a interface de construção passo a passo de um Lanche
public interface LancheBuilder {
    LancheBuilder comNome(String nome);
    LancheBuilder comPao(Ingrediente pao);
    LancheBuilder comProteina(Ingrediente proteina);
    LancheBuilder comQueijo(Ingrediente queijo);
    LancheBuilder comMolho(Ingrediente molho);
    LancheBuilder comExtra(Ingrediente extra);
    LancheBuilder comObservacoes(String observacoes);
    Lanche construir();
}
