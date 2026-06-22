package com.hamburgueriauni.sistema;

import com.hamburgueriauni.pedido.Pedido;

import java.util.HashMap;
import java.util.Map;

// Padrão Proxy: intercepta chamadas ao GerenciadorEstoque adicionando
// log de auditoria, controle de acesso e cache de consultas recentes.
public class ProxyEstoque implements EstoqueProxy {

    private final GerenciadorEstoque real;
    private final Map<String, Boolean> cache = new HashMap<>();
    private boolean acessoPermitido = true;

    public ProxyEstoque(GerenciadorEstoque real) {
        this.real = real;
    }

    public void bloquearAcesso()    { this.acessoPermitido = false; }
    public void liberarAcesso()     { this.acessoPermitido = true; }

    @Override
    public boolean verificarDisponibilidade(Pedido pedido) {
        if (!acessoPermitido) {
            System.out.println("[Proxy] Acesso ao estoque BLOQUEADO para pedido #" + pedido.getId());
            return false;
        }

        String chaveCache = "pedido_" + pedido.getId();
        if (cache.containsKey(chaveCache)) {
            System.out.println("[Proxy] Resultado do cache para pedido #" + pedido.getId()
                    + ": " + cache.get(chaveCache));
            return cache.get(chaveCache);
        }

        System.out.println("[Proxy] Consultando estoque real para pedido #" + pedido.getId() + "...");
        boolean resultado = real.verificarDisponibilidade(pedido);
        cache.put(chaveCache, resultado);
        return resultado;
    }

    @Override
    public void baixarEstoque(Pedido pedido) {
        if (!acessoPermitido) {
            System.out.println("[Proxy] Baixa de estoque BLOQUEADA para pedido #" + pedido.getId());
            return;
        }
        System.out.println("[Proxy] Autorizando baixa de estoque para pedido #" + pedido.getId());
        real.baixarEstoque(pedido);
        cache.remove("pedido_" + pedido.getId());
    }

    @Override
    public int getQuantidade(String nomeIngrediente) {
        System.out.println("[Proxy] Consultando quantidade: " + nomeIngrediente);
        return real.getQuantidade(nomeIngrediente);
    }
}
