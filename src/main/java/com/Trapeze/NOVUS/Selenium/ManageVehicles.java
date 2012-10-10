package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageVehicles {

	private String vehicle_no;
	private String model;
	private String make;
	private String year;
	private String start_odometer;
	private String gas_range;
	private String garage;
	private String vehicle_type;
	private String capacity;
	private String lincese;
	private String description;
	public String[] add(WebDriver d){
		String[] vehicle_info = new String[7];
		AllTests.click("div.app_menu_new");
		
		vehicle_no = "413ZLD";
		vehicle_info[0] = vehicle_no;
		d.findElement(By.cssSelector("div.BMC label:contains('Vehicle #:') + input")).sendKeys(vehicle_no);
		
		model = "oz_rally";
		d.findElement(By.cssSelector("div.BMC label:contains('Model:') + input")).sendKeys(model);
		
		make = "Mitsubishi";
		vehicle_info[1] = make;
		d.findElement(By.cssSelector("div.BMC label:contains('Make:') + input")).sendKeys(make);
		
		year = "2002";
		vehicle_info[2] = year;
		d.findElement(By.cssSelector("div.BMC label:contains('Year:') + input")).sendKeys(year);
		
		start_odometer = "96000";
		d.findElement(By.cssSelector("div.BMC label:contains('Start Odometer:') + input")).sendKeys(start_odometer);
		
		gas_range = "29mpg";
		d.findElement(By.cssSelector("div.BMC label:contains('Gas Range:') + input")).sendKeys(gas_range);
		
		d.findElement(By.cssSelector("div.BMC label:contains('Garage:') + div.droplist button")).click();
		garage = d.findElement(By.cssSelector("div.listpane + div.listpane + div.listpane div:nth-child(2)")).getText();
		vehicle_info[3] = garage;
		d.findElement(By.cssSelector("div.listpane + div.listpane + div.listpane div:nth-child(2)")).click();
		
		d.findElement(By.cssSelector("div.BMC label:contains('Vehicle Type:') + div.droplist button")).click();
		vehicle_type = d.findElement(By.cssSelector("div.listpane div:nth-child(2)")).getText();
		vehicle_info[4] = vehicle_type;
		d.findElement(By.cssSelector("div.listpane div:nth-child(2)")).click();
		
		d.findElement(By.cssSelector("div.BMC label:contains('Capacity Type:') + div.droplist button")).click();
		capacity = d.findElement(By.cssSelector("div.listpane + div.listpane div:nth-child(5) span.concise")).getText();
		vehicle_info[5] = capacity;
		d.findElement(By.cssSelector("div.listpane + div.listpane div:nth-child(5)")).click();
		
		lincese = "F012823";
		d.findElement(By.cssSelector("div.BMC label:contains('License Number:') + input")).sendKeys(lincese);
		
		description = "This is my Selenium car. Don't touch it or don't even think about it.";
		vehicle_info[6] = description;
		d.findElement(By.cssSelector("div.BMC label:contains('Description:') + textarea")).sendKeys(description);
		
		AllTests.click("div.app_menu_save");
		AllTests.click("div[class=\"app_menu_close\"]");
		
		
		return vehicle_info;
		
	}
	
	public String change(WebDriver d){
		AllTests.click("button.cmd:contains('Select All')");
		AllTests.click("tbody.customPaginatedBody tr button.edit");
		WebElement year = d.findElement(By.cssSelector("div.BMC label:contains('Year:') + input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", year);
		year.sendKeys("2012");
		AllTests.click("div.app_menu_save");
		AllTests.click("div[class=\"app_menu_close\"]");		
		return "2012";
		
	}
}
