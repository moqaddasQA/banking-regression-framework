package com.moqaddas.banking.pages.dashboard;

import com.moqaddas.banking.pages.BasePage;
import com.moqaddas.banking.pages.billpay.BillPayPage;
import com.moqaddas.banking.pages.transfer.TransferFundsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountOverviewPage extends BasePage<AccountOverviewPage> {
  private static final By ACCOUNTS_TABLE = By.id("accountTable");
  private static final By TRANSFER_FUNDS_LINK = By.linkText("Transfer Funds");
  private static final By BILL_PAY_LINK = By.linkText("Bill Pay");
  private static final By OPEN_NEW_ACCOUNT_LINK = By.linkText("Open New Account");
  private static final By ACCOUNT_LINKS = By.xpath("//table[@id='accountTable']//a");
  private static final By ACCOUNT_TYPE_SELECT = By.id("type");
  private static final By OPEN_ACCOUNT_BUTTON = By.xpath("//input[@value='Open New Account']");
  private static final By NEW_ACCOUNT_ID = By.id("newAccountId");

  public AccountOverviewPage(WebDriver driver) {
    super(driver);
  }

  public boolean tableVisible() {
    return waitForElement(ACCOUNTS_TABLE).isDisplayed();
  }

  public String getFirstAccountId() {
    List<WebElement> accountLinks = driver.findElements(ACCOUNT_LINKS);
    if (!accountLinks.isEmpty()) {
      String accountId = accountLinks.get(0).getText();
      System.out.println("First Account ID: " + accountId);
      return accountId;
    }
    return null;
  }

  public String getSecondAccountId() {
    List<WebElement> accountLinks = driver.findElements(ACCOUNT_LINKS);
    if (accountLinks.size() > 1) {
      String accountId = accountLinks.get(1).getText();
      System.out.println("Second Account ID: " + accountId);
      return accountId;
    }
    return null;
  }

  public TransferFundsPage goToTransfers() {
    waitForClickable(TRANSFER_FUNDS_LINK).click();
    return new TransferFundsPage(driver);
  }

  public BillPayPage goToBillPay() {
    waitForClickable(BILL_PAY_LINK).click();
    return new BillPayPage(driver);
  }

  public AccountOverviewPage openNewAccount() {
    // Click "Open New Account" link
    waitForClickable(OPEN_NEW_ACCOUNT_LINK).click();
    
    // Select account type (defaults to SAVINGS, which is fine)
    // Click the "Open New Account" button
    waitForClickable(OPEN_ACCOUNT_BUTTON).click();
    
    // Wait for new account confirmation
    waitForElement(NEW_ACCOUNT_ID);
    System.out.println("New account opened successfully");
    
    // Navigate back to account overview
    driver.get("https://parabank.parasoft.com/parabank/overview.htm");
    sleep(2000); // Give the page time to load the new account
    return this;
  }

  @Override
  public boolean isLoaded() {
    return waitForElement(ACCOUNTS_TABLE).isDisplayed();
  }
}
