package com.moqaddas.banking.pages;

import com.moqaddas.banking.pages.dashboard.AccountOverviewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage<LoginPage> {
  private static final By USERNAME = By.name("username");
  private static final By PASSWORD = By.name("password");
  private static final By LOGIN_BUTTON = By.cssSelector("input[value='Log In']");
  private static final By HEADING = By.cssSelector("#leftPanel h2");

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
    return new AccountOverviewPage(driver);
  }

  @Override
  public boolean isLoaded() {
    return driver.findElement(HEADING).isDisplayed();
  }
}
