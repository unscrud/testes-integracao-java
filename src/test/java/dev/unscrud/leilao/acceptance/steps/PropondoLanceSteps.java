package dev.unscrud.leilao.acceptance.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import dev.unscrud.leilao.model.Lance;
import dev.unscrud.leilao.model.Leilao;
import dev.unscrud.leilao.model.Usuario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PropondoLanceSteps {
  private Lance lance;
  private Leilao leilao;

  @Given("Dado um lance valido")
  public void dado_um_lance_valido(){
    lance = new Lance(new Usuario("Ana"), BigDecimal.TEN);
  }
  
  @When("Quando propoe o lance")
  public void quando_propoe_o_lance(){
    leilao = new Leilao("PS Station");
    leilao.propoe(lance);
  }
  
  @Then("Entao o lance eh aceito")
  public void entao_o_lance_eh_aceito(){
    assertEquals(1, leilao.getLances().size());
    assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
  }
}
