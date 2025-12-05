package com.moqaddas.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage<T extends BasePage<T>> {
  protected final WebDriver driver;
  protected final WebDriverWait wait;

  protected BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  protected WebElement waitForElement(By locator) {
    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    sleep(500); // Slow down for visual observation
    return element;
  }

  protected WebElement waitForClickable(By locator) {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    sleep(500); // Slow down for visual observation
    return element;
  }

  protected void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public abstract boolean isLoaded();
}
