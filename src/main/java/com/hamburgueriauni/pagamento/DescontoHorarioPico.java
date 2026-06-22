package com.hamburgueriauni.pagamento;

import java.time.LocalTime;

// Strategy: desconto happy hour aplicado fora do horário de pico (após 14h e antes de 17h)
public class DescontoHorarioPico implements EstrategiaDesconto {

    private static final LocalTime INICIO_HAPPY_HOUR = LocalTime.of(14, 0);
    private static final LocalTime FIM_HAPPY_HOUR    = LocalTime.of(17, 0);
    private static final double PERCENTUAL           = 0.15;

    @Override
    public double aplicar(double valorTotal) {
        LocalTime agora = LocalTime.now();
        boolean emHappyHour = agora.isAfter(INICIO_HAPPY_HOUR) && agora.isBefore(FIM_HAPPY_HOUR);

        if (emHappyHour) {
            double desconto = valorTotal * PERCENTUAL;
            System.out.printf("[Strategy] Happy Hour ativo! (15%%): -R$%.2f%n", desconto);
            return valorTotal - desconto;
        }

        System.out.println("[Strategy] Fora do horário de Happy Hour — sem desconto.");
        return valorTotal;
    }

    @Override
    public String descricao() { return "Happy Hour (15% entre 14h e 17h)"; }
}
