package dev.unscrud.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.unscrud.leilao.leiloes.LeiloesPage;

public class LoginPage {
  private WebDriver browser;
  private final String BASE_URL = "http://localhost:8080/";
  private final String LOGIN_URL = BASE_URL + "login";

  public LoginPage() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    browser = new ChromeDriver();
    browser.navigate().to(LOGIN_URL);
  }

  public void fechar() {
    this.browser.quit();
  }

  public void preencheUsuarioSenha(String usuario, String senha) {
    browser.findElement(By.id("username")).sendKeys(usuario);
    browser.findElement(By.id("password")).sendKeys(senha);
  }

  public LeiloesPage efetuaLogin() {
    browser.findElement(By.id("login-form")).submit();
    return new LeiloesPage(browser);
  }

  public boolean isPaginaDeLogin() {
    return browser.getCurrentUrl().equals(LOGIN_URL);
  }

  public Object getNomeUsuarioLogado() {
    try {
      return browser.findElement(By.id("usuario-logado")).getText();
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  public boolean isPaginaDeLoginComErro() {
    return browser.getCurrentUrl().equals(LOGIN_URL + "?error");
  }

  public void navegaParaPaginaDeLances() {
    browser.navigate().to(BASE_URL + "leiloes/2");
  }

  public boolean possuiString(String string) {
    return browser.getPageSource().contains(string);
  }

}
