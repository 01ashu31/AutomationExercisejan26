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
	    log.info("Verifying Home Page URL contains 'automationexercise'");
	    waitForUrlContains("automationexercise");
	    log.info("Home Page URL verification successful");
	    return true;
	}

	public LoginPage clickOnSignUpLoginButton() {
	    log.info("Clicking on SignUp / Login button");
	    waitForClickable(LoginSingnupButton).click();
	    log.info("Navigated to Login Page");
	    return new LoginPage(driver);
	}

	public boolean isUserLoggedIn() {
	    log.info("Checking whether user is logged in");
	    boolean status = waitForVisibility(loggedInUserName).isDisplayed();
	    log.info("User logged-in status: {}", status);
	    return status;
	}

	public String getLoggedInUserName() {
	    log.info("Fetching logged-in user name");
	    String userName = waitForVisibility(loggedInUserName).getText().trim();
	    log.info("Logged-in user name: {}", userName);
	    return userName;
	}

	public boolean getLoggedInUser() {
	    log.info("Verifying logged-in user name visibility");
	    boolean status = waitForVisibility(loggedInUserName).isDisplayed();
	    log.info("Logged-in user visibility status: {}", status);
	    return status;
	}

	public AccountCreatedPage clickOnDeleteAccount() {
	    log.info("Clicking on Delete Account button");
	    waitForVisibility(deleteAccountButton).click();
	    log.info("Delete Account action triggered");
	    return new AccountCreatedPage(driver);
	}
	
	


}
