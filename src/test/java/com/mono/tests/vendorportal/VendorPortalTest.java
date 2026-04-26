package com.mono.tests.vendorportal;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mono.pages.vendorportal.DashboardPage;
import com.mono.pages.vendorportal.LoginPage;
import com.mono.tests.BaseTest;
import com.mono.tests.vendorportal.model.VendorPortalTestData;
import com.mono.utils.ConfigReader;
import com.mono.utils.Constants;
import com.mono.utils.JsonUtil;

public class VendorPortalTest extends BaseTest {
	
	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private VendorPortalTestData testData;
	
	@BeforeClass
	@Parameters({"testDataPath"})
	public void setUpPages(String testDataPath) {
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
	}
	
	@Test
	public void loginTest() {
		loginPage.goTo(ConfigReader.get(Constants.VENDOR_PORTAL_URL));
		Assert.assertTrue(loginPage.isAt());
		
		loginPage.login(testData.username(), testData.password());
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void dashboardTest() {
		Assert.assertTrue(dashboardPage.isAt());
		
		String monthlyEarning = dashboardPage.getMonthlyEarning();
		Assert.assertEquals(monthlyEarning, testData.monthlyEarning());
		String annualEarning = dashboardPage.getAnnualEarning();
		Assert.assertEquals(annualEarning, testData.annualEarning());
		String profiMargin = dashboardPage.getProfitMargin();
		Assert.assertEquals(profiMargin, testData.profitMargin());
		String availableInventory = dashboardPage.getAvailableInventory();
		Assert.assertEquals(availableInventory, testData.availableInventory());
		
		dashboardPage.enterSearchKeyword(testData.searchKeyword());
		int searchResultsCount = dashboardPage.getSearchResultsCount();
		Assert.assertEquals(searchResultsCount, testData.searchResultsCount());
	}
	
	@Test(dependsOnMethods = "dashboardTest")
	public void logoutTest() {
		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.logout();
		Assert.assertTrue(loginPage.isAt());
	}
	
}
