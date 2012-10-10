package com.Trapeze.NOVUS.Selenium;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditLinks {
	private int One_Num;
	
	public String AddLink(WebDriver d){		
		String add = null;
		
		AllTests.click("div[class=\"droplist\"][value=\"-1\"]");		
		
		Random random = new Random();
		One_Num = random.nextInt(50);
		// Those following numbers in the statement don't exist. Replace them with no. 30
		if (One_Num == 31 || One_Num == 33 || One_Num == 34 || One_Num == 26 || One_Num == 44){
			One_Num = 30;
		}
		
		WebElement e2 = d.findElement(By.cssSelector("div.listpane div[value=\""+One_Num+"\"]"));
		add = e2.getText();
		 ((JavascriptExecutor)d).executeScript("arguments[0].focus()", e2); 
		e2.click();
		
		AllTests.click("button.cmd:contains('Add') + label + div + input + button");
		
		return add;
	}
	
	public String Remove(WebDriver d){
		String remove = null;
		WebElement e1 = d.findElement(By.cssSelector("div.linkTableFrame tbody.linkTableTbody tr:last-child td.title"));
		remove = e1.getText();
		((JavascriptExecutor) d).executeScript("arguments[0].focus()", e1);
		e1.click();
		
		AllTests.click("button.cmd:contains('Remove')");
		
		return remove;
	}
	
	public void remove(WebDriver d){
		AllTests.click("button.cmd:contains('Remove')");
	}
	
	public String AddGroup(WebDriver d){		
		String add = "Hi";
		
		WebElement e1 = null;
		List<WebElement> inputField = d.findElements(By.cssSelector("input[type=text]"));
		for (WebElement i: inputField){
			if (i.getAttribute("style").equalsIgnoreCase("POSITION: absolute; WIDTH: 124px; HEIGHT: 24px; TOP: 12px; LEFT: 56px")){
				e1 = i;
				}
			}		
		try{
			e1.sendKeys(add);
		}
		catch(StaleElementReferenceException e){}
		
		AllTests.click("button.cmd:contains('Add')");
		
		return add;
	}
	
	public String Edit(WebDriver d){
		String added = "Hello, I am testing!";
		WebElement e1 = d.findElement(By.cssSelector("div.linkTableFrame tbody.linkTableTbody tr:last-child td.title"));
		((JavascriptExecutor) d).executeScript("arguments[0].focus()", e1);
		e1.click();
		
		AllTests.click("button.cmd:contains('Edit')");
		
		WebElement e3 = d.findElement(By.cssSelector("textarea[maxlength=\"254\"]"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", e3);
		e3.sendKeys(added);
		
		AllTests.click("button.cmd:contains('Apply')");
		
		return added;
	}

}
	
	
