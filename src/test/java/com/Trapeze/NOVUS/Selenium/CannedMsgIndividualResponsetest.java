package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CannedMsgIndividualResponsetest {
	private static WebDriver driver=AllTests.driver1;
	private CannedMsgIndividualResponses tester = new CannedMsgIndividualResponses();
		
	private String response_groups_tab = "div.tab_top_row span:nth-child(2)";
	private String add_response_group = "button[title=\"Add Response Group\"][class=\"add\"]";
	private String added_description1 ="div.modalDialog div.checklist div.checkbox + span";
	private String cancel = "td.commands button.cmd:contains('Cancel')";
	private String added_description2 ="table.grid tbody tr td:first-child";
	private String delete = "button[title=\"Delete Response\"][class=\"del\"]";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Edit Canned Messages)");		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}

	@Test
	public void testIndividual_Response() {
		String actual = tester.add(driver);
		
		String expect1, expect2;			
		AllTests.click(response_groups_tab);
		
		AllTests.click(add_response_group);		
		
		AllTests.wait_Element(added_description1);
		expect1 = driver.findElement(By.cssSelector(added_description1)).getText();
		
		AllTests.click(cancel);		
		
		AllTests.click(CannedMsgIndividualResponses.individual_response_tag);
		
		AllTests.wait_Element(added_description2);
		expect2 = driver.findElement(By.cssSelector(added_description2)).getText();
		
		assertEquals("Result", expect1, actual);
		assertEquals("Result", expect2, actual);
		
		String window = driver.getWindowHandle();
		AllTests.click(delete);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().window(window);
	}	
}
