package com.hamburgueriauni.sistema;

import com.hamburgueriauni.cardapio.CardapioFolha;
import com.hamburgueriauni.cozinha.Atendente;
import com.hamburgueriauni.cozinha.CentralCozinha;
import com.hamburgueriauni.cozinha.ClienteColaborador;
import com.hamburgueriauni.cozinha.Cozinheiro;
import com.hamburgueriauni.pagamento.AdaptadorPix;
import com.hamburgueriauni.pagamento.GatewayPix;
import com.hamburgueriauni.pedido.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Testa os padrões Facade e Mediator
class FacadeMediatorTest {

    private SistemaRestaurante sistema;

    @BeforeEach
    void setUp() {
        sistema = new SistemaRestaurante();
    }

    // --- FACADE ---

    @Test
    void abrirPedidoBalcaoDeveCriarPedidoComTipoCorreto() {
        Pedido pedido = sistema.abrirPedidoBalcao("Ana");
        assertEquals(TipoPedido.BALCAO, pedido.getTipo());
        assertEquals("Ana", pedido.getCliente());
        assertEquals(StatusPedido.NOVO, pedido.getStatus());
    }

    @Test
    void abrirPedidoMesaDeveConterNumeroMesa() {
        Pedido pedido = sistema.abrirPedidoMesa("Bob", 5);
        assertEquals(TipoPedido.MESA, pedido.getTipo());
        assertTrue(pedido instanceof PedidoMesa);
        assertEquals(5, ((PedidoMesa) pedido).getNumeroMesa());
    }

    @Test
    void abrirPedidoDeliveryDeveConterEndereco() {
        Pedido pedido = sistema.abrirPedidoDelivery("Carol", "Rua X, 10");
        assertEquals(TipoPedido.DELIVERY, pedido.getTipo());
        assertTrue(pedido instanceof PedidoDelivery);
        assertEquals("Rua X, 10", ((PedidoDelivery) pedido).getEnderecoEntrega());
    }

    @Test
    void adicionarItemViaFacadeDeveReflectirNoTotal() {
        Pedido pedido = sistema.abrirPedidoBalcao("Davi");
        sistema.adicionarItem(pedido, new CardapioFolha("Lanche", 20.00, "Teste"));
        assertEquals(20.00, pedido.calcularTotal(), 0.001);
    }

    @Test
    void desfazerViaFacadeDeveRemoverUltimoItem() {
        Pedido pedido = sistema.abrirPedidoBalcao("Eva");
        sistema.adicionarItem(pedido, new CardapioFolha("Item", 15.00, "Teste"));
        sistema.desfazerUltimaAcao();
        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void processarPedidoValidoDeveRetornarTrue() {
        Pedido pedido = sistema.abrirPedidoBalcao("Felipe");
        sistema.adicionarItem(pedido, new CardapioFolha("Refrigerante", 6.00, "350ml"));

        boolean ok = sistema.processarPedido(pedido, new AdaptadorPix(new GatewayPix()));
        assertTrue(ok);
        assertEquals(StatusPedido.PREPARANDO, pedido.getStatus());
    }

    @Test
    void processarPedidoVazioDeveRetornarFalse() {
        Pedido pedido = sistema.abrirPedidoBalcao("Gabi");
        boolean ok = sistema.processarPedido(pedido, new AdaptadorPix(new GatewayPix()));
        assertFalse(ok);
    }

    @Test
    void fluxoCompletoDeveChegar_aoEstadoEntregue() {
        Pedido pedido = sistema.abrirPedidoBalcao("Henrique");
        sistema.adicionarItem(pedido, new CardapioFolha("Lanche Teste", 15.00, "Desc"));

        sistema.processarPedido(pedido, new AdaptadorPix(new GatewayPix()));
        sistema.finalizarPreparoCozinha(pedido);
        sistema.entregarPedido(pedido);

        assertEquals(StatusPedido.ENTREGUE, pedido.getStatus());
    }

    @Test
    void cancelarViaFacadeDeveTransicionarParaCancelado() {
        Pedido pedido = sistema.abrirPedidoBalcao("Igor");
        sistema.cancelarPedido(pedido);
        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
    }

    // --- MEDIATOR ---

    @Test
    void registrarColaboradoresNaoCentralDeveAssociarMediator() {
        CentralCozinha central   = new CentralCozinha();
        Atendente atendente      = new Atendente("Ana");
        Cozinheiro cozinheiro    = new Cozinheiro("Carlos");
        ClienteColaborador cli   = new ClienteColaborador("João");

        assertDoesNotThrow(() -> {
            central.registrar(atendente);
            central.registrar(cozinheiro);
            central.registrar(cli);
        });
    }

    @Test
    void abrirPedidoViaAtendenteDeveNotificarCozinheiro() {
        CentralCozinha central = new CentralCozinha();
        Atendente atendente    = new Atendente("Ana");
        Cozinheiro cozinheiro  = new Cozinheiro("Carlos");

        central.registrar(atendente);
        central.registrar(cozinheiro);

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
        atendente.abrirPedido("X-Burguer");
        System.setOut(System.out);

        assertTrue(saida.toString().contains("NOVO_PEDIDO") ||
                   saida.toString().contains("X-Burguer"));
    }

    @Test
    void finalizarPedidoViacozinheiroDeveNotificarAtendente() {
        CentralCozinha central   = new CentralCozinha();
        Atendente atendente      = new Atendente("Ana");
        Cozinheiro cozinheiro    = new Cozinheiro("Carlos");
        ClienteColaborador cli   = new ClienteColaborador("João");

        central.registrar(atendente);
        central.registrar(cozinheiro);
        central.registrar(cli);

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
        cozinheiro.finalizarPedido("Pedido #ABC");
        System.setOut(System.out);

        String output = saida.toString();
        assertTrue(output.contains("PEDIDO_PRONTO") || output.contains("pronto") || output.contains("Pedido #ABC"));
    }
}
