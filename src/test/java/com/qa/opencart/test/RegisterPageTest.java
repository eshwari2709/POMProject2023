package com.qa.opencart.test;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import Utilities.ExcelUtil;
public class RegisterPageTest extends BaseTest{
	@BeforeClass
	public void doLogin()
	{
		registerPage =loginPage.navigateToRegisterPage();
	}
	public String getRandomEmailID() {
		return "testautomation" + System.currentTimeMillis() + "@opencart.com";
		//return "testautomation" + UUID.randomUUID()+"@gmail.com";
	}
	@DataProvider
	public Object[][] getUserRegData() {
		return new Object[][] {
				{ "Eshwari", "K", "9900990099", "Pass@123", "yes" },
				{ "Tom", "T", "9900990044", "test@123", "yes" },
				{ "Sibi", "R", "9900990011", "test@123", "yes" }
		};
	}
	
//	/**DataProvider will pass the retrieved from ExcelUtil - data to @Test **/
//	@DataProvider
//	public Object[][] getUserRegTestExcelData() {
//		Object regData[][] = Utilities.ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
//		return regData;
//	}
	
	//@Test(dataProvider = "getUserRegTestExcelData")
	@Test(dataProvider = "getUserRegData")
	public void UserRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {

		boolean isRegDone = registerPage.userRegisteration(firstName, lastName, getRandomEmailID(), telephone, password,
				subscribe);
		Assert.assertTrue(isRegDone);
	}
}