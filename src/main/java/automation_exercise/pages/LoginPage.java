package automation_exercise.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import automation_exercise.base.BasePage;

public class LoginPage extends BasePage {
	
	public LoginPage() {
		super();
	}
	
	@FindBy(xpath="//h2[contains(text(),'New User Signup!')]")
	WebElement signUpText;
	
	@FindBy(xpath="//input[@data-qa='signup-name']")
	WebElement signUpName;
	
	@FindBy(xpath="//input[@data-qa='signup-email']")
	WebElement signupEmail;
	
	@FindBy(xpath="//button[@data-qa='signup-button']")
	WebElement signUpButton;
	
	public SignUpPage verifySignup(String userName, String email) {
	    log.info("Entering signup details");
	    log.info("Signup UserName: {}, Email: {}", userName, email);

	    signUpName.sendKeys(userName);
	    signupEmail.sendKeys(email);
	    signUpButton.click();

	    log.info("Signup form submitted, navigating to SignUp Page");
	    return new SignUpPage();
	}

	public boolean isNewUserSignupVisible() {
	    log.info("Verifying 'New User Signup' section visibility");
	    boolean status = waitForVisibility(signUpText).isDisplayed();
	    log.info("New User Signup section visible: {}", status);
	    return status;
	}

	@Override
	protected String getPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	
	
	

}
