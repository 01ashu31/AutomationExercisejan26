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
	
	
	public LoginPage clickOnSignUpLoginButton() {
		LoginSingnupButton.click();
		return new LoginPage(driver);
		
	}
	
	public boolean isHomePageVisible() {
		return getCurrentUrl().contains("automationexercise");
	}
	


}
