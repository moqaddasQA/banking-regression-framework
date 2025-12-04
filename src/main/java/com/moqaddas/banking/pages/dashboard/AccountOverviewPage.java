package com.moqaddas.banking.pages.dashboard;

import com.moqaddas.banking.pages.BasePage;
import com.moqaddas.banking.pages.billpay.BillPayPage;
import com.moqaddas.banking.pages.transfer.TransferFundsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountOverviewPage extends BasePage<AccountOverviewPage> {
  private static final By ACCOUNTS_TABLE = By.id("accountTable");
  private static final By TRANSFER_FUNDS_LINK = By.linkText("Transfer Funds");
  private static final By BILL_PAY_LINK = By.linkText("Bill Pay");

  public AccountOverviewPage(WebDriver driver) {
    super(driver);
  }

  public boolean tableVisible() {
    return driver.findElement(ACCOUNTS_TABLE).isDisplayed();
  }

  public TransferFundsPage goToTransfers() {
    driver.findElement(TRANSFER_FUNDS_LINK).click();
    return new TransferFundsPage(driver);
  }

  public BillPayPage goToBillPay() {
    driver.findElement(BILL_PAY_LINK).click();
    return new BillPayPage(driver);
  }

  @Override
  public boolean isLoaded() {
    return driver.findElement(ACCOUNTS_TABLE).isDisplayed();
  }
}
