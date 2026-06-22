package com.hamburgueriauni.pedido;

import java.util.ArrayDeque;
import java.util.Deque;

// Padrão Memento — Caretaker: armazena os snapshots sem conhecer seu conteúdo interno
public class HistoricoPedido {

    private final Deque<PedidoMemento> pilha = new ArrayDeque<>();

    public void salvar(PedidoMemento memento) {
        pilha.push(memento);
        System.out.println("[Memento] Estado salvo: " + memento);
    }

    public PedidoMemento desfazer() {
        if (pilha.isEmpty()) {
            throw new IllegalStateException("Nenhum estado anterior para restaurar.");
        }
        PedidoMemento memento = pilha.pop();
        System.out.println("[Memento] Restaurando estado: " + memento);
        return memento;
    }

    public boolean temHistorico() { return !pilha.isEmpty(); }
    public int tamanho()          { return pilha.size(); }
}
