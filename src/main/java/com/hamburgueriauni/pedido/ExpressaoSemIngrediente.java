package com.hamburgueriauni.pedido;

// Interpreter — Expressão Terminal: interpreta cláusulas do tipo "sem <ingrediente>"
public class ExpressaoSemIngrediente implements ExpressaoPersonalizacao {

    private final String ingrediente;

    public ExpressaoSemIngrediente(String ingrediente) {
        this.ingrediente = ingrediente.trim();
    }

    @Override
    public void interpretar(ContextoPersonalizacao contexto) {
        contexto.adicionarRestricao(ingrediente);
        System.out.println("[Interpreter] Restrição registrada: sem " + ingrediente);
    }
}
