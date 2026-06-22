package com.hamburgueriauni.pedido;

import com.hamburgueriauni.cardapio.CardapioFolha;
import com.hamburgueriauni.cardapio.ItemCardapio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa os padrões Command e Memento
class CommandMementoTest {

    private Pedido pedido;
    private GerenciadorComandos gerenciador;
    private HistoricoPedido historico;
    private ItemCardapio xBurguer;
    private ItemCardapio refrigerante;

    @BeforeEach
    void setUp() {
        pedido       = new PedidoBalcao("Teste Command");
        gerenciador  = new GerenciadorComandos();
        historico    = new HistoricoPedido();
        xBurguer     = new CardapioFolha("X-Burguer", 18.00, "Clássico");
        refrigerante = new CardapioFolha("Refrigerante", 6.00, "350ml");
    }

    // --- COMMAND ---

    @Test
    void adicionarItemDeveAumentarTotal() {
        gerenciador.executar(new ComandoAdicionarItem(pedido, xBurguer));
        assertEquals(18.00, pedido.calcularTotal(), 0.001);
    }

    @Test
    void desfazerAdicionarDeveRemoverItem() {
        gerenciador.executar(new ComandoAdicionarItem(pedido, xBurguer));
        gerenciador.desfazer();
        assertEquals(0.0, pedido.calcularTotal(), 0.001);
        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void removerItemDeveReduzirTotal() {
        pedido.adicionarItem(xBurguer);
        pedido.adicionarItem(refrigerante);
        gerenciador.executar(new ComandoRemoverItem(pedido, refrigerante));
        assertEquals(18.00, pedido.calcularTotal(), 0.001);
    }

    @Test
    void desfazerRemoverDeveRestaurarItem() {
        pedido.adicionarItem(xBurguer);
        pedido.adicionarItem(refrigerante);
        gerenciador.executar(new ComandoRemoverItem(pedido, refrigerante));
        gerenciador.desfazer();
        assertEquals(24.00, pedido.calcularTotal(), 0.001);
    }

    @Test
    void desfazerSemHistoricoNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> gerenciador.desfazer());
    }

    @Test
    void multiplosUndosDevemDesfazerEmOrdemInversa() {
        gerenciador.executar(new ComandoAdicionarItem(pedido, xBurguer));
        gerenciador.executar(new ComandoAdicionarItem(pedido, refrigerante));
        assertEquals(24.00, pedido.calcularTotal(), 0.001);

        gerenciador.desfazer(); // remove refrigerante
        assertEquals(18.00, pedido.calcularTotal(), 0.001);

        gerenciador.desfazer(); // remove xBurguer
        assertEquals(0.0, pedido.calcularTotal(), 0.001);
    }

    // --- MEMENTO ---

    @Test
    void criarMementoDeveCapturarEstadoAtual() {
        pedido.adicionarItem(xBurguer);
        pedido.adicionarItem(refrigerante);
        PedidoMemento memento = pedido.criarMemento();

        assertNotNull(memento);
        assertEquals(StatusPedido.NOVO, memento.getEstado().getStatus());
        assertEquals(2, memento.getItens().size());
    }

    @Test
    void restaurarMementoDeveVoltarEstadoAnterior() {
        pedido.adicionarItem(xBurguer);
        PedidoMemento memento = pedido.criarMemento();

        pedido.adicionarItem(refrigerante);
        assertEquals(24.00, pedido.calcularTotal(), 0.001);

        pedido.restaurarMemento(memento);
        assertEquals(18.00, pedido.calcularTotal(), 0.001);
        assertEquals(1, pedido.getItens().size());
    }

    @Test
    void comandoCancelarComMementoDevePermitirRestauracao() {
        pedido.adicionarItem(xBurguer);
        pedido.pagar();

        gerenciador.executar(new ComandoCancelarPedido(pedido, historico));
        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());

        gerenciador.desfazer();
        assertEquals(StatusPedido.PAGO, pedido.getStatus());
        assertEquals(1, pedido.getItens().size());
    }

    @Test
    void historicoDeveEmpilharMementos() {
        historico.salvar(pedido.criarMemento());
        historico.salvar(pedido.criarMemento());
        assertEquals(2, historico.tamanho());
    }
}
