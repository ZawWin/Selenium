package com.Trapeze.NOVUS.Selenium;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpportunityAnnouncementTest {
	private static WebDriver driver=AllTests.driver1;
	private OpportunityAnnouncement tester = new OpportunityAnnouncement();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Edit Canned Messages)");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}

	@Test
	public void testOpportunity_Announce() {
		//	private String descript, sign_txt, sound, interval, radius, lat, lon;
		String[] actual = tester.add(driver);
		String[] expect = new String[8];
		AllTests.wait_Element("table.grid tbody tr td:first-child");
		expect[0] = driver.findElement(By.cssSelector("table.grid tbody tr td:first-child")).getText();
		expect[1] = driver.findElement(By.cssSelector("table.grid tbody tr td:nth-child(2)")).getText();
		expect[2] = driver.findElement(By.cssSelector("table.grid tbody tr td:nth-child(3)")).getText();
		expect[7] = driver.findElement(By.cssSelector("table.grid tbody tr td:nth-child(4)")).getText();
		expect[5] = driver.findElement(By.cssSelector("table.grid tbody tr td:nth-child(5)")).getText();
		expect[6] = driver.findElement(By.cssSelector("table.grid tbody tr td:nth-child(6)")).getText();
		expect[4] = driver.findElement(By.cssSelector("table.grid tbody tr td:nth-child(7)")).getText();
		expect[3] = driver.findElement(By.cssSelector("table.grid tbody tr td:nth-child(8)")).getText();
		
		Assert.assertArrayEquals("Result",expect, actual);
		
		String window = driver.getWindowHandle();
		AllTests.click("table.grid tbody tr:last-child td.btn button[title=\"Delete Announcement\"][class=\"del\"]");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().window(window);
	}
}
