package com.hamburgueriauni.sistema;

import com.hamburgueriauni.pedido.Pedido;

import java.util.List;

// Padrão Bridge — Refined Abstraction: relatório de pedido desacoplado do formato de saída.
// Qualquer FormatoSaida pode ser injetado sem alterar esta classe.
public class RelatorioPedido {

    private final FormatoSaida formato;

    public RelatorioPedido(FormatoSaida formato) {
        this.formato = formato;
    }

    public void imprimir(Pedido pedido) {
        formato.imprimirTitulo("Comprovante de Pedido");
        formato.imprimirLinha("Pedido", "#" + pedido.getId());
        formato.imprimirLinha("Cliente", pedido.getCliente());
        formato.imprimirLinha("Tipo", pedido.getTipo().toString());
        formato.imprimirLinha("Status", pedido.getStatus().toString());
        formato.imprimirSeparador();

        List<String> nomes = pedido.getItens().stream()
                .map(i -> i.getNome() + "  R$" + String.format("%.2f", i.getPreco()))
                .toList();
        formato.imprimirLista("Itens", nomes);

        formato.imprimirSeparador();
        formato.imprimirLinha("TOTAL", "R$" + String.format("%.2f", pedido.calcularTotal()));
    }
}
