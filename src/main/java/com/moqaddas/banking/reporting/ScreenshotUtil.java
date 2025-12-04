package com.moqaddas.banking.reporting;

import com.moqaddas.banking.support.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {
  private ScreenshotUtil() {}

  public static Path capture(String testName) {
    WebDriver driver = DriverManager.getDriver();
    if (driver == null) {
      return null;
    }
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    Path target = Path.of("target", "screenshots", testName + "_" + timestamp + ".png");
    try {
      Files.createDirectories(target.getParent());
      FileUtils.copyFile(src, target.toFile());
      return target;
    } catch (IOException e) {
      return null;
    }
  }
}
