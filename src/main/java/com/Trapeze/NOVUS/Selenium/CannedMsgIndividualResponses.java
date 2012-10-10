package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CannedMsgIndividualResponses {
	
	
	public static String individual_response_tag = "div.tab_top_row span:nth-child(3)";
	private String add_response = "button[title=\"Add Response\"][\"add\"]";
	private String new_description = "div.modalDialog td:contains('Description:') + td input";
	private String OK = "td.commands button.cmd:contains('OK')";
	
	public String add(WebDriver d){
		WebElement description;
		String des1 = "A New Individual Repsonse!";
		
		AllTests.click(individual_response_tag);
		AllTests.click(add_response);
		
		AllTests.wait_Element(new_description);
		description = d.findElement(By.cssSelector(new_description));		
		((JavascriptExecutor) d).executeScript("arguments[0].select()", description);
		description.sendKeys(Keys.DELETE);
		description.sendKeys(des1);
		
		AllTests.click(OK);
		
		return des1;
	}
}
