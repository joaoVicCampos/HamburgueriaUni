package com.hamburgueriauni.pedido;

// Padrão Factory Method: subclasses decidem qual tipo concreto de Pedido criar
public abstract class FabricaDePedido {

    // Factory Method — subclasse implementa
    public abstract Pedido criarPedido(String cliente);

    // Operação que usa o factory method
    public Pedido abrirPedido(String cliente) {
        Pedido pedido = criarPedido(cliente);
        System.out.println("Pedido aberto: " + pedido.getResumo());
        return pedido;
    }
}
