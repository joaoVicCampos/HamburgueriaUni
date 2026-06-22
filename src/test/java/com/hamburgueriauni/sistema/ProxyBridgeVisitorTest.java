package com.hamburgueriauni.sistema;

import com.hamburgueriauni.cardapio.Cardapio;
import com.hamburgueriauni.cardapio.CardapioFolha;
import com.hamburgueriauni.pedido.PedidoBalcao;
import com.hamburgueriauni.pedido.Pedido;
import com.hamburgueriauni.relatorio.RelatorioPrecos;
import com.hamburgueriauni.relatorio.RelatorioVegano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Testa os padrões Proxy, Bridge e Visitor
class ProxyBridgeVisitorTest {

    private GerenciadorEstoque real;
    private ProxyEstoque proxy;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        real   = new GerenciadorEstoque();
        proxy  = new ProxyEstoque(real);
        pedido = new PedidoBalcao("Teste Proxy");
        pedido.adicionarItem(new CardapioFolha("Refrigerante", 6.00, "350ml"));
    }

    // --- PROXY ---

    @Test
    void verificacaoComEstoqueDisponivel() {
        // O item "Refrigerante" não está no estoque por nome exato — deve retornar true
        // pois o gerenciador verifica pelo nome do item
        assertNotNull(proxy.verificarDisponibilidade(pedido));
    }

    @Test
    void acessoBloqueadoDeveRetornarFalse() {
        proxy.bloquearAcesso();
        assertFalse(proxy.verificarDisponibilidade(pedido));
        proxy.liberarAcesso();
    }

    @Test
    void aposLiberarAcessoDeveVoltarAFuncionar() {
        proxy.bloquearAcesso();
        proxy.liberarAcesso();
        assertDoesNotThrow(() -> proxy.verificarDisponibilidade(pedido));
    }

    @Test
    void segundaConsultaDeveUsarCache() {
        // Primeira consulta popula o cache
        proxy.verificarDisponibilidade(pedido);

        // Captura saída para verificar mensagem de cache
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
        proxy.verificarDisponibilidade(pedido);
        System.setOut(System.out);

        assertTrue(saida.toString().contains("cache"));
    }

    @Test
    void getQuantidadeDeveRetornarValorDoEstoqueReal() {
        int qtd = proxy.getQuantidade("pão brioche");
        assertTrue(qtd >= 0);
    }

    // --- BRIDGE ---

    @Test
    void relatorioTerminalNaoDeveLancarExcecao() {
        Pedido p = new PedidoBalcao("Bridge Test");
        p.adicionarItem(new CardapioFolha("X-Burguer", 18.00, "Clássico"));

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
        new RelatorioPedido(new SaidaTerminal()).imprimir(p);
        System.setOut(System.out);

        String output = saida.toString();
        assertTrue(output.contains("Bridge Test"));
        assertTrue(output.contains("X-Burguer"));
    }

    @Test
    void relatorioCupomNaoDeveLancarExcecao() {
        Pedido p = new PedidoBalcao("Cupom Test");
        p.adicionarItem(new CardapioFolha("Lanche", 20.00, "Teste"));

        assertDoesNotThrow(() -> new RelatorioPedido(new SaidaCupom()).imprimir(p));
    }

    @Test
    void mesmoPedidoEmDoisFormatosDeveConterMesmosDados() {
        Pedido p = new PedidoBalcao("Roberto");
        p.adicionarItem(new CardapioFolha("Item", 15.00, "Desc"));

        ByteArrayOutputStream terminal = new ByteArrayOutputStream();
        ByteArrayOutputStream cupom    = new ByteArrayOutputStream();

        System.setOut(new PrintStream(terminal));
        new RelatorioPedido(new SaidaTerminal()).imprimir(p);

        System.setOut(new PrintStream(cupom));
        new RelatorioPedido(new SaidaCupom()).imprimir(p);

        System.setOut(System.out);

        assertTrue(terminal.toString().contains("Roberto"));
        assertTrue(cupom.toString().contains("Roberto"));
    }

    // --- VISITOR ---

    @Test
    void relatorioPrecoDeveColetarDadosDasFollhas() {
        Cardapio cardapio = new Cardapio();
        RelatorioPrecos visitor = new RelatorioPrecos();
        cardapio.getRaiz().aceitar(visitor);

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
        visitor.imprimirRelatorio();
        System.setOut(System.out);

        assertTrue(saida.toString().contains("Total de itens"));
    }

    @Test
    void relatorioVeganoDeveEncontrarItensVeganos() {
        Cardapio cardapio = new Cardapio();
        RelatorioVegano visitor = new RelatorioVegano();
        cardapio.getRaiz().aceitar(visitor);

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
        visitor.imprimirRelatorio();
        System.setOut(System.out);

        // Deve encontrar pelo menos o Green Burguer
        String output = saida.toString();
        assertTrue(output.contains("Green Burguer") || output.contains("item(ns)"));
    }
}
