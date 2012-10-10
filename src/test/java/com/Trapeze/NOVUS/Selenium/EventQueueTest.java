package com.Trapeze.NOVUS.Selenium;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EventQueueTest {

	private static WebDriver driver=AllTests.driver1;
	private EventQueue tester = new EventQueue();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Event Queues)");	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}

	@Test
	public void testall_events() {
		String[] actual = tester.all_events(driver);
		int count = 0;
		AllTests.wait_Element("div.footer");
		List<WebElement> collection = driver.findElements(By.cssSelector("div.footer"));
		String[] expected = new String[collection.size()]; 
		for (WebElement i: collection){
			expected[count]= i.getText().substring(0,1);
			if (expected[count].equalsIgnoreCase("0")){
				expected[count] = null;
			}
			count++;
		}
		Assert.assertArrayEquals("Result", expected, actual);
	}
	
	@Test
	public void testevent_detail (){
		String[] actual = tester.event_detail(driver);
		String[] expected = new String[5];
		AllTests.wait_Element("table.appCadText tbody td:contains('Vehicle:') + td");
		expected[0] = driver.findElement(By.cssSelector("table.appCadText tbody td:contains('Vehicle:') + td")).getText();
		expected[1] = driver.findElement(By.cssSelector("table.appCadText tbody td:contains('Date Tx:') + td")).getText();
		expected[2] = driver.findElement(By.cssSelector("table.appCadText tbody td:contains('Time Tx:') + td")).getText();
		expected[3] = driver.findElement(By.cssSelector("table.appCadText tbody td:contains('Type') + td")).getText();
		expected[4] = driver.findElement(By.cssSelector("table.appCadText tbody td:contains('Description:') + td")).getText();
				
		Assert.assertArrayEquals("Result", expected, actual);
	}
}
