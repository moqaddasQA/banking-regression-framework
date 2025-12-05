package com.moqaddas.banking.tests;

import com.moqaddas.banking.data.CsvDataProvider;
import com.moqaddas.banking.pages.RegisterPage;
import com.moqaddas.banking.pages.billpay.BillPayPage;
import com.moqaddas.banking.pages.dashboard.AccountOverviewPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPayTest extends BaseTest {

  @Test(priority = 2, dataProvider = "billpay", dataProviderClass = CsvDataProvider.class)
  public void payeeFlow(String payee, String address, String account, String amount, String fromAccount) {
    // First register a new user for this test
    RegisterPage registerPage = new RegisterPage(driver);
    registerPage.clickRegisterLink();
    System.out.println("Registering with username: " + uniqueUsername);
    registerPage.fillRegistrationForm("Moqaddas", "Qadri", uniqueUsername, TEST_PASSWORD)
        .submitRegistration();
    System.out.println("Registration completed for bill pay test");
    
    // Navigate to account overview page after registration
    driver.get("https://parabank.parasoft.com/parabank/overview.htm");
    AccountOverviewPage overview = new AccountOverviewPage(driver);
    Assert.assertTrue(overview.tableVisible(), "Overview table should be visible");

    // Get the actual account ID instead of using hardcoded CSV data
    String actualAccountId = overview.getFirstAccountId();
    
    BillPayPage billPayPage = overview.goToBillPay();
    billPayPage
        .withPayee(payee, address)
        .withAccount(account)
        .withAmount(amount)
        .fromAccount(actualAccountId)  // Use actual account ID
        .submitPayment();

    Assert.assertTrue(billPayPage.confirmationHeading().contains("Bill Payment") || 
                      billPayPage.confirmationHeading().contains("Bill Pay"),
        "Bill pay page should be displayed");
  }
}
