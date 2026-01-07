package testCases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;
import org.testng.Assert;
import testBase.BaseClass;

public class TC01_LaunchApplication extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(TC01_LaunchApplication.class);

    @Test(
        groups = {"sanity", "regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testLaunchApplication() {

        logger.debug("===== Starting TC01_LaunchApplication =====");

        try {
            logger.debug("Fetching page title");
            String actualTitle = getDriver().getTitle();
            logger.debug("Actual Page Title: {}", actualTitle);

            String expectedTitle = "Your store of fun";
            logger.debug("Expected Page Title: {}", expectedTitle);

            Assert.assertEquals(actualTitle, expectedTitle);
            logger.debug("Assertion PASSED: Page title matched");

        } catch (AssertionError ae) {
            logger.error("Assertion FAILED: Page title did not match", ae);
            throw ae; // IMPORTANT: rethrow to trigger RetryAnalyzer

        } catch (Exception e) {
            logger.error("Unexpected exception occurred during test execution", e);
            Assert.fail("Test failed due to unexpected exception");
        }

        logger.debug("===== Ending TC01_LaunchApplication =====");
    }
}
