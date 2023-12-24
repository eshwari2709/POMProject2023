package com.qa.opencart.listeners;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import com.qa.opencart.factory.DriverFactory;

public class AllureReportListener implements ITestListener {
		//Getting the method name - to mention in the test cases
		private static String getTestMethodName(ITestResult iTestResult) {
			return iTestResult.getMethod().getConstructorOrMethod().getName();
		}
		// Text attachments for Allure
		@Attachment(value = "Page screenshot", type = "image/png")
		public byte[] saveScreenshotPNG(WebDriver driver) {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
		// Text attachments for Allure
		@Attachment(value = "{0}", type = "text/plain")
		public static String saveTextLog(String message) {
			return message;
		}
		// HTML attachments for Allure
		@Attachment(value = "{0}", type = "text/html")
		public static String attachHtml(String html) {
			return html; }
		
		/** Implementation of "Interface - ITestListener" **/
		@Override
		public void onStart(ITestContext iTestContext) {
			System.out.println("onStart - Starting the method" + iTestContext.getName());
			//iTestContext.setAttribute("WebDriver", BasePage.getDriver());
		}
		
		@Override
		public void onFinish(ITestContext iTestContext) {
			System.out.println("onFinish - Test case" + iTestContext.getName());
		}

		@Override
		public void onTestStart(ITestResult iTestResult) {
			System.out.println("onTestStart - Starting test case" + getTestMethodName(iTestResult) + " start");
		}

		@Override
		public void onTestSuccess(ITestResult iTestResult) {
			System.out.println("onTestSuccess" + getTestMethodName(iTestResult) + " succeed");
		}

		@Override
		public void onTestFailure(ITestResult iTestResult) {
			System.out.println("onTestFailure " + getTestMethodName(iTestResult) + " failed");
			Object testClass = iTestResult.getInstance(); //The instance on which this method was run. 
			
			//WebDriver driver = BasePage.getDriver();
			// Allure ScreenShotRobot and SaveTestLog
			if (DriverFactory.getDriver() instanceof WebDriver) {
				System.out.println("Screenshot for the failed: " + getTestMethodName(iTestResult));
				saveScreenshotPNG(DriverFactory.getDriver());
			}
			// Save a log on allure.
			saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");		
		}

		@Override
		public void onTestSkipped(ITestResult iTestResult) {
			System.out.println("onTestSkipped " + getTestMethodName(iTestResult) + " skipped");
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
			System.out.println("Test failed in defined success ratio " + getTestMethodName(iTestResult));
		}			
}