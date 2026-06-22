package com.hamburgueriauni.pedido;

// Interpreter — Expressão Terminal: interpreta cláusulas do tipo "com <ingrediente>"
public class ExpressaoComAdicional implements ExpressaoPersonalizacao {

    private final String adicional;

    public ExpressaoComAdicional(String adicional) {
        this.adicional = adicional.trim();
    }

    @Override
    public void interpretar(ContextoPersonalizacao contexto) {
        contexto.adicionarAdicional(adicional);
        System.out.println("[Interpreter] Adicional registrado: com " + adicional);
    }
}
