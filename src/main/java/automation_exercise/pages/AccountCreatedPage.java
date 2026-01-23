package automation_exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation_exercise.base.BasePage;

public class AccountCreatedPage extends BasePage{

	public AccountCreatedPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(css = ".btn.btn-primary")
	WebElement accountContinueButton;

	@FindBy(xpath = "//h2[@data-qa='account-created']")
	WebElement accountCreatedPage;

	@FindBy(xpath = "//h2[@data-qa='account-deleted']")
	WebElement accountDeletedPage;

	
	
	public boolean isContnuePageDisplayed() {
		return waitForVisibility(accountCreatedPage).isDisplayed();
	}

	public boolean isDeletePageDisplayed() {
		return waitForVisibility(accountDeletedPage).isDisplayed();
	}

	public HomePage clickOnContinueButton() {
		waitForClickable(accountContinueButton).click();
		return new HomePage(driver);
	}

}
