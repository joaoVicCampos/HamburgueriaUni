package com.hamburgueriauni.ingrediente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa o padrão Abstract Factory
class FabricaIngredientesTest {

    @Test
    void fabricaTradicionalDeveCriarIngredientesNaoVeganos() {
        FabricaDeIngredientes fabrica = new FabricaTradicional();
        assertFalse(fabrica.criarProteina().isVegano());
        assertFalse(fabrica.criarQueijo().isVegano());
        assertEquals(CategoriaCardapio.TRADICIONAL, fabrica.getCategoria());
    }

    @Test
    void fabricaVeganaDeveCriarTodosIngredientesVeganos() {
        FabricaDeIngredientes fabrica = new FabricaVegana();
        assertTrue(fabrica.criarPao().isVegano());
        assertTrue(fabrica.criarProteina().isVegano());
        assertTrue(fabrica.criarQueijo().isVegano());
        assertTrue(fabrica.criarMolho().isVegano());
        assertEquals(CategoriaCardapio.VEGANO, fabrica.getCategoria());
    }

    @Test
    void fabricaGourmetDeveCriarIngredientesComPrecoMaisAlto() {
        FabricaDeIngredientes tradicional = new FabricaTradicional();
        FabricaDeIngredientes gourmet     = new FabricaGourmet();

        assertTrue(gourmet.criarProteina().getPreco() > tradicional.criarProteina().getPreco());
        assertTrue(gourmet.criarQueijo().getPreco()   > tradicional.criarQueijo().getPreco());
        assertEquals(CategoriaCardapio.GOURMET, gourmet.getCategoria());
    }

    @Test
    void cadaFabricaDeveCriarOsQuatroTiposDeIngrediente() {
        for (FabricaDeIngredientes f : new FabricaDeIngredientes[]{
                new FabricaTradicional(), new FabricaVegana(), new FabricaGourmet()}) {
            assertNotNull(f.criarPao());
            assertNotNull(f.criarProteina());
            assertNotNull(f.criarQueijo());
            assertNotNull(f.criarMolho());
        }
    }
}
