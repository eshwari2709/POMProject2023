package com.qa.opencart.test;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void doLogin()
	{
		regPage=loginPage.navigateToRegisterPage();
	}
	
	
	
	@Test
	public void UserRegisterTest()
	{
			boolean isRegistered = regPage.userRegisteration(null, null, null, null, null, null);
			Assert.assertTrue(isRegistered);
	}
}
