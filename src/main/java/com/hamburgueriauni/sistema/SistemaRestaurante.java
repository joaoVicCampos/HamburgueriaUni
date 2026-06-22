package com.hamburgueriauni.sistema;

import com.hamburgueriauni.cardapio.Cardapio;
import com.hamburgueriauni.cardapio.ItemCardapio;
import com.hamburgueriauni.config.ConfiguracaoRestaurante;
import com.hamburgueriauni.cozinha.*;
import com.hamburgueriauni.pagamento.MetodoPagamento;
import com.hamburgueriauni.pedido.*;

// Padrão Facade: ponto único de entrada para todas as operações do restaurante.
// O cliente do sistema não precisa conhecer Factory, Chain of Responsibility,
// State, Observer, Mediator, Proxy, Command ou Memento — só usa esta classe.
public class SistemaRestaurante {

    // Subsistemas internos
    private final Cardapio cardapio;
    private final GerenciadorEstoque gerenciadorEstoque;
    private final ProxyEstoque estoqueProxy;
    private final CentralCozinha centralCozinha;
    private final GerenciadorComandos gerenciadorComandos;
    private final HistoricoPedido historicoPedido;

    // Observers padrão que todo pedido recebe automaticamente
    private final ObservadorPedido obsCliente    = new NotificacaoCliente();
    private final ObservadorPedido obsCozinha    = new NotificacaoCozinha();
    private final ObservadorPedido obsAtendente  = new NotificacaoAtendente();

    // Colaboradores da cozinha registrados no Mediator
    private final Atendente atendente;
    private final Cozinheiro cozinheiro;

    public SistemaRestaurante() {
        ConfiguracaoRestaurante config = ConfiguracaoRestaurante.getInstancia();
        System.out.println("[Facade] Iniciando " + config.getNomeRestaurante() + "...");

        this.cardapio           = new Cardapio();
        this.gerenciadorEstoque = new GerenciadorEstoque();
        this.estoqueProxy       = new ProxyEstoque(gerenciadorEstoque);
        this.gerenciadorComandos = new GerenciadorComandos();
        this.historicoPedido    = new HistoricoPedido();

        // Configura Mediator
        this.centralCozinha = new CentralCozinha();
        this.atendente      = new Atendente("Ana");
        this.cozinheiro     = new Cozinheiro("Carlos");
        ClienteColaborador clienteColaborador = new ClienteColaborador("Geral");
        centralCozinha.registrar(atendente);
        centralCozinha.registrar(cozinheiro);
        centralCozinha.registrar(clienteColaborador);

        System.out.println("[Facade] Sistema pronto.\n");
    }

    // -------------------------------------------------------------------------
    // Abertura de pedido (usa Factory Method internamente)
    // -------------------------------------------------------------------------

    public Pedido abrirPedidoBalcao(String cliente) {
        Pedido pedido = new FabricaPedidoBalcao().abrirPedido(cliente);
        registrarObservadores(pedido);
        atendente.abrirPedido("Pedido #" + pedido.getId() + " — " + cliente);
        return pedido;
    }

    public Pedido abrirPedidoMesa(String cliente, int mesa) {
        Pedido pedido = new FabricaPedidoMesa(mesa).abrirPedido(cliente);
        registrarObservadores(pedido);
        atendente.abrirPedido("Pedido #" + pedido.getId() + " — Mesa " + mesa);
        return pedido;
    }

    public Pedido abrirPedidoDelivery(String cliente, String endereco) {
        Pedido pedido = new FabricaPedidoDelivery(endereco).abrirPedido(cliente);
        registrarObservadores(pedido);
        atendente.abrirPedido("Pedido #" + pedido.getId() + " — Delivery para " + endereco);
        return pedido;
    }

    // -------------------------------------------------------------------------
    // Montagem do pedido (usa Command internamente para suporte a undo)
    // -------------------------------------------------------------------------

    public void adicionarItem(Pedido pedido, ItemCardapio item) {
        gerenciadorComandos.executar(new ComandoAdicionarItem(pedido, item));
    }

    public void removerItem(Pedido pedido, ItemCardapio item) {
        gerenciadorComandos.executar(new ComandoRemoverItem(pedido, item));
    }

    public void desfazerUltimaAcao() {
        gerenciadorComandos.desfazer();
    }

    // -------------------------------------------------------------------------
    // Processamento do pedido (Chain of Responsibility)
    // -------------------------------------------------------------------------

    public boolean processarPedido(Pedido pedido, MetodoPagamento pagamento) {
        System.out.println("\n[Facade] Iniciando processamento do pedido #" + pedido.getId());

        ManipuladorPedido validadorItens    = new ValidadorItens();
        ManipuladorPedido validadorEstoque  = new ValidadorEstoque(estoqueProxy);
        ManipuladorPedido validadorPagamento = new ValidadorPagamento(pagamento);
        ManipuladorPedido processadorCozinha = new ProcessadorCozinha();

        validadorItens
                .setProximo(validadorEstoque)
                .setProximo(validadorPagamento)
                .setProximo(processadorCozinha);

        boolean sucesso = validadorItens.processar(pedido);
        if (sucesso) {
            estoqueProxy.baixarEstoque(pedido);
        }
        return sucesso;
    }

    // -------------------------------------------------------------------------
    // Fluxo da cozinha (State + Mediator)
    // -------------------------------------------------------------------------

    public void finalizarPreparoCozinha(Pedido pedido) {
        pedido.marcarPronto();
        cozinheiro.finalizarPedido("Pedido #" + pedido.getId());
    }

    public void entregarPedido(Pedido pedido) {
        pedido.entregar();
        atendente.entregarPedido("Pedido #" + pedido.getId());
    }

    // -------------------------------------------------------------------------
    // Cancelamento com undo (Command + Memento)
    // -------------------------------------------------------------------------

    public void cancelarPedido(Pedido pedido) {
        gerenciadorComandos.executar(new ComandoCancelarPedido(pedido, historicoPedido));
    }

    // -------------------------------------------------------------------------
    // Relatório (Bridge)
    // -------------------------------------------------------------------------

    public void imprimirComprovante(Pedido pedido, FormatoSaida formato) {
        new RelatorioPedido(formato).imprimir(pedido);
    }

    // -------------------------------------------------------------------------
    // Consultas
    // -------------------------------------------------------------------------

    public Cardapio getCardapio()                      { return cardapio; }
    public ProxyEstoque getEstoque()                   { return estoqueProxy; }
    public ConfiguracaoRestaurante getConfiguracao()   { return ConfiguracaoRestaurante.getInstancia(); }

    // -------------------------------------------------------------------------

    private void registrarObservadores(Pedido pedido) {
        pedido.adicionarObservador(obsCliente);
        pedido.adicionarObservador(obsCozinha);
        pedido.adicionarObservador(obsAtendente);
    }
}
