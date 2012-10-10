package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UnauthorizedMovementTest {
	private static WebDriver driver = AllTests.driver1;
	private UnauthorizedMovement tester = new UnauthorizedMovement();
	private static Process pp;
	
	@BeforeClass
	public static void setUp() throws Exception {
		Runtime run = Runtime.getRuntime();
		
		
		pp = run.exec(AllTests.MDTRouteSim);
//		pp = run.exec("C:\\NovusITS Demo Setup\\Trapeze\\Dev\\6.3\\Trapeze\\Base\\Bin\\VC7\\Win32\\ReleaseU\\MDTRouteSim.exe");
		Thread.sleep(5000);
		
		AllTests.switchToCAD();
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Block Assignment)");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		AllTests.close_link();
		pp.destroy();
	}

	/*
	 * UnAuthorized Movement Alarm appears when the driver log off from the mobile site
	 * But I don't know the time it is going to appear (after 1 minute or 5 seconds or 10 minutes??)
	 * The setting time is 2 minutes now 
	 */
	@Test
	public void testUnauthorizedMovement() throws InterruptedException, IOException, ParseException {	
		
		tester.Assign_Vehicle(driver);		
//		System.out.println ("Waiting for Data Population in Route Sim! Waiting time = 45 sec........");
		Thread.sleep(1*45*1000);		// waiting for 45 sec to let the routesim populate with the data
		AllTests.switchToDCC();
		AllTests.click("div.app_body button:nth-child(4)");		// Then, log off
		AllTests.switchToCAD();				// Go back to window 1		
		int timer = 0;
		
		// check whether the other messages sections get "Unauthorized Movement Alarm"
		// Wait for 3 minutes, and check every one minute interval 
//		while (!driver.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('Other Messages') + div.eventqitems table.qlistTable tbody tr:last-child td:nth-child(2)")).getText().equalsIgnoreCase("UnAuthorized Movement Alarm")){
		boolean presence = true;
		AllTests.click("div.eventqueue table.qDetails td.qName:contains('Other Messages')");
		while (driver.findElements(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(5):contains('UnAuthorized Movement Alarm')")).size()==0){
			timer++;
			if (timer==7){
				presence = false;
				break;				
			}
//			System.out.println ("Waiting for Alarm in Other Messages! Waiting time = 1 min........");
			Thread.sleep(1*10*1000);						
			driver.findElement(By.cssSelector("div.app_menu_cmd:contains('Refresh')")).click();
		}
		assertTrue("Unauthorized Movement Alarm ",presence);
//		tester.unassign(driver);
	}
}
