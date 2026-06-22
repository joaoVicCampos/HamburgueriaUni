package com.hamburgueriauni.lanche;

import com.hamburgueriauni.ingrediente.Ingrediente;

// Template Method concreto: preparo vegano (sem chapa de carne, temperatura controlada)
public class PreparacaoVegana extends PreparacaoLanche {

    @Override
    protected void prepararPao(Ingrediente pao) {
        System.out.println("  2. Aquecendo " + pao.getNome() + " no forno por 2 min (sem manteiga).");
    }

    @Override
    protected void cozinharProteina(Ingrediente proteina) {
        System.out.println("  3. Grelhando " + proteina.getNome() + " com azeite (4 min cada lado).");
    }

    @Override
    protected void adicionarQueijo(Ingrediente queijo) {
        System.out.println("  4. Adicionando " + queijo.getNome() + " frio (não derrete na chapa).");
    }

    @Override
    protected void montarLanche(Lanche lanche) {
        System.out.println("  5. Montando: pão + proteína vegetal + folhas frescas.");
    }

    @Override
    protected void aplicarMolho(Ingrediente molho) {
        System.out.println("  6. Espalhando " + molho.getNome() + " em ambas as metades do pão.");
    }
}
