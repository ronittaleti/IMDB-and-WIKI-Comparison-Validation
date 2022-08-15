package com.ronittaleti;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTestNG {

	// Stores the command line arguments from main method.
	private static ArrayList<String> cmdLineArguments = new ArrayList<String>();
	
	public static void main(String[] args) throws ParseException, InvalidURLException {
		
		// Create command line parser with options for movie pages.
		Options options = new Options();
		options.addOption("i", "imdb", true, "movie link of imdb page");
		options.addOption("w", "wiki", true, "movie link of wikipedia page");
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);
		
		// Get links to the IMDB and Wikipedia pages as strings, assuming they exist, and add them as arguments.
		String imdbLink = cmd.getOptionValue("imdb");
		String wikiLink = cmd.getOptionValue("wiki");
		cmdLineArguments.add(imdbLink);
		cmdLineArguments.add(wikiLink);
		
		// Check if the parser found the options, if not, store the given option as null.
		if (!cmd.hasOption("imdb")) {
			System.out.println("IMDB page not found. Defaulting to parameter found in testng.xml");
			cmdLineArguments.set(0, null);
		}
		if (!cmd.hasOption("wiki")) {
			System.out.println("Wikipedia page not found. Defaulting to parameter found in testng.xml");
			cmdLineArguments.set(1, null);
		}
		
		// Create TestNG object, add the testng.xml file, and run the suite.
		TestNG runner = new TestNG();
		List<String> suitefiles = new ArrayList<String>();
		suitefiles.add("testng.xml");
		runner.setTestSuites(suitefiles);
		runner.run();
	}
	
	@Test
	@Parameters({"IMDBMovieLink","WikipediaMovieLink"})
	public void imdbWikipediaAssertionTest(String IMDBMovieLink, String WikipediaMovieLink) throws IOException {
		
		// Downloads the chrome driver using Boni García's Web Driver Manager.
		WebDriverManager.chromedriver().setup();
		
		// Opens a window.
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();	
		
		// Checks if the links passed from the command line are null, if they aren't, use their values instead.
		if (cmdLineArguments.get(0) != null) {
			IMDBMovieLink = cmdLineArguments.get(0);
		}
		if (cmdLineArguments.get(1) != null) {
			WikipediaMovieLink = cmdLineArguments.get(1);
		}

		// Opens the IMDB page link as an IMDBMoviePage, and gets the release date and country of origin.
		IMDBMoviePage imdbMoviePage = new IMDBMoviePage(driver, IMDBMovieLink);
		String imdbReleaseDate = imdbMoviePage.getReleaseDate();
		String imdbCountryOfOrigin = imdbMoviePage.getCountryInfo();
		
		// Opens the Wikipedia page link as a WikipediaMoviePage, and gets the release date and country of origin.
		WikipediaMoviePage wikipediaMoviePage = new WikipediaMoviePage(driver, WikipediaMovieLink);
		String wikipediaReleaseDate = wikipediaMoviePage.getReleaseDate();
		String wikipediaCountryOfOrigin = wikipediaMoviePage.getCountryInfo();
		
		// Prints the retrieved release dates and countries of origin from IMDB and Wikipedia.
		System.out.println("------------------------");
		System.out.println("IMDB Release Date: " + imdbReleaseDate);
		System.out.println("Wikipedia Release Date: " + wikipediaReleaseDate);
		System.out.println("IMDB Country of Origin: " + imdbCountryOfOrigin);
		System.out.println("Wikipedia Country of Origin: " + wikipediaCountryOfOrigin);
		System.out.println("------------------------");
		
		// Closes the window.
		driver.close();
		
		// Assertion test, comparing the IMDB and Wikipedia release dates and countries of origin respectively.
		assertEquals(imdbReleaseDate, wikipediaReleaseDate);
		assertEquals(imdbCountryOfOrigin, wikipediaCountryOfOrigin);
	}
}