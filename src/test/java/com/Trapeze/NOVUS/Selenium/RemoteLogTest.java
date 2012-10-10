package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RemoteLogTest {

	private static WebDriver driver=AllTests.driver1;
	private RemoteLogOn tester = new RemoteLogOn(); 
	public static Process pp;
	
	@BeforeClass
	public static void setUp() throws Exception {
		Runtime run = Runtime.getRuntime();
		pp = run.exec(AllTests.MDTRouteSim);
//		driver.switchTo().window(AllTests.CAD);
//		((JavascriptExecutor) driver).executeScript("window.focus()");
		AllTests.switchToCAD();
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Block Assignment)");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		AllTests.switchToCAD();
		AllTests.close_link();
	}

	@Test
	public void testTitle() throws InterruptedException{		
		SimpleDateFormat dateFormat = new SimpleDateFormat ("MM-dd-yy");
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		try{
			assertEquals("Result","Block Assignment ["+dateFormat.format(cal.getTime())+"]",driver.findElement(By.cssSelector("div.app_caption")).getText());
		}catch (Exception e){
			Thread.sleep(3*1000);
			assertEquals("Result","Block Assignment ["+dateFormat.format(cal.getTime())+"]",driver.findElement(By.cssSelector("div.app_caption")).getText());
		}
	}	
	
	@Test
	public void testLogOn() throws ParseException, InterruptedException {
		String[] expected = tester.logOn(driver);		
		String[] actual = new String[3];		
		AllTests.switchToDCC();
		AllTests.wait_Element("div.app_body button:nth-child(4)"); 		//pause waiting time for loading
		actual[2] = driver.findElement(By.cssSelector("div.app_body button:nth-child(4)")).getText();		
		
		AllTests.switchToCAD();
		AllTests.wait_Element("tbody.blockInfo_tbody tr:first-child td:nth-child(5)");
		actual[0]=driver.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(5)")).getText();
		actual[1]=driver.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(6)")).getText();
		Assert.assertArrayEquals("Result",expected,actual);
	}		
}
