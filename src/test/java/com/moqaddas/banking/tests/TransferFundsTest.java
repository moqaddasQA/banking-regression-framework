package com.moqaddas.banking.tests;

import com.moqaddas.banking.data.CsvDataProvider;
import com.moqaddas.banking.pages.LoginPage;
import com.moqaddas.banking.pages.dashboard.AccountOverviewPage;
import com.moqaddas.banking.pages.transfer.TransferFundsPage;
import com.moqaddas.banking.support.ConfigManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransferFundsTest extends BaseTest {

  @Test(dataProvider = "transfers", dataProviderClass = CsvDataProvider.class)
  public void transferSmoke(String fromAccount, String toAccount, String amount) {
    LoginPage login = new LoginPage(driver);
    AccountOverviewPage account = login.loginAs(ConfigManager.username(), ConfigManager.password());
    Assert.assertTrue(account.tableVisible(), "Account overview should be visible");

    TransferFundsPage transferPage = account.goToTransfers();
    transferPage
        .enterAmount(amount)
        .selectFromAccount(fromAccount)
        .selectToAccount(toAccount)
        .submitTransfer();

    Assert.assertTrue(transferPage.confirmationHeading().contains("Transfer Complete"),
        "Transfer confirmation should display");
  }
}
