package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmergenecyEventTest {
	private static WebDriver driver=AllTests.driver1;
	EventQueue tester = new EventQueue();
	private static String title = "Emergency Events";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.click("td.qName:contains("+title+")");	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}
	
	@Test
	public void testtitle_check(){
		String actual = tester.title_check(title);
		AllTests.wait_Element("div.app_caption");
		String expected = driver.findElement(By.cssSelector("div.app_caption")).getText();
		assertEquals("Result",expected,actual);
	}
	
	@Test
	public void testpage_check(){
		String list;
		int data_size;
		int count;
		String[] actual = tester.page_check(driver, title);
		AllTests.wait_Element("div.cplDataFrame table tbody");
		list = driver.findElement(By.cssSelector("div.cplDataFrame table tbody")).getText();
		if (list.isEmpty()){
			String[] expected = null;
			Assert.assertArrayEquals("Result",expected,actual);
		}
		else{			
			data_size = driver.findElements(By.cssSelector("div.cplDataFrame table tbody tr")).size();
			String[] expected;
			count = data_size*3;
			expected = new String[count];
			
			if (data_size == 1){
				expected[0] = driver.findElement(By.cssSelector("div.cplDataFrame table tbody tr td:nth-child(2)")).getText();
				expected[1] = driver.findElement(By.cssSelector("div.cplDataFrame table tbody tr td:nth-child(5)")).getText();
				expected[2] = driver.findElement(By.cssSelector("div.cplDataFrame table tbody tr td:nth-child(4)")).getText();
				Assert.assertArrayEquals("Result", expected, actual);
			}
			
			else {
				for (int i= 1; i<=data_size; i++){
					int j = i-1;
					int array_count = i-1;
					expected[array_count] = driver.findElement(By.cssSelector("div.cplDataFrame table tbody tr[rowsetRowIndex=\""+j+"\"] td:nth-child(2)")).getText();
					expected[array_count+1] = driver.findElement(By.cssSelector("div.cplDataFrame table tbody tr[rowsetRowIndex=\""+j+"\"] td:nth-child(5)")).getText();
					expected[array_count+2] = driver.findElement(By.cssSelector("div.cplDataFrame table tbody tr[rowsetRowIndex=\""+j+"\"] td:nth-child(4)")).getText();
					array_count = array_count+3;
				}
				Assert.assertArrayEquals("Result", expected, actual);
			}
		}
	}
}
