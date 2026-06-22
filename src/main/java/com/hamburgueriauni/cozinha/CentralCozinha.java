package com.hamburgueriauni.cozinha;

import java.util.ArrayList;
import java.util.List;

// Padrão Mediator: coordena toda a comunicação entre Atendente, Cozinheiro e Cliente.
// Nenhum colaborador conhece os outros diretamente — tudo passa pela central.
public class CentralCozinha implements CozinhaMediator {

    private final List<ColaboradorCozinha> colaboradores = new ArrayList<>();

    @Override
    public void registrar(ColaboradorCozinha colaborador) {
        colaboradores.add(colaborador);
        colaborador.setMediator(this);
        System.out.println("[Mediator] " + colaborador.getNome() + " registrado na central.");
    }

    @Override
    public void enviarMensagem(ColaboradorCozinha remetente, String evento, String dados) {
        System.out.println("[Mediator] " + remetente.getNome() + " → evento: '" + evento + "' | " + dados);

        switch (evento) {
            case "NOVO_PEDIDO" ->
                // Atendente abriu pedido → avisa o Cozinheiro
                notificarPor("COZINHEIRO", evento, dados);

            case "PEDIDO_PRONTO" ->
                // Cozinheiro terminou → avisa Atendente e Cliente
                notificarExceto(remetente, evento, dados);

            case "PEDIDO_ENTREGUE" ->
                // Atendente entregou → avisa todos (log)
                notificarExceto(remetente, evento, dados);

            default ->
                notificarExceto(remetente, evento, dados);
        }
    }

    private void notificarPor(String tipoNome, String evento, String dados) {
        colaboradores.stream()
                .filter(c -> c.getNome().toUpperCase().contains(tipoNome))
                .forEach(c -> c.receberMensagem(evento, dados));
    }

    private void notificarExceto(ColaboradorCozinha remetente, String evento, String dados) {
        colaboradores.stream()
                .filter(c -> c != remetente)
                .forEach(c -> c.receberMensagem(evento, dados));
    }
}
