package com.hamburgueriauni.pedido;

import com.hamburgueriauni.cardapio.ItemCardapio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Padrão Memento: snapshot imutável do estado de um Pedido em um momento específico.
// Só o próprio Pedido (Originator) cria e consome mementos — o Caretaker apenas os guarda.
public class PedidoMemento {

    private final List<ItemCardapio> itens;
    private final EstadoPedido estado;
    private final LocalDateTime timestamp;

    // Construtor package-private: só Pedido pode criar mementos
    PedidoMemento(List<ItemCardapio> itens, EstadoPedido estado) {
        this.itens     = new ArrayList<>(itens);
        this.estado    = estado;
        this.timestamp = LocalDateTime.now();
    }

    List<ItemCardapio> getItens()  { return new ArrayList<>(itens); }
    EstadoPedido getEstado()       { return estado; }
    LocalDateTime getTimestamp()   { return timestamp; }

    @Override
    public String toString() {
        return String.format("[Memento] Status: %s | Itens: %d | Salvo em: %s",
                estado.getStatus(), itens.size(), timestamp);
    }
}
