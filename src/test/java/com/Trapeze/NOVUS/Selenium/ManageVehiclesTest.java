package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageVehiclesTest {

	/*
	 * This test is not complete yet, meaning
	 * there are still some mismatch between adding and checking.
	 * and that's why i haven't matched all of the items yet
	 * 
	 */
	private static WebDriver driver = AllTests.driver1;
	private ManageVehicles tester =  new ManageVehicles();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Manage Vehicles)");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		String window = driver.getWindowHandle();
		AllTests.click("tbody.customPaginatedBody tr button.del");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().window(window);
		AllTests.close_link();
	}

	@Test
	public void testAddVehicle() {
		String[] actual = tester.add(driver);
		int i = 0; int j = 0;
		driver.findElement(By.cssSelector("button.cmd:contains('Select All')")).click();
		AllTests.wait_Element("tbody.customPaginatedBody tr td:nth-child(3)");
		i = Integer.parseInt(driver.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(3)")).getText());
		j = Integer.parseInt(driver.findElement(By.cssSelector("tbody.customPaginatedBody tr + tr td:nth-child(3)")).getText());
		
		while (j>i){
			driver.findElement(By.cssSelector("div.cplDataFrame table thead tr th + th + th")).click();
			AllTests.wait_Element("tbody.customPaginatedBody tr td:nth-child(2)");
			i = Integer.parseInt(driver.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(2)")).getText());
			j = Integer.parseInt(driver.findElement(By.cssSelector("tbody.customPaginatedBody tr + tr td:nth-child(2)")).getText());
		}
		
		String[] expected = new String[7];
		expected[0] = driver.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(4)")).getText();		
		expected[1] =driver.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(5)")).getText();	
		expected[2] =driver.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(6)")).getText();
		driver.findElement(By.className("edit")).click();
		AllTests.wait_Element("div.BMC label:contains('Vehicle #:') + input");			
		expected[3] =driver.findElement(By.cssSelector("div.BMC label:contains('Garage:') + div.droplist span")).getText();
		expected[4] =driver.findElement(By.cssSelector("div.BMC label:contains('Vehicle Type:') + div.droplist span")).getText();		
		expected[5] =driver.findElement(By.cssSelector("div.BMC label:contains('Capacity Type:') + div.droplist span")).getText();
		expected[6] = driver.findElement(By.cssSelector("div.BMC label:contains('Description:') + textarea")).getText();
		AllTests.click("div[class=\"app_menu_close\"]");
		Assert.assertArrayEquals("Result",actual, expected);		
	}
	
	@Test
	public void testEditVehicle() {
		String actual = tester.change(driver);
		AllTests.click("button.cmd:contains('Select All')");
		String expected = driver.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(6)")).getText();
		assertEquals("Result", actual,expected);
	}

}
