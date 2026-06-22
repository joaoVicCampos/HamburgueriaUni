package com.hamburgueriauni.lanche;

import com.hamburgueriauni.ingrediente.IngredienteImpl;
import com.hamburgueriauni.ingrediente.TipoIngrediente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Testa o padrão Builder
class BuilderTest {

    private final IngredienteImpl pao      = new IngredienteImpl("Pão", TipoIngrediente.PAO, 2.00, false);
    private final IngredienteImpl proteina = new IngredienteImpl("Carne", TipoIngrediente.PROTEINA, 12.00, false);
    private final IngredienteImpl queijo   = new IngredienteImpl("Cheddar", TipoIngrediente.QUEIJO, 3.00, false);
    private final IngredienteImpl molho    = new IngredienteImpl("Molho", TipoIngrediente.MOLHO, 1.00, true);
    private final IngredienteImpl bacon    = new IngredienteImpl("Bacon", TipoIngrediente.ADICIONAL, 4.00, false);

    @Test
    void deveConstruirLancheComTodosOsComponentes() {
        Lanche lanche = new LancheBuilderImpl()
                .comNome("Meu Burguer")
                .comPao(pao)
                .comProteina(proteina)
                .comQueijo(queijo)
                .comMolho(molho)
                .construir();

        assertEquals("Meu Burguer", lanche.getNome());
        assertEquals(pao,      lanche.getPao());
        assertEquals(proteina, lanche.getProteina());
        assertEquals(queijo,   lanche.getQueijo());
        assertEquals(molho,    lanche.getMolho());
    }

    @Test
    void precoDeveSerSomaDosIngredientes() {
        Lanche lanche = new LancheBuilderImpl()
                .comNome("Soma Test")
                .comPao(pao)
                .comProteina(proteina)
                .comQueijo(queijo)
                .comMolho(molho)
                .comExtra(bacon)
                .construir();

        assertEquals(22.00, lanche.calcularPreco(), 0.001);
    }

    @Test
    void devePermitirMultiplosExtras() {
        Lanche lanche = new LancheBuilderImpl()
                .comNome("Extra Test")
                .comPao(pao)
                .comProteina(proteina)
                .comExtra(bacon)
                .comExtra(queijo)
                .construir();

        assertEquals(2, lanche.getExtras().size());
    }

    @Test
    void semPaoDeveLancarExcecao() {
        assertThrows(IllegalStateException.class, () ->
            new LancheBuilderImpl()
                .comNome("Sem Pao")
                .comProteina(proteina)
                .construir()
        );
    }

    @Test
    void semProteinaDeveLancarExcecao() {
        assertThrows(IllegalStateException.class, () ->
            new LancheBuilderImpl()
                .comNome("Sem Proteina")
                .comPao(pao)
                .construir()
        );
    }

    @Test
    void observacoesDevemSerArmazenadas() {
        Lanche lanche = new LancheBuilderImpl()
                .comNome("Com Obs")
                .comPao(pao)
                .comProteina(proteina)
                .comObservacoes("Sem cebola")
                .construir();

        assertEquals("Sem cebola", lanche.getObservacoes());
    }
}
