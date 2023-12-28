package com.qa.opencart.test;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;

@Epic("Epic 100 - To test open cart application")
@Story("US 100 - Login page features")
@Feature("F100 - Feature login page specific validation")
public class LoginPageTest extends BaseTest {
	
	@Description ("Title login page validation....")
	@Severity(SeverityLevel.MINOR)
	@Test (priority = 1)
	public void loginPgTitle() throws TimeoutException
	{
		System.out.println("The thread ID for Chrome is "+ Thread.currentThread().getId());
		String actTitle = loginPage.getLoginPageTitle();
		softAssert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	
	}
	
	@Description ("URL login page validation....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPgURLTest()
	{
		String actURL = loginPage.getLoginPageURL();
		softAssert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));	
	}
	
	@Description ("Login to application....")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 3)
	public void forgotLinkTest()
	{
		softAssert.assertTrue(loginPage.isForgetLinkExist());
	}
	@Description ("Login to application....")
	@Severity(SeverityLevel.BLOCKER)
	@Test (priority = 4)
	public void isRegisterLink()
	{
		softAssert.assertTrue(loginPage.isRegisterLinkVisible());
	}
	
	@Description ("Login to application....")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 5)
	public void loginTest()
	{
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		softAssert.assertTrue(accPage.isLogoutExist());
	}
}