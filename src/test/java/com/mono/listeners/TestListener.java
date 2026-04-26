package com.mono.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {
	
	public void onTestFailure(ITestResult result) {
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
		String htmlImage = String.format(htmlImageFormat, base64);
		Reporter.log(htmlImage);
	  }

}
