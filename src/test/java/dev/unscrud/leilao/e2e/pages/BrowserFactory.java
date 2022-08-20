package dev.unscrud.leilao.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BrowserFactory {

  // Para consulta
  // http://chromedriver.storage.googleapis.com/index.html
  // https://github.com/mozilla/geckodriver/releases

  public WebDriver createWebDriver() {
    String webDriver = System.getProperty("browser", "htmlunit");
    switch (webDriver) {
      case "firefox":
        return initFirefoxDriver();
      case "chrome":
        return initChromeDriver();
      default:
        return new HtmlUnitDriver();
    }
  }

  private WebDriver initChromeDriver() {
    return new ChromeDriver();
  }

  private WebDriver initFirefoxDriver() {
    return new FirefoxDriver();
  }

}
