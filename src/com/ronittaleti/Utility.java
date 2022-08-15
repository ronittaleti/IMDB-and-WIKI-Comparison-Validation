package com.ronittaleti;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Utility {

	// Test whether a given string is a valid URL by creating a URL object from it.
	public static boolean isUrlValid(String url) {
		try {
			URL urlObject = new URL(url);
			urlObject.toURI();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (URISyntaxException e) {
			return false;
		}
	}
	
	// Redirect the driver to a webpage, checking if the URL is valid, as well as whether it has a valid response code.
	public static void redirectToWebpage(WebDriver driver, String webpageURL) throws IOException {
		// Check if URL is valid. If not, throw an exception and close the window.
		if(isUrlValid(webpageURL)) {
			// Connect to the url and check the response code to see if it is valid.  If not, throw an exception and close the window.
			URL url = new URL(webpageURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
	        int response = conn.getResponseCode();
	        if (response < 400) {
	        	// Open the page if it returned a valid response code
	        	driver.get(webpageURL);
	        } else {
	        	driver.close();
	        	throw new InvalidWebpageResponseException("Response Code: " + response, "Website did not return a valid response code: " + response);
	        }
		} else {
			driver.close();
        	throw new InvalidURLException("URL possibly malformed", "The URL was deemed invalid.");
        }
	}
	
	
}
