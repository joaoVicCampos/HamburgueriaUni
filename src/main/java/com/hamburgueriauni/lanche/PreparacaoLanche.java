package com.hamburgueriauni.lanche;

import com.hamburgueriauni.ingrediente.Ingrediente;

// Padrão Template Method: define o esqueleto fixo de preparação de qualquer lanche.
// As etapas concretas variam conforme o tipo (Tradicional, Vegano, Gourmet).
public abstract class PreparacaoLanche {

    // Template Method — final para não permitir alteração da ordem das etapas
    public final void preparar(Lanche lanche) {
        System.out.println("\n[Cozinha] Iniciando preparo: " + lanche.getNome());
        esquentarChapa();
        prepararPao(lanche.getPao());
        cozinharProteina(lanche.getProteina());
        if (lanche.getQueijo() != null) adicionarQueijo(lanche.getQueijo());
        montarLanche(lanche);
        if (lanche.getMolho() != null) aplicarMolho(lanche.getMolho());
        if (!lanche.getExtras().isEmpty()) adicionarExtras(lanche);
        embalar(lanche);
        System.out.println("[Cozinha] Lanche pronto: " + lanche.getNome() + "\n");
    }

    // Etapas concretas — iguais para todos os tipos
    protected void esquentarChapa() {
        System.out.println("  1. Aquecendo chapa...");
    }

    protected void embalar(Lanche lanche) {
        System.out.println("  7. Embalando " + lanche.getNome() + " e entregando ao atendente.");
    }

    protected void adicionarExtras(Lanche lanche) {
        lanche.getExtras().forEach(e -> System.out.println("  6. Adicionando extra: " + e.getNome()));
    }

    // Etapas abstratas — cada subclasse define o comportamento específico
    protected abstract void prepararPao(Ingrediente pao);
    protected abstract void cozinharProteina(Ingrediente proteina);
    protected abstract void adicionarQueijo(Ingrediente queijo);
    protected abstract void montarLanche(Lanche lanche);
    protected abstract void aplicarMolho(Ingrediente molho);
}
