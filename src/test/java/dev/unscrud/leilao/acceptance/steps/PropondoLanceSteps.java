package dev.unscrud.leilao.acceptance.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.unscrud.leilao.model.Lance;
import dev.unscrud.leilao.model.Leilao;
import dev.unscrud.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class PropondoLanceSteps {
  private Lance lance;
  private Leilao leilao;
  private ArrayList<Lance> lista;

  @Before
  public void setup() {
    this.lista = new ArrayList<Lance>();
    leilao = new Leilao("Tablet XPTO");
  }

  @Dado("um lance valido")
  public void dado_um_lance_valido() {
    lance = new Lance(new Usuario("Ana"), BigDecimal.TEN);
  }

  @Quando("propoe o lance")
  public void quando_propoe_o_lance() {
    leilao.propoe(lance);
  }

  @Entao("o lance eh aceito")
  public void entao_o_lance_eh_aceito() {
    assertEquals(1, leilao.getLances().size());
    assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
  }

  @Dado("um lance de {double} reais do usuario {string}")
  public void dado_um_lance_de_reais_do_usuario(Double valor, String nomeDoUsuario) {
    Lance lance = new Lance(new Usuario(nomeDoUsuario), new BigDecimal(valor));
    lista.add(lance);
  }

  @Quando("propoe os lances")
  public void quando_propoe_os_lances() {
    this.lista.forEach(lance -> leilao.propoe(lance));
  }

  @Entao("os lances sao aceitos")
  public void entao_os_lances_sao_aceitos() {
    assertEquals(this.lista.size(), leilao.getLances().size());
    assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
    assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
  }

  @Dado("um lance invalido de {double} reais do usuario {string}")
  public void dado_um_lance_invalido(Double valor, String nomeUsuario) {
    System.out.println(nomeUsuario);
    lance = new Lance(new BigDecimal(valor));
  }

  @Entao("o lance nao eh aceito")
  public void entao_o_lance_nao_eh_aceito() {
    assertEquals(0, leilao.getLances().size());
  }

  @Dado("dois lances")
  public void dois_lances(DataTable dataTable) {
    List<Map<String, String>> valores = dataTable.asMaps();

    for (Map<String, String> mapa : valores) {
      BigDecimal valor = new BigDecimal(mapa.get("valor"));
      Usuario usuario = new Usuario(mapa.get("nomeUsuario"));
      Lance lance = new Lance(usuario, valor);
      lista.add(lance);
    }
  }

  @Entao("o segundo lance nao eh aceito")
  public void entao_o_segundo_lance_nao_eh_aceito() {
    assertEquals(1, leilao.getLances().size());
    assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
  }
}
