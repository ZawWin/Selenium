package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpportunityAnnouncement {
		
		private String[] new_stuff = new String[8];
		
		public String[] add (WebDriver d){
			new_stuff[0] = "A New Opportunity";
		WebElement description, sign_text, sound_file, interval_play, radius1, latiude, longitude ;
		
		// Adding Test
		AllTests.click("div.tab_top_row span:nth-child(5)");
		AllTests.click("button[title=\"Add Announcement\"][class=\"add\"]");
		
		AllTests.wait_Element("div.modalDialog td:contains('Description:') + td input");
		description = d.findElement(By.cssSelector("div.modalDialog td:contains('Description:') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", description);
		description.sendKeys(Keys.DELETE);
		description.sendKeys(new_stuff[0]);
		
		new_stuff[1]="Selenium Testing";
		sign_text = d.findElement(By.cssSelector("div.modalDialog td:contains('Sign Text') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", sign_text);
		sign_text.sendKeys(Keys.DELETE);
		sign_text.sendKeys(new_stuff[1]);
		
		new_stuff[2] = "Selenium.wav";
		sound_file = d.findElement(By.cssSelector("div.modalDialog td:contains('Sound File') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", sound_file);
		sound_file.sendKeys(Keys.DELETE);
		sound_file.sendKeys(new_stuff[2]);
		
		//for Weekdays checkbox (Monday, Wednesday, Friday, Saturday)
		AllTests.wait_Element("div.modalDialog td:contains('Weekdays:') + td div.weekdays div:nth-child(2) div.checkbox");
		d.findElement(By.cssSelector("div.modalDialog td:contains('Weekdays:') + td div.weekdays div:nth-child(2) div.checkbox")).click();
		d.findElement(By.cssSelector("div.modalDialog td:contains('Weekdays:') + td div.weekdays div:nth-child(4) div.checkbox")).click();
		d.findElement(By.cssSelector("div.modalDialog td:contains('Weekdays:') + td div.weekdays div:nth-child(6) div.checkbox")).click();
		d.findElement(By.cssSelector("div.modalDialog td:contains('Weekdays:') + td div.weekdays div:nth-child(7) div.checkbox")).click();
		
		new_stuff[3] = "25";
		AllTests.wait_Element("div.modalDialog td:contains('Interval To Play:') + td input");
		interval_play = d.findElement(By.cssSelector("div.modalDialog td:contains('Interval To Play:') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", interval_play);
		interval_play.sendKeys(Keys.DELETE);
		interval_play.sendKeys(new_stuff[3]);
		
		d.findElement(By.cssSelector("td.commands button.cmd:contains('OK')")).click();
		
		// Editing Test
				
		AllTests.click("table.grid tbody tr button[title=\"Edit Announcement\"][class=\"edit\"]");
		
		new_stuff[4] = "150.0";
		AllTests.wait_Element("div.modalDialog td:contains('Radius:') + td input");
		radius1 = d.findElement(By.cssSelector("div.modalDialog td:contains('Radius:') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", radius1);
		radius1.sendKeys(Keys.DELETE);
		radius1.sendKeys(new_stuff[4]);
		
		new_stuff[5] = "123.5";
		latiude = d.findElement(By.cssSelector("div.modalDialog td:contains('Latitude:') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", latiude);
		latiude.sendKeys(Keys.DELETE);
		latiude.sendKeys(new_stuff[5]);
		
		new_stuff[6] = "-123.8";
		longitude = d.findElement(By.cssSelector("div.modalDialog td:contains('Longitude:') + td input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", longitude);
		longitude.sendKeys(Keys.DELETE);
		longitude.sendKeys(new_stuff[6]);
		
		new_stuff[7] = "0101011";
		d.findElement(By.cssSelector("td.commands button.cmd:contains('OK')")).click();
		return new_stuff;
	}
}