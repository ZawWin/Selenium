package com.Trapeze.NOVUS.Selenium;

import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ViewCommuRosterLister {

	private String title;
	public static String Vehicle = null;
	public static String Line_Group = null;
	public static String Line_Abbr = null;
	public static String Line = null;
	public static String Block = null;
	public static String Run = null;
	public static String Driver = null;
	
	// Checking Detail Info of a vehicle on the Vehicle Details Page
	// Just done checking Title, Vehicle, Last Heard, Line Group, Block, Driver
	public String[] getDetailInfo (WebDriver d){
		AllTests.click("td.rh");
		String[] info = new String[5];

		WebElement e2 = d.findElement(By.cssSelector("div.app_caption"));
		title  = e2.getText();		
		info[0] = title;
				
		e2 = d.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Vehicle') + td"));
		Vehicle = e2.getText();
		info[1]=Vehicle;
		
		
		e2 = d.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Line Group:') + td"));
		Line_Group = e2.getText();
		info[2] = Line_Group;
		
		e2 = d.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Line:') + td"));
		String line = e2.getText();
		Line_Abbr = line.substring(line.indexOf("[")+1, line.indexOf("]"));
		Line = line.substring(line.indexOf("]")+2);
		
		e2 = d.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Block:') + td"));
		Block = e2.getText();
		info[3] = Block;
		
		e2 = d.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Run:') + td"));
		Run = e2.getText();
		
		e2 = d.findElement(By.cssSelector("table.appCadText tbody tr td:contains('Driver:') + td"));
		Driver = e2.getText();	
		info[4]=Driver.substring(Driver.indexOf("[")+1,Driver.indexOf("]"));
		
		return info;
	}
	
	public String[] checkDetailInfo (WebDriver d) throws ParseException, InterruptedException{
		if (RemoteLogOn.Vehicle_Assigned==null&&RemoteLogOn.Line_Group==null&&RemoteLogOn.Block==null&&
				RemoteLogOn.Driver==null){
			AllTests.click("div[class=\"app_menu_close\"]");
			AllTests.click("div[class=\"app_menu_close\"]");
			AllTests.click("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Block Assignment)");
			new RemoteLogOn().logOn(d);
			}
		String[] check = new String[5];
		check[0] = "Vehicle Details"+RemoteLogOn.Vehicle_Assigned;
		check[1] = RemoteLogOn.Vehicle_Assigned;
		check[2] = RemoteLogOn.Line_Group;
		check[3] = RemoteLogOn.Block;
		check[4] = RemoteLogOn.Driver;
		return check;
	}
}
