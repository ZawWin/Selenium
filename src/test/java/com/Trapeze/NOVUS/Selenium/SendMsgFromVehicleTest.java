package com.Trapeze.NOVUS.Selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SendMsgFromVehicleTest {
	private static WebDriver driver = AllTests.driver1;
	private SendMsgFromVehicle tester = new SendMsgFromVehicle();
	
	@Before
	public void setUp() throws Exception {
		AllTests.switchToDCC();
	}

	@After
	public void tearDown() throws Exception {
		AllTests.close_link();
	}
	
	public String[] getter(String s) throws InterruptedException{
		AllTests.switchToCAD();
		
		AllTests.click("td.qName:contains("+s+")");
		AllTests.click("div.app_menu_cmd:contains(Refresh)");
		AllTests.click("div.cplDataFrame table tbody tr:last-child td:last-child button.edit");
		
		String[] actual = new String[2];
		AllTests.wait_Element("table.appCadText tbody tr td:contains('Type:') + td");
		actual[0] = driver.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Type:') + td")).getText();
		actual[1] = driver.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Description:') + td")).getText();	
		
		return actual;
	}

	@Test
	public void testSendEmergencyMsg() throws InterruptedException {
		tester.sendMsgBtn(driver);
		String[] expected =  tester.sendEmergencyMsg(driver);
		String[] actual = getter("Emergency Events");		
		Assert.assertArrayEquals("Result",expected, actual);
	}
	
	@Test
	public void testSendOperatorMsg() throws InterruptedException{
		tester.sendMsgBtn(driver);
		String[] expected = tester.sendOperatorMsg(driver);
		String[] actual = getter("Other Messages");
		Assert.assertArrayEquals("Result",expected, actual);
	}
	
	@Test
	public void testSendServiceMsg() throws InterruptedException{
		tester.sendMsgBtn(driver);
		String[] expected = tester.sendServiceMsg(driver);
		String[] actual = getter("Other Messages");
		Assert.assertArrayEquals("Result",expected, actual);
	}
	
	@Test
	public void testSendFreeText() throws InterruptedException{
		tester.sendMsgBtn(driver);
		String[] expected = tester.sendFreeText(driver);
		String[] actual = getter("Other Messages");
		Assert.assertArrayEquals("Result",expected, actual);
	}
}
