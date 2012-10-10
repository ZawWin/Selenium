package com.Trapeze.NOVUS.Selenium;

import java.net.URL;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class SUITE1 {

	public static WebDriver driver1;
	public static String window1;
	public static String window2;
	
	@BeforeClass	
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.ie.driver", "C:/Users/zaw.win/Desktop/Eclipse Project");
		
		DesiredCapabilities iecapability = DesiredCapabilities.internetExplorer();
		iecapability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);		
		driver1 = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),iecapability);
		driver1.manage().window().maximize();
		driver1.get("http://www.google.com");		
 	}
	
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		driver1.quit();
	}
	
	public void main (String args[]){
	}
}

