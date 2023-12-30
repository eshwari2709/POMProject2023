package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void doLogin4ProdInfoPage()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
		
//	@DataProvider
//	public Object[][] getSearchExcelTestData() {
//		return ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
//	}
	
	@Test(dataProvider = "getSearchExcelTestData")
	public void productImagesTest(String searchKey, String productName, String imageCount) {
		searchPage = accPage.searchingAccountPage(searchKey);
		prodInfoPage = searchPage.selectProduct(productName);
		Assert.assertEquals(String.valueOf(prodInfoPage.getProductImagesCount()), imageCount);
	}

	@Test
	public void productInfoTest() {
		searchPage = accPage.searchingAccountPage("MacBook");
		prodInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = prodInfoPage.getProductDetails();

		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");

		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("extaxprice"), "$2,000.00");
		
		softAssert.assertAll();

	}

}
