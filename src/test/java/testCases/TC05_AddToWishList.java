package testCases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountLoginPage;
import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import testBase.BaseClass;

public class TC05_AddToWishList extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(TC05_AddToWishList.class);

    @Test(
        groups = {"regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testAddToWishList() {

        logger.debug("===== Starting TC05_AddToWishList =====");

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

            CategoryPage cp = new CategoryPage(getDriver());

            logger.debug("Navigating to Laptops & Notebooks");
            cp.clickLaptopsAndNotebooks();

            logger.debug("Clicking Show All");
            cp.clickShowAll();

            logger.debug("Waiting for products to load");
            Thread.sleep(500);

            logger.debug("Selecting HP product");
            cp.selectHPProduct();

            ProductPage pp = new ProductPage(getDriver());

            logger.debug("Adding product to Wishlist");
            pp.addToWishlist();

            logger.debug("Verifying wishlist success message");

            try {
                Assert.assertTrue(
                        pp.isSuccessMessageDisplayed(),
                        "Wishlist message not shown."
                );
                logger.debug("Assertion PASSED: Product added to wishlist successfully");

            } catch (AssertionError ae) {
                logger.error("Assertion FAILED: Wishlist confirmation not displayed", ae);
                throw ae; // Rethrow to trigger RetryAnalyzer
            }

        } catch (InterruptedException ie) {
            logger.error("Thread interrupted during execution", ie);
            Thread.currentThread().interrupt();
            Assert.fail("Test interrupted");

        } catch (Exception e) {
            logger.error("Unexpected exception occurred during TC05_AddToWishList", e);
            Assert.fail("Test failed due to unexpected exception");
        }

        logger.debug("===== Ending TC05_AddToWishList =====");
    }
}
