package Utilities;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class WebElementUtil {
	public static WebDriver driver;
	public static Select select;
	public static WebDriverWait wait;
	
	public WebElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	private Select select(By locator) {
		Select select = new Select(getElement(locator));
		return select;
	}
	
	/**
	 * This method will help user give locator Type -> id, name, xpath etc., this
	 * Utility will take care finding which type in switch case and pass the
	 * respective locator value to find and returns By [on which type it is] and
	 * pass this By to getElement method here
	 * 
	 * @param locator Type ->id, name, xpath etc.,
	 * @return By
	 */
	public By getBy(String locatorType, String locatorValue) {
		By by = null;

		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "classname":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "cssselector":
			by = By.cssSelector(locatorValue);
			break;
		case "linkText":
			by = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			by = By.partialLinkText(locatorValue);
			break;
		default:
			System.out.println("Invalid input");
		}
		return by;
	}

	// for the above getBy -> "sendKeys" to be called as below:
	public void sendKeys(String locatorType, String locatorValue, String value) {
		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
	}

	/**
	 * On this method "By" locator explicitly mentioned by maintaining as POM By
	 * empID = By.id("input-email"); By empName = By.name("name");
	 * @param locator
	 * @return
	 */
// getElement
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			System.out.println("Element is found ...." + locator);
		} catch (NoSuchElementException e) {
			System.out.println("Element not found...." + locator);
			// element = waitforElementVisible(locator, Default_Time_Out);
			/// add method for wait
		}
		return element;
	}
//getElement Overloaded
	public WebElement getElement(String locator, String locatorValue)
	{
		return driver.findElement(getBy(locator,locatorValue));
	}

//SendKeys
	public void doSendKeys(By locator, String value) {
		if (value == null) {
			System.out.println("null values are not allowed...");
			// add Exception
		}
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

// Click
	public void doClick(By locator) {
		getElement(locator).click();
	}

//getText
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

//getTextOverloaded
	public String doElementGetText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
	}
	


//isElementDisplayed
	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

//isSelected
	public boolean isEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

//getAttributeValue
	public String getAttributeValue(By locator, String attributeName) {
		return driver.findElement(locator).getAttribute(attributeName);
	}
	/*"************************************Starts collection of Elements************************************"*/
//getElements
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

//getElementsCount
	public int getElementsCount(By locator) {
		return driver.findElements(locator).size();
	}

//getElementTextList
	public List<String> getElementTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleListText = new ArrayList<String>();

		for (WebElement e : eleList) {
			String Text = e.getText();
			eleListText.add(Text);
		}
		return eleListText;
	}

