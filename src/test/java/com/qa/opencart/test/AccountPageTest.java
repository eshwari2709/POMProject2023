package com.qa.opencart.test;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void logintoAccPage()
	{
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void validateAccPage() throws TimeoutException
	{
		String accTitle = accPage.accountPageTitle();
		softAssert.assertEquals(accTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void validateAccPage2()
	{
		String accURL = accPage.accountURL();
		softAssert.assertEquals(accURL, AppConstants.ACC_PAGE_URL_FRACTION);
	}
	@Test
	public void validateAccPage3()
	{
		List<String> list = accPage.areMenuDisplayed();
		System.out.println(list);
	}
	@Test
	public void searchTest()
	{
		searchPage = accPage.searchingAccountPage("macbook"); //hardcoded
		prodInfoPage = searchPage.selectProduct("");


	}
	
	
}