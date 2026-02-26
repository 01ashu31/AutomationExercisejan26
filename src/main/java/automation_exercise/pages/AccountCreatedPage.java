package automation_exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation_exercise.base.BasePage;

public class AccountCreatedPage extends BasePage{

	public AccountCreatedPage(WebDriver driver) {
		super(driver);
//		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(css = ".btn.btn-primary")
	WebElement accountContinueButton;

	@FindBy(xpath = "//h2[@data-qa='account-created']")
	WebElement accountCreatedPage;

	@FindBy(xpath = "//h2[@data-qa='account-deleted']")
	WebElement accountDeletedPage;

	
	
	public boolean isContnuePageDisplayed() {
		log.info("Checking whether Account Created page is displayed");
		boolean isDisplay= waitForVisibility(accountCreatedPage).isDisplayed();
		log.info("Account Created page displayed status: {}", isDisplay);
		return isDisplay;
	}

	public boolean isDeletePageDisplayed() {
		log.info("Checking whether Account Deleted page is displayed");
	    boolean isDisplayed = waitForVisibility(accountDeletedPage).isDisplayed();
	    log.info("Account Deleted page displayed status: {}", isDisplayed);
	    return isDisplayed;
	}

	public HomePage clickOnContinueButton() {
		log.info("Clicking on Continue button");
	    waitForClickable(accountContinueButton).click();
	    log.info("Continue button clicked successfully, navigating to Home Page");
	    return new HomePage(driver);
	}

	@Override
	protected String getPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

}
