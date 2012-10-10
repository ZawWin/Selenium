package com.Trapeze.NOVUS.Selenium;


import java.text.ParseException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ViewCommuRosterListerTest {
	private static WebDriver driver=AllTests.driver1;
	private ViewCommuRosterLister tester = new ViewCommuRosterLister();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(View Communication Roster List)");		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}

	@Test
	public void testMatchInfo() throws ParseException, InterruptedException {
		String[] actual = tester.checkDetailInfo(driver);
		String[] expected = tester.getDetailInfo(driver);
		Assert.assertArrayEquals("Result", expected, actual);
	}
}
