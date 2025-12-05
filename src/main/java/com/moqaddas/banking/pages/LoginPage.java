package com.moqaddas.banking.pages;

import com.moqaddas.banking.pages.dashboard.AccountOverviewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage<LoginPage> {
  private static final By USERNAME = By.name("username");
  private static final By PASSWORD = By.name("password");
  private static final By LOGIN_BUTTON = By.cssSelector("input[value='Log In']");
  private static final By HEADING = By.cssSelector("#leftPanel h2");
  private static final By ERROR_MESSAGE = By.cssSelector(".error");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public String headingText() {
    return driver.findElement(HEADING).getText().trim();
  }

  public AccountOverviewPage loginAs(String user, String pass) {
    waitForElement(USERNAME).clear();
    driver.findElement(USERNAME).sendKeys(user);
    waitForElement(PASSWORD).clear();
    driver.findElement(PASSWORD).sendKeys(pass);
    waitForClickable(LOGIN_BUTTON).click();
    sleep(1000); // Wait for page transition
    
    // Check for login error
    try {
      if (!driver.findElements(ERROR_MESSAGE).isEmpty()) {
        String error = driver.findElement(ERROR_MESSAGE).getText();
        System.out.println("LOGIN ERROR: " + error);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());
      }
    } catch (Exception e) {
      // Error element not found, continue
    }
    
    return new AccountOverviewPage(driver);
  }

  @Override
  public boolean isLoaded() {
    return driver.findElement(HEADING).isDisplayed();
  }
}
