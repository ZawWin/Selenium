
package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditLinksTest {
	
	private static WebDriver driver=AllTests.driver1;
	private EditLinks tester = new EditLinks();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{	
		AllTests.open_link("div.app_menu_cmd");		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}

	@After
	public void tearDown() throws Exception {
		tester.remove(driver);
	}
	
	@Test
	public void testAddLink() throws Exception {
		String add = tester.AddLink(driver);
		AllTests.wait_Element("div.linkTableFrame tbody.linkTableTbody tr:last-child td.title");
		String test = driver.findElement(By.cssSelector("div.linkTableFrame tbody.linkTableTbody tr:last-child td.title")).getText();
		assertEquals("Result",test,add);
	}

	@Test
	public void testAddGroup() {
		String add = tester.AddGroup(driver);
		AllTests.wait_Element("div.linkTableFrame tbody.linkTableTbody tr:last-child td.title");
		String test = driver.findElement(By.cssSelector("div.linkTableFrame tbody.linkTableTbody tr:last-child td.title")).getText();
		assertEquals("Result",test,add);
	}

	@Test
	public void testEdit() {
		String edit = tester.Edit(driver);
		AllTests.wait_Element("div.linkTableFrame tbody.linkTableTbody tr:last-child td.linkDescription");
		String test = driver.findElement(By.cssSelector("div.linkTableFrame tbody.linkTableTbody tr:last-child td.linkDescription")).getText();
		assertEquals ("Result",test,edit);
	}
	
	@Test
	public void testRemove(){
		String remove = tester.Remove(driver);
		AllTests.wait_Element("div.linkTableFrame tbody.linkTableTbody tr:last-child td.linkDescription");
		String test = driver.findElement(By.cssSelector("div.linkTableFrame tbody.linkTableTbody tr:last-child td.title")).getText();
		assertNotSame("Result",test,remove);
	}

}
