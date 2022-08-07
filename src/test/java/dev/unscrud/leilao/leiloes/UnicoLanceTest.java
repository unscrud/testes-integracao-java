package dev.unscrud.leilao.leiloes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import dev.unscrud.leilao.model.Lance;
import dev.unscrud.leilao.model.Leilao;
import dev.unscrud.leilao.model.Usuario;

@Nested
@DisplayName("Dado um lance válido")
public class UnicoLanceTest {

  Leilao leilao = new Leilao("Notebook Asus A310");
  BigDecimal doisMil = new BigDecimal("2000.00");

  @Nested
  @DisplayName("Quando propõe o lance")
  class assertLanceNormal {

    @Test
    @DisplayName("Então o lance é aceito")
    public void deveReceberUmLance() {
      leilao.propoe(new Lance(new Usuario("Juliana"), doisMil));
      assertEquals(1, leilao.getLances().size());
      assertEquals(doisMil, leilao.getLances().get(0).getValor());
    }

  }
}
