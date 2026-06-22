package com.hamburgueriauni.lanche;

import com.hamburgueriauni.ingrediente.Ingrediente;

// Template Method concreto: preparo gourmet (selagem rápida em alta temperatura, queijo derretido no forno)
public class PreparacaoGourmet extends PreparacaoLanche {

    @Override
    protected void prepararPao(Ingrediente pao) {
        System.out.println("  2. Tostando " + pao.getNome() + " com manteiga clarificada por 90 seg.");
    }

    @Override
    protected void cozinharProteina(Ingrediente proteina) {
        System.out.println("  3. Selando " + proteina.getNome() + " em alta temperatura (2 min) — ao ponto.");
    }

    @Override
    protected void adicionarQueijo(Ingrediente queijo) {
        System.out.println("  4. Gratinando " + queijo.getNome() + " sob o salamander por 45 seg.");
    }

    @Override
    protected void montarLanche(Lanche lanche) {
        System.out.println("  5. Montando com pinça: pão → proteína → queijo gratinado → microverdes.");
    }

    @Override
    protected void aplicarMolho(Ingrediente molho) {
        System.out.println("  6. Finalizando com " + molho.getNome() + " em espiral decorativa.");
    }
}
