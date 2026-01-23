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
		PageFactory.initElements(driver, this);
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
	
	public AccountCreatedPage enterAccountInfomation(String pass, String day, String month, String year, String fName, String lName,
			String address, String countyName, String stateName, String cityName, String zipcodeadd, String Mobilenum) {
		titile.click();
		password.sendKeys(pass);

		Select daySelect = new Select(days);
		daySelect.selectByVisibleText(day);

		Select monthSelect = new Select(months);
		monthSelect.selectByValue(month);

		Select yearSelect = new Select(years);
		yearSelect.selectByValue(year);

		newsSletterCheckBox.click();
		partnerCheckBox.click();

		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		address1.sendKeys(address);
		Select countrySelect = new Select(country);
		countrySelect.selectByValue(countyName);
		state.sendKeys(stateName);
		city.sendKeys(cityName);
		zipcode.sendKeys(zipcodeadd);
		mobileNumber.sendKeys(Mobilenum);
		createAccountButton.click();
		return new AccountCreatedPage(driver);

	}

	

}
