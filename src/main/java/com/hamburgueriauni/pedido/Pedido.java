package com.hamburgueriauni.pedido;

import com.hamburgueriauni.cardapio.ItemCardapio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe base de Pedido.
// Padrão State: delega transições de status ao objeto EstadoPedido atual.
// Padrão Observer: notifica observadores registrados a cada mudança de estado.
public abstract class Pedido {

    protected final String id;
    protected final String cliente;
    protected final TipoPedido tipo;
    protected final List<ItemCardapio> itens;
    protected final LocalDateTime horaCriacao;

    private EstadoPedido estadoAtual;
    private final List<ObservadorPedido> observadores = new ArrayList<>();

    protected Pedido(String cliente, TipoPedido tipo) {
        this.id          = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.cliente     = cliente;
        this.tipo        = tipo;
        this.itens       = new ArrayList<>();
        this.horaCriacao = LocalDateTime.now();
        this.estadoAtual = new EstadoNovo();
    }

    // --- Gerenciamento de observers (Observer) ---

    public void adicionarObservador(ObservadorPedido obs)  { observadores.add(obs); }
    public void removerObservador(ObservadorPedido obs)    { observadores.remove(obs); }

    private void notificarObservadores(StatusPedido anterior) {
        StatusPedido novo = estadoAtual.getStatus();
        for (ObservadorPedido obs : observadores) {
            obs.atualizar(this, anterior, novo);
        }
    }

    // Chamado pelos estados concretos para trocar o estado atual (State)
    public void setEstado(EstadoPedido novoEstado) {
        StatusPedido anterior = estadoAtual.getStatus();
        this.estadoAtual = novoEstado;
        notificarObservadores(anterior);
    }

    // --- Transições de estado (delegadas ao EstadoPedido atual) ---

    public void pagar()          { estadoAtual.pagar(this); }
    public void iniciarPreparo() { estadoAtual.iniciarPreparo(this); }
    public void marcarPronto()   { estadoAtual.marcarPronto(this); }
    public void entregar()       { estadoAtual.entregar(this); }
    public void cancelar()       { estadoAtual.cancelar(this); }

    // --- Itens ---

    public void adicionarItem(ItemCardapio item) { itens.add(item); }

    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemCardapio::getPreco).sum();
    }

    // --- Getters ---

    public String getId()               { return id; }
    public String getCliente()          { return cliente; }
    public TipoPedido getTipo()         { return tipo; }
    public StatusPedido getStatus()     { return estadoAtual.getStatus(); }
    public List<ItemCardapio> getItens(){ return itens; }
    public LocalDateTime getHoraCriacao() { return horaCriacao; }

    public abstract String getResumo();

    @Override
    public String toString() {
        return String.format("Pedido #%s | Cliente: %s | Tipo: %s | Status: %s | Total: R$%.2f",
                id, cliente, tipo, getStatus(), calcularTotal());
    }
}
