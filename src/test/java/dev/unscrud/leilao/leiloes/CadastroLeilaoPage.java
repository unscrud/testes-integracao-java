package dev.unscrud.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage {
  private final String BASE_URL = "http://localhost:8080/leiloes";
  private final String NOVO_LEILAO_URL = BASE_URL + "/new";

  private WebDriver browser;

  public CadastroLeilaoPage(WebDriver browser) {
    this.browser = browser;
  }

  public void fechar() {
    browser.quit();
  }

  public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
    browser.findElement(By.id("nome")).sendKeys(nome);
    browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
    browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
    browser.findElement(By.id("button-submit")).submit();
    return new LeiloesPage(browser);
  }

  public boolean isPaginaAtual() {
    return browser.getCurrentUrl().equals(NOVO_LEILAO_URL);
  }

  public boolean isMensagensDeValidacaoVisiveis() {
    String pageSource = browser.getPageSource();
    return pageSource.contains("minimo 3 caracteres")
        && pageSource.contains("deve ser um valor maior de 0.1")
        && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
  }

}
