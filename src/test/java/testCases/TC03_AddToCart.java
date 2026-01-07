package testCases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.CategoryPage;
import pageObjects.ProductPage;
import testBase.BaseClass;

public class TC03_AddToCart extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(TC03_AddToCart.class);

    @Test(
        groups = {"sanity", "regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testAddToCart() {

        logger.debug("===== Starting TC03_AddToCart =====");

        try {
            CategoryPage cp = new CategoryPage(getDriver());

            logger.debug("Clicking on Laptops & Notebooks category");
            cp.clickLaptopsAndNotebooks();

            logger.debug("Clicking on Show All");
            cp.clickShowAll();

            logger.debug("Waiting for products to load");
            Thread.sleep(500);

            logger.debug("Selecting HP product");
            cp.selectHPProduct();

            ProductPage pp = new ProductPage(getDriver());

            logger.debug("Setting delivery date");
            pp.setDeliveryDate();

            logger.debug("Clicking Add to Cart button");
            pp.clickAddToCart();

            logger.debug("Verifying success message for Add to Cart");

            try {
                Assert.assertTrue(
                        pp.isSuccessMessageDisplayed(),
                        "Add to Cart Failed!"
                );
                logger.debug("Assertion PASSED: Product added to cart successfully");

            } catch (AssertionError ae) {
                logger.error("Assertion FAILED: Add to Cart validation failed", ae);
                throw ae; // Required to trigger RetryAnalyzer
            }

        } catch (InterruptedException ie) {
            logger.error("Thread interrupted during execution", ie);
            Thread.currentThread().interrupt();
            Assert.fail("Test interrupted");

        } catch (Exception e) {
            logger.error("Unexpected exception occurred during TC03_AddToCart", e);
            Assert.fail("Test failed due to unexpected exception");
        }

        logger.debug("===== Ending TC03_AddToCart =====");
    }
}
