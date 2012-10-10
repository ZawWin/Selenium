package com.Trapeze.NOVUS.Selenium;

import static org.junit.Assert.assertFalse;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SendTxtMsgTest {
	private static WebDriver driver=AllTests.driver1;
	private SendTxtMsg tester = new SendTxtMsg();
	
	/*
	 * vicious troubles: Need to remote-log first for DCC --> Send the operator log on response to "Other Messages" section
	 * Then, send text message, DCC receive and reply --> Send operator response to "Other Messages" section
	 * The two messages under the same section are racing and no guarantee that Operator Log On response will respond even though it was invoked first 
	 *  Selecting the element based on the last element --> so, if operator log on msg is later than operator resposne, gets an error
	 */

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Send Text Message)");		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {		
		AllTests.close_link();
	}

	@Test
	public void testSending() throws InterruptedException {
		String[] actual = tester.sending(driver);
		String[] expected = new String[3];
//		Thread.sleep(1000);
		AllTests.switchToDCC();
				
		AllTests.click("button.cmdVehicleBig_1 div[id=\"readMsgButton\"]");
		AllTests.wait_Element("table.grid tr:last-child td:nth-child(2)");
		expected[0] = driver.findElement(By.cssSelector("table.grid tr:last-child td:nth-child(2)")).getText();
		AllTests.click("table.grid tr:last-child button:contains('Open')");
		AllTests.wait_Element("div.app_body p.detailText");
		expected[1]=driver.findElement(By.cssSelector("div.app_body p.detailText")).getText();
		expected[2]=driver.findElement(By.cssSelector("div.app_body div td:first-child")).getText()+"_"+driver.findElement(By.cssSelector("div.app_body div td:last-child")).getText();;
		Assert.assertArrayEquals("Result",expected, actual);
		
		WebElement response = driver.findElement(By.cssSelector("div.app_body div button.cmdVehicleSmallText"));
		String answer = response.getText();
		response.click();
		
		AllTests.wait_Element("table.grid tr:last-child");
		assertFalse ( actual[1].equalsIgnoreCase(driver.findElement(By.cssSelector("table.grid tr:last-child")).getText()));
		
		AllTests.click("button.backbtn");
		AllTests.switchToCAD();		
		
		AllTests.click("td.qName:contains(Other Messages)");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.app_menu_cmd:contains(Refresh)")).click();
		AllTests.click("div.cplDataFrame table tbody tr:last-child td:last-child button.edit");
		
		String[] match = new String[3];
		match[0] = "Operator Response";
		match[1] = answer;
		match[2] = actual[1];
		
		String[] confirm = new String[3];		
		AllTests.wait_Element("table.appCadText tbody tr td:contains('Type:') + td");
		confirm[0] = driver.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Type:') + td")).getText();
		String d = driver.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Description:') + td")).getText();
		confirm[1] = d.substring(d.indexOf("]")+2,d.lastIndexOf("[")-1);
		confirm[2] = d.substring(d.lastIndexOf("]")+2);
		Assert.assertArrayEquals("Result", match,confirm);
	}
}
