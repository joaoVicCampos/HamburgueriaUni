package com.hamburgueriauni.sistema;

import java.util.List;

// Bridge — Concrete Implementor: renderiza relatórios no formato de cupom fiscal (80 colunas)
public class SaidaCupom implements FormatoSaida {

    private static final String LINHA = "-".repeat(40);

    @Override
    public void imprimirTitulo(String titulo) {
        System.out.println(LINHA);
        System.out.printf("%s%n", centrar(titulo, 40));
        System.out.println(LINHA);
    }

    @Override
    public void imprimirLinha(String chave, String valor) {
        String linha = chave + ": " + valor;
        System.out.println(linha.length() > 40 ? linha.substring(0, 40) : linha);
    }

    @Override
    public void imprimirSeparador() {
        System.out.println(LINHA);
    }

    @Override
    public void imprimirLista(String titulo, List<String> itens) {
        System.out.println(titulo.toUpperCase());
        itens.forEach(i -> System.out.println("  > " + i));
    }

    private String centrar(String texto, int largura) {
        int pad = (largura - texto.length()) / 2;
        return " ".repeat(Math.max(0, pad)) + texto;
    }
}
