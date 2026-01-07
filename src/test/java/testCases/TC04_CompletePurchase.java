package testCases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountLoginPage;
import pageObjects.CategoryPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductPage;
import testBase.BaseClass;

public class TC04_CompletePurchase extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(TC04_CompletePurchase.class);

    @Test(
        groups = {"sanity", "regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testCompletePurchase() {

        logger.debug("===== Starting TC04_CompletePurchase =====");

        try {
            CategoryPage cp = new CategoryPage(getDriver());

            logger.debug("Navigating to Laptops & Notebooks");
            cp.clickLaptopsAndNotebooks();

            logger.debug("Clicking Show All");
            cp.clickShowAll();

            logger.debug("Waiting for product list to load");
            Thread.sleep(500);

            logger.debug("Selecting HP product");
            cp.selectHPProduct();

            ProductPage pp = new ProductPage(getDriver());

            logger.debug("Setting delivery date");
            pp.setDeliveryDate();

            logger.debug("Adding product to cart");
            pp.clickAddToCart();

            logger.debug("Proceeding to checkout");
            pp.clickCheckout();

            CheckoutPage cop = new CheckoutPage(getDriver());

            logger.debug("Clicking Login from checkout page");
            cop.clickLogin();

            AccountLoginPage alp = new AccountLoginPage(getDriver());

            logger.debug("Entering login credentials");
            alp.setEmail("raymond.rohring@gmail.com");
            alp.setPwd("CloudBerry123");

            logger.debug("Submitting login");
            alp.clickLogin();

            logger.debug("Completing checkout process");
            cop.completeCheckout();

            ConfirmationPage confirmationPage =
                    new ConfirmationPage(getDriver());

            logger.debug("Verifying order placement confirmation");

            try {
                Assert.assertTrue(
                        confirmationPage.isOrderPlaced(),
                        "Order placement failed!"
                );
                logger.debug("Assertion PASSED: Order placed successfully");

            } catch (AssertionError ae) {
                logger.error("Assertion FAILED: Order was not placed", ae);
                throw ae; // Required to trigger RetryAnalyzer
            }

        } catch (InterruptedException ie) {
            logger.error("Thread interrupted during execution", ie);
            Thread.currentThread().interrupt();
            Assert.fail("Test interrupted");

        } catch (Exception e) {
            logger.error("Unexpected exception occurred during TC04_CompletePurchase", e);
            Assert.fail("Test failed due to unexpected exception");
        }

        logger.debug("===== Ending TC04_CompletePurchase =====");
    }
}
