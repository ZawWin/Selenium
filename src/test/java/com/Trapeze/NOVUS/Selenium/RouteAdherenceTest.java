package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RouteAdherenceTest {

	private static WebDriver d=AllTests.driver1;
	private RouteAdherence tester = new RouteAdherence();
	
	@Before
	public void setUp() throws Exception {
		d.findElement(By.cssSelector("td.s_command:contains('My Tasks')")).click();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// Reverting back to the Default Value
		d.findElement(By.cssSelector("td.s_tab_off:contains('My Tasks')")).click();
		d.findElement(By.cssSelector("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Switches)")).click();
		((JavascriptExecutor)d).executeScript("arguments[0].click()", d.findElement(By.cssSelector("div.switch_page_normal:contains(Page 40)")));
		Thread.sleep(500);
		d.findElement(By.cssSelector("td.switch_list table tbody td.switch_link:contains('Obs/Services/ObsRouteInfo/Track/OffRouteDistance')")).click();
		d.findElement(By.cssSelector("button.cmd:contains('Default')")).click();
		d.findElement(By.cssSelector("button.cmd:contains('Update')")).click();
		
		while (!d.findElement(By.cssSelector("div.app_caption")).getText().equalsIgnoreCase("My Tasks")){
			d.findElement(By.cssSelector("div[class=\"app_menu_close\"]")).click();
		}
		Thread.sleep(3000);
	}

	@Test
	public void testEdit_routeAdhere() throws InterruptedException {
		String expected = tester.edit_routeAdhere(d);
		AllTests.wait_Element("td.switch_list table tbody td.switch_link:contains('Obs/Services/ObsRouteInfo/Track/OffRouteDistance') + td");
		String actual = d.findElement(By.cssSelector("td.switch_list table tbody td.switch_link:contains('Obs/Services/ObsRouteInfo/Track/OffRouteDistance') + td")).getText();
		assertEquals("Result,",expected,actual);		
	}
	
	public String[] actualResult(){
		String[] ss = new String[7];
		AllTests.click("table.gridsort thead tr th[selectIndex=\"6\"]");
		AllTests.click("td.s_tab_on:contains('Route Summary')");		//Refresh the page to update the element on the page
		AllTests.wait_Element("tbody.blockInfo_tbody tr td[colName=\"lineGroupAbbr\"]");
		ss[0]=d.findElement(By.cssSelector("tbody.blockInfo_tbody tr td[colName=\"lineGroupAbbr\"]")).getText();
		ss[1]=d.findElement(By.cssSelector("tbody.blockInfo_tbody tr td[colName=\"lineAbbr\"]")).getText();
		ss[2] = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr td[colName=\"lineName\"]")).getText();
		ss[3] = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr td[colName=\"blockName\"]")).getText();
		ss[4]= d.findElement(By.cssSelector("tbody.blockInfo_tbody tr td[colName=\"runName\"]")).getText();
		ss[5]= d.findElement(By.cssSelector("tbody.blockInfo_tbody tr td[colName=\"vehicleNumber\"]")).getText();
		ss[6]= d.findElement(By.cssSelector("tbody.blockInfo_tbody tr td[colName=\"driverName\"]")).getText();
		return ss;
	}
	
	// Testing the one, got from Operations View
	@Test
	public void test_routeAdhere_page() throws InterruptedException{
		String[] expected = tester.routeAdhere_Page(d);
		String[] actual = actualResult();		
		Assert.assertArrayEquals("Result",expected, actual);
	}
	
	// Testing normal one
	@Test
	public void test_routeAdhere(){
		String[] expected = tester.get_routeAdherePage(d);
		String[] actual = actualResult();		
		Assert.assertArrayEquals("Result",expected, actual);
	}
}
