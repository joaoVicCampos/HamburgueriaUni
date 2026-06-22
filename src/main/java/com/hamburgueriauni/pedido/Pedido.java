package com.hamburgueriauni.pedido;

import com.hamburgueriauni.cardapio.ItemCardapio;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe base de Pedido, usada por State, Observer, Memento, Command e Chain of Responsibility
public abstract class Pedido {

    protected final String id;
    protected final String cliente;
    protected final TipoPedido tipo;
    protected StatusPedido status;
    protected final List<ItemCardapio> itens;
    protected final LocalDateTime horaCriacao;

    protected Pedido(String cliente, TipoPedido tipo) {
        this.id          = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.cliente     = cliente;
        this.tipo        = tipo;
        this.status      = StatusPedido.NOVO;
        this.itens       = new ArrayList<>();
        this.horaCriacao = LocalDateTime.now();
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemCardapio::getPreco).sum();
    }

    public String getId()             { return id; }
    public String getCliente()        { return cliente; }
    public TipoPedido getTipo()       { return tipo; }
    public StatusPedido getStatus()   { return status; }
    public void setStatus(StatusPedido s) { this.status = s; }
    public List<ItemCardapio> getItens() { return itens; }
    public LocalDateTime getHoraCriacao() { return horaCriacao; }

    public abstract String getResumo();

    @Override
    public String toString() {
        return String.format("Pedido #%s | Cliente: %s | Tipo: %s | Status: %s | Total: R$%.2f",
                id, cliente, tipo, status, calcularTotal());
    }
}
