package com.mono.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mono.pages.BasePage;

public class DashboardPage extends BasePage {

	@FindBy(id = "monthly-earning")
	private WebElement monthlyEarningLabel;
	
	@FindBy(id = "annual-earning")
	private WebElement annualEarningLabel;
	
	@FindBy(id = "profit-margin")
	private WebElement profitMarginLabel;
	
	@FindBy(id = "available-inventory")
	private WebElement availableInventoryLabel;
	
	@FindBy(css = "#dataTable_filter input")
	private WebElement searchInput;
	
	@FindBy(id = "dataTable_info")
	private WebElement dataTableInfoLabel;
	
	@FindBy(css = "img.img-profile")
	private WebElement profileIcon;
	
	@FindBy(xpath = "//a[@class='dropdown-item'][last()]")
	private WebElement logoutLink;
	
	@FindBy(css = "#logoutModal a")
	private WebElement modalLogoutButton;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public Boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.searchInput));
		return this.searchInput.isDisplayed();
	}
	
	public String getMonthlyEarning() {
		return this.monthlyEarningLabel.getText().trim();
	}
	
	public String getAnnualEarning() {
		return this.annualEarningLabel.getText().trim();
	}
	
	public String getProfitMargin() {
		return this.profitMarginLabel.getText().trim();
	}
	
	public String getAvailableInventory() {
		return this.availableInventoryLabel.getText().trim();
	}
	
	public void enterSearchKeyword(String keyword) {
		this.searchInput.sendKeys(keyword);
	}
	
	public int getSearchResultsCount() {
		String info = this.dataTableInfoLabel.getText().trim();
		String[] a = info.split(" ");
		return Integer.parseInt(a[5]);
	}
	
	public void logout() {
		this.profileIcon.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
		this.logoutLink.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
		this.modalLogoutButton.click();
	}

}
