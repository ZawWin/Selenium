package com.Trapeze.NOVUS.Selenium;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditCannedMsgTest {
	private static WebDriver driver=AllTests.driver1;
	private EditCannedMsg tester= new EditCannedMsg();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Edit Canned Messages)");
	}
	
	@Test
	public void testInbound() throws Exception {
		String[] actuals = tester.InOutAdd(driver, "Inbound");
		String[] expected = new String[3];
				
		expected[0]=driver.findElement(By.cssSelector("td.tab_body div table tbody tr td:first-child table.grid tbody tr:last-child td:nth-child(2)")).getText();		
		AllTests.click("td.tab_body div table tbody tr td:first-child table.grid tbody tr:last-child td:nth-child(2)");
		AllTests.wait_Element("td.tab_body div table tbody tr td:last-child table.grid tbody tr td:nth-child(2)");
		String temp = driver.findElement(By.cssSelector("td.tab_body div table tbody tr td:last-child table.grid tbody tr td:nth-child(2)")).getText();
		expected[1]=temp.substring(0,temp.indexOf("!")+1);
		expected[2]=temp.substring(temp.indexOf("!")+1);
		Assert.assertArrayEquals("Result", expected, actuals);
		
		String window = driver.getWindowHandle();
		AllTests.click("td.sel + td.btn button.del");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().window(window);
		}
	
	@Test
	public void testOutbound() throws Exception {
		String[] actuals = tester.InOutAdd(driver, "Outbound");
		String[] expected = new String[3];
		
		expected[0]=driver.findElement(By.cssSelector("td.tab_body div table tbody tr td:first-child table.grid tbody tr:last-child td:nth-child(2)")).getText();
		AllTests.click("td.tab_body div table tbody tr td:first-child table.grid tbody tr:last-child td:nth-child(2)");
		AllTests.wait_Element("td.tab_body div table tbody tr td:last-child table.grid tbody tr td:nth-child(2)");
		String temp = driver.findElement(By.cssSelector("td.tab_body div table tbody tr td:last-child table.grid tbody tr td:nth-child(2)")).getText();
		expected[1]=temp.substring(0,temp.indexOf("!")+1);
		expected[2]=temp.substring(temp.indexOf("!")+1);
		Assert.assertArrayEquals("Result", expected, actuals);
		
		String window = driver.getWindowHandle();
		AllTests.click("td.sel + td.btn button.del");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().window(window);
		}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();

	}
}
