package dev.unscrud.leilao.login;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
  private WebDriver browser;
  private final String BASE_URL = "http://localhost:8080/";
  private final String LOGIN_URL = BASE_URL + "login";

  @BeforeAll
  private static void antesDeTudo() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
  }

  @BeforeEach
  private void inicializar() {
    browser = new ChromeDriver();
    browser.navigate().to(LOGIN_URL);
  }

  @Test
  public void deveriaEfetuarLoginComDadosValidos() {
    browser.findElement(By.id("username")).sendKeys("fulano");
    browser.findElement(By.id("password")).sendKeys("pass");
    browser.findElement(By.id("login-form")).submit();
    assertFalse(browser.getCurrentUrl().equals(LOGIN_URL));
    assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
  }

  @Test
  public void naoDeveriaLogarComDadosInvalidos() {
    browser.findElement(By.id("username")).sendKeys("usuarioinvalido");
    browser.findElement(By.id("password")).sendKeys("senhainvalida");
    browser.findElement(By.id("login-form")).submit();
    assertTrue(browser.getCurrentUrl().equals(LOGIN_URL + "?error"));
    assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
    assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());
  }

  @Test
  public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
    browser.navigate().to(BASE_URL + "leiloes/2");
    assertTrue(browser.getCurrentUrl().equals(LOGIN_URL));
    assertFalse(browser.getPageSource().contains("Dados do Leilão"));
  }

  @AfterEach
  private void finalizar() {
    browser.quit();
  }

}
