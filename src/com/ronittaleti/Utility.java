package com.ronittaleti;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Utility {

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
	
	public static void redirectToWebpage(WebDriver driver, String webpageURL) throws IOException {
		if(isUrlValid(webpageURL)) {
			URL url = new URL(webpageURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
	        int response = conn.getResponseCode();
	        if (response < 400) {
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
