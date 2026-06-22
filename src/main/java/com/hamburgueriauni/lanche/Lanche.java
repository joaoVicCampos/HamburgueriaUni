package com.hamburgueriauni.lanche;

import com.hamburgueriauni.ingrediente.Ingrediente;

import java.util.ArrayList;
import java.util.List;

// Produto construído pelo Builder
public class Lanche {

    private final String nome;
    private final Ingrediente pao;
    private final Ingrediente proteina;
    private final Ingrediente queijo;
    private final Ingrediente molho;
    private final List<Ingrediente> extras;
    private final String observacoes;

    Lanche(LancheBuilderImpl builder) {
        this.nome        = builder.nome;
        this.pao         = builder.pao;
        this.proteina    = builder.proteina;
        this.queijo      = builder.queijo;
        this.molho       = builder.molho;
        this.extras      = new ArrayList<>(builder.extras);
        this.observacoes = builder.observacoes;
    }

    public double calcularPreco() {
        double total = 0;
        if (pao      != null) total += pao.getPreco();
        if (proteina != null) total += proteina.getPreco();
        if (queijo   != null) total += queijo.getPreco();
        if (molho    != null) total += molho.getPreco();
        for (Ingrediente e : extras) total += e.getPreco();
        return total;
    }

    public String getNome()              { return nome; }
    public Ingrediente getPao()          { return pao; }
    public Ingrediente getProteina()     { return proteina; }
    public Ingrediente getQueijo()       { return queijo; }
    public Ingrediente getMolho()        { return molho; }
    public List<Ingrediente> getExtras() { return extras; }
    public String getObservacoes()       { return observacoes; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(nome).append(" (R$").append(String.format("%.2f", calcularPreco())).append(") ===\n");
        if (pao      != null) sb.append("  Pão:      ").append(pao).append("\n");
        if (proteina != null) sb.append("  Proteína: ").append(proteina).append("\n");
        if (queijo   != null) sb.append("  Queijo:   ").append(queijo).append("\n");
        if (molho    != null) sb.append("  Molho:    ").append(molho).append("\n");
        extras.forEach(e -> sb.append("  Extra:    ").append(e).append("\n"));
        if (observacoes != null && !observacoes.isBlank())
            sb.append("  Obs:      ").append(observacoes).append("\n");
        return sb.toString();
    }
}
