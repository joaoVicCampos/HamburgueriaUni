package com.hamburgueriauni.pedido;

import java.util.ArrayDeque;
import java.util.Deque;

// Padrão Command — Invoker: executa comandos e mantém a pilha de undo
public class GerenciadorComandos {

    private final Deque<ComandoPedido> historico = new ArrayDeque<>();

    public void executar(ComandoPedido comando) {
        comando.executar();
        historico.push(comando);
    }

    public void desfazer() {
        if (historico.isEmpty()) {
            System.out.println("[Command] Nada para desfazer.");
            return;
        }
        ComandoPedido ultimo = historico.pop();
        System.out.println("[Command] Desfazendo: " + ultimo.descricao());
        ultimo.desfazer();
    }

    public boolean temComandos() { return !historico.isEmpty(); }
    public int totalComandos()   { return historico.size(); }
}
