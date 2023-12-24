package com.qa.opencart.pages;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;

import Utilities.WebElementUtil;

public class AccountsPage {
	//Global
	private WebDriver driver;
	private WebElementUtil eleUtil;
	
	//Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new WebElementUtil(this.driver);
	}
	
	//Locators
	private By search = By.name("search");
	private By logoutLink = By.linkText("Logout");
	private By accTitle = By.xpath("//a[text()='Account']");
	private By menuList = By.xpath("//a[@class='list-group-item']");
	private By searchIcon = By.cssSelector("div#search button");
	
	//Methods
	public String accountPageTitle() throws TimeoutException
	{
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account Page Title loaded properly "+title);
		return title;
	}
	
	public String accountURL()
	{
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account Page URL "+url);
		return url;
	}
	
	public boolean isLogoutExist()
	{
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public List<String> areMenuDisplayed()
	{
		return eleUtil.getElementTextList(menuList);
		//return (List<String>) menuList;
	}
	
	public SearchResultsPage searchingAccountPage (String searchItem) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).clear();
		eleUtil.doSendKeys(search, searchItem);
		eleUtil.doClick(searchIcon);
		
		return new SearchResultsPage(driver); //TDD
		
	}
	
	public void logout()
	{
		if (isLogoutExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	
	
	
	
	
	


}
