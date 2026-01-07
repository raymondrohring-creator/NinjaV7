package pageObjects;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class BasePage {
	// Constructor
	WebDriver driver;
	// This is the constructor for the class.
	// It takes a WebDriver object as an argument, which is used to interact with the browser.

	public BasePage(WebDriver driver) {
		this.driver = driver;
		// The passed driver is assigned to the instance variable driver.
		// This allows the class and its subclasses to use it for browser interactions.

		PageFactory.initElements(driver, this);
		// The above line initializes the web elements defined in the class using Selenium's PageFactory.
		// PageFactory.initElements() tells Selenium to scan the current class (this) for any @FindBy annotations
		// and connect them to actual elements on the page using the provided driver.	
	}

}
