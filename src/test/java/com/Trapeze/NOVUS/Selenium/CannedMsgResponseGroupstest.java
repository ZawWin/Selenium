package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CannedMsgResponseGroupstest {
	private static WebDriver driver=AllTests.driver1;
	private CannedMsgResponseGroups tester = new CannedMsgResponseGroups();
	private String canned_msgs_tab = "div.tab_top_row span:nth-child(1)";
	private String add_button = "button[title=\"Add Message\"][class=\"add\"]";
	private String dropdown_list= "div.modalDialog div.droplist button.dropbutton";
	private String added_descript1 = "div.listpane div[value=\"3\"]";	
	private String cancel = "td.commands button.cmd:contains('Cancel')";
	private String response_group_tab = "div.tab_top_row span:nth-child(2)";
	private String added_descript2 ="table.grid tbody tr:first-child td";
	private String added_response1 = "table.grid tbody tr:nth-child(2) td.sel";
	private String added_response2 = "table.grid tbody tr:nth-child(4) td.sel";
	private String added_response3 = "table.grid tbody tr:nth-child(3) td.sel";
	private String del = "table.grid tbody tr:first-child td:last-child button.del";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Edit Canned Messages)");		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}

	@Test
	public void testAdd_Group() {
		String[] actual = tester.add(driver);
		
		String[] expected = new String[4];
		AllTests.click(canned_msgs_tab);
		AllTests.click(add_button);
		AllTests.click(dropdown_list);
				
		AllTests.wait_Element(added_descript1);
		String extra = driver.findElement(By.cssSelector(added_descript1)).getText();
		
		AllTests.click("div.listpane div[value=\"3\"]");
		AllTests.click(cancel);
		
		AllTests.click(response_group_tab);
		AllTests.wait_Element(added_descript2);
		expected[0]=driver.findElement(By.cssSelector(added_descript2)).getText();
		AllTests.click(added_descript2);
		
		AllTests.wait_Element(added_response1);
		expected[1]=driver.findElement(By.cssSelector(added_response1)).getText();
		expected[2]=driver.findElement(By.cssSelector(added_response2)).getText();
		expected[3]=driver.findElement(By.cssSelector(added_response3)).getText();
		
		Assert.assertArrayEquals("Result",expected, actual);
		assertEquals("Result", extra, actual[0]);
		
		String window = driver.getWindowHandle();
		AllTests.click(del);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().window(window);
	}
}
