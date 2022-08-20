package dev.unscrud.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
  protected static final String BASE_URL = "http://localhost:8080/";
  protected WebDriver browser;

  public PageObject(WebDriver browser) {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    if (browser == null)
      browser = new ChromeDriver();
    this.browser = browser;
    this.browser.manage().timeouts()
        .implicitlyWait(5, TimeUnit.SECONDS)
        .pageLoadTimeout(10, TimeUnit.SECONDS);
  }

  public void fechar() {
    browser.quit();
  }

  public void esperaCarregar(String elemento) {
    WebDriverWait wait = new WebDriverWait(browser, 2);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elemento)));
  }
}
