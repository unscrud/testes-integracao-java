package dev.unscrud.leilao.lances;

import dev.unscrud.leilao.PageObject;

public class LancesPage extends PageObject {
  private static final String LANCES_URL = BASE_URL + "leiloes/2";

  public LancesPage() {
    super(null);
    this.browser.navigate().to(LANCES_URL);
  }

  public boolean isPaginaDeLances() {
    return browser.getCurrentUrl().contains(LANCES_URL);
  }

  public boolean isTituloLeilaoVisivel() {
    String texto = "Dados do Leilão";
    esperaCarregar("//h1[contains(text(),'" + texto + "')]");
    return browser.getPageSource().contains(texto);
  }

}
