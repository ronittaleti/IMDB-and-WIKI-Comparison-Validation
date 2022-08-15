package com.ronittaleti;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WikipediaMoviePage {
	
	// Reference to driver as well as the xPaths to the release date and country info nodes on Wikipedia.
	WebDriver driver;
	By releaseDateInfo = By.xpath("//*[text()='Release date']/../../td");
	By countryInfo = By.xpath("//*[text()='Country']/../td");
	
	// Constructor that assigns the driver and also redirects the open browser to the given page.
	public WikipediaMoviePage(WebDriver driver, String webpage) throws IOException {
		this.driver = driver;
		Utility.redirectToWebpage(driver, webpage);
	}
	
	// Returns the release date, and removes the text of the country associated with it, if it exists.
	public String getReleaseDate() {
		String releaseDateRaw = driver.findElement(releaseDateInfo).getText();
		releaseDateRaw = releaseDateRaw.substring(0, releaseDateRaw.indexOf("(")-1);
		return releaseDateRaw;
	}
	
	// Returns the country info.
	public String getCountryInfo() {
		return driver.findElement(countryInfo).getText();
	}
}
