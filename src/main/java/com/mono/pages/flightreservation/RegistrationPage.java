package com.mono.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mono.pages.BasePage;

public class RegistrationPage extends BasePage {
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "firstName")
	private WebElement firstNameInput;
	
	@FindBy(id = "lastName")
	private WebElement lastNameInput;
	
	@FindBy(id = "email")
	private WebElement emailInput;
	
	@FindBy(id = "password")
	private WebElement passwordInput;
	
	@FindBy(name = "street")
	private WebElement streetInput;
	
	@FindBy(name = "city")
	private WebElement cityInput;
	
	@FindBy(name = "zip")
	private WebElement zipInput;
	
	@FindBy(id = "register-btn")
	private WebElement registerButton;
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	public void enterUserDetails(String fName, String lName) {
		firstNameInput.sendKeys(fName);
		lastNameInput.sendKeys(lName);
	}
	
	public void enterUserCredentials(String email, String password) {
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
	}
	
	public void enterUserAddress(String street, String city, String zip) {
		streetInput.sendKeys(street);
		cityInput.sendKeys(city);
		zipInput.sendKeys(zip);
	}
	
	public void register() {
		registerButton.click();
	}

	@Override
	public Boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(this.firstNameInput));
		return this.firstNameInput.isDisplayed();
	}
	
}
