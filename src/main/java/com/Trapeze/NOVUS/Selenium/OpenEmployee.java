package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenEmployee {
	
	public String[] select (WebDriver d) throws InterruptedException{
		int employee_id;
		String[] actual = new String[11];
		if (NewEmployee.E_Code == null){
			AllTests.click("div.app_menu_new");
			new NewEmployee().add_employee(d);
			AllTests.click("div[class=\"app_menu_close\"]");
			AllTests.click("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(Open Employee)");
		}	
		
		int j = 0;
		AllTests.click("button.cmd:contains('Select All')");
		AllTests.wait_Element("tbody.customPaginatedBody tr td:nth-child(2)");
		String a = d.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(2)")).getText();
		String b = d.findElement(By.cssSelector("tbody.customPaginatedBody tr + tr td:nth-child(2)")).getText();
		employee_id = Integer.parseInt(a);
		j = Integer.parseInt(b);
		while (j>employee_id){
			AllTests.click("div.cplDataFrame table thead tr th + th");
			employee_id = Integer.parseInt(d.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(2)")).getText());
			j = Integer.parseInt(d.findElement(By.cssSelector("tbody.customPaginatedBody tr + tr td:nth-child(2)")).getText());
		}
		AllTests.wait_Element("tbody.customPaginatedBody tr td:nth-child(9)");
		actual[4] = d.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(9)")).getText();
		actual[5] = d.findElement(By.cssSelector("tbody.customPaginatedBody tr td:nth-child(10)")).getText();
		d.findElement(By.cssSelector("tbody.customPaginatedBody tr td.rh")).click();
		AllTests.wait_Element("div.app_caption");
		actual[9] = d.findElement(By.cssSelector("div.app_caption")).getText();
		actual[3] = d.findElement(By.cssSelector("table.empInfoTable tbody tr td:contains('Selected Employee:') + td")).getText();
		actual[1] = d.findElement(By.cssSelector("table.fullInfo tbody td:contains('Badge:') + td")).getText();
		actual[6] = d.findElement(By.cssSelector("table.fullInfo tbody td:contains('Licence:') + td")).getText();
		actual[7] = d.findElement(By.cssSelector("table.fullInfo tbody td:contains('Licence Code:') + td")).getText();
		actual[0] = d.findElement(By.cssSelector("table.fullInfo tbody tr:nth-child(5) td + td")).getText();
		actual[2] = d.findElement(By.cssSelector("table.fullInfo tbody td:contains('PIN:') + td")).getText();
		
		d.findElement(By.cssSelector("button.cmdTask:contains('Edit')")).click();
		AllTests.wait_Element("div.app_caption");
		actual[10] = d.findElement(By.cssSelector("div.app_caption")).getText();
		actual[8] = d.findElement(By.cssSelector("td.tab_body div textarea")).getText();
		
		return actual;
	}
	
	public String[] match(WebDriver d){
		String[] expect = new String[11];
			
		String employee_name = NewEmployee.Title+". "+NewEmployee.First_Name+" "+NewEmployee.Last_Name+" ["+NewEmployee.Badge+"]";
		expect[0]=NewEmployee.E_Code;
		expect[1]=NewEmployee.Badge;
		expect[2]=NewEmployee.PIN;
		expect[3]=employee_name;
		expect[4]=NewEmployee.Gender;
		expect[5]=NewEmployee.ID;
		expect[6]=NewEmployee.Driver_License;
		expect[7]=NewEmployee.Driver_Code;
		expect[8]=NewEmployee.Comment;
		expect[9] = "Open Employee"+employee_name;
		expect[10]= "Edit Employee"+employee_name;
		
		return expect;
	}
}
