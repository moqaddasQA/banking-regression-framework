package com.moqaddas.banking.tests;

import com.moqaddas.banking.support.ConfigManager;
import com.moqaddas.banking.support.DriverFactory;
import com.moqaddas.banking.support.DriverManager;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Listeners({ReportingListener.class, AllureTestNg.class})
public abstract class BaseTest {
  protected WebDriver driver;
  
  // Shared timestamped username for all tests to avoid "username already exists" errors
  // Using static initialization block to ensure it's created once per test run
  protected static final String TEST_USERNAME;
  protected static final String TEST_PASSWORD = "123456";
  
  // Instance variable for unique username per test iteration (for data-driven tests)
  protected String uniqueUsername;
  
  static {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmss"));
    TEST_USERNAME = "Moqaddas" + timestamp;
  }

  @BeforeMethod
  public void openBrowser() {
    // Generate unique username for this test iteration
    String iterationTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmssSSS"));
    uniqueUsername = "Moq" + iterationTimestamp;
    
    driver = DriverFactory.createChrome();
    DriverManager.setDriver(driver);
    if (!ConfigManager.headless()) {
      driver.manage().window().maximize();
    }
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
