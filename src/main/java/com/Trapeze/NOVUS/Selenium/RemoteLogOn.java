package com.Trapeze.NOVUS.Selenium;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class RemoteLogOn {
	
	public static String Vehicle_Assigned=null,Driver=null, Line_Group=null, Block=null;	
	private Calendar cal = Calendar.getInstance(TimeZone.getDefault());
	private static int currentTime ;
	private String driver;
	
	public String[] logOn(WebDriver d) throws ParseException, InterruptedException{
		
		String[] info = new String[3];
		AllTests.wait_Element("tbody.selectionListBody tr div[title = \"Vehicle is live!\"]");
		WebElement vehicle_status = d.findElement(By.cssSelector("tbody.selectionListBody tr div[title = \"Vehicle is live!\"]"));
		((JavascriptExecutor)d).executeScript("arguments[0].focus()", vehicle_status);
		vehicle_status.click();
		vehicle_status.click();
		
		AllTests.wait_Element("tbody.selectionListBody tr.selected");
		WebElement vehicle_id = d.findElement(By.cssSelector("tbody.selectionListBody tr.selected"));
		Vehicle_Assigned = vehicle_id.getText();
		info[0] = Vehicle_Assigned;
		
		// Because of DCC page. DCC page has to be opened before the vehicle is assigned.
		// So, even if the vehicle is already assigned, we should always assign and reassign after opening DCC page
		// in order to see the changes on DCC. 
	/*			
		if (d.findElements(By.cssSelector("tbody.blockInfo_tbody tr td:nth-child(7) div.activity_img")).size()!= 0){
			WebElement check = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr td:nth-child(7) div.activity_img"));
			((JavascriptExecutor) d).executeScript("arguments[0].click()", check);
			//check.click();
			check = d.findElement(By.cssSelector("td.s_tab_on:contains('Block Assignment')"));
			check.click();
			
			WebElement c1 = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr.selected td:nth-child(5)"));
			Vehicle_Assigned = c1.getText();
			c1 = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr.selected td:nth-child(6)"));
			Driver = c1.getText();	
			driver = Driver;
			c1 = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr.selected td:nth-child(1)"));
			Line_Group = c1.getText();
			c1 = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr.selected td:nth-child(3)"));
			Block = c1.getText();
		}
		*/
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
			logOn(d);
		}
	
		else{
			try{
				Date date = new Date();
				SimpleDateFormat Hour = new SimpleDateFormat ("HH");
				final int hour = Integer.parseInt(Hour.format(cal.getTime()));
				SimpleDateFormat Minute = new SimpleDateFormat ("mm");
				final int min = Integer.parseInt(Minute.format(cal.getTime()));
				currentTime = hour*60 + min;
				
				List<WebElement> blockList = d.findElements(By.cssSelector("tbody.blockInfo_tbody tr td:nth-child(4)"));		
				SimpleDateFormat pullout = new SimpleDateFormat ("HH:mm");	
				int hm = 0;
				WebElement rightBlock = null;
				try{
				for (WebElement i:blockList){
						date = pullout.parse(i.getText());
						int h = Integer.parseInt(Hour.format(date));			
						int m = Integer.parseInt(Minute.format(date));
						hm = h*60 + m;				
						if (hm > currentTime){					
							blockList.clear();
							break;
						}
						else { 
							rightBlock = i;
						}
				}
				((JavascriptExecutor) d).executeScript("arguments[0].focus()",rightBlock);
				rightBlock.click();
				//((JavascriptExecutor) d).executeScript("arguments[0].click()",rightBlock);
				}
				catch(StaleElementReferenceException e){	
					e.printStackTrace();
				}
				
				AllTests.click("button.cmd:contains('Assign')");
				
				AllTests.wait_Element("div.modalDialog option");
				WebElement employee = d.findElement(By.cssSelector("div.modalDialog option"));
				String d_assigned = employee.getText();					
				driver = d_assigned.substring(d_assigned.indexOf("[")+1,d_assigned.indexOf("]"));
				
				AllTests.click("td.commands button.cmd:contains('OK')");
				Thread.sleep(5000);
				
			//	WebElement check = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr.selected td:nth-child(5)"));
		//		String v_assigned = check.getText();
				AllTests.switchToDCC();
				AllTests.wait_Element("button.cmdVehicleSmall:contains('Log Off')");
				AllTests.switchToCAD();
				
				d.findElement(By.cssSelector("table.gridsort thead th:contains('Vehicle Assigned')")).click();
				AllTests.click("div.app_menu_cmd:contains('Refresh')");
				Thread.sleep(2000);
				AllTests.wait_Element("tbody.blockInfo_tbody tr:first-child td:nth-child(5)");
				if (d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(5)")).getText().isEmpty()){
					d.findElement(By.cssSelector("table.gridsort thead th:contains('Vehicle Assigned')")).click();
					AllTests.click("div.app_menu_cmd:contains('Refresh')");
					Thread.sleep(2000);
				}				
				
				WebElement	check = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(6)"));
				Driver = check.getText();
				
				check = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(1)"));
				Line_Group = check.getText();
				
				check = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(3)"));
				Block = check.getText();
				
			}catch (WebDriverException e){
				logOn(d);
				info[1] = driver;
			}
		}
		info[1] = driver;
		info[2] = "Log Off";
		return info;
	}
	
//	public void unassign(WebDriver d) throws InterruptedException{
//		d.findElement(By.cssSelector("table.gridsort thead th:contains('Vehicle Assigned')")).click();
//		if (d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(5)")).getText().equalsIgnoreCase(null)){
//			d.findElement(By.cssSelector("table.gridsort thead th:contains('Vehicle Assigned')")).click();
//		}
//		WebElement v = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child"));
//		
//		((JavascriptExecutor)d).executeScript("arguments[0].focus()", v);
//		v.click();
//		d.findElement(By.cssSelector("button.cmd:contains('Unassign')")).click();
//	}
	
	public void logOff(WebDriver d) throws InterruptedException, ParseException{
		if (d.findElements(By.cssSelector("tbody.blockInfo_tbody tr td:nth-child(5):contains('"+Vehicle_Assigned+"')")).size()!= 0){
//			System.out.println("Already Assigned");
			
			AllTests.wait_Element("tbody.blockInfo_tbody tr:first-child td:nth-child(5)");
			if (d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(5)")).getText().isEmpty()){
				d.findElement(By.cssSelector("table.gridsort thead th:contains('Vehicle Assigned')")).click();
			}
			AllTests.wait_Element("tbody.blockInfo_tbody tr:first-child");
			WebElement v = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child"));			
			((JavascriptExecutor)d).executeScript("arguments[0].focus()", v);
			v.click();
			d.findElement(By.cssSelector("button.cmd:contains('Unassign')")).click();
			Thread.sleep(5000);
			}
		
		else {
//			System.out.println("Not Assigned");
			logOn(d);
			AllTests.wait_Element("tbody.blockInfo_tbody tr:first-child td:nth-child(5)");
			if (d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child td:nth-child(5)")).getText().isEmpty()){
				d.findElement(By.cssSelector("table.gridsort thead th:contains('Vehicle Assigned')")).click();
			}
			AllTests.wait_Element("tbody.blockInfo_tbody tr:first-child");
			WebElement v = d.findElement(By.cssSelector("tbody.blockInfo_tbody tr:first-child"));
			
			((JavascriptExecutor)d).executeScript("arguments[0].focus()", v);
			v.click();
			d.findElement(By.cssSelector("button.cmd:contains('Unassign')")).click();
			Thread.sleep(5000);
		}
		
//		Thread.sleep(5000);
	}
}
