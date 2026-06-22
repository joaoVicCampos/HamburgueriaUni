package com.hamburgueriauni.pedido;

// Padrão Interpreter: interface abstrata que toda expressão da linguagem de pedidos deve implementar
public interface ExpressaoPersonalizacao {
    void interpretar(ContextoPersonalizacao contexto);
}
