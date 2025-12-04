package com.moqaddas.banking.pages.transfer;

import com.moqaddas.banking.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferFundsPage extends BasePage<TransferFundsPage> {
  private static final By AMOUNT = By.id("amount");
  private static final By FROM_ACCOUNT = By.id("fromAccountId");
  private static final By TO_ACCOUNT = By.id("toAccountId");
  private static final By TRANSFER_BUTTON = By.cssSelector("input.button");
  private static final By RESULT_MESSAGE = By.cssSelector("#rightPanel h1");

  public TransferFundsPage(WebDriver driver) {
    super(driver);
  }

  public TransferFundsPage enterAmount(String value) {
    driver.findElement(AMOUNT).clear();
    driver.findElement(AMOUNT).sendKeys(value);
    return this;
  }

  public TransferFundsPage selectFromAccount(String accountId) {
    driver.findElement(FROM_ACCOUNT).sendKeys(accountId);
    return this;
  }

  public TransferFundsPage selectToAccount(String accountId) {
    driver.findElement(TO_ACCOUNT).sendKeys(accountId);
    return this;
  }

  public TransferFundsPage submitTransfer() {
    driver.findElement(TRANSFER_BUTTON).click();
    return this;
  }

  public String confirmationHeading() {
    return driver.findElement(RESULT_MESSAGE).getText().trim();
  }

  @Override
  public boolean isLoaded() {
    return driver.findElement(AMOUNT).isDisplayed();
  }
}
