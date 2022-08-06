package dev.unscrud.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {
  protected static final String BASE_URL = "http://localhost:8080/";
  protected WebDriver browser;

  public PageObject(WebDriver browser) {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    if (browser == null)
      browser = new ChromeDriver();
    this.browser = browser;
  }

  public void fechar() {
    browser.quit();
  }
}
