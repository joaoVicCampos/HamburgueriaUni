package com.hamburgueriauni.lanche;

import com.hamburgueriauni.ingrediente.Ingrediente;

// Template Method concreto: preparo estilo tradicional (chapa quente, ponto médio)
public class PreparacaoTradicional extends PreparacaoLanche {

    @Override
    protected void prepararPao(Ingrediente pao) {
        System.out.println("  2. Tostando " + pao.getNome() + " na chapa por 1 min.");
    }

    @Override
    protected void cozinharProteina(Ingrediente proteina) {
        System.out.println("  3. Grelhando " + proteina.getNome() + " ao ponto médio (3 min cada lado).");
    }

    @Override
    protected void adicionarQueijo(Ingrediente queijo) {
        System.out.println("  4. Derretendo " + queijo.getNome() + " sobre a carne.");
    }

    @Override
    protected void montarLanche(Lanche lanche) {
        System.out.println("  5. Montando: pão + proteína + queijo + vegetais.");
    }

    @Override
    protected void aplicarMolho(Ingrediente molho) {
        System.out.println("  6. Aplicando " + molho.getNome() + " no pão superior.");
    }
}
