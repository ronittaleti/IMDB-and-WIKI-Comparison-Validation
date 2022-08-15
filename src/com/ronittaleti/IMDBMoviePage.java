package com.ronittaleti;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IMDBMoviePage {
	
	WebDriver driver;
	By releaseDateInfo = By.xpath("//*[@data-testid='title-details-releasedate']/div");
	By countryInfo = By.xpath("//*[@data-testid='title-details-origin']/div");

	public IMDBMoviePage(WebDriver driver, String webpage) throws IOException {
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
