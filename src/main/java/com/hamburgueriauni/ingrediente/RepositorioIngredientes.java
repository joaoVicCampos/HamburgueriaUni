package com.hamburgueriauni.ingrediente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// Padrão Flyweight: fábrica que garante reuso de instâncias de ingredientes idênticos.
// O estado intrínseco (nome, tipo, preço, vegano) é compartilhado entre todos os lanches
// que usam o mesmo ingrediente — nenhuma cópia nova é criada.
public class RepositorioIngredientes {

    private static final Map<String, Ingrediente> cache = new HashMap<>();

    private RepositorioIngredientes() {}

    public static Ingrediente obter(String nome, TipoIngrediente tipo, double preco, boolean vegano) {
        String chave = nome.toLowerCase().trim();
        if (!cache.containsKey(chave)) {
            cache.put(chave, new IngredienteImpl(nome, tipo, preco, vegano));
            System.out.println("[Flyweight] Novo ingrediente criado no pool: " + nome);
        }
        return cache.get(chave);
    }

    // Atalho para buscar um ingrediente já existente no cache
    public static Ingrediente buscar(String nome) {
        return cache.get(nome.toLowerCase().trim());
    }

    public static int totalNoPool()                { return cache.size(); }
    public static Collection<Ingrediente> todos()  { return cache.values(); }

    public static void exibirPool() {
        System.out.println("[Flyweight] Pool de ingredientes (" + cache.size() + " instâncias compartilhadas):");
        cache.values().forEach(i -> System.out.println("  " + i));
    }
}
