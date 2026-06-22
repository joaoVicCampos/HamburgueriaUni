package com.hamburgueriauni.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa o padrão Singleton
class ConfiguracaoRestauranteTest {

    @Test
    void deveRetornarSempreAMesmaInstancia() {
        ConfiguracaoRestaurante c1 = ConfiguracaoRestaurante.getInstancia();
        ConfiguracaoRestaurante c2 = ConfiguracaoRestaurante.getInstancia();
        assertSame(c1, c2);
    }

    @Test
    void deveConterNomeDoRestaurante() {
        assertEquals("HamburgueriaUni", ConfiguracaoRestaurante.getInstancia().getNomeRestaurante());
    }

    @Test
    void alteracaoEmUmaReferenciaDeveRefletirEmOutra() {
        ConfiguracaoRestaurante c1 = ConfiguracaoRestaurante.getInstancia();
        ConfiguracaoRestaurante c2 = ConfiguracaoRestaurante.getInstancia();

        double taxaOriginal = c1.getTaxaEntregaDelivery();
        c1.setTaxaEntregaDelivery(20.00);
        assertEquals(20.00, c2.getTaxaEntregaDelivery());

        c1.setTaxaEntregaDelivery(taxaOriginal); // restaura
    }

    @Test
    void descontoFidelidadeDeveEstarEntre0e1() {
        double desconto = ConfiguracaoRestaurante.getInstancia().getDescontoFidelidade();
        assertTrue(desconto > 0 && desconto < 1);
    }
}