//getAttributeValue
	public List<String> getElementsAttributeValue(By locator, String attributeName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttributeList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attributeValue = e.getAttribute(attributeName);
			eleAttributeList.add(attributeValue);
		}
		return eleAttributeList;
	}
	/*"*********************************************** Drop Down********************************************"*/
	/** with Select Class **/
	public void doSelectDropDownByIndex(By locator, int index) {
		select.selectByIndex(index);
	}

	public void doSelectDropDownByValueAttribute(By locator, String value) {
		select.deselectByValue(value);

	}

	public void doSelectDropDownByVisisbleText(By locator, String text) {
		select.selectByVisibleText(text);
	}

	public int getDropDownValueCount(By locator) {
		return getAllDropDownAllOptions(locator).size();
	}

	public List<String> getAllDropDownAllOptions(By locator) {
		List<WebElement> optionsList = select.getOptions();
		List<String> allOptionsList = new ArrayList<String>();
		for (WebElement e : optionsList) {
			String eachList = e.getText();
			allOptionsList.add(eachList);
		}
		return allOptionsList;
	}

	public boolean doSelectDropDownValue(By locator, String dropDownValue) {
		boolean flag = false;
		List<WebElement> optionList = select.getOptions();
		System.out.println("Total number of options .. " + optionList);
		for (WebElement e : optionList) {
			String text = e.getText();
			if (text.equals(dropDownValue)) {
				flag = true;
				e.click();
				break;
			}
		}
		if (flag == false) {
			System.out.println(dropDownValue + "is not present in the drop down" + locator);
		}
		return flag;
	}

	// ** without Select method ** / --> ** JQuery ** / ** BootStrap **//
	public boolean DoSelectValueFromDropDownWithoutSelect(By locator, String value) {
		return false;

	}

	/*************************************************
	 * Search in search box and select respective searchText
	 **********************************************/
	public void Search(By searchField, By suggestionsText, String searchString, String selectionSearch)
			throws InterruptedException {
		// driver.findElement(searchField).sendKeys(searchString);
		doSendKeys(searchField, searchString);
		Thread.sleep(2000);

		List<WebElement> searchLlist = getElements(suggestionsText);
		System.out.println("Total size of search count = " + searchLlist.size());

		for (WebElement e : searchLlist) {
			String text = e.getText();
			if (text.contains(selectionSearch)) {
				e.click();
				break;
			}
		}
	}

	/*"************************************************** Wait**********************************************"*/
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible on the page.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForPresenceOfElement(By locator, int timeout)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible on the page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param intervalTime
	 * @return
	 */
	public WebElement waitWithIntervalPresence(By locator, int timeout, int intervalTime)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout, intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(By locator, int timeout)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitWithIntervalVisibility(By locator, int timeout, int intervalTime)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout, intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeout)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	
	public List<WebElement> waitForPresenceOfElements(By locator, int timeout)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void doClickWithWait(By locator, int timeout)
	{
		waitForVisibilityOfElement(locator, timeout).click();
	}
	
	public void doSendKeysWithWait(By locator, int timeout, String text)
	{
		waitForVisibilityOfElement(locator,timeout).sendKeys(text);
	}
	
	/*"************************************************** WaitWithAlerts**********************************************"*/
	
	public Alert waitForAlert(By locator, int timeout)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptJSAlert(By locator, int timeout)
	{
		waitForAlert(locator, timeout).accept();
	}
	
	public void dismissJSAlert(By locator, int timeout)
	{
		waitForAlert(locator, timeout).dismiss();
	}
	public String getTextJSAlert(By locator, int timeout)
	{
		String waitForAlert= waitForAlert(locator, timeout).getText();
		return waitForAlert;
		//or
		//return waitForAlert(locator, timeout).getText();
	}
	public void alertSendKeys(By locator, int timeout, String value)
	{
		waitForAlert(locator, timeout).sendKeys(value);
	}
	
	/*"**************************************************waitWithFrames**********************************************"*/
	public void generalWaittoReuse(int timeout)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	}
	
	public void switchToAlertByLocator(By locator, int timeout)
	{
		generalWaittoReuse(timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	public void switchToAlertByIndex(int index, int timeout)
	{
		generalWaittoReuse(timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(timeout));
	}
	public void switchToAlertByIDorName(String IDorName, int timeout)
	{
		generalWaittoReuse(timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDorName));
	}

	public void switchToAlertByElement(WebElement element, int timeout)
	{
		generalWaittoReuse(timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	/*"**************************************************miscelleanousWait**********************************************"*/
	
	public void clickWhenClickable1(By locator, int timeout)
	{
		generalWaittoReuse(timeout);
		//try 
		//{
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		//check
		//} //catch (TimeoutException e)
		//{
			//System.out.println("Element is not clickable....");
		//}
	}
	
	public boolean isPageLoaded(int timeout)
	{
		generalWaittoReuse(timeout);
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
		return Boolean.parseBoolean(flag);
	}
	///Dooo
	public void waitForElementWithFluentWait(By locator, int timeout, int intervalTime)
	{
		
	}
	
	/*
	 * "**************************************************customWaitwithPolling**********************************************"
	 */
	public WebElement customWait(By locator, int timeout, int IntervalTime) {
		WebElement element = null;
		int attempts = 0;
		while (timeout < attempts) {
			try {
				element = getElement(locator);
				System.out.println("WebElement idetified.... " + locator + "in attempts" + attempts);
			} catch (NoSuchElementException e) {
				try {
					Thread.sleep(500); //default interval time
					Thread.sleep(IntervalTime); //user input interval time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}

		if (element == null) {
			System.out.println("Element is not found... tried for " + timeout + "");
			throw new FrameworkException("No Such Element....");
		}
		return element;
	}
		
	/*"**************************************************CheckNewWindowPresent**********************************************"*/
	public boolean isNewWindowPresent(int timeout, int ExpectedNoOfWindows)
	{
		generalWaittoReuse(timeout);
		
		if (wait.until(ExpectedConditions.numberOfWindowsToBe(ExpectedNoOfWindows)))
		{
		return true;	
		}
		System.out.println("Number of Windows are not matching.... ");
		return false;
	}
	/*"**************************************************WindowHandles**********************************************"*/
	public boolean selectWindows (String name)
	{
		Set<String> windows = driver.getWindowHandles();
		//Iterator it = windowCollection.iterator();
		for (String window : windows)
		{
			driver.switchTo().window(window);
			if(driver.getTitle().contains(name))
			{
				return true;
			}
			System.out.println("Given Window not present... "+name);
		}
		return false;
	
	}
		/*"*******************************************ExceptionHandling*****************************************"*/

		/**Created a class AutomationException <- under Utility**/
		
		/*"**************************************************Actions**********************************************"*/

	public void DragandDrop(WebElement fromElement, WebElement toElement)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(fromElement,toElement).perform();		
	}
	
	//ActionClick
	//ActionSendKeys
	//TwoLevelMenuHandling
	//FourLevelMenuHandling
	
	/*"***************************************Basic common Validations************************************"*/
	//Dooo
	/*public String waitForTitleIs2(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println(title + " title value is not present....");
			e.printStackTrace();
		}
		return null;
	} */
	
	@Step("waiting for the page 'Title': {0} and timeout: {1}")
	public String waitForTitleIs(String title, int timeOut) throws TimeoutException {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	if (wait.until(ExpectedConditions.titleIs(title))) {
		return driver.getTitle();
	}
	return null;
	}
	
	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	
	public String waitForURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		if (wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
//Dooo
	
	
}
	
	
	
	
	
	
	

	