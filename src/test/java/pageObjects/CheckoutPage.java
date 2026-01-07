package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
	// Constructor
    public CheckoutPage(WebDriver driver) {
    	super(driver);
    }

    // Locators
    @FindBy(xpath = "//strong[normalize-space()='login page']")
    WebElement loginPageLink;

    @FindBy(id = "input-shipping-address")
    WebElement shippingAddressDropdown;

    @FindBy(id = "button-shipping-methods")
    WebElement shippingMethodsButton;

    @FindBy(id = "button-shipping-method")
    WebElement flatShippingButton;

    @FindBy(id = "button-payment-methods")
    WebElement paymentMethodsButton;

    @FindBy(id = "button-payment-method")
    WebElement codButton;

    @FindBy(xpath = "//div[@class='text-end']//button[contains(text(),'Confirm')]")
    WebElement confirmButton;

    // Actions
    public void clickLogin() {
        loginPageLink.click();
    }

    public void completeCheckout() throws InterruptedException {
        new Select(shippingAddressDropdown).selectByIndex(1);
        shippingMethodsButton.click();
        flatShippingButton.click();
        paymentMethodsButton.click();
        codButton.click();
        scroll(confirmButton);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmButton);
    }

    private void scroll(WebElement element) {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    	// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
