package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RouteAdherence {
	
	public void open(WebDriver d, String s){
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains("+s+")");		
	}
	
	public String edit_routeAdhere(WebDriver d) throws InterruptedException{
		// Open the switches link first to change the value of the off_track
		String offRouteDistance = "1";
		open(d, "Switches");
		((JavascriptExecutor)d).executeScript("arguments[0].click()", d.findElement(By.cssSelector("div.switch_page_normal:contains(Page 40)")));
		Thread.sleep(500);
		d.findElement(By.cssSelector("td.switch_link:contains('Obs/Services/ObsRouteInfo/Track/OffRouteDistance')")).click();
		AllTests.wait_Element("td.switch_detail input");
		WebElement input = d.findElement(By.cssSelector("td.switch_detail input"));
		((JavascriptExecutor)d).executeScript("arguments[0].select()", input);
		input.sendKeys(offRouteDistance);
		d.findElement(By.cssSelector("button.cmd:contains('Update')")).click();
		return offRouteDistance;
	}
	
	
	// For testing the route Summary page directly from Operations View
	// Similar Method used in ScheduleAdherence class
	public String[] routeAdhere_Page(WebDriver d) throws InterruptedException{
		Actions action = new Actions(d);
		String[] current_vehicle = new String[7];		
		open (d, "Operations Table");
		current_vehicle = new Operations_Table().operations_view(d);
		
		// Right Click Method
		action.dragAndDrop(d.findElement(By.cssSelector("div.app_menu_cmd:contains('Refresh')")), d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child"))).perform();
		Thread.sleep(500);
		action.contextClick( d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child"))).perform();
		d.findElement(By.cssSelector("div[id=\"opsViewPopupMenu\"] div.pmmenubar div:contains('Open View')")).click();
		d.findElement(By.cssSelector("div.pmpopupMenu div.pmmenuitem:contains('Route Summary')")).click();		
		return current_vehicle;
	}
	
	// This one is for testing the same routeAdhere page but normal manual way
	public String[] get_routeAdherePage(WebDriver d){
		open(d,"Operations Table");
		String[] current_vehicle = new String[7];
		current_vehicle = new Operations_Table().operations_view(d);
		d.findElement(By.cssSelector("td.s_tab_off:contains('My Tasks')")).click();
		open(d,"Route Summary");
		d.findElement(By.cssSelector("label:contains('Line:') + select")).click();
		AllTests.click("option:contains('["+current_vehicle[1]+"] "+current_vehicle[2]+"')");
		AllTests.click("button.cmd:contains('View')");
		return current_vehicle;
	}
}
