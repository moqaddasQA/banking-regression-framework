package com.moqaddas.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage<RegisterPage> {
  private static final By REGISTER_LINK = By.linkText("Register");
  private static final By FIRST_NAME = By.id("customer.firstName");
  private static final By LAST_NAME = By.id("customer.lastName");
  private static final By ADDRESS = By.id("customer.address.street");
  private static final By CITY = By.id("customer.address.city");
  private static final By STATE = By.id("customer.address.state");
  private static final By ZIP_CODE = By.id("customer.address.zipCode");
  private static final By PHONE = By.id("customer.phoneNumber");
  private static final By SSN = By.id("customer.ssn");
  private static final By USERNAME = By.id("customer.username");
  private static final By PASSWORD = By.id("customer.password");
  private static final By CONFIRM_PASSWORD = By.id("repeatedPassword");
  private static final By REGISTER_BUTTON = By.cssSelector("input[value='Register']");

  public RegisterPage(WebDriver driver) {
    super(driver);
  }

  public RegisterPage clickRegisterLink() {
    waitForClickable(REGISTER_LINK).click();
    return this;
  }

  public RegisterPage fillRegistrationForm(String firstName, String lastName, 
                                            String username, String password) {
    waitForElement(FIRST_NAME).sendKeys(firstName);
    driver.findElement(LAST_NAME).sendKeys(lastName);
    driver.findElement(ADDRESS).sendKeys("123 Test St");
    driver.findElement(CITY).sendKeys("TestCity");
    driver.findElement(STATE).sendKeys("CA");
    driver.findElement(ZIP_CODE).sendKeys("12345");
    driver.findElement(PHONE).sendKeys("555-1234");
    driver.findElement(SSN).sendKeys("123-45-6789");
    driver.findElement(USERNAME).sendKeys(username);
    driver.findElement(PASSWORD).sendKeys(password);
    driver.findElement(CONFIRM_PASSWORD).sendKeys(password);
    return this;
  }

  public void submitRegistration() {
    System.out.println("Clicking register button...");
    waitForClickable(REGISTER_BUTTON).click();
    sleep(3000); // Wait for registration to complete
    System.out.println("After click, URL: " + driver.getCurrentUrl());
    
    // Check for validation errors
    try {
      if (!driver.findElements(By.cssSelector(".error")).isEmpty()) {
        driver.findElements(By.cssSelector(".error")).forEach(error -> {
          if (error.isDisplayed()) {
            System.out.println("VALIDATION ERROR: " + error.getText());
          }
        });
      }
      if (!driver.findElements(By.id("customer.username.errors")).isEmpty()) {
        System.out.println("USERNAME ERROR: " + driver.findElement(By.id("customer.username.errors")).getText());
      }
    } catch (Exception e) {
      System.out.println("No validation errors found or error checking failed");
    }
  }

  public String getSuccessMessage() {
    sleep(2000); // Wait for message to appear
    try {
      // Try multiple possible success message locators
      if (!driver.findElements(By.cssSelector("#rightPanel p")).isEmpty()) {
        return driver.findElement(By.cssSelector("#rightPanel p")).getText();
      }
      if (!driver.findElements(By.cssSelector(".title")).isEmpty()) {
        return driver.findElement(By.cssSelector(".title")).getText();
      }
      // Get page title or URL as fallback
      String url = driver.getCurrentUrl();
      String title = driver.getTitle();
      System.out.println("Current URL after registration: " + url);
      System.out.println("Page title: " + title);
      return url + " | " + title;
    } catch (Exception e) {
      return "Error getting message: " + e.getMessage();
    }
  }

  @Override
  public boolean isLoaded() {
    return driver.findElement(REGISTER_LINK).isDisplayed();
  }
}
