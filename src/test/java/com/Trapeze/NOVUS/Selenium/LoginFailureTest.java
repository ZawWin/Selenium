package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFailureTest {
	
	/*
	 * In Other Messages Section, Operator Logon Failure shows up abnormally
	 * Sometimes, it shows up but sometimes, it doesn't. Abnormal
	 * The test scripts say deferred in Excel regression tests
	 */

	private static WebDriver driver = AllTests.driver1;
	private LoginFailure tester = new LoginFailure();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		AllTests.switchToDCC();	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.switchToCAD();	
	}

	@Test
	public void testBadge() {
		//system.out.println("Begin testBadge");
		tester.Badge(driver, "99999");
		AllTests.wait_Element("label:contains(Reason) + label");
		String actual = driver.findElement(By.cssSelector("label:contains(Reason) + label")).getText();
		String expected = "Driver ID is not valid."; 
		assertEquals("Result,", expected, actual);
		AllTests.click("label.blackLabel:contains('Badge:') + button.gokeypad");
		driver.findElement(By.cssSelector("button:contains('Clear')")).click();
		driver.findElement(By.cssSelector("button:contains('Enter')")).click();
		//system.out.println("End testBadge");
	}
	
	@Test
	public void testBlock() {
		////system.out.println("Begin testBlock");
		tester.Block(driver, "123214324");
		AllTests.wait_Element("label:contains(Reason) + label");
		String actual = driver.findElement(By.cssSelector("label:contains(Reason) + label")).getText();
		String expected = "Block ID is not valid."; 
		assertEquals("Result,", expected, actual);
		AllTests.click("label.blackLabel:contains('Badge:') + button.gokeypad + button.gokeypad");
		driver.findElement(By.cssSelector("button:contains('Clear')")).click();
		driver.findElement(By.cssSelector("button:contains('Enter')")).click();
		AllTests.click("label.blackLabel:contains('Badge:') + button.gokeypad");
		driver.findElement(By.cssSelector("button:contains('Clear')")).click();
		driver.findElement(By.cssSelector("button:contains('Enter')")).click();
		//system.out.println("End testBlock");
	}
	
	@Test
	public void testBadgeAndBlock(){
		//system.out.println("Begin testBadgeAndBlock");
		tester.BadgeAndBlock(driver, "99999", "123214");
		AllTests.wait_Element("label:contains(Reason) + label");
		String actual = driver.findElement(By.cssSelector("label:contains(Reason) + label")).getText();
		String expected = "Driver ID is not valid.";
		assertEquals("Result,", expected, actual);
		AllTests.click("label.blackLabel:contains('Badge:') + button.gokeypad + button.gokeypad");
		driver.findElement(By.cssSelector("button:contains('Clear')")).click();
		driver.findElement(By.cssSelector("button:contains('Enter')")).click();
		AllTests.click("label.blackLabel:contains('Badge:') + button.gokeypad");
		driver.findElement(By.cssSelector("button:contains('Clear')")).click();
		driver.findElement(By.cssSelector("button:contains('Enter')")).click();
		//system.out.println("End testBadgeAndBlock");
	}
}
