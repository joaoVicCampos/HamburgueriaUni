package com.hamburgueriauni.lanche;

import com.hamburgueriauni.ingrediente.Ingrediente;

import java.util.ArrayList;
import java.util.List;

// Padrão Builder: construtor concreto — acumula os componentes e gera o Lanche final
public class LancheBuilderImpl implements LancheBuilder {

    String nome        = "Lanche Personalizado";
    Ingrediente pao;
    Ingrediente proteina;
    Ingrediente queijo;
    Ingrediente molho;
    List<Ingrediente> extras = new ArrayList<>();
    String observacoes;

    @Override
    public LancheBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public LancheBuilder comPao(Ingrediente pao) {
        this.pao = pao;
        return this;
    }

    @Override
    public LancheBuilder comProteina(Ingrediente proteina) {
        this.proteina = proteina;
        return this;
    }

    @Override
    public LancheBuilder comQueijo(Ingrediente queijo) {
        this.queijo = queijo;
        return this;
    }

    @Override
    public LancheBuilder comMolho(Ingrediente molho) {
        this.molho = molho;
        return this;
    }

    @Override
    public LancheBuilder comExtra(Ingrediente extra) {
        this.extras.add(extra);
        return this;
    }

    @Override
    public LancheBuilder comObservacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }

    @Override
    public Lanche construir() {
        if (pao == null || proteina == null) {
            throw new IllegalStateException("Lanche precisa de pão e proteína.");
        }
        System.out.println("[Builder] Lanche '" + nome + "' construído com sucesso.");
        return new Lanche(this);
    }
}
