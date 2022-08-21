package dev.unscrud.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dev.unscrud.leilao.PageObject;

public class LeiloesPage extends PageObject {
  private static final String LEILOES_URL = BASE_URL + "leiloes";
  private static final String NOVO_LEILAO_URL = LEILOES_URL + "/new";

  public LeiloesPage(WebDriver browser) {
    super(browser);
  }

  public CadastroLeilaoPage abrirFormulario() {
    browser.navigate().to(NOVO_LEILAO_URL);
    return new CadastroLeilaoPage(browser);
  }

  public boolean isLeilaoCadastrado(String nome, String valor, String data) {
    WebElement ultimaLinha = browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));

    WebElement colunaNome = ultimaLinha.findElement(By.cssSelector("td:nth-child(1)"));
    WebElement colunaDataAbertura = ultimaLinha.findElement(By.cssSelector("td:nth-child(2)"));
    WebElement colunaValorInicial = ultimaLinha.findElement(By.cssSelector("td:nth-child(3)"));

    return colunaNome.getText().equals(nome)
        && colunaDataAbertura.getText().equals(data)
        && colunaValorInicial.getText().equals(valor);
  }

  public boolean isPaginaDeLeiloes() {
    return browser.getCurrentUrl().equals(LEILOES_URL);
  }
}
