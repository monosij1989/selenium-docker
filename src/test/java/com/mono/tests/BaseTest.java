package com.mono.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.mono.utils.ConfigReader;
import com.mono.utils.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.mono.listeners.TestListener;

@Listeners({TestListener.class})
public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeSuite
	public void initializeProperties() {
		ConfigReader.initialize();
	}
	
	@BeforeClass
	public void setUp(ITestContext ctx) throws MalformedURLException {
		System.out.println(ConfigReader.get(Constants.SELENIUM_GRID_ENABLED));
		if (Boolean.parseBoolean(ConfigReader.get(Constants.SELENIUM_GRID_ENABLED)))
			this.driver = setRemoteDriver();
		else
			this.driver = setLocalDriver();	
		this.driver.manage().window().maximize();
		ctx.setAttribute("driver", this.driver);
	}
	
	private WebDriver setLocalDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}
	
	private WebDriver setRemoteDriver() throws MalformedURLException {
		Capabilities capabilities = new ChromeOptions();
		if (ConfigReader.get(Constants.BROWSER).equals("firefox"))
			capabilities = new FirefoxOptions();
		String urlFormat = ConfigReader.get(Constants.SELENIUM_GRID_URL_FORMAT);
		String url = String.format(urlFormat, ConfigReader.get(Constants.SELENIUM_GRID_HUB_HOST));
		return new RemoteWebDriver(new URL(url),capabilities);
	}
	
	@AfterClass
	public void tearDown() {
		this.driver.quit();
	}

}
