package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage extends BasePage {
	// Constructor
	public CategoryPage(WebDriver driver) {
		super(driver);
    }

	// Locators
	@FindBy(xpath = "//a[normalize-space()='Laptops & Notebooks']")
    WebElement link_Laptops;

    @FindBy(xpath = "//a[normalize-space()='Show All Laptops & Notebooks']")
    WebElement link_ShowAll;

    @FindBy(xpath = "//a[normalize-space()='HP LP3065']")
    WebElement product_HP;

    // Actions
    public void clickLaptopsAndNotebooks() throws InterruptedException {
    	// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link_Laptops);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now click
        link_Laptops.click();
    }

    public void clickShowAll() {
        link_ShowAll.click();
    }

    public void selectHPProduct() throws InterruptedException {
    	// Scroll into view
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product_HP);

    	// Optional: Add a small wait after scrolling to let it stabilize
    	Thread.sleep(500);

    	// Now click
        product_HP.click();
    }

}
