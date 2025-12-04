package com.moqaddas.banking.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {
  private static ExtentReports extent;

  private ExtentManager() {}

  public static synchronized ExtentReports getInstance() {
    if (extent == null) {
      ExtentSparkReporter spark = new ExtentSparkReporter("target/extent/extent-report.html");
      extent = new ExtentReports();
      extent.attachReporter(spark);
      extent.setSystemInfo("Project", "Banking Regression Framework");
    }
    return extent;
  }
}
