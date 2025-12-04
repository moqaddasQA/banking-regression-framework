package com.moqaddas.banking.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.moqaddas.banking.reporting.ExtentManager;
import com.moqaddas.banking.reporting.ScreenshotUtil;
import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReportingListener implements ITestListener {
  private final ExtentReports extent = ExtentManager.getInstance();
  private final Map<String, ExtentTest> tests = new ConcurrentHashMap<>();

  @Override
  public void onTestStart(ITestResult result) {
    String key = keyFor(result);
    ExtentTest test = extent.createTest(displayName(result));
    tests.put(key, test);
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    ExtentTest test = tests.get(keyFor(result));
    if (test != null) {
      test.log(Status.PASS, "Passed");
    }
  }

  @Override
  public void onTestFailure(ITestResult result) {
    ExtentTest test = tests.get(keyFor(result));
    if (test != null) {
      test.log(Status.FAIL, result.getThrowable());
    }
    Path screenshot = ScreenshotUtil.capture(result.getMethod().getMethodName());
    if (screenshot != null) {
      if (test != null) {
        test.addScreenCaptureFromPath(screenshot.toString());
      }
      try {
        Allure.addAttachment("Failure Screenshot", java.nio.file.Files.newInputStream(screenshot));
      } catch (Exception ignored) {}
    }
  }

  @Override
  public void onFinish(ITestContext context) {
    extent.flush();
  }

  private static String keyFor(ITestResult result) {
    return result.getMethod().getQualifiedName() + Arrays.toString(result.getParameters());
  }

  private static String displayName(ITestResult result) {
    if (result.getParameters() == null || result.getParameters().length == 0) {
      return result.getMethod().getMethodName();
    }
    return result.getMethod().getMethodName() + Arrays.toString(result.getParameters());
  }
}
