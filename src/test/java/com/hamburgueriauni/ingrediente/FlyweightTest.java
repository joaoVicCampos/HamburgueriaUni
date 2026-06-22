package com.hamburgueriauni.ingrediente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa o padrão Flyweight
class FlyweightTest {

    @Test
    void mesmaSolicitacaoDeveRetornarMesmaInstancia() {
        Ingrediente i1 = RepositorioIngredientes.obter("Alface Teste FW", TipoIngrediente.VEGETAL, 0.50, true);
        Ingrediente i2 = RepositorioIngredientes.obter("Alface Teste FW", TipoIngrediente.VEGETAL, 0.50, true);
        assertSame(i1, i2);
    }

    @Test
    void ingredientesDiferentesDevemSerInstanciasDiferentes() {
        Ingrediente pao    = RepositorioIngredientes.obter("Pao FW Unique1", TipoIngrediente.PAO,    1.00, false);
        Ingrediente carne  = RepositorioIngredientes.obter("Carne FW Unique2", TipoIngrediente.PROTEINA, 5.00, false);
        assertNotSame(pao, carne);
    }

    @Test
    void poolDeveAumentarApenasComNovosIngredientes() {
        int antes = RepositorioIngredientes.totalNoPool();
        RepositorioIngredientes.obter("Ingrediente Pool A", TipoIngrediente.ADICIONAL, 1.00, false);
        RepositorioIngredientes.obter("Ingrediente Pool A", TipoIngrediente.ADICIONAL, 1.00, false); // duplicata
        RepositorioIngredientes.obter("Ingrediente Pool A", TipoIngrediente.ADICIONAL, 1.00, false); // duplicata
        assertEquals(antes + 1, RepositorioIngredientes.totalNoPool());
    }

    @Test
    void ingredienteDeveTerPropriedadesCorretas() {
        Ingrediente i = RepositorioIngredientes.obter("Tomate FW", TipoIngrediente.VEGETAL, 0.80, true);
        assertEquals("Tomate FW", i.getNome());
        assertEquals(TipoIngrediente.VEGETAL, i.getTipo());
        assertEquals(0.80, i.getPreco(), 0.001);
        assertTrue(i.isVegano());
    }
}
