package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SendCanMsg {

	public String[] sending(WebDriver d){
		String[] actual = new String[3];
		// Type of message 
		actual[0] = "Canned Msg";
		
		//Selecting a Vehicle
		AllTests.click("tr.destVw_row tr:contains('VEHICLES') + tr.destVw_row td.leaf div.checkbox");
		
		//Choosing the Message type to send		
		WebElement text = d.findElement(By.cssSelector("div.listbox div[value=\"3\"]"));
		actual[1] = text.getText();
		text.click();		
		
		//Requiring operator response
		AllTests.click("td:contains('Operator Response:') + td select");
		AllTests.wait_Element("td:contains('Operator Response:') + td select option[value=\"1\"]");
		WebElement response = d.findElement(By.cssSelector("td:contains('Operator Response:') + td select option[value=\"1\"]"));
		actual[2] = response.getText();
		response.click();
		
		//Finally Sending && waiting until done
		AllTests.click("button.cmd:contains('Send')");
		AllTests.wait_Element("div.app_caption:contains('My Tasks')");
		
		return actual;
	}
}
