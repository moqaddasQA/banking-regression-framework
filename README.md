# 🏦 Banking Regression Framework

A production-ready Selenium WebDriver automation framework for testing ParaBank demo banking application. Built with Java 21, TestNG, and Page Object Model design pattern.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.25.0-green.svg)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red.svg)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-blue.svg)](https://maven.apache.org/)

## 📋 Project Overview

This framework automates critical banking workflows including user registration, fund transfers, and bill payments. Features data-driven testing, comprehensive reporting, and CI/CD integration with Jenkins and Docker.

**Test Coverage:**
- ✅ User Registration & Authentication
- ✅ Fund Transfers Between Accounts
- ✅ Bill Payment Processing (Data-Driven)
- ✅ Account Management Operations

**Key Metrics:**
- 4 Test Classes | 6 Page Objects
- 100% Pass Rate | ~1 min Execution Time
- Data-Driven Testing with CSV Support

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Core Programming Language |
| Selenium WebDriver | 4.25.0 | Browser Automation |
| TestNG | 7.10.2 | Test Framework & Assertions |
| Maven | 3.x | Build & Dependency Management |
| ExtentReports | 5.1.1 | HTML Test Reports |
| Allure | 2.26.0 | Advanced Reporting |
| Apache POI | 5.2.5 | CSV Data Management |
| WebDriverManager | 5.9.2 | Automatic Driver Management |

## 🏗️ Framework Features

- **Page Object Model** - Maintainable and reusable page classes
- **Data-Driven Testing** - CSV-based test data management
- **Dynamic Account Handling** - Runtime account creation and extraction
- **Timestamped User Generation** - Prevents username conflicts
- **Comprehensive Reporting** - ExtentReports & Allure integration
- **CI/CD Ready** - Jenkins pipeline and Docker support
- **Automatic Driver Management** - WebDriverManager handles browser drivers
- **Parallel Execution** - TestNG parallel test support

## Project Structure

## 📁 Project Structure

```
banking-regression-framework/
├── src/
│   ├── main/java/com/moqaddas/banking/
│   │   ├── pages/              # Page Object Model classes
│   │   │   ├── BasePage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── RegisterPage.java
│   │   │   ├── dashboard/AccountOverviewPage.java
│   │   │   ├── transfer/TransferFundsPage.java
│   │   │   └── billpay/BillPayPage.java
│   │   ├── support/            # Framework utilities
│   │   │   ├── DriverFactory.java
│   │   │   ├── DriverManager.java
│   │   │   └── ConfigManager.java
│   │   ├── data/               # Data providers
│   │   │   └── CsvDataProvider.java
│   │   └── reporting/          # Reporting utilities
│   │       ├── ExtentManager.java
│   │       └── ScreenshotUtil.java
│   └── test/
│       ├── java/com/moqaddas/banking/tests/
│       │   ├── BaseTest.java
│       │   ├── LoginSmokeTest.java
│       │   ├── RegisterUserTest.java
│       │   ├── TransferFundsTest.java
│       │   └── BillPayTest.java
│       └── resources/
│           ├── testng-smoke.xml
│           ├── testng-regression.xml
│           ├── framework.properties
│           └── data/
│               ├── billpay.csv
│               └── transfers.csv
├── docker-compose.yml          # Selenium Grid setup
├── Jenkinsfile                 # CI/CD pipeline
└── pom.xml                     # Maven configuration
```

## 🚀 Getting Started

### Prerequisites

- **Java 21** - [Download JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven 3.x** - [Download Maven](https://maven.apache.org/download.cgi)
- **Git** - [Download Git](https://git-scm.com/downloads)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/moqaddasQA/banking-regression-framework.git
   cd banking-regression-framework
   ```

2. **Verify Java installation**
   ```bash
   java -version
   # Should show: java version "21.x.x"
   ```

3. **Install dependencies**
   ```bash
   mvn clean install -DskipTests
   ```

## ▶️ Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Smoke Tests Only
```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng-smoke.xml
```

### Run Regression Suite
```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng-regression.xml
```

### Run Specific Test Class
```bash
mvn clean test -Dtest=TransferFundsTest
```

### Run in Headless Mode
Edit `src/test/resources/framework.properties`:
```properties
browser.headless=true
```

## 📊 Test Reports

After test execution, reports are generated in:

- **ExtentReports**: `test-output/ExtentReport.html`
- **TestNG Reports**: `test-output/index.html`
- **Allure Reports**: Run `allure serve target/allure-results`

## 🐳 Docker Support

Run tests in Selenium Grid using Docker:

```bash
# Start Selenium Grid
docker-compose up -d

# Run tests against Grid
mvn clean test -Dselenium.grid.url=http://localhost:4444

# Stop Grid
docker-compose down
```

## ⚙️ Configuration

Edit `src/test/resources/framework.properties`:

```properties
# Browser Configuration
browser.type=chrome
browser.headless=false

# Application URL
app.url=https://parabank.parasoft.com/

# Default Credentials
user.name=Moqaddas
user.password=123456

# Timeouts (seconds)
implicit.wait=10
explicit.wait=15
```

## 👤 Author

**Moqaddas Rauf**
- GitHub: [@moqaddasQA](https://github.com/moqaddasQA)
- LinkedIn: [Connect on LinkedIn](https://www.linkedin.com/in/moqaddas-rauf)

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Test application: [ParaBank](https://parabank.parasoft.com/) by Parasoft
- Selenium WebDriver community
- TestNG framework contributors
