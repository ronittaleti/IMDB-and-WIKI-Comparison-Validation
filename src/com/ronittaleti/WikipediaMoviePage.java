package com.ronittaleti;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WikipediaMoviePage {
	
	WebDriver driver;
	By releaseDateInfo = By.xpath("//*[text()='Release date']/../../td");
	By countryInfo = By.xpath("//*[text()='Country']/../td");
	
	public WikipediaMoviePage(WebDriver driver, String webpage) throws IOException {
		this.driver = driver;
		Utility.redirectToWebpage(driver, webpage);
	}
	
	public String getReleaseDate() {
		String releaseDateRaw = driver.findElement(releaseDateInfo).getText();
		releaseDateRaw = releaseDateRaw.substring(0, releaseDateRaw.indexOf("(")-1);
		return releaseDateRaw;
	}
	
	public String getCountryInfo() {
		return driver.findElement(countryInfo).getText();
	}
}
