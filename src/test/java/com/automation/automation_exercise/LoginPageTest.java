package com.automation.automation_exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

import automation_exercise.base.BaseTest;
import automation_exercise.pages.HomePage;
import automation_exercise.pages.LoginPage;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void verifyRegisterUser() {
		HomePage homePage= new HomePage(driver);
		Assert.assertTrue(homePage.isHomePageVisible());
		LoginPage loginPage=homePage.clickOnSignUpLoginButton();
		Assert.assertTrue(loginPage.isNewUserSignupVisible());
//		String userName=prop.
		loginPage.verifySignup("Ashutosh", "ashutosh" + System.currentTimeMillis()+"@test.com");
		
		
		
	}

}
