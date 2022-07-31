package dev.unscrud.leilao.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteSelenium {
  private WebDriver browser;
  private final String LEILOES_URL = "http://localhost:8080/leiloes";

  @BeforeEach
  private void inicializar() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    browser = new ChromeDriver();
  }

  @Test
  public void abrirNavegador() {
    browser.navigate().to(LEILOES_URL);
    browser.quit();
  }
}
