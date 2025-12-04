package com.moqaddas.banking.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {
  private DriverFactory() {}

  public static WebDriver createChrome() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
    options.addArguments("--disable-blink-features=AutomationControlled");
    if (ConfigManager.headless()) {
      options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
    }
    return new ChromeDriver(options);
  }
}
