package com.hamburgueriauni.cozinha;

// Padrão Mediator: interface Colleague — participantes que se comunicam via mediador
public interface ColaboradorCozinha {
    void receberMensagem(String evento, String dados);
    void setMediator(CozinhaMediator mediator);
    String getNome();
}
