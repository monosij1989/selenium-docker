package com.mono.pages.flightreservation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mono.pages.BasePage;

public class SelectFlights extends BasePage {

	public SelectFlights(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name = "departure-flight")
	private List<WebElement> departureFlights;
	
	@FindBy(name = "arrival-flight")
	private List<WebElement> arrivalFlights;
	
	@FindBy(id = "confirm-flights")
	private WebElement confirmFlights;

	@Override
	public Boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlights));
		return this.confirmFlights.isDisplayed();
	}
	
	public void selectFlights() {
		int random = ThreadLocalRandom.current().nextInt(0,departureFlights.size());
		departureFlights.get(random).click();
		arrivalFlights.get(random).click();
	}
	
	public void confirmFlights() {
		this.confirmFlights.click();
	}

}
