package com.mono.tests.flightreservation;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mono.tests.vendorportal.model.FlightReservationTestData;

import com.mono.pages.flightreservation.FlightConfirmationPage;
import com.mono.pages.flightreservation.FlightSearch;
import com.mono.pages.flightreservation.RegistrationConfirmationPage;
import com.mono.pages.flightreservation.RegistrationPage;
import com.mono.pages.flightreservation.SelectFlights;
import com.mono.tests.BaseTest;
import com.mono.utils.ConfigReader;
import com.mono.utils.Constants;
import com.mono.utils.JsonUtil;

public class FlightReservationTest extends BaseTest {
	
	private FlightReservationTestData testData;
	
	@BeforeClass
	@Parameters({"testDataPath"})
	public void setUpParameters(String testDataPath) {
		testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
	}
	
	@Test
	public void userRegistrationTest() {
		RegistrationPage registrationPage =  new RegistrationPage(driver);
		registrationPage.goTo(ConfigReader.get(Constants.FLIGHT_RESERVATION_URL));
		Assert.assertTrue(registrationPage.isAt());
		
		registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
		registrationPage.enterUserCredentials(testData.email(), testData.password());
		registrationPage.enterUserAddress(testData.street(), testData.city(), testData.zip());
		
		registrationPage.register();
	}
	
	@Test (dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationTest() {
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		Assert.assertTrue(registrationConfirmationPage.isAt());
		registrationConfirmationPage.goToFlightsSearch();
	}
	
	@Test (dependsOnMethods = "registrationConfirmationTest")
	public void flightSearchTest() {
		FlightSearch flightSearch = new FlightSearch(driver);
		Assert.assertTrue(flightSearch.isAt());
		
		flightSearch.selectPassengers(testData.passengerCount());
		flightSearch.searchFlight();
	}
	
	@Test (dependsOnMethods = "flightSearchTest")
	public void selectFlightsTest() {
		SelectFlights selectFlights = new SelectFlights(driver);
		Assert.assertTrue(selectFlights.isAt());
		
		selectFlights.selectFlights();
		selectFlights.confirmFlights();
	}
	
	@Test (dependsOnMethods = "selectFlightsTest")
	public void flightConfirmationTest() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		Assert.assertTrue(flightConfirmationPage.isAt());
		
		String price = flightConfirmationPage.getTotalPrice();
		Assert.assertEquals(price, testData.expectedTotalPrice());
	}
	
}
