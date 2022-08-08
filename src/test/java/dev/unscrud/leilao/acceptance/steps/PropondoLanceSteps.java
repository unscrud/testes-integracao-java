package dev.unscrud.leilao.acceptance.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import dev.unscrud.leilao.model.Lance;
import dev.unscrud.leilao.model.Leilao;
import dev.unscrud.leilao.model.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class PropondoLanceSteps {
  private Lance lance;
  private Lance lanceA;
  private Lance lanceB;
  private Leilao leilao;

  @Dado("um lance valido")
  public void dado_um_lance_valido() {
    lance = new Lance(new Usuario("Ana"), BigDecimal.TEN);
  }

  @Quando("propoe o lance")
  public void quando_propoe_o_lance() {
    leilao = new Leilao("PS Station");
    leilao.propoe(lance);
  }

  @Entao("o lance eh aceito")
  public void entao_o_lance_eh_aceito() {
    assertEquals(1, leilao.getLances().size());
    assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
  }

  @Dado("varios lances validos")
  public void dado_varios_lances_validos() {
    lanceA = new Lance(new Usuario("Ana"), BigDecimal.TEN);
    lanceB = new Lance(new Usuario("Pedro"), new BigDecimal("15"));
  }

  @Quando("propoe os lances")
  public void quando_propoe_os_lances() {
    leilao = new Leilao("PS Station");
    leilao.propoe(lanceA);
    leilao.propoe(lanceB);
  }

  @Entao("os lances sao aceitos")
  public void entao_os_lances_sao_aceitos() {
    assertEquals(2, leilao.getLances().size());
    assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    assertEquals(new BigDecimal("15"), leilao.getLances().get(1).getValor());
  }
}
