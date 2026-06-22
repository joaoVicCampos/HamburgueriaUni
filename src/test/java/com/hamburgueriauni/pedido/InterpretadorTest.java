package com.hamburgueriauni.pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa o padrão Interpreter
class InterpretadorTest {

    private InterpretadorPedido interpretador;

    @BeforeEach
    void setUp() {
        interpretador = new InterpretadorPedido();
    }

    @Test
    void deveInterpretarRestricaoSem() {
        ContextoPersonalizacao ctx = interpretador.interpretar("sem cebola");
        assertTrue(ctx.getRestricoes().contains("cebola"));
        assertTrue(ctx.getAdicionais().isEmpty());
    }

    @Test
    void deveInterpretarAdicionalCom() {
        ContextoPersonalizacao ctx = interpretador.interpretar("com bacon");
        assertTrue(ctx.getAdicionais().contains("bacon"));
        assertTrue(ctx.getRestricoes().isEmpty());
    }

    @Test
    void deveInterpretarPontoDeCarne() {
        ContextoPersonalizacao ctx = interpretador.interpretar("bem passado");
        assertEquals("bem passado", ctx.getPonto());
    }

    @Test
    void deveInterpretarMultiplasClausulas() {
        ContextoPersonalizacao ctx = interpretador.interpretar("sem cebola, sem tomate, com bacon, bem passado");
        assertEquals(2, ctx.getRestricoes().size());
        assertTrue(ctx.getRestricoes().contains("cebola"));
        assertTrue(ctx.getRestricoes().contains("tomate"));
        assertEquals(1, ctx.getAdicionais().size());
        assertEquals("bem passado", ctx.getPonto());
    }

    @Test
    void clausulaDesconhecidaNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> interpretador.interpretar("qualquer coisa estranha"));
    }

    @Test
    void pontoDefaultDeveSerAoPonto() {
        ContextoPersonalizacao ctx = interpretador.interpretar("sem cebola");
        assertEquals("ao ponto", ctx.getPonto());
    }

    @Test
    void deveInterpretarVariosPotnos() {
        assertEquals("mal passado",  interpretador.interpretar("mal passado").getPonto());
        assertEquals("ao ponto",     interpretador.interpretar("ao ponto").getPonto());
        assertEquals("bem passado",  interpretador.interpretar("bem passado").getPonto());
    }
}
