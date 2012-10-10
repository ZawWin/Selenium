package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RemoteLogOffTest {
	private static WebDriver driver=AllTests.driver1;
	private RemoteLogOn tester = new RemoteLogOn();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Block Assignment)");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.switchToCAD();
		AllTests.close_link();
		
	}

	@Test
	public void testLogOff() throws InterruptedException, ParseException, IOException {
//		AllTests.switchToCAD();
		tester.logOff(driver);		
		String expected = "Logon";			
		AllTests.switchToDCC();					
		AllTests.wait_Element("div.app_body button:nth-child(8)");
		Thread.sleep(5000);
		String actual = driver.findElement(By.cssSelector("div.app_body button:nth-child(8)")).getText();		
		assertEquals("Result", expected, actual);
	}
}
