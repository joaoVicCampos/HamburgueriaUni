package com.hamburgueriauni.pagamento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa os padrões Adapter (pagamento) e Strategy (desconto)
class PagamentoStrategyTest {

    // --- ADAPTER ---

    @Test
    void adaptadorPixDeveAprovarPagamento() {
        MetodoPagamento pix = new AdaptadorPix(new GatewayPix());
        assertTrue(pix.processarPagamento(50.00));
        assertEquals("PIX", pix.getNomeMetodo());
    }

    @Test
    void adaptadorCartaoDeveAprovarPagamento() {
        MetodoPagamento cartao = new AdaptadorCartao(new GatewayCartao(), 1);
        assertTrue(cartao.processarPagamento(100.00));
        assertTrue(cartao.getNomeMetodo().contains("Cartão"));
    }

    @Test
    void adaptadorDinheiroComValorSuficienteDeveAprovar() {
        MetodoPagamento dinheiro = new AdaptadorDinheiro(new GatewayDinheiro(), 100.00);
        assertTrue(dinheiro.processarPagamento(80.00));
        assertEquals("Dinheiro", dinheiro.getNomeMetodo());
    }

    @Test
    void adaptadorDinheiroComValorInsuficienteDeveRecusar() {
        MetodoPagamento dinheiro = new AdaptadorDinheiro(new GatewayDinheiro(), 20.00);
        assertFalse(dinheiro.processarPagamento(50.00));
    }

    @Test
    void cartaoComParcelasDeveIndicarNumeroDeParcelas() {
        MetodoPagamento cartao3x = new AdaptadorCartao(new GatewayCartao(), 3);
        assertTrue(cartao3x.getNomeMetodo().contains("3"));
    }

    // --- STRATEGY ---

    @Test
    void semDescontoDeveRetornarValorOriginal() {
        EstrategiaDesconto s = new SemDesconto();
        assertEquals(100.00, s.aplicar(100.00), 0.001);
    }

    @Test
    void descontoFidelidadeDeveReduzirValor() {
        EstrategiaDesconto s = new DescontoFidelidade();
        double resultado = s.aplicar(100.00);
        assertTrue(resultado < 100.00);
    }

    @Test
    void descontoProgressivoComUmItemNaoDeveAplicar() {
        EstrategiaDesconto s = new DescontoProgressivo(1);
        assertEquals(100.00, s.aplicar(100.00), 0.001);
    }

    @Test
    void descontoProgressivoCom2ItensDeveAplicar5Porcento() {
        EstrategiaDesconto s = new DescontoProgressivo(2);
        assertEquals(95.00, s.aplicar(100.00), 0.001);
    }

    @Test
    void descontoProgressivoCom3ItensDeveAplicar10Porcento() {
        EstrategiaDesconto s = new DescontoProgressivo(3);
        assertEquals(90.00, s.aplicar(100.00), 0.001);
    }

    @Test
    void descontoProgressivoCom5ItensDeveAplicar20Porcento() {
        EstrategiaDesconto s = new DescontoProgressivo(5);
        assertEquals(80.00, s.aplicar(100.00), 0.001);
    }

    @Test
    void estrategiasDevemTerDescricaoNaoVazia() {
        for (EstrategiaDesconto s : new EstrategiaDesconto[]{
                new SemDesconto(), new DescontoFidelidade(),
                new DescontoProgressivo(3), new DescontoHorarioPico()}) {
            assertNotNull(s.descricao());
            assertFalse(s.descricao().isBlank());
        }
    }
}
