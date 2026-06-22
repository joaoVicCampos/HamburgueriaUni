package com.hamburgueriauni.pedido;

// Chain of Responsibility: elo final da cadeia — envia o pedido para preparo na cozinha
public class ProcessadorCozinha extends ManipuladorPedido {

    @Override
    public boolean processar(Pedido pedido) {
        System.out.println("[Chain] ProcessadorCozinha: todas as validações passaram.");
        pedido.iniciarPreparo();
        System.out.println("[Chain] Pedido #" + pedido.getId() + " enviado à cozinha com sucesso.");
        return true;
    }
}
