package com.moqaddas.banking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage<T extends BasePage<T>> {
  protected final WebDriver driver;
  private final WebDriverWait wait;

  protected BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  protected void waitUntil(ExpectedCondition<Boolean> condition) {
    wait.until(condition);
  }

  public abstract boolean isLoaded();
}
