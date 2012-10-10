package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Operations_TableTest {
	private static WebDriver driver=AllTests.driver1;
	private Operations_Table tester = new Operations_Table();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Operations Table)");		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AllTests.close_link();
	}

	@Test
	public void testMatchDetail() {
		String[] actual = tester.operations_view(driver);
		String[] expected = tester.matchDetail(driver);
		Assert.assertArrayEquals("Result",expected, actual);
	}
	@Test
	public void testSorting_LineGroup() {
		assertEquals("Result", true, tester.sorting(driver,"lineGroupAbbr",0));
	}

	@Test
	public void testSorting_LineAbbr() {
		assertEquals("Result", true, tester.sorting(driver,"lineAbbr",1));
	}
	@Test
	public void testSorting_Line() {
		assertEquals("Result", true, tester.sorting(driver,"lineName",2));
	}
	@Test
	public void testSorting_Block() {
		assertEquals("Result", true, tester.sorting(driver,"blockName",3));
	}
	@Test
	public void testSorting_Run() {
		assertEquals("Result", true, tester.sorting(driver,"runName", 4));
	}
	@Test
	public void testSorting_SchedPulloutTime() {
		assertEquals("Result", true, tester.sorting_time(driver,"scheduledPullout", 5));
	}
	@Test
	public void testSorting_Vehicle() {
		assertEquals("Result", true, tester.sorting(driver,"vehicleNumber", 7));
	}
	@Test
	public void testSorting_Driver() {
		assertEquals("Result", true, tester.sorting(driver,"driverName", 8));
	}
	@Test
	public void testSorting_VehicleStatus() {
		assertEquals("Result", true, tester.sorting(driver,"systemStatus",10));
	}
	@Test
	public void testSorting_LastTimePt() {
		assertEquals("Result", true, tester.sorting(driver,"lastTimePtCrossing",12));
	}
	@Test
	public void testSorting_ArrivalTime() {
		assertEquals("Result", true, tester.sorting_time(driver, "lastTimePtArrivalTime", 13));
	}
	@Test
	public void testSorting_DepartureTime() {
		assertEquals("Result", true, tester.sorting_time(driver,"lastTimePtDepartureTime", 14));
	}
	@Test
	public void testSorting_LastStop() {
		assertEquals("Result", true, tester.sorting(driver,"lastStopName", 15));
	}
	@Test
	public void testSorting_LastStopArriveTime() {
		assertEquals("Result", true, tester.sorting_time(driver,"lastStopArriveTime", 16));
	}
	@Test
	public void testSorting_LineDirection() {
		assertEquals("Result", true, tester.sorting(driver,"direction", 17));
	}

}
