package com.hamburgueriauni.cozinha;

// Mediator Colleague: representa o cliente aguardando o pedido
public class ClienteColaborador implements ColaboradorCozinha {

    private final String nome;
    private CozinhaMediator mediator;

    public ClienteColaborador(String nome) {
        this.nome = nome;
    }

    @Override
    public void receberMensagem(String evento, String dados) {
        switch (evento) {
            case "PEDIDO_PRONTO"   -> System.out.println("[Cliente " + nome + "] Oba! Meu pedido está pronto: " + dados);
            case "PEDIDO_ENTREGUE" -> System.out.println("[Cliente " + nome + "] Pedido recebido. Bom apetite!");
        }
    }

    @Override
    public void setMediator(CozinhaMediator mediator) { this.mediator = mediator; }

    @Override
    public String getNome() { return "CLIENTE_" + nome; }
}
