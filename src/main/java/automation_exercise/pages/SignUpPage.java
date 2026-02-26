package automation_exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import automation_exercise.base.BasePage;

public class SignUpPage extends BasePage {

	public SignUpPage(WebDriver driver) {
		super(driver);
//		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "id_gender1")
	WebElement titile;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "days")
	WebElement days;

	@FindBy(id = "months")
	WebElement months;

	@FindBy(id = "years")
	WebElement years;

	@FindBy(id = "newsletter")
	WebElement newsSletterCheckBox;

	@FindBy(id = "optin")
	WebElement partnerCheckBox;

	@FindBy(id = "first_name")
	WebElement firstName;

	@FindBy(id = "last_name")
	WebElement lastName;

	@FindBy(id = "address1")
	WebElement address1;

	@FindBy(id = "country")
	WebElement country;

	@FindBy(id = "state")
	WebElement state;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "zipcode")
	WebElement zipcode;

	@FindBy(id = "mobile_number")
	WebElement mobileNumber;

	@FindBy(xpath = "(//button[@type='submit'])[1]")
	WebElement createAccountButton;

	public AccountCreatedPage enterAccountInfomation(String pass, String day, String month, String year, String fName,
			String lName, String address, String countyName, String stateName, String cityName, String zipcodeadd,
			String Mobilenum) {

		log.info("Entering account information");

		titile.click();
		password.sendKeys(pass);

		log.info("Selecting DOB: Day={}, Month={}, Year={}", day, month, year);
		new Select(days).selectByVisibleText(day);
		new Select(months).selectByValue(month);
		new Select(years).selectByValue(year);

		log.info("Selecting newsletter and partner options");
		newsSletterCheckBox.click();
		partnerCheckBox.click();

		log.info("Entering personal details");
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		address1.sendKeys(address);

		log.info("Selecting country: {}", countyName);
		new Select(country).selectByValue(countyName);

		state.sendKeys(stateName);
		city.sendKeys(cityName);
		zipcode.sendKeys(zipcodeadd);
		mobileNumber.sendKeys(Mobilenum);

		log.info("Submitting Create Account form");
		createAccountButton.click();

		log.info("Account creation submitted, navigating to Account Created Page");
		return new AccountCreatedPage(driver);

	}

	@Override
	protected String getPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

}
