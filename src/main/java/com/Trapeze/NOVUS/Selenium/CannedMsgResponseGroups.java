package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CannedMsgResponseGroups {
		
	private String response_groups_tab = "div.tab_top_row span:nth-child(2)";
	private String add_button = "button[title=\"Add Response Group\"][class=\"add\"]";
	private String new_description = "div.modalDialog td:contains('Description:') + td input";
	private String driver_ack_checkbox = "div.modalDialog div.checklist div:first-child div.checkbox";
	private String driver_ans = "div.modalDialog div.checklist div:first-child div.checkbox + span";
	private String yes_checkbox ="div.modalDialog div.checklist div:last-child div.checkbox" ;
	private String yes_ans = "div.modalDialog div.checklist div:last-child div.checkbox + span";
	private String no_checkbox = "div.modalDialog div.checklist div:nth-child(2) div.checkbox";
	private String no_ans = "div.modalDialog div.checklist div:nth-child(2) div.checkbox + span";
	private String OK = "td.commands button.cmd:contains('OK')";
	public String[] add(WebDriver d){	
		
		String[] answer = new String[4];
		WebElement message = null , driver_ack = null,
				yes = null, no = null; 
		answer[0] = "New Group Response";
		
		AllTests.click(response_groups_tab);
		AllTests.click(add_button);
		
		AllTests.wait_Element(new_description);
		message = d.findElement(By.cssSelector(new_description));		
		((JavascriptExecutor) d).executeScript("arguments[0].select()", message);
		message.sendKeys(Keys.DELETE);
		message.sendKeys(answer[0]);
		
		AllTests.click(this.driver_ack_checkbox);		
		AllTests.wait_Element(driver_ans);
		driver_ack = d.findElement(By.cssSelector(driver_ans));
		answer[1] = driver_ack.getText();
		
		AllTests.click(yes_checkbox);
		AllTests.wait_Element(yes_ans);
		yes = d.findElement(By.cssSelector(yes_ans));
		answer[2] = yes.getText();
		
		AllTests.click(no_checkbox);
		AllTests.wait_Element(no_ans);
		no = d.findElement(By.cssSelector(no_ans));
		answer[3] = no.getText();
		
		AllTests.click(OK);
		
		return answer;
	}
}