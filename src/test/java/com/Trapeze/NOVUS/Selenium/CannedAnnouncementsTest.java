package com.Trapeze.NOVUS.Selenium;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CannedAnnouncementsTest {

	private static WebDriver driver=AllTests.driver1;
	private CannedAnnouncements tester = new CannedAnnouncements();
	
	private static String edit_canned_msg_link = "div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Edit Canned Messages)";
	private String added_description = "table.grid tbody tr:last-child td:first-child";
	private String added_sign_text = "table.grid tbody tr:last-child td:nth-child(2)";
	private String added_sound_file = "table.grid tbody tr:last-child td:nth-child(3)";
	private String added_ext_msg = "table.grid tbody tr:last-child td:nth-child(4)";
	private String delete_added ="table.grid tbody tr:last-child button.del";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		AllTests.open_link(edit_canned_msg_link);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}
	@Test
	public void testAnnouncement() {
		String[] actual = tester.add(driver);
		String[] expect = new String[4];
		AllTests.wait_Element(added_description);
		expect[0] = driver.findElement(By.cssSelector(added_description)).getText();
		expect[1] = driver.findElement(By.cssSelector(added_sign_text)).getText();
		expect[2] = driver.findElement(By.cssSelector(added_sound_file)).getText();
		expect[3] = driver.findElement(By.cssSelector(added_ext_msg)).getText();
		
		Assert.assertArrayEquals("Result", expect,actual);	
		
		String current_window = driver.getWindowHandle();
		AllTests.click(delete_added);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().window(current_window);
	}
}
