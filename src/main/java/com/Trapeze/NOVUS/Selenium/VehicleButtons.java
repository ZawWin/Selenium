package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleButtons {
	
	public void Menu2(WebDriver d){
//		AllTests.wait_Element("button.cmdVehicleSmall:contains(MENU 2)");
		if (d.findElements(By.cssSelector("button.cmdVehicleSmall:contains(MENU 2)")).size()== 0){
			Menu1(d);
		}
		AllTests.click("button.cmdVehicleSmall:contains(MENU 2)");
	}
	
	public void Menu1(WebDriver d){
//		AllTests.wait_Element("button.cmdVehicleSmall:contains(MENU 1)");
		if (d.findElements(By.cssSelector("button.cmdVehicleSmall:contains(MENU 1)")).size()== 0){
			Menu2(d);
		}
		AllTests.click("button.cmdVehicleSmall:contains(MENU 1)");
	}
	
	public void Alarm(WebDriver d) throws InterruptedException{
		AllTests.click("button.alarm:contains(A)");
		Thread.sleep(1500);
		AllTests.wait_Element("div.app_body button.cmdVehicleSmall:nth-child(4):contains('Log Off')");
	}

}
