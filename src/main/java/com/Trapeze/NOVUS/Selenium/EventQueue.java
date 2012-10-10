package com.Trapeze.NOVUS.Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EventQueue {

	public static int table_size;
	public String title_check(String s){		
		return s;
	}
	
	public String[] page_check(WebDriver d, String s){
		String list_table;
		int count;
		
		String[] component;
		AllTests.wait_Element("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable");
		list_table = d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable")).getText();
		if (list_table.isEmpty()){
			return null;
		}
		else{
			table_size = d.findElements(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr")).size();
			count = table_size*3;
			component = new String[count];

			if (table_size == 1){
				component[0] = d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr td:first-child")).getText();
				component [1]= d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr td:nth-child(2)")).getText();
				if (d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr td:last-child")).getText().substring(0,1).equalsIgnoreCase("0")){
					component[2] = d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr td:last-child")).getText().substring(1);
				}
				else{
					component[2] = d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr td:last-child")).getText();
				}
			}
			
			else{
				for (int i= 1; i<=table_size; i++){
					int j = i-1;
					component[j] = d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr:nth-child("+i+") td:first-child")).getText();
					component[j+1] = d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr:nth-child("+i+") td:nth-child(2)")).getText();
					if (d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr:nth-child("+i+") td:last-child")).getText().substring(0,1).equalsIgnoreCase("0")){
						component[j+2] = d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr:nth-child("+i+") td:last-child")).getText().substring(1);
					}
					else{
						component[j+2] = d.findElement(By.cssSelector("div.eventqueue table.qDetails:contains('"+s+"') + div.eventqitems table.qlistTable tbody tr:nth-child("+i+") td:last-child")).getText();
					}
					
					j = j+3;
				}				
			}
			return component;
		}
	}
	
	public String[] all_events(WebDriver d){
		int count = 0;
		List<WebElement> collection = d.findElements(By.cssSelector("td.qSize"));
		String[] msg_count = new String[collection.size()]; 
		for (WebElement i: collection){			
			msg_count[count]= i.getText();
			if (msg_count[count].isEmpty()){
				msg_count[count]=null;
			}
			count++;
		}
		return msg_count;
	}
	
	public String[] event_detail(WebDriver d){
		String[] title = new String[4];
		int count = 0;
		List<WebElement> collection = d.findElements(By.cssSelector("tr.ta_datarow"));
		for (WebElement i: collection){	
			title[count] = i.getText().substring(0,i.getText().indexOf("#")-1);
			//System.out.println(title[count]);
			count++;
		}
		String single_event;
		String[] event_detail = new String[5];
		List<WebElement> collection1 = d.findElements(By.cssSelector("td.qSize"));
		int count1 = 0;
		for (WebElement i: collection1){
			single_event= i.getText();
			if (!single_event.isEmpty()){
				event_detail[0] = d.findElement(By.cssSelector("tr.ta_datarow:contains('"+title[count1]+"') tbody.customPaginatedBody tr td:nth-child(2)")).getText();
				event_detail[1] = d.findElement(By.cssSelector("tr.ta_datarow:contains('"+title[count1]+"') tbody.customPaginatedBody tr td:nth-child(3)")).getText();
				event_detail[2] = d.findElement(By.cssSelector("tr.ta_datarow:contains('"+title[count1]+"') tbody.customPaginatedBody tr td:nth-child(4)")).getText();
				event_detail[3] = d.findElement(By.cssSelector("tr.ta_datarow:contains('"+title[count1]+"') tbody.customPaginatedBody tr td:nth-child(5)")).getText();
				event_detail[4] = d.findElement(By.cssSelector("tr.ta_datarow:contains('"+title[count1]+"') tbody.customPaginatedBody tr td:nth-child(6)")).getText();
				d.findElement(By.cssSelector("tr.ta_datarow:contains('"+title[count1]+"') tbody.customPaginatedBody button.edit")).click();
				break;
			}
			count1++;
		}
		return event_detail;
	}
}

