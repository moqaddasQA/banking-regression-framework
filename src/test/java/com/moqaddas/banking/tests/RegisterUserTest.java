package com.moqaddas.banking.tests;

import com.moqaddas.banking.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterUserTest extends BaseTest {
  @Test(priority = 0)
  public void registerNewUser() {
    RegisterPage registerPage = new RegisterPage(driver);
    registerPage.clickRegisterLink();
    
    System.out.println("Registering with username: " + TEST_USERNAME);
    registerPage.fillRegistrationForm("Moqaddas", "Qadri", TEST_USERNAME, TEST_PASSWORD)
        .submitRegistration();
    
    String message = registerPage.getSuccessMessage();
    System.out.println("Registration message: " + message);
    Assert.assertTrue(message.contains("Your account was created successfully") || 
                      message.contains("successfully") ||
                      message.contains("overview"),
        "Registration should be successful");
  }
}
