package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFailure {
	private String Badge ="label.blackLabel:contains('Badge:') + button.gokeypad" ;
	private String input_field = "div.app_body input";
	private String Enter = "button:contains('Enter')";
	private String Logon = "button.blue_BlackBkgrnd:contains('Logon')";
	private String block1 = "label.blackLabel:contains('Badge:') + button.gokeypad + button.gokeypad";
	
	public void Badge(WebDriver d, String input){
		// Just the wrong badge
		AllTests.click(Badge);
		AllTests.wait_Element(input_field);
		WebElement id = d.findElement(By.cssSelector(input_field));
		((JavascriptExecutor)d).executeScript("arguments[0].select()", id);
		id.sendKeys(input);
		d.findElement(By.cssSelector(Enter)).click();
		AllTests.click(Logon);
	}
	
	public void Block(WebDriver d, String input){
		// Adding the right badge
		AllTests.click(Badge);
		AllTests.wait_Element(input_field);
		WebElement id = d.findElement(By.cssSelector(input_field));
		((JavascriptExecutor)d).executeScript("arguments[0].select()", id);
		id.sendKeys("0");
		d.findElement(By.cssSelector(Enter)).click();
		
		// But the wrong block
		AllTests.click(block1);
		AllTests.wait_Element(input_field);
		WebElement id1 = d.findElement(By.cssSelector(input_field));
		((JavascriptExecutor)d).executeScript("arguments[0].select()", id1);
		id1.sendKeys(input);
		d.findElement(By.cssSelector(Enter)).click();
		AllTests.click(Logon);
	}
	
	public void BadgeAndBlock(WebDriver d, String badge, String block){
		// Adding the wrong badge number
		AllTests.click(Badge);
		AllTests.wait_Element(input_field);
		WebElement id = d.findElement(By.cssSelector(input_field));
		((JavascriptExecutor)d).executeScript("arguments[0].select()", id);
		id.sendKeys(badge);
		d.findElement(By.cssSelector(Enter)).click();
		
		//Adding the wrong block number
		AllTests.click(block1);
		AllTests.wait_Element(input_field);
		WebElement id1 = d.findElement(By.cssSelector(input_field));
		((JavascriptExecutor)d).executeScript("arguments[0].select()", id1);
		id1.sendKeys(block);
		d.findElement(By.cssSelector(Enter)).click();
		
		AllTests.click(Logon);
	}
}
