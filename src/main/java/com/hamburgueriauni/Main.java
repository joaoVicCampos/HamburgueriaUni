package com.hamburgueriauni;

import com.hamburgueriauni.cardapio.*;
import com.hamburgueriauni.config.ConfiguracaoRestaurante;
import com.hamburgueriauni.ingrediente.*;
import com.hamburgueriauni.lanche.*;
import com.hamburgueriauni.pagamento.*;
import com.hamburgueriauni.pedido.*;
import com.hamburgueriauni.relatorio.*;
import com.hamburgueriauni.sistema.*;

public class Main {

    public static void main(String[] args) {
        titulo("HamburgueriaUni — Demo dos 23 Padrões de Projeto");

        demo01_Singleton();
        demo02_AbstractFactory();
        demo03_Flyweight();
        demo04_Composite_e_Prototype();
        demo05_Decorator();
        demo06_Builder_e_TemplateMethod();
        demo07_Iterator();
        demo08_Visitor();
        demo09_Strategy();
        demo10_Interpreter();
        demo11_State_e_Observer();
        demo12_Command_e_Memento();
        demo13_ChainOfResponsibility();
        demo14_Mediator();
        demo15_Adapter();
        demo16_Proxy();
        demo17_Bridge();
        demo18_Facade_FluxoCompleto();

        titulo("Todos os 23 padrões demonstrados com sucesso!");
    }

    // =========================================================================
    // 1. SINGLETON
    // =========================================================================
    static void demo01_Singleton() {
        secao("1. SINGLETON — ConfiguracaoRestaurante");

        ConfiguracaoRestaurante c1 = ConfiguracaoRestaurante.getInstancia();
        ConfiguracaoRestaurante c2 = ConfiguracaoRestaurante.getInstancia();

        System.out.println(c1);
        System.out.println("Mesma instância? " + (c1 == c2));

        c1.setTaxaEntregaDelivery(8.00);
        System.out.println("Taxa alterada via c1, lida via c2: R$" + c2.getTaxaEntregaDelivery());
        c1.setTaxaEntregaDelivery(6.00); // restaura
    }

    // =========================================================================
    // 2. ABSTRACT FACTORY
    // =========================================================================
    static void demo02_AbstractFactory() {
        secao("2. ABSTRACT FACTORY — Famílias de Ingredientes");

        FabricaDeIngredientes[] fabricas = {
            new FabricaTradicional(), new FabricaVegana(), new FabricaGourmet()
        };

        for (FabricaDeIngredientes fabrica : fabricas) {
            System.out.println("\nFamília: " + fabrica.getCategoria());
            System.out.println("  Pão:     " + fabrica.criarPao());
            System.out.println("  Proteína:" + fabrica.criarProteina());
            System.out.println("  Queijo:  " + fabrica.criarQueijo());
            System.out.println("  Molho:   " + fabrica.criarMolho());
        }
    }

    // =========================================================================
    // 3. FLYWEIGHT
    // =========================================================================
    static void demo03_Flyweight() {
        secao("3. FLYWEIGHT — Pool de Ingredientes Compartilhados");

        Ingrediente i1 = RepositorioIngredientes.obter("Pão Brioche", TipoIngrediente.PAO, 2.00, false);
        Ingrediente i2 = RepositorioIngredientes.obter("Pão Brioche", TipoIngrediente.PAO, 2.00, false);
        Ingrediente i3 = RepositorioIngredientes.obter("Queijo Cheddar", TipoIngrediente.QUEIJO, 3.00, false);

        System.out.println("i1 == i2 (mesma instância)? " + (i1 == i2));
        System.out.println("i1 == i3 (objetos diferentes)? " + (i1 == i3));
        RepositorioIngredientes.exibirPool();
    }

    // =========================================================================
    // 4. COMPOSITE + PROTOTYPE
    // =========================================================================
    static void demo04_Composite_e_Prototype() {
        secao("4. COMPOSITE — Árvore do Cardápio");
        Cardapio cardapio = new Cardapio();
        cardapio.exibir();

        secao("4b. PROTOTYPE — Clonagem de Item para Personalização");
        CardapioFolha original = cardapio.clonarItem("X-Burguer");
        CardapioFolha clone    = cardapio.clonarItem("X-Burguer");

        clone.setNome("X-Burguer Personalizado");
        clone.adicionarIngrediente(
            RepositorioIngredientes.obter("Bacon", TipoIngrediente.ADICIONAL, 4.00, false));

        System.out.println("Original: " + original.getNome() + " R$" + String.format("%.2f", original.getPreco()));
        System.out.println("Clone:    " + clone.getNome()    + " R$" + String.format("%.2f", clone.getPreco()));
        System.out.println("(Modificar o clone não alterou o original)");
    }

