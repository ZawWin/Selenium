package com.Trapeze.NOVUS.Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class UnauthorizedMovement {
	
	
	private String Vehicle_Assigned;
	
	public void Assign_Vehicle(WebDriver d) throws InterruptedException{
		WebElement vehicle_status = d.findElement(By.cssSelector("tbody.selectionListBody tr div[title = \"Vehicle is live!\"]"));
		((JavascriptExecutor)d).executeScript("arguments[0].focus()", vehicle_status);
		vehicle_status.click();
		vehicle_status.click();
		
		AllTests.wait_Element("tbody.selectionListBody tr.selected");
		WebElement vehicle_id = d.findElement(By.cssSelector("tbody.selectionListBody tr.selected"));
		Vehicle_Assigned = vehicle_id.getText();
		
		
		if (d.findElements(By.cssSelector("tbody.blockInfo_tbody tr td:nth-child(5):contains('"+Vehicle_Assigned+"')")).size()!= 0){
			d.findElement(By.cssSelector("table.gridsort thead th:contains('Vehicle Assigned')")).click();
			AllTests.click("div.app_menu_cmd:contains('Refresh')");
			AllTests.wait_Element("tbody.blockInfo_tbody tr:first-child td:nth-child(5)");
			if (d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(5)")).getText().isEmpty()){
				d.findElement(By.cssSelector("table.gridsort thead th:contains('Vehicle Assigned')")).click();
				AllTests.click("div.app_menu_cmd:contains('Refresh')");
			}			
			WebElement v = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child"));			
			((JavascriptExecutor)d).executeScript("arguments[0].focus()", v);
			v.click();
			d.findElement(By.cssSelector("button.cmd:contains('Unassign')")).click();
			Thread.sleep(5000);
			Assign_Vehicle(d);
		}
	
		else{
			try{
				AllTests.click("tbody.blockInfo_tbody tr");
				AllTests.click("button.cmd:contains('Assign')");
				
				AllTests.click("td.commands button.cmd:contains('OK')");
				
			//	WebElement check = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr.selected td:nth-child(5)"));
		//		String v_assigned = check.getText();
				d.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
						
			}catch (WebDriverException e){
				Assign_Vehicle(d);
				}
			}
		}
	
//	public void unassign(WebDriver d) throws InterruptedException{
//		WebElement vehicle_status = d.findElement(By.cssSelector("tbody.selectionListBody tr div[title = \"Vehicle is live!\"]"));
//		((JavascriptExecutor)d).executeScript("arguments[0].focus()", vehicle_status);
//		vehicle_status.click();
//		vehicle_status.click();
//		
//		if (!d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(5)")).getText().equalsIgnoreCase(null)){
//			d.findElement(By.cssSelector("table.gridsort thead th:contains('Vehicle Assigned')")).click();
//		}
//		WebElement v = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child"));
//		
//		((JavascriptExecutor)d).executeScript("arguments[0].focus()", v);
//		v.click();
//		d.findElement(By.cssSelector("button.cmd:contains('Unassign')")).click();
//		Thread.sleep(2000);
//	}
}
