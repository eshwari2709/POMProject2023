package com.qa.opencart.pages;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.constants.AppConstants;
import Utilities.WebElementUtil;
import io.qameta.allure.Step;

public class LoginPage {
	/**Global declarations --Driver, Classes, Variables**/
	private WebDriver driver;
	private WebElementUtil eleUtil; 
	
	/**Locators**/
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By image = By.cssSelector("img[title='naveenopencart']");
	private By registerLink = By.linkText("Register");

	
	/**Constructor**/
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new WebElementUtil(this.driver);
	}

	/**Page actions --> Supporting methods
	 * @throws TimeoutException **/
	public String getLoginPageTitle() throws TimeoutException
	{
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,AppConstants.MEDIUM_DEFAULT_WAIT);
		System.out.println("Page loaded with the title "+title);
		return title;
	}
	public String getLoginPageURL()
	{
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("URL is "+url);
		return url;	
	}
	public boolean isForgetLinkExist() 
	{
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}
	public boolean isRegisterLinkVisible()
	{
		return eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}
	@Step("Login to application UN {0} PWD {1}")
	public AccountsPage doLogin(String strUN, String strpwd)
	{
		eleUtil.doSendKeys(userName, strUN);
		eleUtil.doSendKeys(password, strpwd);
		eleUtil.doClick(loginBtn);
		//eleUtil.doSendKeysWithWait(userName, AppConstants.MEDIUM_DEFAULT_WAIT, pwd);
		return new AccountsPage(driver);
		
	}
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
	}
}