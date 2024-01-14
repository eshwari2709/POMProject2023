package com.qa.opencart.factory;

import java.util.Properties;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	 OptionsManager(Properties prop)
	{
		this.prop = prop;
	}
	public ChromeOptions getChromeOptions()
		{
		co= new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
			co.addArguments("--headlesss");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			co.addArguments("--incognito");
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
			}
		return co;
		}
	public EdgeOptions getEdgeOptions()
		{
		eo= new EdgeOptions();
        eo.addArguments("--disable-notifications");
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
			eo.addArguments("--headlesss");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			eo.addArguments("--incognito");
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "edge");
			}
		return eo;
		}
	public FirefoxOptions getFirefoxOptions()
			{
			fo= new FirefoxOptions();
			if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
				fo.addArguments("--headlesss");
			if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
				fo.addArguments("--incognito");
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				co.setCapability("browserName", "firefox");
			}
			return fo;
			}			
	}