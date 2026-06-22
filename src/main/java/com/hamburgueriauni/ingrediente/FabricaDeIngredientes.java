package com.hamburgueriauni.ingrediente;

// Padrão Abstract Factory: define a interface para criar famílias de ingredientes
public interface FabricaDeIngredientes {
    Ingrediente criarPao();
    Ingrediente criarProteina();
    Ingrediente criarQueijo();
    Ingrediente criarMolho();
    CategoriaCardapio getCategoria();
}
