package dev.unscrud.leilao.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
  private WebDriver browser;
  private final String LOGIN_URL = "http://localhost:8080/login";

  @BeforeEach
  private void inicializar() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    browser = new ChromeDriver();
  }

  @Test
  public void deveriaEfetuarLoginComDadosValidos() {
    browser.navigate().to(LOGIN_URL);
    browser.findElement(By.id("username")).sendKeys("fulano");
    browser.findElement(By.id("password")).sendKeys("pass");
    browser.findElement(By.id("login-form")).submit();
    assertFalse(browser.getCurrentUrl().equals("LOGIN_URL"));
    assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    browser.quit();
  }
}
