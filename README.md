# Banking Regression & Reporting Framework

Enterprise-grade test automation framework for banking applications built with Selenium WebDriver, TestNG, and dual reporting (Extent + Allure). Features Page Object Model architecture, data-driven testing, and CI/CD integration for reliable regression coverage.

## Tech Stack

- **Core:** Java 17 • Maven 3.9+
- **Testing:** Selenium WebDriver 4.25 • TestNG 7.10 • REST Assured 5.4
- **Reporting:** ExtentReports 5.1 • Allure 2.26
- **Data:** Apache POI 5.2 • CSV/Excel DDT • MySQL 9.0
- **DevOps:** Docker • Jenkins • Selenium Grid
- **Patterns:** Page Object Model • ThreadLocal Driver Management • Fluent API

## Features

✅ **UI Automation** - Comprehensive Selenium-based regression covering login, transfers, and bill pay workflows  
✅ **Data-Driven Testing** - CSV-based test data providers for scalable test scenarios  
✅ **Dual Reporting** - ExtentReports HTML dashboards + Allure test reports with screenshots  
✅ **Thread-Safe Execution** - ThreadLocal WebDriver management for parallel execution  
✅ **Auto Screenshots** - Automatic failure evidence capture via TestNG listeners

## Project Structure

```
banking-regression-framework/
├── src/
│   ├── main/java/com/moqaddas/banking/
│   │   ├── pages/           # Page Objects (Login, Transfer, BillPay)
│   │   ├── reporting/       # ExtentManager, ScreenshotUtil
│   │   └── support/         # ConfigManager, DriverFactory, DriverManager
│   └── test/
│       ├── java/com/moqaddas/banking/
│       │   ├── data/        # CSV DataProviders
│       │   └── tests/       # Test classes
│       └── resources/       
│           ├── data/        # Test data (CSV files)
│           ├── testng-smoke.xml
│           └── testng-regression.xml
└── pom.xml                 # Maven dependencies
```

## Quick Start

### Prerequisites
- Java 21 or higher
- Maven 3.9+

### Running Tests

```bash
# Clone and build
git clone <repo-url>
cd banking-regression-framework
mvn clean install

# Run smoke suite
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-smoke.xml

# Run full regression
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-regression.xml

# Run specific test
mvn test -Dtest=LoginSmokeTest
```

### Configuration

Edit `src/test/resources/framework.properties`:
```properties
base.url=https://parabank.parasoft.com/
user.name=demo
user.password=demo
browser.headless=true
```

Override via system properties:
```bash
mvn test -Dbase.url=https://your-env.com -Duser.name=testuser
```

## Test Reports

### ExtentReports
Location: `target/extent/extent-report.html`
- HTML dashboard with test execution summary
- Screenshots embedded for failures
- Test metadata and timing

### Allure
```bash
# Generate and serve Allure report
mvn allure:serve
```
- Interactive test results
- Test history and trends
- Screenshot attachments

## Page Object Examples

```java
// Fluent API pattern for readable tests
new LoginPage(driver)
    .loginAs("demo", "demo")
    .goToTransfers()
    .enterAmount("250.00")
    .selectFromAccount("CHK-10001")
    .selectToAccount("SAV-20001")
    .submitTransfer();
```

## Architecture Highlights

- **BasePage** - Generic page superclass with WebDriverWait utilities
- **ThreadLocal Driver** - Safe parallel execution via DriverManager
- **CSV Data Providers** - TestNG integration for data-driven testing
- **Listeners** - Automatic screenshot capture and reporting hooks
- **Config Management** - Centralized properties with system property override

## Target Application

Tests run against [ParaBank](https://parabank.parasoft.com/) - a demo banking application.

## License

MIT License - See LICENSE file for details
