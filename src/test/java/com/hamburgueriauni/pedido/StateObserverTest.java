package com.hamburgueriauni.pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Testa os padrões State e Observer
class StateObserverTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new PedidoBalcao("João Teste");
    }

    // --- STATE: transições válidas ---

    @Test
    void estadoInicialDeveSerNovo() {
        assertEquals(StatusPedido.NOVO, pedido.getStatus());
    }

    @Test
    void pagarDeveTransicionarParaPago() {
        pedido.pagar();
        assertEquals(StatusPedido.PAGO, pedido.getStatus());
    }

    @Test
    void fluxoCompletoDevePercorrerTodosOsEstados() {
        pedido.pagar();
        assertEquals(StatusPedido.PAGO, pedido.getStatus());

        pedido.iniciarPreparo();
        assertEquals(StatusPedido.PREPARANDO, pedido.getStatus());

        pedido.marcarPronto();
        assertEquals(StatusPedido.PRONTO, pedido.getStatus());

        pedido.entregar();
        assertEquals(StatusPedido.ENTREGUE, pedido.getStatus());
    }

    @Test
    void cancelarPedidoNovoDeveTransicionarParaCancelado() {
        pedido.cancelar();
        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
    }

    // --- STATE: transições inválidas ---

    @Test
    void iniciarPreparoSemPagarDeveLancarExcecao() {
        assertThrows(IllegalStateException.class, () -> pedido.iniciarPreparo());
    }

    @Test
    void entregarSemEstarProntoDeveLancarExcecao() {
        pedido.pagar();
        assertThrows(IllegalStateException.class, () -> pedido.entregar());
    }

    @Test
    void cancelarPedidoEmPreparoDeveLancarExcecao() {
        pedido.pagar();
        pedido.iniciarPreparo();
        assertThrows(IllegalStateException.class, () -> pedido.cancelar());
    }

    @Test
    void pagarPedidoJaPagoDeveLancarExcecao() {
        pedido.pagar();
        assertThrows(IllegalStateException.class, () -> pedido.pagar());
    }

    @Test
    void qualquerAcaoEmEstadoEntregueDeveLancarExcecao() {
        pedido.pagar();
        pedido.iniciarPreparo();
        pedido.marcarPronto();
        pedido.entregar();

        assertThrows(IllegalStateException.class, () -> pedido.cancelar());
        assertThrows(IllegalStateException.class, () -> pedido.pagar());
    }

    // --- OBSERVER ---

    @Test
    void observadorDeveSerNotificadoNaMudancaDeEstado() {
        List<StatusPedido> statusRecebidos = new ArrayList<>();
        pedido.adicionarObservador((p, anterior, novo) -> statusRecebidos.add(novo));

        pedido.pagar();
        pedido.iniciarPreparo();

        assertEquals(2, statusRecebidos.size());
        assertEquals(StatusPedido.PAGO,       statusRecebidos.get(0));
        assertEquals(StatusPedido.PREPARANDO, statusRecebidos.get(1));
    }

    @Test
    void observadorRemovidoNaoDeveSerNotificado() {
        List<StatusPedido> recebidos = new ArrayList<>();
        ObservadorPedido obs = (p, anterior, novo) -> recebidos.add(novo);

        pedido.adicionarObservador(obs);
        pedido.pagar();
        pedido.removerObservador(obs);
        pedido.iniciarPreparo();

        assertEquals(1, recebidos.size());
    }

    @Test
    void observadorDeveReceberStatusAnteriorENovoCorretos() {
        List<StatusPedido> anteriores = new ArrayList<>();
        List<StatusPedido> novos      = new ArrayList<>();

        pedido.adicionarObservador((p, anterior, novo) -> {
            anteriores.add(anterior);
            novos.add(novo);
        });

        pedido.pagar();

        assertEquals(StatusPedido.NOVO, anteriores.get(0));
        assertEquals(StatusPedido.PAGO, novos.get(0));
    }
}
