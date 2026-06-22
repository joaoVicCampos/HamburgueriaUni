package com.hamburgueriauni.cozinha;

// Padrão Mediator: define a interface de comunicação centralizada entre os colaboradores
public interface CozinhaMediator {
    void registrar(ColaboradorCozinha colaborador);
    void enviarMensagem(ColaboradorCozinha remetente, String evento, String dados);
}
