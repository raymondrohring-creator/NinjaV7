package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
	// Constructor

	// HomePage is a subclass of BasePage.
	// When a HomePage object is created, it needs a WebDriver instance to work with.
	// super(driver); is a call to the constructor of the parent class (BasePage), passing along the WebDriver.
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement link_Login;

	@FindBy(xpath = "//i[@class='fa-solid fa-user']")
	WebElement link_MyAccount;

	// Actions
	public void clickMyAccount() {
		link_MyAccount.click();
	}

	public void goToLogin() {
    	link_Login.click();
    }

}
