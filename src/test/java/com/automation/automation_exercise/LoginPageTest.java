package com.automation.automation_exercise;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import automation_exercise.base.BaseTest;
import automation_exercise.pages.AccountCreatedPage;
import automation_exercise.pages.HomePage;
import automation_exercise.pages.LoginPage;
import automation_exercise.pages.SignUpPage;
import utils.PropertyManager;

public class LoginPageTest extends BaseTest {

	@Test
	public void verifyRegisterUser() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		HomePage homePage = new HomePage(driver);
		Assert.assertTrue(homePage.isHomePageVisible());

		LoginPage loginPage = homePage.clickOnSignUpLoginButton();
		Assert.assertTrue(loginPage.isNewUserSignupVisible());

		Properties testData = PropertyManager.getTestData();

		String name = testData.getProperty("userName");
		String email = testData.getProperty("emailPrefix") + System.currentTimeMillis() + "@test.com";
		loginPage.verifySignup(name, email);

		SignUpPage signupForm = new SignUpPage(driver);
		String password = testData.getProperty("password");
		String days = testData.getProperty("days");
		String months = testData.getProperty("months");
		String year = testData.getProperty("year");
		String fName = testData.getProperty("fName");
		String lName = testData.getProperty("lName");
		String address = testData.getProperty("address");
		String countyName = testData.getProperty("countyName");
		String stateName = testData.getProperty("stateName");
		String cityName = testData.getProperty("cityName");
		String zipcode = testData.getProperty("zipcode");
		String mobileNum = testData.getProperty("mobileNum");

		AccountCreatedPage accountCreatePage=signupForm.enterAccountInfomation(password, days, months, year, fName, lName, address, countyName, stateName,
				cityName, zipcode, mobileNum);
		
		softAssert.assertTrue(accountCreatePage.isContnuePageDisplayed());
		homePage=accountCreatePage.clickOnContinueButton();
		
//		softAssert.assertTrue(homePage.isUserLoggedIn());
		String actualUserName = homePage.getLoggedInUserName();
		softAssert.assertEquals(actualUserName, name);
		softAssert.assertTrue(homePage.getLoggedInUser());
		homePage.clickOnDeleteAccount();
		softAssert.assertTrue(accountCreatePage.isDeletePageDisplayed());
		homePage=accountCreatePage.clickOnContinueButton();

	}


}
