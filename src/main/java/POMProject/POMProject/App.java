package POMProject.POMProject;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import POMProject.POMProject.*;
import Utilities.WebElementUtil;

/**
 * Hello world!
 *
 */
public class App {
	static WebDriver driver;
	static WebElementUtil Util;

	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://www.google.co.in/");
		// driver.findElement(By.xpath("//div[@class='wM6W7d']")).sendKeys("Selenium
		// Automation");
		By searchField = By.name("q");
		By suggestionsText = By.xpath("//div[@class='wM6W7d']/span");
		searchList(searchField,suggestionsText,"Selenium Automation", "selenium tutorial");
	}

	public static void searchList(By searchField, By suggestionsText, String searchString, String selectionSearch)
		throws InterruptedException {
		driver.findElement(searchField).sendKeys(searchString);
		//Util.getElement(searchField).sendKeys(searchString);
		Thread.sleep(2000);
		List<WebElement> searchLlist = driver.findElements(suggestionsText);
				//Util.getElements(suggestionsText);
		System.out.println("Total size of search count = " + searchLlist.size());

		for (WebElement e : searchLlist) {
			String text = e.getText();
			if (text.contains(selectionSearch)) {
				e.click();
				break;
			}
		}
	}
}
