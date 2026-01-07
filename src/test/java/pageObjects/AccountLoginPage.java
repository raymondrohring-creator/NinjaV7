package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountLoginPage extends BasePage {
	// Constructor
	public AccountLoginPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btn_Login;

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement confirmationText_MyAccount;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_Email;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_Password;

	// Actions
	public void clickLogin() {
		btn_Login.click();
	}

	public void setEmail(String email) {
		txt_Email.sendKeys(email);
	}

	public void setPwd(String pwd) {
		txt_Password.sendKeys(pwd);
	}

	public WebElement getMyAccoutnConfirmation() {
		return confirmationText_MyAccount;
	}

}
