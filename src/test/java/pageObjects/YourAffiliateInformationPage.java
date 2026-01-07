package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourAffiliateInformationPage extends BasePage {
	// Constructor
    public YourAffiliateInformationPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "//a[normalize-space()='Affiliate']")
    WebElement affiliateLink;

    @FindBy(id = "input-company")
    WebElement inputCompany;

    @FindBy(id = "input-website")
    WebElement inputWebsite;

    @FindBy(id = "input-tax")
    WebElement inputTax;

    @FindBy(id = "input-cheque")
    WebElement inputCheque;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement continueButton;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement successMessage;

    // Actions
    public void navigateToAffiliateForm() throws InterruptedException {
        scrollToView(affiliateLink);
    	Thread.sleep(500);
    	scrollAndClick(affiliateLink);
    }

    public void fillAffiliateDetails(String company, String website, String tax, String chequeName) throws InterruptedException {
        inputCompany.clear();
        inputCompany.sendKeys(company);
        inputWebsite.clear();
        inputWebsite.sendKeys(website);
        inputTax.clear();
        inputTax.sendKeys(tax);
        scrollToView(inputCheque);
        Thread.sleep(300);
        inputCheque.clear();
        inputCheque.sendKeys(chequeName);
        scrollAndClick(continueButton);
    }

    public boolean isAffiliateAdded() {
        return successMessage.isDisplayed();
    }

    private void scrollToView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void scrollAndClick(WebElement element) throws InterruptedException {
        scrollToView(element);
        Thread.sleep(500);
        element.click();
    }

}
