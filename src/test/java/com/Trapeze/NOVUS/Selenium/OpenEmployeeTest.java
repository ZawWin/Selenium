package com.Trapeze.NOVUS.Selenium;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenEmployeeTest {

	private static WebDriver driver = AllTests.driver1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Open Employee)");		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}

	@Test
	public void testEmployeeDetail() throws InterruptedException {
		OpenEmployee tester = new OpenEmployee();
		String[] actual = tester.select(driver);
		String[] expect = tester.match(driver);
		Assert.assertArrayEquals("Result",expect, actual);
		String window = driver.getWindowHandle();
		AllTests.wait_Element("div.app_menu_delete");
		driver.findElement(By.className("app_menu_delete")).click();		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().window(window);
	}
}
