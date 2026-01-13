# NinjaV7 – Selenium Hybrid Automation Framework (CloudBerry Store)
NinjaV7 is an enterprise-grade Selenium Hybrid Automation Framework built for the CloudBerry Store (OpenCart) application.

This framework follows real-world industry standards and is designed for scalable, maintainable, and high-performance test automation without using BDD/Cucumber.

## 🔧 Tech Stack
* Language: Java
* Automation Tool: Selenium WebDriver 4
* Test Framework: TestNG
* Build Tool: Maven
* Design Pattern: Page Object Model (POM)
* Reporting: Extent Reports
* Logging: Log4j
* CI Ready: Jenkins compatible
* Browser Support: Chrome, Firefox, Edge

## 🧱 Framework Architecture (NinjaV7)
`
NinjaV7
├── src/test/java
│   ├── pageObjects        # Page Object Model classes
│   ├── testCases          # TestNG test classes
│   ├── testBase           # BaseClass (WebDriver setup)
│   ├── utilities          # Utilities (config, waits, screenshots)
│   └── listeners          # TestNG listeners (Extent, retry)
│
├── src/test/resources
│   ├── config.properties  # Environment & credentials
│   └── testdata           # Test data files (Excel / JSON)
│
├── test-output            # TestNG reports
├── screenshots            # Failure screenshots
├── testng.xml             # Suite configuration
├── pom.xml                # Maven dependencies
└── README.md
`

## 🚀 Key Features
* ✅ Hybrid framework design (POM + utilities + TestNG)
* ✅ Reusable Page Objects
* ✅ Centralized WebDriver management
* ✅ TestNG annotations & grouping
* ✅ Retry mechanism for flaky tests
* ✅ Screenshot capture on failure
* ✅ Extent HTML reports
* ✅ Data-driven testing support
* ✅ Multi-browser execution
* ✅ Parallel execution ready

## 📘 Sample Test Case (Hybrid – TestNG)
```
@Test(groups = {"sanity","regression"})
public void verifyLogin() {
    HomePage home = new HomePage(driver);
    LoginPage login = new LoginPage(driver);

    home.clickMyAccount();
    home.goToLogin();

    login.setEmail(prop.getProperty("email"));
    login.setPassword(prop.getProperty("password"));
    login.clickLogin();

    Assert.assertTrue(login.isMyAccountPageDisplayed());
}
```

## ▶️ How to Run the Tests
### 🔹 Run via TestNG XML
```
Right click testng.xml → Run as TestNG Suite
```
### 🔹 Run via Maven
```
mvn test
```
## 🌐 Application Under Test
CloudBerry Store (OpenCart)
`
https://www.cloudberrystore.services
`
## 🧪 Test Execution Control
### 🔹 Run by TestNG Groups
```
<groups>
  <run>
    <include name="sanity"/>
  </run>
</groups>
```
        
### 🔹 Parallel Execution        
```
<suite parallel="tests" thread-count="3">
```

## 📊 Reports
* Extent Report: Generated after execution
`
/test-output/ExtentReport.html
`
* Screenshots: Captured automatically on test failure

## 🧠 Framework Design Philosophy
* Built for enterprise UI automation
* Clear separation of concerns
* Easy to extend for new modules
* Designed for real client projects & interviews

## 🧩 Future Enhancements
* CI/CD integration with Jenkins
* Selenium Grid / Docker support
* Cloud execution (BrowserStack / Sauce Labs)
* API automation integration

## 👨‍🏫 Author
Raymond Rohring – Quality Automation Engineer | Selenium | Hybrid Framework | TestNG | CI/CD

## ⭐ Support
If you find this framework useful, give the repository a ⭐ and feel free to fork it.
