package com.moqaddas.banking.tests;

import com.moqaddas.banking.support.ConfigManager;
import com.moqaddas.banking.support.DriverFactory;
import com.moqaddas.banking.support.DriverManager;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ReportingListener.class, AllureTestNg.class})
public abstract class BaseTest {
  protected WebDriver driver;

  @BeforeMethod
  public void openBrowser() {
    driver = DriverFactory.createChrome();
    DriverManager.setDriver(driver);
    driver.manage().window().maximize();
    driver.get(ConfigManager.baseUrl());
  }

  @AfterMethod(alwaysRun = true)
  public void closeBrowser() {
    if (driver != null) {
      driver.quit();
    }
    DriverManager.unload();
  }
}
