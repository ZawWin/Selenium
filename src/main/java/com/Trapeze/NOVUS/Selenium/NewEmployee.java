package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewEmployee {
	
	public static String E_Code = null;
	public static String Badge = null;
	public static String PIN  = null;
	public static String Title  = null;
	public static String First_Name  = null;
	public static String M_Name = null;
	public static String Last_Name = null;
	public static String Gender = null;
	public static String Marital_Status = null;
	public static String ID = null;
	public static String Driver_License = null;
	public static String Driver_Code = null;
	public static String Comment = null;
	
	public String title (){
		return "New Employee"+Title+". "+First_Name+" "+Last_Name+" ["+Badge+"]";
	}
	
	public void add_employee(WebDriver d){
		E_Code = "12345";
		d.findElement(By.cssSelector("td.tab_body div label:contains('Employee Code:') + input")).sendKeys(E_Code);
		
		Badge = "28";
		WebElement badge = d.findElement(By.cssSelector("td.tab_body div label:contains('Badge Number:') + input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", badge);
		badge.sendKeys(Badge);
		
		PIN = "2134352";
		d.findElement(By.cssSelector("td.tab_body div label:contains('PIN:') + input")).sendKeys(PIN);
		
		Title = "MRS";
		d.findElement(By.cssSelector("div.droplist button[tabIndex=\"-1\"]")).click();
		d.findElement(By.cssSelector("div.listpane:contains("+Title+")")).click();
		
		First_Name = "Michael";
		d.findElement(By.cssSelector("td.tab_body div label:contains('First Name:') + input")).sendKeys(First_Name);
		
		M_Name = "S";
		d.findElement(By.cssSelector("td.tab_body div label:contains('Middle Name:') + input")).sendKeys(M_Name);
		
		Last_Name = "Wooh";
		d.findElement(By.cssSelector("td.tab_body div label:contains('Last Name:') + input")).sendKeys(Last_Name);
		
		d.findElement(By.cssSelector("div#cbxGender[initialDisplayValue=\"M\"]")).click();
		WebElement gender = d.findElement(By.cssSelector("div.listpane[value=\"M\"] div:last-child"));
		Gender = gender.getText();
		gender.click();
		
		d.findElement(By.cssSelector("div.droplist[initialDisplayValue=\"Single\"]")).click();
		WebElement marital = d.findElement(By.cssSelector("div.listpane[value=\"Single\"] div:nth-child(2)"));
		Marital_Status = marital.getText();
		marital.click();
		
		ID = "007";
		WebElement id = d.findElement(By.cssSelector("td.tab_body div label:contains('Identification Number:') + input"));
		((JavascriptExecutor) d).executeScript("arguments[0].select()", id);
		id.sendKeys(ID);
		
		Driver_License = "F002123";
		d.findElement(By.cssSelector("td.tab_body div label:contains('Driver Licence Number:') + input")).sendKeys(Driver_License);
		
		Driver_Code = "327";
		d.findElement(By.cssSelector("td.tab_body div label:contains('Driver Licence Code:') + input")).sendKeys(Driver_Code);
		
		Comment = "Beware! This is Selenium Test Product";
		d.findElement(By.cssSelector("td.tab_body div label:contains('Comments:') + textarea")).sendKeys(Comment);
		
		d.findElement(By.cssSelector("div.app_menu_save")).click();
	}

}
