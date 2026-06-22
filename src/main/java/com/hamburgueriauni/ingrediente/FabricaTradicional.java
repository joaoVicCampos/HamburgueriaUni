package com.hamburgueriauni.ingrediente;

// Fábrica concreta: família Tradicional
public class FabricaTradicional implements FabricaDeIngredientes {

    @Override
    public Ingrediente criarPao() {
        return new IngredienteImpl("Pão Brioche", TipoIngrediente.PAO, 2.00, false);
    }

    @Override
    public Ingrediente criarProteina() {
        return new IngredienteImpl("Blend Bovino 150g", TipoIngrediente.PROTEINA, 12.00, false);
    }

    @Override
    public Ingrediente criarQueijo() {
        return new IngredienteImpl("Queijo Cheddar", TipoIngrediente.QUEIJO, 3.00, false);
    }

    @Override
    public Ingrediente criarMolho() {
        return new IngredienteImpl("Molho da Casa", TipoIngrediente.MOLHO, 1.00, true);
    }

    @Override
    public CategoriaCardapio getCategoria() {
        return CategoriaCardapio.TRADICIONAL;
    }
}
