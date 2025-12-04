package com.moqaddas.banking.tests;

import com.moqaddas.banking.data.CsvDataProvider;
import com.moqaddas.banking.pages.LoginPage;
import com.moqaddas.banking.pages.billpay.BillPayPage;
import com.moqaddas.banking.pages.dashboard.AccountOverviewPage;
import com.moqaddas.banking.support.ConfigManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPayTest extends BaseTest {

  @Test(dataProvider = "billpay", dataProviderClass = CsvDataProvider.class)
  public void payeeFlow(String payee, String address, String account, String amount, String fromAccount) {
    AccountOverviewPage overview = new LoginPage(driver)
        .loginAs(ConfigManager.username(), ConfigManager.password());
    Assert.assertTrue(overview.tableVisible(), "Overview table should be visible");

    BillPayPage billPayPage = overview.goToBillPay();
    billPayPage
        .withPayee(payee, address)
        .withAccount(account)
        .withAmount(amount)
        .fromAccount(fromAccount)
        .submitPayment();

    Assert.assertTrue(billPayPage.confirmationHeading().contains("Bill Payment Complete"),
        "Bill pay confirmation should show");
  }
}
