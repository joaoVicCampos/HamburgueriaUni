package com.hamburgueriauni.pedido;

// Padrão Observer: notifica o atendente quando o pedido está pronto para ser levado à mesa/balcão
public class NotificacaoAtendente implements ObservadorPedido {

    @Override
    public void atualizar(Pedido pedido, StatusPedido anterior, StatusPedido novo) {
        if (novo == StatusPedido.PRONTO) {
            System.out.println("[Notificação → Atendente] Pedido #" + pedido.getId()
                    + " PRONTO! Tipo: " + pedido.getTipo() + " | Cliente: " + pedido.getCliente());
        }
    }
}
