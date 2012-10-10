package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScheduleAdherence {
	
	public void openSchedule(WebDriver d, String s){
		AllTests.open_link("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains('"+s+"')");		
	}
	
	public String[] edit(WebDriver d){
		openSchedule(d, "Edit Schedule Adherence Thresholds");
		String[] save = new String[4];
		AllTests.click("button.edit");
		save[0] = "05-27-11";	// From Date
		save[1] = "05-27-70";	// To Date
		save[2] = "15";							// Early Threshold (seconds)
		save[3] = "60";							// Late Threshold (seconds)
		
		WebElement i = d.findElement(By.cssSelector("td:contains('From Date:') + td div.dropdate input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", i);
		i.sendKeys(save[0]);
		
		i = d.findElement(By.cssSelector("td:contains('To Date:') + td div.dropdate input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", i);
		i.sendKeys(save[1]);
		
		i = d.findElement(By.cssSelector("td:contains('EARLY Threshold (seconds):') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", i);
		i.sendKeys(save[2]);
				
		// Probably will never be tested this late situation because of
		// the rate Route Sim send the location really fast
		i = d.findElement(By.cssSelector("td:contains('LATE Threshold (seconds):') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", i);
		i.sendKeys(save[3]);
		
		AllTests.click("button.cmd:contains('OK')");
		
		AllTests.click("div[class=\"app_menu_close\"]");
		openSchedule(d,"Edit Schedule Adherence Thresholds");
		return save;
	}
	
	// Building the block as usual by going through the list on the Schedule View page
	public String[] getScheduleView(WebDriver d){
		
		openSchedule(d,"Operations Table");
		d.findElement(By.cssSelector("div.app_menu_cmd:contains('Refresh')")).click();
		String[] current_vehicle = new String[7];
		current_vehicle = new Operations_Table().operations_view(d);
		String[] info = new String[2]; 			// Just require info for initial checking Schedule View page
		info[0] = current_vehicle[1];			// Line Abbreviation
		info[1] = current_vehicle[2]; 			// Line Name		
		d.findElement(By.cssSelector("td.s_tab_off:contains('My Tasks')")).click();
		openSchedule(d,"Schedule View");
		AllTests.click("label:contains('Block:') + select");
		AllTests.click("option:contains('LineGroup-"+current_vehicle[0]+": Block-"+current_vehicle[3]+"')");
		AllTests.click("button.cmd:contains('Build')");
		return info;
	}
	
	// Right-clicking the current the vehicle on the operations view page and open direct schedule View page
	public String[] getSchedule(WebDriver d) throws InterruptedException{
		Actions action = new Actions(d);
		String[] current_vehicle = new String[7];		
		openSchedule(d,"Operations Table");
		current_vehicle = new Operations_Table().operations_view(d);
		
		// Right Click Method (First Time using)
		action.dragAndDrop(d.findElement(By.cssSelector("div.app_menu_cmd:contains('Refresh')")), d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child"))).perform();
		Thread.sleep(500);
		action.contextClick( d.findElement(By.cssSelector("tbody.blockInfo_tbody tr td[colName=\"blockName\"]"))).perform();
		d.findElement(By.cssSelector("div[id=\"opsViewPopupMenu\"] div.pmmenubar div:contains('Open View')")).click();
		d.findElement(By.cssSelector("div.pmpopupMenu div.pmmenuitem:contains('Schedule View')")).click();		
		String[] info = new String [2];
		info[0] = current_vehicle[1];			// Line Abbreviation
		info[1] = current_vehicle[2];			// Line Name
		return info;
	}
}
