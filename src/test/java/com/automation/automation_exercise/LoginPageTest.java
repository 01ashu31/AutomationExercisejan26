package com.automation.automation_exercise;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import automation_exercise.base.BaseTest;
import automation_exercise.pages.HomePage;
import automation_exercise.pages.LoginPage;
import utils.PropertyManager;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void verifyRegisterUser() {
		HomePage homePage= new HomePage(driver);
		Assert.assertTrue(homePage.isHomePageVisible());
		
		LoginPage loginPage=homePage.clickOnSignUpLoginButton();
		Assert.assertTrue(loginPage.isNewUserSignupVisible());
		
		Properties testData=PropertyManager.getTestData();
		
		String name=testData.getProperty("userName");
		String email= testData.getProperty("emailPrefix")
		        + System.currentTimeMillis() + "@test.com";
		loginPage.verifySignup(name, email);
		
		
		
	}

}
