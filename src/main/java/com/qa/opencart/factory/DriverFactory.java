package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import Utilities.FrameworkException;

public class DriverFactory {
	public static WebDriver driver;
	public static Properties prop;
	public static OptionsManager optionsManager;
	
	//Thread local - Java
	public static ThreadLocal<WebDriver>
	tlDriver = new ThreadLocal<WebDriver>();
	public static String highlight = null;
	
	/**
	 * This method is used to launch the browser n the basis of given browserName
	 * @param browserName
	 * @return this returns the driver instance
	 */
	public static WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser"); // from config.properties
		//String browserName = System.getProperty("browser")); //when run from maven input for different browser
		optionsManager = new OptionsManager(prop); // from OptionsManager.java
		highlight = prop.getProperty("highlight");  //highlight on webelement Y/N
		System.out.println("Your browser name is "+browserName);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			//tlDriver.set(driver);
			//or
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

			break;
		case "edge":
			driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println("Please pass valid browser.....");			// throw new AutomationException("INVALID BROWSER "+browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		return getDriver();
	}
	
	//Get the Thread local Driver 
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}

	/**
	 * This method is used to retrieve properties input from config file
	 * @return this returns property value requested by sending 'key'
	 */
	public Properties initProp() {
		// mvn clean install -Denv="qa" --> pick data from config.qa.properties
		// mvn clean install --> not giving any input, handle it
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		try {
			if (envName == null) {
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else
				switch (envName.toLowerCase().trim()) {
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "sit":
					ip = new FileInputStream("./src/test/resources/config/config.sit.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.prod.properties");
					break;
				default:
					System.out.println("Please input valid environment. . . " + envName);
					throw new FrameworkException("Wrong Env Name: " + envName);
				}
		} catch (FileNotFoundException e) {

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	/**** Take Screenshots
	 * Implemented in ExtendReportListener.java
	 * @param methodName
	 * @return
	 */
	
	public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+".png";
				
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}