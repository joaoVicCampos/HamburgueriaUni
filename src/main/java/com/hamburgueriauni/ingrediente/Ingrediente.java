package com.hamburgueriauni.ingrediente;

// Interface base para Flyweight e Abstract Factory
public interface Ingrediente {
    String getNome();
    TipoIngrediente getTipo();
    double getPreco();
    boolean isVegano();
    String toString();
}
