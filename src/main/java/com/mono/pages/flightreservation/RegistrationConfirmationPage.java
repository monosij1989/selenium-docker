package com.mono.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mono.pages.BasePage;

public class RegistrationConfirmationPage extends BasePage {

	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "go-to-flights-search")
	private WebElement goToFlightsSearchButton;
	
	public void goToFlightsSearch() {
		this.goToFlightsSearchButton.click();
	}

	@Override
	public Boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
		return this.goToFlightsSearchButton.isDisplayed();
	}
}