    // =========================================================================
    // 5. DECORATOR
    // =========================================================================
    static void demo05_Decorator() {
        secao("5. DECORATOR — Adicionais em Camadas");

        ItemCardapio lanche = new CardapioFolha("X-Burguer Base", 18.00, "Blend bovino + Brioche");
        System.out.println("Base:          " + lanche.getDescricao() + " | R$" + String.format("%.2f", lanche.getPreco()));

        lanche = new DecoradorBacon(lanche);
        System.out.println("+ Bacon:       " + lanche.getDescricao() + " | R$" + String.format("%.2f", lanche.getPreco()));

        lanche = new DecoradorQueijoExtra(lanche);
        System.out.println("+ Queijo:      " + lanche.getDescricao() + " | R$" + String.format("%.2f", lanche.getPreco()));

        lanche = new DecoradorMolhoEspecial(lanche);
        System.out.println("+ Molho:       " + lanche.getDescricao() + " | R$" + String.format("%.2f", lanche.getPreco()));
    }

    // =========================================================================
    // 6. BUILDER + TEMPLATE METHOD
    // =========================================================================
    static void demo06_Builder_e_TemplateMethod() {
        secao("6. BUILDER — Montagem do Lanche Personalizado");

        Lanche lanche = new LancheBuilderImpl()
                .comNome("Meu Burger")
                .comPao(RepositorioIngredientes.obter("Pão Brioche",       TipoIngrediente.PAO,      2.00, false))
                .comProteina(RepositorioIngredientes.obter("Blend Bovino 150g", TipoIngrediente.PROTEINA, 12.00, false))
                .comQueijo(RepositorioIngredientes.obter("Queijo Cheddar",    TipoIngrediente.QUEIJO,   3.00, false))
                .comMolho(RepositorioIngredientes.obter("Molho da Casa",     TipoIngrediente.MOLHO,    1.00, true))
                .comExtra(RepositorioIngredientes.obter("Bacon",             TipoIngrediente.ADICIONAL, 4.00, false))
                .comObservacoes("Sem cebola, bem passado")
                .construir();

        System.out.println(lanche);

        secao("6b. TEMPLATE METHOD — Preparo na Cozinha");
        PreparacaoLanche preparacaoTrad = new PreparacaoTradicional();
        preparacaoTrad.preparar(lanche);

        Lanche lancheVegano = new LancheBuilderImpl()
                .comNome("Green Burger")
                .comPao(RepositorioIngredientes.obter("Pão Integral Vegano",       TipoIngrediente.PAO,      3.00, true))
                .comProteina(RepositorioIngredientes.obter("Burger de Grão-de-Bico",   TipoIngrediente.PROTEINA, 11.00, true))
                .construir();

        new PreparacaoVegana().preparar(lancheVegano);
    }

    // =========================================================================
    // 7. ITERATOR
    // =========================================================================
    static void demo07_Iterator() {
        secao("7. ITERATOR — Navegação pelo Cardápio");

        Cardapio cardapio = new Cardapio();
        IteradorCardapio it = new IteradorCardapio(cardapio.getRaiz());

        System.out.println("Itens do cardápio em ordem (DFS):");
        int num = 1;
        while (it.hasNext()) {
            CardapioFolha item = it.next();
            System.out.printf("  %2d. %-25s R$%.2f%n", num++, item.getNome(), item.getPreco());
        }
        System.out.println("Total de itens: " + it.totalItens());
    }

    // =========================================================================
    // 8. VISITOR
    // =========================================================================
    static void demo08_Visitor() {
        secao("8. VISITOR — Relatórios sobre o Cardápio");

        Cardapio cardapio = new Cardapio();

        RelatorioPrecos relPrecos = new RelatorioPrecos();
        cardapio.getRaiz().aceitar(relPrecos);
        relPrecos.imprimirRelatorio();

        RelatorioVegano relVegano = new RelatorioVegano();
        cardapio.getRaiz().aceitar(relVegano);
        relVegano.imprimirRelatorio();
    }

    // =========================================================================
    // 9. STRATEGY
    // =========================================================================
    static void demo09_Strategy() {
        secao("9. STRATEGY — Estratégias de Desconto");

        double valor = 85.00;
        System.out.println("Valor original: R$" + String.format("%.2f", valor));

        EstrategiaDesconto[] estrategias = {
            new SemDesconto(),
            new DescontoFidelidade(),
            new DescontoHorarioPico(),
            new DescontoProgressivo(5)
        };

        for (EstrategiaDesconto e : estrategias) {
            double resultado = e.aplicar(valor);
            System.out.printf("  %-35s → R$%.2f%n", e.descricao(), resultado);
        }
    }

