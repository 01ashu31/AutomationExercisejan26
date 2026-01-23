package automation_exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation_exercise.base.BasePage;

public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement LoginSingnupButton;
	
	@FindBy(xpath="//a[//i[contains(@class,'fa-user')]]/b")
	WebElement loggedInUserName;
	
	@FindBy(xpath="//a[@href='/delete_account']")
	WebElement deleteAccountButton;
	
	
	public boolean isHomePageVisible() {
		waitForUrlContains("automationexercise");
		return true;
	}
	
	public LoginPage clickOnSignUpLoginButton() {
		waitForClickable(LoginSingnupButton).click();
		return new LoginPage(driver);
		
	}
	
	public boolean isUserLoggedIn() {
		return waitForVisibility(loggedInUserName).isDisplayed();
	}
	
	public String getLoggedInUserName() {
	    return waitForVisibility(loggedInUserName).getText().trim();
	}
	
	public boolean getLoggedInUser() {
		return waitForVisibility(loggedInUserName).isDisplayed();
	}
	
	public AccountCreatedPage clickOnDeleteAccount() {
		waitForVisibility(deleteAccountButton).click();
		return new AccountCreatedPage(driver);
	}
	
	


}
