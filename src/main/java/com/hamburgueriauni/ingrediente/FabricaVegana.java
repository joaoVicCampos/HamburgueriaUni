package com.hamburgueriauni.ingrediente;

// Fábrica concreta: família Vegana
public class FabricaVegana implements FabricaDeIngredientes {

    @Override
    public Ingrediente criarPao() {
        return new IngredienteImpl("Pão Integral Vegano", TipoIngrediente.PAO, 3.00, true);
    }

    @Override
    public Ingrediente criarProteina() {
        return new IngredienteImpl("Burger de Grão-de-Bico", TipoIngrediente.PROTEINA, 11.00, true);
    }

    @Override
    public Ingrediente criarQueijo() {
        return new IngredienteImpl("Queijo Vegano de Castanha", TipoIngrediente.QUEIJO, 4.00, true);
    }

    @Override
    public Ingrediente criarMolho() {
        return new IngredienteImpl("Maionese Vegana", TipoIngrediente.MOLHO, 1.50, true);
    }

    @Override
    public CategoriaCardapio getCategoria() {
        return CategoriaCardapio.VEGANO;
    }
}
