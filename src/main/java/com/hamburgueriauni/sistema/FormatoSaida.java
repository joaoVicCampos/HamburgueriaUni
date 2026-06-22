package com.hamburgueriauni.sistema;

import java.util.List;

// Padrão Bridge — Implementor: define a interface de renderização independente do conteúdo
public interface FormatoSaida {
    void imprimirTitulo(String titulo);
    void imprimirLinha(String chave, String valor);
    void imprimirSeparador();
    void imprimirLista(String titulo, List<String> itens);
}
