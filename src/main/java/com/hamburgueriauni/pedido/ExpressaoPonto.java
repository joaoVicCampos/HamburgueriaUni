package com.hamburgueriauni.pedido;

// Interpreter — Expressão Terminal: interpreta cláusulas de ponto de carne
// Reconhece: "mal passado", "ao ponto", "bem passado", "passado"
public class ExpressaoPonto implements ExpressaoPersonalizacao {

    private final String ponto;

    public ExpressaoPonto(String ponto) {
        this.ponto = ponto.trim();
    }

    @Override
    public void interpretar(ContextoPersonalizacao contexto) {
        contexto.setPonto(ponto);
        System.out.println("[Interpreter] Ponto registrado: " + ponto);
    }
}
