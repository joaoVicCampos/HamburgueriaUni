package com.hamburgueriauni.cozinha;

// Mediator Colleague: representa o cozinheiro que prepara os pedidos
public class Cozinheiro implements ColaboradorCozinha {

    private final String nome;
    private CozinhaMediator mediator;

    public Cozinheiro(String nome) {
        this.nome = nome;
    }

    public void finalizarPedido(String descricaoPedido) {
        System.out.println("[Cozinheiro " + nome + "] Pedido finalizado: " + descricaoPedido);
        mediator.enviarMensagem(this, "PEDIDO_PRONTO", descricaoPedido);
    }

    @Override
    public void receberMensagem(String evento, String dados) {
        if ("NOVO_PEDIDO".equals(evento)) {
            System.out.println("[Cozinheiro " + nome + "] Novo pedido recebido para preparo: " + dados);
        }
    }

    @Override
    public void setMediator(CozinhaMediator mediator) { this.mediator = mediator; }

    @Override
    public String getNome() { return "COZINHEIRO_" + nome; }
}
