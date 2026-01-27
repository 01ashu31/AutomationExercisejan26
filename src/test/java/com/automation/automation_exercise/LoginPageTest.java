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
	public void verifyRegisterUser() {

		log.info("===== Register User Test Started =====");
		extentTest.get().info("Register User test started");

		SoftAssert softAssert = new SoftAssert();
		HomePage homePage = new HomePage(driver);

		log.info("Verifying home page visibility");
		Assert.assertTrue(homePage.isHomePageVisible());
		extentTest.get().pass("Home page is visible");

		LoginPage loginPage = homePage.clickOnSignUpLoginButton();
		Assert.assertTrue(loginPage.isNewUserSignupVisible());
		extentTest.get().pass("New User Signup section visible");

		Properties testData = PropertyManager.getTestData();

		String name = testData.getProperty("userName");
		String email = testData.getProperty("emailPrefix") + System.currentTimeMillis() + "@test.com";

		log.info("Registering user with Name: {} Email: {}", name, email);
		extentTest.get().info("Registering new user");

		loginPage.verifySignup(name, email);

		SignUpPage signupForm = new SignUpPage(driver);

		AccountCreatedPage accountCreatePage = signupForm.enterAccountInfomation(

				testData.getProperty("password"), testData.getProperty("days"), testData.getProperty("months"),
				testData.getProperty("year"), testData.getProperty("fName"), testData.getProperty("lName"),
				testData.getProperty("address"), testData.getProperty("countyName"), testData.getProperty("stateName"),
				testData.getProperty("cityName"), testData.getProperty("zipcode"), testData.getProperty("mobileNum"));

		softAssert.assertTrue(accountCreatePage.isContnuePageDisplayed());
		extentTest.get().pass("Account created successfully");

		homePage = accountCreatePage.clickOnContinueButton();

		String actualUserName = homePage.getLoggedInUserName();
		softAssert.assertEquals(actualUserName, name);
		softAssert.assertTrue(homePage.getLoggedInUser());
		extentTest.get().pass("User logged in successfully");

		homePage.clickOnDeleteAccount();
		softAssert.assertTrue(accountCreatePage.isDeletePageDisplayed());
		extentTest.get().pass("Account deleted successfully");

		accountCreatePage.clickOnContinueButton();

		log.info("===== Register User Test Completed =====");
		extentTest.get().pass("Register User test completed");

		softAssert.assertAll();

	}

}
