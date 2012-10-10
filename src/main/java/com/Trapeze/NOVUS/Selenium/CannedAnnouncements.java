package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CannedAnnouncements {
		
	private String canned_announcement_tab = "div.tab_top_row span:nth-child(4)";
	private String add_announcement = "button[title=\"Add Announcement\"][class=\"add\"]";
	private String new_description = "div.modalDialog td:contains('Description:') + td input";
	private String new_sign_text = "div.modalDialog td:contains('Sign Text') + td input";
	private String new_sound_file = "div.modalDialog td:contains('Sound File') + td input";
	private String OK = "td.commands button.cmd:contains('OK')";
	private String edit_announcement = "table.grid tbody tr:last-child button[title=\"Edit Announcement\"][class=\"edit\"]";
	private String new_ext_msg = "div.modalDialog td:contains('External') + td input";
	
	public String[] add(WebDriver d){
		WebElement description, sign_text, sound_file, external;	
		String[] msg = new String[4];
		
		msg[0] = "A New Selenium";		
		AllTests.click(canned_announcement_tab);
		
		AllTests.click(add_announcement);
		
		AllTests.wait_Element(new_description);
		description = d.findElement(By.cssSelector(new_description));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", description);
		description.sendKeys(Keys.DELETE);
		description.sendKeys(msg[0]);
		
		msg[1]="Selenium Testing";
		sign_text = d.findElement(By.cssSelector(new_sign_text));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", sign_text);
		sign_text.sendKeys(Keys.DELETE);
		sign_text.sendKeys(msg[1]);
		
		msg[2] = "Selenium.wav";
		sound_file = d.findElement(By.cssSelector(new_sound_file));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", sound_file);
		sound_file.sendKeys(Keys.DELETE);
		sound_file.sendKeys(msg[2]);
		
		AllTests.click(OK);
		
		msg[3] = "For Edit Button";
		AllTests.click(edit_announcement);
		
		AllTests.wait_Element(new_ext_msg);
		external = d.findElement(By.cssSelector(new_ext_msg));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", external);
		external.sendKeys(Keys.DELETE);
		external.sendKeys(msg[3]);
		
		AllTests.click(OK);
		
		return msg;
	}
}
