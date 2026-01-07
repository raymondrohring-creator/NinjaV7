package testCases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountLoginPage;
import pageObjects.HomePage;
import pageObjects.YourAffiliateInformationPage;
import testBase.BaseClass;

public class TC06_AddAffiliate extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(TC06_AddAffiliate.class);

    @Test(
        groups = {"regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testAddAffiliate() {

        logger.debug("===== Starting TC06_AddAffiliate =====");

        try {
            HomePage hp = new HomePage(getDriver());

            logger.debug("Clicking My Account");
            hp.clickMyAccount();

            logger.debug("Navigating to Login page");
            hp.goToLogin();

            AccountLoginPage alp = new AccountLoginPage(getDriver());

            logger.debug("Entering email");
            alp.setEmail("raymond.rohring@gmail.com");

            logger.debug("Entering password");
            alp.setPwd("CloudBerry123");

            logger.debug("Submitting login");
            alp.clickLogin();

            YourAffiliateInformationPage yaip =
                    new YourAffiliateInformationPage(getDriver());

            logger.debug("Navigating to Affiliate Information form");
            yaip.navigateToAffiliateForm();

            logger.debug("Filling affiliate details");
            yaip.fillAffiliateDetails(
                    "CloudBerry",
                    "https://www.linkedin.com/in/raymond-rohring/",
                    "123456",
                    "Raymond L. Rohring"
            );

            logger.debug("Verifying affiliate addition confirmation");

            try {
                Assert.assertTrue(
                        yaip.isAffiliateAdded(),
                        "Affiliate details not added successfully."
                );
                logger.debug("Assertion PASSED: Affiliate added successfully");

            } catch (AssertionError ae) {
                logger.error("Assertion FAILED: Affiliate not added", ae);
                throw ae; // Required for RetryAnalyzer
            }

        } catch (Exception e) {
            logger.error("Unexpected exception occurred during TC06_AddAffiliate", e);
            Assert.fail("Test failed due to unexpected exception");
        }

        logger.debug("===== Ending TC06_AddAffiliate =====");
    }
}
