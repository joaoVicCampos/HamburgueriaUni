package com.hamburgueriauni.ingrediente;

// Implementação concreta de Ingrediente, usada pelo Flyweight e pelas Abstract Factories
public class IngredienteImpl implements Ingrediente {

    private final String nome;
    private final TipoIngrediente tipo;
    private final double preco;
    private final boolean vegano;

    public IngredienteImpl(String nome, TipoIngrediente tipo, double preco, boolean vegano) {
        this.nome   = nome;
        this.tipo   = tipo;
        this.preco  = preco;
        this.vegano = vegano;
    }

    @Override public String getNome()            { return nome; }
    @Override public TipoIngrediente getTipo()   { return tipo; }
    @Override public double getPreco()           { return preco; }
    @Override public boolean isVegano()          { return vegano; }

    @Override
    public String toString() {
        return String.format("%s (R$%.2f)%s", nome, preco, vegano ? " [vegano]" : "");
    }
}