    // =========================================================================
    // 10. INTERPRETER
    // =========================================================================
    static void demo10_Interpreter() {
        secao("10. INTERPRETER — Linguagem de Pedidos Especiais");

        InterpretadorPedido interpretador = new InterpretadorPedido();
        interpretador.interpretar("sem cebola, sem tomate, bem passado, com bacon");
        interpretador.interpretar("ao ponto, com queijo extra, sem molho");
        interpretador.interpretar("mal passado, com ovo");
    }

    // =========================================================================
    // 11. STATE + OBSERVER
    // =========================================================================
    static void demo11_State_e_Observer() {
        secao("11. STATE + OBSERVER — Ciclo de Vida do Pedido");

        Pedido pedido = new PedidoBalcao("Maria");
        pedido.adicionarObservador(new NotificacaoCliente());
        pedido.adicionarObservador(new NotificacaoCozinha());
        pedido.adicionarObservador(new NotificacaoAtendente());

        System.out.println("Status inicial: " + pedido.getStatus());
        pedido.pagar();
        pedido.iniciarPreparo();
        pedido.marcarPronto();
        pedido.entregar();
        System.out.println("Status final:   " + pedido.getStatus());

        System.out.println("\nTentando transição inválida:");
        try {
            pedido.cancelar();
        } catch (IllegalStateException e) {
            System.out.println("[State] Bloqueado: " + e.getMessage());
        }
    }

    // =========================================================================
    // 12. COMMAND + MEMENTO
    // =========================================================================
    static void demo12_Command_e_Memento() {
        secao("12. COMMAND + MEMENTO — Operações com Undo");

        Pedido pedido       = new PedidoBalcao("Carlos");
        GerenciadorComandos gerenciador = new GerenciadorComandos();
        HistoricoPedido     historico   = new HistoricoPedido();

        ItemCardapio xBurguer = new CardapioFolha("X-Burguer", 18.00, "Clássico");
        ItemCardapio bebida   = new CardapioFolha("Refrigerante", 6.00, "350ml");

        gerenciador.executar(new ComandoAdicionarItem(pedido, xBurguer));
        gerenciador.executar(new ComandoAdicionarItem(pedido, bebida));
        System.out.println("Total após adicionar: R$" + String.format("%.2f", pedido.calcularTotal()));

        gerenciador.desfazer(); // remove bebida
        System.out.println("Total após undo:      R$" + String.format("%.2f", pedido.calcularTotal()));

        gerenciador.executar(new ComandoCancelarPedido(pedido, historico));
        System.out.println("Status após cancelar: " + pedido.getStatus());

        gerenciador.desfazer(); // restaura com Memento
        System.out.println("Status após undo:     " + pedido.getStatus());
    }

    // =========================================================================
    // 13. CHAIN OF RESPONSIBILITY
    // =========================================================================
    static void demo13_ChainOfResponsibility() {
        secao("13. CHAIN OF RESPONSIBILITY — Validação de Pedido");

        GerenciadorEstoque real  = new GerenciadorEstoque();
        ProxyEstoque proxy       = new ProxyEstoque(real);
        MetodoPagamento pagPix   = new AdaptadorPix(new GatewayPix());

        Pedido pedido = new PedidoBalcao("Pedro");
        pedido.adicionarItem(new CardapioFolha("X-Burguer", 18.00, "Clássico"));

        ManipuladorPedido cadeia = new ValidadorItens();
        cadeia.setProximo(new ValidadorEstoque(proxy))
              .setProximo(new ValidadorPagamento(pagPix))
              .setProximo(new ProcessadorCozinha());

        boolean ok = cadeia.processar(pedido);
        System.out.println("Pedido processado: " + ok);

        System.out.println("\nTentando pedido vazio (deve ser bloqueado):");
        Pedido vazio = new PedidoBalcao("Teste");
        new ValidadorItens().processar(vazio);
    }

    // =========================================================================
    // 14. MEDIATOR
    // =========================================================================
    static void demo14_Mediator() {
        secao("14. MEDIATOR — Comunicação na Cozinha");

        com.hamburgueriauni.cozinha.CentralCozinha central = new com.hamburgueriauni.cozinha.CentralCozinha();
        com.hamburgueriauni.cozinha.Atendente atendente    = new com.hamburgueriauni.cozinha.Atendente("Ana");
        com.hamburgueriauni.cozinha.Cozinheiro cozinheiro  = new com.hamburgueriauni.cozinha.Cozinheiro("Carlos");
        com.hamburgueriauni.cozinha.ClienteColaborador cli = new com.hamburgueriauni.cozinha.ClienteColaborador("João");

        central.registrar(atendente);
        central.registrar(cozinheiro);
        central.registrar(cli);

        atendente.abrirPedido("X-Burguer + Refrigerante");
        cozinheiro.finalizarPedido("X-Burguer + Refrigerante");
        atendente.entregarPedido("X-Burguer + Refrigerante");
    }

