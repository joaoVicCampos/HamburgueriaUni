package com.hamburgueriauni.cozinha;

// Mediator Colleague: representa o atendente do restaurante
public class Atendente implements ColaboradorCozinha {

    private final String nome;
    private CozinhaMediator mediator;

    public Atendente(String nome) {
        this.nome = nome;
    }

    public void abrirPedido(String descricaoPedido) {
        System.out.println("[Atendente " + nome + "] Abrindo pedido: " + descricaoPedido);
        mediator.enviarMensagem(this, "NOVO_PEDIDO", descricaoPedido);
    }

    public void entregarPedido(String descricaoPedido) {
        System.out.println("[Atendente " + nome + "] Entregando pedido ao cliente: " + descricaoPedido);
        mediator.enviarMensagem(this, "PEDIDO_ENTREGUE", descricaoPedido);
    }

    @Override
    public void receberMensagem(String evento, String dados) {
        if ("PEDIDO_PRONTO".equals(evento)) {
            System.out.println("[Atendente " + nome + "] Sinal recebido — pedido pronto para retirada: " + dados);
        }
    }

    @Override
    public void setMediator(CozinhaMediator mediator) { this.mediator = mediator; }

    @Override
    public String getNome() { return "ATENDENTE_" + nome; }
}
