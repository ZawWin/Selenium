package com.Trapeze.NOVUS.Selenium;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScheduleAdherenceTest {

	private ScheduleAdherence tester = new ScheduleAdherence();
	private static WebDriver d = AllTests.driver1;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		AllTests.close_link();
	}

	//@Test
	public void test_Edit_Schedule_Adherence_Threshold() {
		String[] expected = tester.edit(d);
		String[] actual = new String[4];
		AllTests.wait_Element("div.BMC div + table tbody tr td:first-child");
		actual[0] = d.findElement(By.cssSelector("div.BMC div + table tbody tr td:first-child")).getText();
		actual[1] = d.findElement(By.cssSelector("div.BMC div + table tbody tr td:nth-child(3)")).getText();
		actual[2] = d.findElement(By.cssSelector("div.BMC div + table tbody tr td:nth-child(5)")).getText();
		actual[3] = d.findElement(By.cssSelector("div.BMC div + table tbody tr td:nth-child(6)")).getText();
		
		Assert.assertArrayEquals("Result",expected, actual);
		
		d.findElement(By.cssSelector("div[class=\"app_menu_close\"]")).click();
	}
	
	// What to check on this page???
	// I believe there are tons of issues on this function
	// Testing the usual way of opening Schedule View page
	@Test
	public void test_Schedule_View_page() throws InterruptedException{
		String[] actual = tester.getScheduleView(d);
		String[] expected = new String[2];
		// For line abbreviation column
				List<WebElement> line_abbr = d.findElements(By.cssSelector("tbody.blockInfo_tbody td[colName=\"lineAbbr\"]"));
				for (WebElement each: line_abbr){
					expected[0] = each.getText();
					if (!expected[0].equalsIgnoreCase(expected[0])){
						break;
					}
				}
				
				// For line Name column
				List<WebElement> line_name = d.findElements(By.cssSelector("tbody.blockInfo_tbody td[colName=\"lineName\"]"));
				for (WebElement each: line_name){
					expected[1] = each.getText();
					if (!expected[1].equalsIgnoreCase(expected[1])){
						break;
					}
				}
		Assert.assertArrayEquals("Result",expected, actual);
	}

	//Testing opening Schedule View Page from Operations View
	@Test
	public void test_Schedule_View() throws InterruptedException{
		String[] actual = tester.getSchedule(d);
		String[] expected = new String[2];
		// For line abbreviation column
				List<WebElement> line_abbr = d.findElements(By.cssSelector("tbody.blockInfo_tbody td[colName=\"lineAbbr\"]"));
				for (WebElement each: line_abbr){
					expected[0] = each.getText();
					if (!expected[0].equalsIgnoreCase(expected[0])){
						break;
					}
				}
				
				// For line Name column
				List<WebElement> line_name = d.findElements(By.cssSelector("tbody.blockInfo_tbody td[colName=\"lineName\"]"));
				for (WebElement each: line_name){
					expected[1] = each.getText();
					if (!expected[1].equalsIgnoreCase(expected[1])){
						break;
					}
				}
		Assert.assertArrayEquals("Result",expected, actual);
	}
}
