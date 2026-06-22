package com.hamburgueriauni.pedido;

// Padrão Observer: notifica a cozinha quando um novo pedido pago precisa ser preparado
public class NotificacaoCozinha implements ObservadorPedido {

    @Override
    public void atualizar(Pedido pedido, StatusPedido anterior, StatusPedido novo) {
        if (novo == StatusPedido.PAGO) {
            System.out.println("[Notificação → Cozinha] Novo pedido #" + pedido.getId()
                    + " pago e aguardando preparo. Itens: " + pedido.getItens().size());
        }
        if (novo == StatusPedido.CANCELADO && anterior == StatusPedido.PAGO) {
            System.out.println("[Notificação → Cozinha] Pedido #" + pedido.getId() + " cancelado — ignorar.");
        }
    }
}
