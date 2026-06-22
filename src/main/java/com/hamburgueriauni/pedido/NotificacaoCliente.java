package com.hamburgueriauni.pedido;

// Padrão Observer: notifica o cliente sobre mudanças no status do seu pedido
public class NotificacaoCliente implements ObservadorPedido {

    @Override
    public void atualizar(Pedido pedido, StatusPedido anterior, StatusPedido novo) {
        String mensagem = switch (novo) {
            case PAGO       -> "Pagamento confirmado! Seu pedido entrou na fila.";
            case PREPARANDO -> "Sua comida está sendo preparada com carinho!";
            case PRONTO     -> "PRONTO! Retire seu pedido no balcão — #" + pedido.getId();
            case ENTREGUE   -> "Obrigado pela preferência, " + pedido.getCliente() + "! Bom apetite!";
            case CANCELADO  -> "Seu pedido #" + pedido.getId() + " foi cancelado.";
            default         -> null;
        };
        if (mensagem != null) {
            System.out.println("[Notificação → Cliente " + pedido.getCliente() + "] " + mensagem);
        }
    }
}
