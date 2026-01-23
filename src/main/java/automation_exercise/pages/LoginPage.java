package automation_exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation_exercise.base.BasePage;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
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
		signUpName.sendKeys(userName);
		signupEmail.sendKeys(email);
		signUpButton.click();
		SignUpPage signup=new SignUpPage(driver);
		return signup;
	}
	
	public boolean isNewUserSignupVisible() {
		return signUpText.isDisplayed();
	}
	
	
	

}
