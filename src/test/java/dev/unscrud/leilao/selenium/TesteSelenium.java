package dev.unscrud.leilao.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteSelenium {

  @Test
  public void abrirNavegador() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    WebDriver browser = new ChromeDriver();
    browser.navigate().to("http://localhost:8080/leiloes");
    browser.quit();
  }
}
