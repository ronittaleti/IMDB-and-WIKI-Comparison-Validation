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

	private static ArrayList<String> cmdLineArguments = new ArrayList<String>();
	
	public static void main(String[] args) throws ParseException, InvalidURLException {
		
		Options options = new Options();
		
		options.addOption("i", "imdb", true, "movie link of imdb page");
		options.addOption("w", "wiki", true, "movie link of wikipedia page");
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);
		
		String imdbLink = cmd.getOptionValue("imdb");
		String wikiLink = cmd.getOptionValue("wiki");
		
		cmdLineArguments.add(imdbLink);
		cmdLineArguments.add(wikiLink);
		
		if (!cmd.hasOption("imdb")) {
			System.out.println("IMDB page not found. Defaulting to parameter found in testng.xml");
			cmdLineArguments.set(0, null);
		}
		
		if (!cmd.hasOption("wiki")) {
			System.out.println("Wikipedia page not found. Defaulting to parameter found in testng.xml");
			cmdLineArguments.set(1, null);
		}
		
		TestNG runner = new TestNG();
		List<String> suitefiles = new ArrayList<String>();
		suitefiles.add("testng.xml");
		runner.setTestSuites(suitefiles);
		runner.run();
	}
	
	@Test
	@Parameters({"IMDBMovieLink","WikipediaMovieLink"})
	public void imdbWikipediaAssertionTest(String IMDBMovieLink, String WikipediaMovieLink) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();	
		
		if (cmdLineArguments.get(0) != null) {
			IMDBMovieLink = cmdLineArguments.get(0);
		}
		if (cmdLineArguments.get(1) != null) {
			WikipediaMovieLink = cmdLineArguments.get(1);
		}

		IMDBMoviePage imdbMoviePage = new IMDBMoviePage(driver, IMDBMovieLink);
		String imdbReleaseDate = imdbMoviePage.getReleaseDate();
		String imdbCountryOfOrigin = imdbMoviePage.getCountryInfo();
		
		WikipediaMoviePage wikipediaMoviePage = new WikipediaMoviePage(driver, WikipediaMovieLink);
		String wikipediaReleaseDate = wikipediaMoviePage.getReleaseDate();
		String wikipediaCountryOfOrigin = wikipediaMoviePage.getCountryInfo();
		
		System.out.println("------------------------");
		System.out.println("IMDB Release Date: " + imdbReleaseDate);
		System.out.println("Wikipedia Release Date: " + wikipediaReleaseDate);
		System.out.println("IMDB Country of Origin: " + imdbCountryOfOrigin);
		System.out.println("Wikipedia Country of Origin: " + wikipediaCountryOfOrigin);
		System.out.println("------------------------");
		
		driver.close();
		
		assertEquals(imdbReleaseDate, wikipediaReleaseDate);
		assertEquals(imdbCountryOfOrigin, wikipediaCountryOfOrigin);
	}
}