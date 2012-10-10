package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SendTxtMsg {
	
	public String[] sending(WebDriver d){
		String[] actual = new String[3];
		// Type of message 
		actual[0] = "Free Text Msg";
		
		//Selecting a Vehicle
		AllTests.click("tr.destVw_row tr:contains('VEHICLES') + tr.destVw_row td.leaf div.checkbox");
		
		//Inputting the text message to send
		actual[1] = "This is a free text message";
		AllTests.wait_Element("div.BMC td[vAlign=\"top\"]:last-child textarea");
		WebElement text = d.findElement(By.cssSelector("div.BMC td[vAlign=\"top\"]:last-child textarea"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", text);
		text.sendKeys(actual[1]);
		
		//Requiring operator response
		AllTests.click("td:contains('Operator Response:') + td select");
		AllTests.wait_Element("td:contains('Operator Response:') + td select option[value=\"2\"]");
		WebElement response = d.findElement(By.cssSelector("td:contains('Operator Response:') + td select option[value=\"2\"]"));
		actual[2] = response.getText();
		response.click();
		
		//Finally Sending && waiting until done
		d.findElement(By.cssSelector("button.cmd:contains('Send')")).click();
		AllTests.wait_Element("div.app_caption:contains('My Tasks')");		
		return actual;
	}

}
