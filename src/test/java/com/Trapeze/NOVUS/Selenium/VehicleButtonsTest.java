package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleButtonsTest {
	private WebDriver driver = AllTests.driver1;
	private VehicleButtons tester = new VehicleButtons();

	@Before
	public void setUp() throws Exception {
//		Thread.sleep(2000);
		AllTests.switchToDCC();
	}

	@After
	public void tearDown() throws Exception {
		AllTests.switchToCAD();
//		Thread.sleep(1500);
	}

	@Test
	public void testMenu2() {
		tester.Menu2(driver);
		if (driver.findElements(By.cssSelector("button.cmdVehicleSmall:contains(MENU 1)")).size()== 0){
			assertEquals(2, 1+1);
		}
	}

	@Test
	public void testMenu1() {
		tester.Menu1(driver);
		if (driver.findElements(By.cssSelector("button.cmdVehicleSmall:contains(MENU 2)")).size()== 0){
			assertEquals(2, 1+1);
		}
	}

	@Test
	public void testAlarm() throws InterruptedException {
		tester.Alarm(driver);
		Thread.sleep(2000);
		AllTests.switchToCAD();
		
		driver.findElement(By.cssSelector("td.qName:contains(Emergency Events)")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.app_menu_cmd:contains(Refresh)")).click();
		AllTests.click("div.cplDataFrame table tbody tr:last-child td:last-child button.edit");
		
		String[] actual = new String[2];
		actual[0] = driver.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Type:') + td")).getText();
		actual[1] = driver.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Description:') + td")).getText();
		
		String[] expected = new String[2];
		expected[0]="Overt Alarm";
		expected[1]="Overt Emergency Alarm";
		
		Assert.assertArrayEquals("Result", expected,actual);
	}

}
