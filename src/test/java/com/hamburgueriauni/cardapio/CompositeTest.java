package com.hamburgueriauni.cardapio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa o padrão Composite
class CompositeTest {

    private CardapioCategoria categoria;
    private CardapioFolha folha1;
    private CardapioFolha folha2;

    @BeforeEach
    void setUp() {
        categoria = new CardapioCategoria("Lanches", "Categoria teste");
        folha1    = new CardapioFolha("X-Burguer", 18.00, "Clássico");
        folha2    = new CardapioFolha("Refrigerante", 6.00, "350ml");
    }

    @Test
    void precoDaCategoriaDeveSerSomaDosFilhos() {
        categoria.adicionar(folha1);
        categoria.adicionar(folha2);
        assertEquals(24.00, categoria.getPreco(), 0.001);
    }

    @Test
    void removerItemDeveAtualizarPreco() {
        categoria.adicionar(folha1);
        categoria.adicionar(folha2);
        categoria.remover(folha1);
        assertEquals(6.00, categoria.getPreco(), 0.001);
    }

    @Test
    void categoriaSemFilhosDeveTerPrecoZero() {
        assertEquals(0.0, categoria.getPreco(), 0.001);
    }

    @Test
    void devePermitirCategoriaAninhada() {
        CardapioCategoria sub = new CardapioCategoria("Sub", "Subcategoria");
        sub.adicionar(folha1);
        categoria.adicionar(sub);
        categoria.adicionar(folha2);

        assertEquals(24.00, categoria.getPreco(), 0.001);
        assertEquals(2, categoria.getFilhos().size());
    }

    @Test
    void folhaDeveRetornarPrecoEDescricaoCorretos() {
        assertEquals("X-Burguer", folha1.getNome());
        assertEquals(18.00, folha1.getPreco(), 0.001);
        assertEquals("Clássico", folha1.getDescricao());
    }
}
