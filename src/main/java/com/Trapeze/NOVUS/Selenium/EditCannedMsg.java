package com.Trapeze.NOVUS.Selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditCannedMsg {
		
	public String[] InOutAdd(WebDriver d, String command){
		String[] new_msg = new String[3];
		WebElement out = null;
		new_msg[0] = "New "+command;
		new_msg[1] = "This is new "+ command+" message!";
		new_msg[2] = "This is edited";
				
		//Adding Category (left side)
		AllTests.click("div.droplist button.dropbutton");
		AllTests.click("div.listpane div:contains('"+command+"')");
		AllTests.click("td.btn button.add");
		AllTests.wait_Element("div.modalDialog td:contains('Description:') + td input");
		out = d.findElement(By.cssSelector("div.modalDialog td:contains('Description:') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", out);
		out.sendKeys(Keys.DELETE);
		out.sendKeys(new_msg[0]);
		
		d.findElement(By.className("checkbox")).click();
		
		AllTests.click("button.cmd:contains('OK')");
		
		//Adding Outbound message (right side)
		AllTests.click("button[title=\"Add Message\"][class=\"add\"]");
		
		AllTests.wait_Element("div.modalDialog td:contains('Description:') + td input");
		out = d.findElement(By.cssSelector("div.modalDialog td:contains('Description:') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", out);
		out.sendKeys(Keys.DELETE);
		out.sendKeys(new_msg[1]);
		
		AllTests.click("td.commands button.cmd:contains('OK')");
		
		//Testing Editing message (right side)
		//d.findElement(By.cssSelector("td:contains('New "+command+"'")).click();
		AllTests.click("button[title=\"Edit Message\"][\"edit\"]");
		d.findElement(By.cssSelector("div.modalDialog td:contains('Description:') + td input")).sendKeys(new_msg[2]);
		AllTests.click("td.commands button.cmd:contains('OK')");
		
		return new_msg;
	}	
}


