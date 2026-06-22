package com.hamburgueriauni.cardapio;

import com.hamburgueriauni.ingrediente.RepositorioIngredientes;
import com.hamburgueriauni.ingrediente.TipoIngrediente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa os padrões Prototype, Decorator e Iterator no contexto do cardápio
class PrototypeDecoratorIteratorTest {

    private CardapioFolha original;

    @BeforeEach
    void setUp() {
        original = new CardapioFolha("X-Test", 18.00, "Descricao base");
    }

    // --- PROTOTYPE ---

    @Test
    void cloneDeveSerObjetoDiferente() {
        CardapioFolha clone = original.clonar();
        assertNotSame(original, clone);
    }

    @Test
    void cloneDeveIniciarComMesmosValores() {
        CardapioFolha clone = original.clonar();
        assertEquals(original.getNome(),      clone.getNome());
        assertEquals(original.getPreco(),     clone.getPreco(), 0.001);
        assertEquals(original.getDescricao(), clone.getDescricao());
    }

    @Test
    void alterarCloneNaoDeveAfetarOriginal() {
        CardapioFolha clone = original.clonar();
        clone.setNome("Clone Modificado");
        clone.adicionarIngrediente(
            RepositorioIngredientes.obter("Extra Teste Proto", TipoIngrediente.ADICIONAL, 5.00, false));

        assertEquals("X-Test", original.getNome());
        assertEquals(18.00, original.getPreco(), 0.001);
    }

    // --- DECORATOR ---

    @Test
    void decoradorBaconDeveAcrescentar4Reais() {
        ItemCardapio comBacon = new DecoradorBacon(original);
        assertEquals(22.00, comBacon.getPreco(), 0.001);
        assertTrue(comBacon.getDescricao().contains("Bacon"));
    }

    @Test
    void decoradoresEmpilhadosDevemAcumularPreco() {
        ItemCardapio item = new DecoradorBacon(
                new DecoradorQueijoExtra(
                        new DecoradorMolhoEspecial(original)));

        assertEquals(18.00 + 4.00 + 3.00 + 2.00, item.getPreco(), 0.001);
    }

    @Test
    void decoradorNaoDeveAlterarNomeBase() {
        ItemCardapio comBacon = new DecoradorBacon(original);
        assertEquals("X-Test", comBacon.getNome());
    }

    // --- ITERATOR ---

    @Test
    void iteradorDevePercorrerTodasAsFolhas() {
        Cardapio cardapio = new Cardapio();
        IteradorCardapio it = new IteradorCardapio(cardapio.getRaiz());

        int contagem = 0;
        while (it.hasNext()) {
            assertNotNull(it.next());
            contagem++;
        }
        assertTrue(contagem > 0);
        assertEquals(it.totalItens(), contagem);
    }

    @Test
    void iteradorDevePermitirReinicio() {
        Cardapio cardapio = new Cardapio();
        IteradorCardapio it = new IteradorCardapio(cardapio.getRaiz());

        CardapioFolha primeiro = it.next();
        it.reiniciar();
        assertEquals(primeiro.getNome(), it.next().getNome());
    }

    @Test
    void iteradorSemItensSeguintesNaoDeveTerProximo() {
        CardapioCategoria vazia = new CardapioCategoria("Vazia", "Sem itens");
        IteradorCardapio it = new IteradorCardapio(vazia);
        assertFalse(it.hasNext());
    }
}
