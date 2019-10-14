package com.spy.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.spy.constants.Constant;



public class StartBrowser {

	private static String urlToOpen;
	private static String brName;
	WebDriver driver;
//	static WebDriver driver = new ChromeDriver(options);
	public StartBrowser(String url, String browserName) {

		StartBrowser.urlToOpen = url;
		StartBrowser.brName = browserName;

	}

	public  WebDriver  open() {

		if ("chrome".equalsIgnoreCase(brName)) {

			 
			System.setProperty(Constant.driverProperty, Constant.chromeExePath);
			 ChromeOptions options = new ChromeOptions();
			
			options.addArguments("--disable-extensions");
			options.setExperimentalOption("useAutomationExtension", false);
			driver = new ChromeDriver(options);
			driver.get(urlToOpen);
			driver.manage().window().maximize();

		}
		
		else if("chromehl".equalsIgnoreCase(brName)) {
			 
			System.setProperty(Constant.driverProperty, Constant.chromeExePath);
			 ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			options.addArguments("--disable-extensions");
			options.addArguments("--headless");
			options.setExperimentalOption("useAutomationExtension", false);
			
			driver.get(urlToOpen);
			driver.manage().window().maximize();

			
		}
		return driver;

	}

}
