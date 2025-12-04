package com.moqaddas.banking.tests;

import com.moqaddas.banking.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSmokeTest extends BaseTest {
  @Test
  public void loginPageLoads() {
    LoginPage login = new LoginPage(driver);
    Assert.assertEquals(login.headingText(), "Customer Login", "Login panel heading should match");
  }
}
