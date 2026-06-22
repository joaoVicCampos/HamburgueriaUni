package com.hamburgueriauni.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Padrão Interpreter: analisa uma string de personalização e constrói/executa as expressões.
// Gramática suportada (cláusulas separadas por vírgula):
//   "sem <ingrediente>"          → ExpressaoSemIngrediente
//   "com <ingrediente>"          → ExpressaoComAdicional
//   "mal passado" | "ao ponto" | "bem passado" | "passado" → ExpressaoPonto
public class InterpretadorPedido {

    private static final Set<String> PONTOS = Set.of(
            "mal passado", "ao ponto", "bem passado", "passado", "muito bem passado"
    );

    public ContextoPersonalizacao interpretar(String entrada) {
        System.out.println("[Interpreter] Interpretando: \"" + entrada + "\"");

        ContextoPersonalizacao contexto = new ContextoPersonalizacao();
        List<ExpressaoPersonalizacao> expressoes = analisar(entrada);
        expressoes.forEach(e -> e.interpretar(contexto));

        System.out.println(contexto);
        return contexto;
    }

    private List<ExpressaoPersonalizacao> analisar(String entrada) {
        List<ExpressaoPersonalizacao> expressoes = new ArrayList<>();

        String[] clausulas = entrada.toLowerCase().split(",");
        for (String clausula : clausulas) {
            clausula = clausula.trim();

            if (clausula.startsWith("sem ")) {
                expressoes.add(new ExpressaoSemIngrediente(clausula.substring(4)));

            } else if (clausula.startsWith("com ")) {
                expressoes.add(new ExpressaoComAdicional(clausula.substring(4)));

            } else if (PONTOS.contains(clausula)) {
                expressoes.add(new ExpressaoPonto(clausula));

            } else {
                System.out.println("[Interpreter] Cláusula não reconhecida (ignorada): \"" + clausula + "\"");
            }
        }
        return expressoes;
    }
}
