package com.hamburgueriauni.pedido;

import com.hamburgueriauni.cardapio.CardapioFolha;
import com.hamburgueriauni.pagamento.AdaptadorPix;
import com.hamburgueriauni.pagamento.GatewayPix;
import com.hamburgueriauni.pagamento.MetodoPagamento;
import com.hamburgueriauni.sistema.GerenciadorEstoque;
import com.hamburgueriauni.sistema.ProxyEstoque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa o padrão Chain of Responsibility
class ChainOfResponsibilityTest {

    private ProxyEstoque estoque;
    private MetodoPagamento pagamento;

    @BeforeEach
    void setUp() {
        estoque   = new ProxyEstoque(new GerenciadorEstoque());
        pagamento = new AdaptadorPix(new GatewayPix());
    }

    private ManipuladorPedido montarCadeia() {
        ManipuladorPedido cadeia = new ValidadorItens();
        cadeia.setProximo(new ValidadorEstoque(estoque))
              .setProximo(new ValidadorPagamento(pagamento))
              .setProximo(new ProcessadorCozinha());
        return cadeia;
    }

    @Test
    void pedidoValidoDevePassarPorTodaACadeia() {
        Pedido pedido = new PedidoBalcao("Teste Chain OK");
        pedido.adicionarObservador((p, a, n) -> {});
        pedido.adicionarItem(new CardapioFolha("Refrigerante", 6.00, "350ml"));

        boolean resultado = montarCadeia().processar(pedido);
        assertTrue(resultado);
        assertEquals(StatusPedido.PREPARANDO, pedido.getStatus());
    }

    @Test
    void pedidoSemItensDeveSerBloqueadoNoPrimeiroElo() {
        Pedido vazio = new PedidoBalcao("Teste Vazio");
        boolean resultado = new ValidadorItens().processar(vazio);
        assertFalse(resultado);
        assertEquals(StatusPedido.NOVO, vazio.getStatus());
    }

    @Test
    void validadorItensComItemDevePassarParaProximo() {
        Pedido pedido = new PedidoBalcao("Teste Itens");
        pedido.adicionarObservador((p, a, n) -> {});
        pedido.adicionarItem(new CardapioFolha("Item Teste", 5.00, "Desc"));

        // Encadeia apenas ValidadorItens + ProcessadorCozinha (sem estoque/pagamento)
        ManipuladorPedido cadeia = new ValidadorItens();
        cadeia.setProximo(new ValidadorPagamento(pagamento))
              .setProximo(new ProcessadorCozinha());

        boolean resultado = cadeia.processar(pedido);
        assertTrue(resultado);
    }

    @Test
    void estoqueComAcessoBloqueadoDeveInterromperCadeia() {
        estoque.bloquearAcesso();

        Pedido pedido = new PedidoBalcao("Teste Estoque Bloqueado");
        pedido.adicionarItem(new CardapioFolha("Item", 5.00, "Desc"));

        ManipuladorPedido cadeia = new ValidadorItens();
        cadeia.setProximo(new ValidadorEstoque(estoque));

        boolean resultado = cadeia.processar(pedido);
        assertFalse(resultado);

        estoque.liberarAcesso();
    }
}
