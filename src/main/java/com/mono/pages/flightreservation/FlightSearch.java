package com.mono.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.mono.pages.BasePage;

public class FlightSearch extends BasePage {

	@FindBy(id = "passengers")
	private WebElement passengersSelect;
	
	@FindBy(id = "search-flights")
	private WebElement searchFlightsButton;
	
	public FlightSearch(WebDriver driver) {
		super(driver);
	}

	@Override
	public Boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.searchFlightsButton));
		return this.searchFlightsButton.isDisplayed();
	}
	
	public void selectPassengers(String count) {
		Select passengers = new Select(this.passengersSelect);
		passengers.selectByValue(count);
	}
	
	public void searchFlight() {
		this.searchFlightsButton.click();
	}

}
