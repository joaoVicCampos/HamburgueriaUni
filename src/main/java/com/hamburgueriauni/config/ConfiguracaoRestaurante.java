package com.hamburgueriauni.config;

// Padrão Singleton: garante uma única instância da configuração global do restaurante
public class ConfiguracaoRestaurante {

    private static volatile ConfiguracaoRestaurante instancia;

    private String nomeRestaurante;
    private String endereco;
    private double taxaEntregaDelivery;
    private int capacidadeMesas;
    private double descontoFidelidade;

    private ConfiguracaoRestaurante() {
        this.nomeRestaurante    = "HamburgueriaUni";
        this.endereco           = "Rua dos Padrões de Projeto, 23";
        this.taxaEntregaDelivery = 6.00;
        this.capacidadeMesas    = 10;
        this.descontoFidelidade = 0.10;
    }

    public static ConfiguracaoRestaurante getInstancia() {
        if (instancia == null) {
            synchronized (ConfiguracaoRestaurante.class) {
                if (instancia == null) {
                    instancia = new ConfiguracaoRestaurante();
                }
            }
        }
        return instancia;
    }

    public String getNomeRestaurante()       { return nomeRestaurante; }
    public String getEndereco()              { return endereco; }
    public double getTaxaEntregaDelivery()   { return taxaEntregaDelivery; }
    public int getCapacidadeMesas()          { return capacidadeMesas; }
    public double getDescontoFidelidade()    { return descontoFidelidade; }

    public void setTaxaEntregaDelivery(double taxa) { this.taxaEntregaDelivery = taxa; }
    public void setDescontoFidelidade(double d)     { this.descontoFidelidade = d; }

    @Override
    public String toString() {
        return String.format("[Config] %s | Taxa delivery: R$%.2f | Mesas: %d | Desconto fidelidade: %.0f%%",
                nomeRestaurante, taxaEntregaDelivery, capacidadeMesas, descontoFidelidade * 100);
    }
}
