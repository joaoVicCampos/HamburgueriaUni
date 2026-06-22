package com.hamburgueriauni.cardapio;

import com.hamburgueriauni.ingrediente.RepositorioIngredientes;
import com.hamburgueriauni.ingrediente.TipoIngrediente;

// Cardápio raiz: Composite que agrupa categorias.
// Usa Flyweight (RepositorioIngredientes) para reaproveitar ingredientes.
// Itens podem ser clonados via Prototype para personalização.
public class Cardapio {

    private final CardapioCategoria raiz;

    public Cardapio() {
        raiz = new CardapioCategoria("Cardápio HamburgueriaUni", "Menu completo");
        inicializar();
    }

    private void inicializar() {
        // --- Categoria: Lanches Tradicionais ---
        CardapioCategoria tradicionais = new CardapioCategoria("Lanches Tradicionais", "Receitas clássicas");

        CardapioFolha xBurguer = new CardapioFolha("X-Burguer", 0, "Blend bovino com cheddar");
        xBurguer.adicionarIngrediente(RepositorioIngredientes.obter("Pão Brioche",       TipoIngrediente.PAO,      2.00, false));
        xBurguer.adicionarIngrediente(RepositorioIngredientes.obter("Blend Bovino 150g", TipoIngrediente.PROTEINA, 12.00, false));
        xBurguer.adicionarIngrediente(RepositorioIngredientes.obter("Queijo Cheddar",    TipoIngrediente.QUEIJO,   3.00, false));
        xBurguer.adicionarIngrediente(RepositorioIngredientes.obter("Molho da Casa",     TipoIngrediente.MOLHO,    1.00, true));

        CardapioFolha xBacon = new CardapioFolha("X-Bacon", 0, "Com bacon crocante");
        xBacon.adicionarIngrediente(RepositorioIngredientes.obter("Pão Brioche",       TipoIngrediente.PAO,      2.00, false));
        xBacon.adicionarIngrediente(RepositorioIngredientes.obter("Blend Bovino 150g", TipoIngrediente.PROTEINA, 12.00, false));
        xBacon.adicionarIngrediente(RepositorioIngredientes.obter("Queijo Cheddar",    TipoIngrediente.QUEIJO,   3.00, false));
        xBacon.adicionarIngrediente(RepositorioIngredientes.obter("Bacon",             TipoIngrediente.ADICIONAL, 4.00, false));

        tradicionais.adicionar(xBurguer);
        tradicionais.adicionar(xBacon);

        // --- Categoria: Lanches Veganos ---
        CardapioCategoria veganos = new CardapioCategoria("Lanches Veganos", "100% de origem vegetal");

        CardapioFolha greenBurguer = new CardapioFolha("Green Burguer", 0, "Burger de grão-de-bico");
        greenBurguer.adicionarIngrediente(RepositorioIngredientes.obter("Pão Integral Vegano",      TipoIngrediente.PAO,      3.00, true));
        greenBurguer.adicionarIngrediente(RepositorioIngredientes.obter("Burger de Grão-de-Bico",   TipoIngrediente.PROTEINA, 11.00, true));
        greenBurguer.adicionarIngrediente(RepositorioIngredientes.obter("Queijo Vegano de Castanha", TipoIngrediente.QUEIJO,   4.00, true));
        greenBurguer.adicionarIngrediente(RepositorioIngredientes.obter("Maionese Vegana",           TipoIngrediente.MOLHO,    1.50, true));

        veganos.adicionar(greenBurguer);

        // --- Categoria: Lanches Gourmet ---
        CardapioCategoria gourmet = new CardapioCategoria("Lanches Gourmet", "Ingredientes premium");

        CardapioFolha wagyu = new CardapioFolha("Wagyu Especial", 0, "Blend Wagyu com Brie e trufas");
        wagyu.adicionarIngrediente(RepositorioIngredientes.obter("Pão Australiano",  TipoIngrediente.PAO,      4.00, false));
        wagyu.adicionarIngrediente(RepositorioIngredientes.obter("Blend Wagyu 180g", TipoIngrediente.PROTEINA, 22.00, false));
        wagyu.adicionarIngrediente(RepositorioIngredientes.obter("Brie Francês",     TipoIngrediente.QUEIJO,   6.00, false));
        wagyu.adicionarIngrediente(RepositorioIngredientes.obter("Aioli de Trufas",  TipoIngrediente.MOLHO,    3.50, false));

        gourmet.adicionar(wagyu);

        // --- Categoria: Bebidas ---
        CardapioCategoria bebidas = new CardapioCategoria("Bebidas", "Refrigerantes e sucos");
        bebidas.adicionar(new CardapioFolha("Refrigerante Lata",  6.00, "350ml gelado"));
        bebidas.adicionar(new CardapioFolha("Suco Natural",       9.00, "Laranja ou maracujá"));
        bebidas.adicionar(new CardapioFolha("Água Mineral",       3.50, "500ml com ou sem gás"));

        raiz.adicionar(tradicionais);
        raiz.adicionar(veganos);
        raiz.adicionar(gourmet);
        raiz.adicionar(bebidas);
    }

    public CardapioCategoria getRaiz() { return raiz; }

    public void exibir() {
        raiz.exibir("");
    }

    // Prototype: clona um item do cardápio para personalização sem alterar o original
    public CardapioFolha clonarItem(String nomeItem) {
        return buscarFolha(raiz, nomeItem);
    }

    private CardapioFolha buscarFolha(CardapioCategoria categoria, String nome) {
        for (ItemCardapio filho : categoria.getFilhos()) {
            if (filho instanceof CardapioFolha f && f.getNome().equalsIgnoreCase(nome)) {
                return f.clonar();
            }
            if (filho instanceof CardapioCategoria c) {
                CardapioFolha resultado = buscarFolha(c, nome);
                if (resultado != null) return resultado;
            }
        }
        return null;
    }
}
