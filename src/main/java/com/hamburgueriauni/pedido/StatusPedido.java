package com.hamburgueriauni.pedido;

// Enum usado pelo padrão State
public enum StatusPedido {
    NOVO,
    AGUARDANDO_PAGAMENTO,
    PAGO,
    PREPARANDO,
    PRONTO,
    ENTREGUE,
    CANCELADO
}