    // =========================================================================
    // 15. ADAPTER
    // =========================================================================
    static void demo15_Adapter() {
        secao("15. ADAPTER — Gateways de Pagamento Incompatíveis");

        MetodoPagamento[] metodos = {
            new AdaptadorPix(new GatewayPix()),
            new AdaptadorCartao(new GatewayCartao(), 3),
            new AdaptadorDinheiro(new GatewayDinheiro(), 60.00)
        };

        double valor = 52.00;
        for (MetodoPagamento m : metodos) {
            System.out.println("\nPagando R$" + String.format("%.2f", valor) + " via " + m.getNomeMetodo() + ":");
            boolean ok = m.processarPagamento(valor);
            System.out.println("  Resultado: " + (ok ? "APROVADO" : "RECUSADO"));
        }
    }

    // =========================================================================
    // 16. PROXY
    // =========================================================================
    static void demo16_Proxy() {
        secao("16. PROXY — Controle de Acesso ao Estoque");

        GerenciadorEstoque real  = new GerenciadorEstoque();
        ProxyEstoque proxy       = new ProxyEstoque(real);

        Pedido pedido = new PedidoBalcao("Lucas");
        pedido.adicionarItem(new CardapioFolha("X-Burguer", 18.00, "Clássico"));

        System.out.println("1ª consulta (vai ao estoque real):");
        proxy.verificarDisponibilidade(pedido);

        System.out.println("\n2ª consulta (retorna do cache):");
        proxy.verificarDisponibilidade(pedido);

        System.out.println("\nBloqueando acesso e tentando consultar:");
        proxy.bloquearAcesso();
        proxy.verificarDisponibilidade(pedido);
        proxy.liberarAcesso();
    }

    // =========================================================================
    // 17. BRIDGE
    // =========================================================================
    static void demo17_Bridge() {
        secao("17. BRIDGE — Mesmo Relatório em Formatos Diferentes");

        Pedido pedido = new PedidoDelivery("Fernanda", "Rua A, 100");
        pedido.adicionarItem(new CardapioFolha("X-Burguer", 18.00, "Clássico"));
        pedido.adicionarItem(new CardapioFolha("Refrigerante", 6.00, "350ml"));

        System.out.println("--- Formato Terminal ---");
        new RelatorioPedido(new SaidaTerminal()).imprimir(pedido);

        System.out.println("\n--- Formato Cupom ---");
        new RelatorioPedido(new SaidaCupom()).imprimir(pedido);
    }

    // =========================================================================
    // 18. FACADE — Fluxo Completo
    // =========================================================================
    static void demo18_Facade_FluxoCompleto() {
        secao("18. FACADE — Fluxo Completo pelo SistemaRestaurante");

        SistemaRestaurante sistema = new SistemaRestaurante();

        // Abre pedido
        Pedido pedido = sistema.abrirPedidoMesa("Roberto", 4);

        // Adiciona itens via Command
        Cardapio cardapio = sistema.getCardapio();
        ItemCardapio xBurguer = cardapio.clonarItem("X-Burguer");
        ItemCardapio bebida   = new CardapioFolha("Suco Natural", 9.00, "Laranja");

        sistema.adicionarItem(pedido, xBurguer);
        sistema.adicionarItem(pedido, bebida);

        // Aplica desconto Strategy antes de pagar
        EstrategiaDesconto desconto = new DescontoFidelidade();
        double totalComDesconto = desconto.aplicar(pedido.calcularTotal());
        System.out.println("\nTotal com desconto: R$" + String.format("%.2f", totalComDesconto));

        // Processa via Chain of Responsibility (Adapter de pagamento)
        MetodoPagamento pix = new AdaptadorPix(new GatewayPix());
        boolean ok = sistema.processarPedido(pedido, pix);

        if (ok) {
            // Cozinha finaliza (Template Method foi usado no preparo real)
            sistema.finalizarPreparoCozinha(pedido);

            // Entrega
            sistema.entregarPedido(pedido);

            // Imprime comprovante (Bridge)
            sistema.imprimirComprovante(pedido, new SaidaTerminal());
        }
    }

    // =========================================================================
    // Utilitários de formatação
    // =========================================================================
    static void titulo(String texto) {
        System.out.println("\n" + "█".repeat(60));
        System.out.println("  " + texto);
        System.out.println("█".repeat(60));
    }

    static void secao(String texto) {
        System.out.println("\n" + "─".repeat(55));
        System.out.println("  " + texto);
        System.out.println("─".repeat(55));
    }
}
