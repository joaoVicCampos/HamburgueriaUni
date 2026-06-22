package com.hamburgueriauni.ingrediente;

// Fábrica concreta: família Gourmet
public class FabricaGourmet implements FabricaDeIngredientes {

    @Override
    public Ingrediente criarPao() {
        return new IngredienteImpl("Pão Australiano", TipoIngrediente.PAO, 4.00, false);
    }

    @Override
    public Ingrediente criarProteina() {
        return new IngredienteImpl("Blend Wagyu 180g", TipoIngrediente.PROTEINA, 22.00, false);
    }

    @Override
    public Ingrediente criarQueijo() {
        return new IngredienteImpl("Brie Francês", TipoIngrediente.QUEIJO, 6.00, false);
    }

    @Override
    public Ingrediente criarMolho() {
        return new IngredienteImpl("Aioli de Trufas", TipoIngrediente.MOLHO, 3.50, false);
    }

    @Override
    public CategoriaCardapio getCategoria() {
        return CategoriaCardapio.GOURMET;
    }
}
