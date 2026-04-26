package com.mono.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mono.pages.BasePage;

public class FlightConfirmationPage extends BasePage {
	
	private static final Logger LOG = LoggerFactory.getLogger(FlightConfirmationPage.class);

	@FindBy(xpath = "//div[contains(text(),'Total Price')]/following-sibling::div/p")
	private WebElement totalPriceLabel;
	
	@FindBy(xpath = "//div[contains(text(),'Flight Confirmation')]/following-sibling::div/p")
	private WebElement flightConfirmationNumberLabel;
	
	public FlightConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public Boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationNumberLabel));
		return this.flightConfirmationNumberLabel.isDisplayed();
	}
	
	public String getTotalPrice() {
		String flightConfirmationNumber = this.flightConfirmationNumberLabel.getText();
		String totalPrice = this.totalPriceLabel.getText();
		LOG.info("Flight Confirmation Number is: {}", flightConfirmationNumber);
		LOG.info("Total Price is: {}", totalPrice);
		return totalPrice;
	}

}
