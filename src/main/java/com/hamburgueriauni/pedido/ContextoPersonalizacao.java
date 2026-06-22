package com.hamburgueriauni.pedido;

import java.util.ArrayList;
import java.util.List;

// Padrão Interpreter: contexto que acumula o resultado da interpretação do pedido especial
public class ContextoPersonalizacao {

    private final List<String> restricoes   = new ArrayList<>();
    private final List<String> adicionais   = new ArrayList<>();
    private String ponto       = "ao ponto";
    private String tamanho     = "normal";
    private String observacao  = "";

    public void adicionarRestricao(String ingrediente) {
        restricoes.add(ingrediente);
    }

    public void adicionarAdicional(String ingrediente) {
        adicionais.add(ingrediente);
    }

    public void setPonto(String ponto)       { this.ponto = ponto; }
    public void setTamanho(String tamanho)   { this.tamanho = tamanho; }
    public void setObservacao(String obs)    { this.observacao = obs; }

    public List<String> getRestricoes()  { return restricoes; }
    public List<String> getAdicionais()  { return adicionais; }
    public String getPonto()             { return ponto; }
    public String getTamanho()           { return tamanho; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[Contexto] Personalização:\n");
        if (!restricoes.isEmpty())  sb.append("  Sem: ").append(restricoes).append("\n");
        if (!adicionais.isEmpty())  sb.append("  Com extra: ").append(adicionais).append("\n");
        sb.append("  Ponto: ").append(ponto).append("\n");
        sb.append("  Tamanho: ").append(tamanho).append("\n");
        if (!observacao.isBlank()) sb.append("  Obs: ").append(observacao).append("\n");
        return sb.toString();
    }
}
