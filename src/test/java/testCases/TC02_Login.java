package testCases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountLoginPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC02_Login extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(TC02_Login.class);

    @Test(
        groups = {"sanity", "regression", "datadriven"},
        dataProvider = "LoginData",
        dataProviderClass = DataProviders.class,
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testLogin(String email, String pwd) {

        logger.debug("===== Starting TC02_Login =====");
        logger.debug("Test Data | Email: {}", email);

        try {
            HomePage hp = new HomePage(getDriver());
            logger.debug("Clicking My Account");
            hp.clickMyAccount();

            logger.debug("Navigating to Login page");
            hp.goToLogin();

            AccountLoginPage alp = new AccountLoginPage(getDriver());
            logger.debug("Entering email");
            alp.setEmail(email);

            logger.debug("Entering password");
            alp.setPwd(pwd);

            logger.debug("Clicking Login button");
            alp.clickLogin();

            MyAccountPage map = new MyAccountPage(getDriver());
            logger.debug("Checking My Account confirmation visibility");

            boolean status = map.getMyAccountConfirmation().isDisplayed();
            logger.debug("Login status: {}", status);

            try {
                Assert.assertTrue(status, "Login failed: My Account not displayed");
                logger.debug("Assertion PASSED: Login successful");

                // Logout steps
                logger.debug("Performing logout");
                map.clickMyAccountDropDown();
                map.clickLogout();
                logger.debug("Logout completed successfully");

            } catch (AssertionError ae) {
                logger.error("Assertion FAILED: Login validation failed", ae);
                throw ae; // Rethrow to activate RetryAnalyzer
            }

        } catch (Exception e) {
            logger.error("Unexpected exception occurred during TC02_Login execution", e);
            Assert.fail("Test failed due to unexpected exception");
        }

        logger.debug("===== Ending TC02_Login =====");
    }
}
