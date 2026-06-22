package com.hamburgueriauni.sistema;

import java.util.List;

// Bridge — Concrete Implementor: renderiza relatórios no terminal com formatação simples
public class SaidaTerminal implements FormatoSaida {

    @Override
    public void imprimirTitulo(String titulo) {
        System.out.println("\n========== " + titulo.toUpperCase() + " ==========");
    }

    @Override
    public void imprimirLinha(String chave, String valor) {
        System.out.printf("  %-20s %s%n", chave + ":", valor);
    }

    @Override
    public void imprimirSeparador() {
        System.out.println("  ------------------------------------------");
    }

    @Override
    public void imprimirLista(String titulo, List<String> itens) {
        System.out.println("  " + titulo + ":");
        itens.forEach(i -> System.out.println("    • " + i));
    }
}
