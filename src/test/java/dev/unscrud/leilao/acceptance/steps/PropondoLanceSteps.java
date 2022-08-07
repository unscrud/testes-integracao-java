package dev.unscrud.leilao.acceptance.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PropondoLanceSteps {
  @Given("Dado um lance valido")
  public void dado_um_lance_valido(){
    System.out.println("Código do given executado");
  }
  
  @When("Quando propoe o lance")
  public void quando_propoe_o_lance(){
    System.out.println("Código do when executado");
    
  }
  
  @Then("Entao o lance eh aceito")
  public void entao_o_lance_eh_aceito(){
    System.out.println("Código do then executado");

  }
}
