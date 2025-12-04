package com.moqaddas.banking.pages.billpay;

import com.moqaddas.banking.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BillPayPage extends BasePage<BillPayPage> {
  private static final By PAYEE_NAME = By.name("payee.name");
  private static final By PAYEE_ADDRESS = By.name("payee.address.street");
  private static final By PAYEE_ACCOUNT = By.name("payee.accountNumber");
  private static final By VERIFY_ACCOUNT = By.name("verifyAccount");
  private static final By AMOUNT = By.name("amount");
  private static final By FROM_ACCOUNT = By.name("fromAccountId");
  private static final By SEND_PAYMENT = By.cssSelector("input.button");
  private static final By CONFIRM = By.cssSelector("#rightPanel h1");

  public BillPayPage(WebDriver driver) {
    super(driver);
  }

  public BillPayPage withPayee(String name, String address) {
    driver.findElement(PAYEE_NAME).sendKeys(name);
    driver.findElement(PAYEE_ADDRESS).sendKeys(address);
    return this;
  }

  public BillPayPage withAccount(String account) {
    driver.findElement(PAYEE_ACCOUNT).sendKeys(account);
    driver.findElement(VERIFY_ACCOUNT).sendKeys(account);
    return this;
  }

  public BillPayPage withAmount(String amount) {
    driver.findElement(AMOUNT).sendKeys(amount);
    return this;
  }

  public BillPayPage fromAccount(String accountId) {
    driver.findElement(FROM_ACCOUNT).sendKeys(accountId);
    return this;
  }

  public BillPayPage submitPayment() {
    driver.findElement(SEND_PAYMENT).click();
    return this;
  }

  public String confirmationHeading() {
    return driver.findElement(CONFIRM).getText().trim();
  }

  @Override
  public boolean isLoaded() {
    return driver.findElement(PAYEE_NAME).isDisplayed();
  }
}
