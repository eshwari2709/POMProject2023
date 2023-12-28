package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	protected Properties prop;
	DriverFactory df;
	protected WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected RegisterPage regPage;
	protected SearchResultsPage searchPage;
	protected ProductInfoPage prodInfoPage;
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	//public void setup()
	public void setup(String browserName)
	{
		df = new DriverFactory();
		prop = df.initProp();
		//supplying .xml value to properties file by giving preferrence of .xml input
		if(browserName!=null) {
			prop.setProperty("browser", browserName); // need to set - cos DriverFactory refers prop file hence override i/p if .xml contains value 
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}	
}