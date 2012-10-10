package com.Trapeze.NOVUS.Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class Operations_Table {
	private boolean sorting = true;
	private boolean operation = false;
	private String group_name;
	private int group_id;
	private String first, second;
	private int one, two;
	private int count, mark;
	private String text;
		
	public boolean test (WebDriver d){
		
		operations_view(d);
		//System.out.println ("Operation ="+operation);
		
		boolean [] done_sort = new boolean[20];
		sorting(d,"lineGroupAbbr",0);
		done_sort[0]= sorting;
		
		sorting(d,"lineAbbr",1);
		done_sort[1]= sorting;
		
		sorting(d,"lineName",2);
		done_sort[2]= sorting;
		
		sorting(d,"blockName",3);
		done_sort[3]= sorting;
		
		sorting(d, "runName", 4);
		done_sort[4]= sorting;
		
		sorting_time(d, "scheduledPullout", 5);
		done_sort[5]= sorting;
		
		sorting(d, "vehicleNumber", 7);
		done_sort[6]= sorting;
		
		sorting(d, "driverName", 8);
		done_sort[7]= sorting;
		
		sorting (d, "systemStatus",10);
		done_sort[8]= sorting;
		
		sorting (d, "lastTimePtCrossing",12);
		done_sort[9]= sorting;
		
		sorting_time(d, "lastTimePtArrivalTime", 13);
		done_sort[10]= sorting;
		
		sorting_time(d, "lastTimePtDepartureTime", 14);
		done_sort[11]= sorting;
		
		sorting_time(d, "lastStopArriveTime", 16);
		done_sort[12]= sorting;
		
		sorting (d, "direction", 17);
		done_sort[13]= sorting;
		
		if (done_sort[0]&&done_sort[1]&&done_sort[2]&&done_sort[3]&&done_sort[4]&&done_sort[5]&&done_sort[6]&&done_sort[7]&&
				done_sort[8]&&done_sort[9]&&done_sort[10]&&done_sort[11]&&done_sort[12]&&done_sort[13]){
			done_sort[19] = true;
		}
		
		//System.out.println ("Done =" + done_sort[19]);
		if (d.findElement(By.cssSelector("div.app_caption")).getText().equalsIgnoreCase("Operations View")&&operation&&done_sort[19] ){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String[] operations_view(WebDriver d){
		/*
		 * Getting the vehicle information from Vehicle Details Page
		 * Step-by-step follow up
		 * Open My task, click View Communication Roster List, click Vehicle Number on the left
		 * There opens up vehicle detail info
		 * Get those details
		 */
		String[] info = new String[7];		
		AllTests.click("table.gridsort thead tr th[selectIndex=\"6\"]");
		AllTests.click("div.app_menu_cmd:contains('Refresh')");
		AllTests.wait_Element("table.gridsort tbody.blockInfo_tbody tr td[colName=\"vehicleNumber\"]");
		if (d.findElement(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\"vehicleNumber\"]")).getText().isEmpty()){
			AllTests.click("table.gridsort thead tr th[selectIndex=\"6\"]");
			AllTests.click("div.app_menu_cmd:contains('Refresh')");
		}
		AllTests.wait_Element("table.gridsort tbody.blockInfo_tbody tr td[colName=\"lineGroupAbbr\"]");
		info[0] = d.findElement(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\"lineGroupAbbr\"]")).getText();
		info[1] = d.findElement(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\"lineAbbr\"]")).getText();
		info[2] = d.findElement(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\"lineName\"]")).getText();
		info[3] = d.findElement(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\"blockName\"]")).getText();
		info[4]= d.findElement(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\"runName\"]")).getText();
		info[5]= d.findElement(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\"vehicleNumber\"]")).getText();
		info[6]= d.findElement(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\"driverName\"]")).getText();
		return info;	
	}
	
	public String[] matchDetail(WebDriver d){
		String[] match = new String[7];
		if(ViewCommuRosterLister.Line_Abbr==null && ViewCommuRosterLister.Line_Group==null &&
				ViewCommuRosterLister.Line == null && ViewCommuRosterLister.Block==null && ViewCommuRosterLister.Run==null &&
				 ViewCommuRosterLister.Vehicle==null && ViewCommuRosterLister.Driver==null){
			
		d.findElement(By.cssSelector("td.s_command")).click();
		AllTests.click("div.BMC div.linkGroupBox div.linkItemBox div.linkTitle:contains(View Communication Roster List)");
		new ViewCommuRosterLister().getDetailInfo(d);
		AllTests.click("td.s_tab_off:contains('Operations View')");
		}		
		
		match[0] = ViewCommuRosterLister.Line_Group;
		match[1] = ViewCommuRosterLister.Line_Abbr;
		match[2] = ViewCommuRosterLister.Line;
		match[3] = ViewCommuRosterLister.Block;
		match[4] = ViewCommuRosterLister.Run;
		match[5] = ViewCommuRosterLister.Vehicle;
		match[6] = ViewCommuRosterLister.Driver;
		return match;
	}
	
	/*
	 * Sorting Integer and Normal Strings.
	 * For the pages which self-refreshes at random time.
	 * Behaviors - If the program initiates as soon as after self-refreshing the page, it will give the WebDriverException
	 * After self-refreshing, all the previous DOM elements will be gone and even though the new DOM elements have the same id and class name,
	 * they will still be different.
	 * In that case, it can give you StaleElementReferenceException. 
	 * To sum up, there are two exceptions that you might encounter in this test - WebDriverException first and StaleElementReferenceException second
	 * In order to work around those issues, after RANDOM self-refresh, use "catch(webdriverexception)" to catch the error. Since the new elements are different from
	 * the previous elements as described above, stored the whole lists of elements again to avoid StaleElementReferenceException.
	 * Wait for 2 seconds before you start storing the lists again during the exception because we have to give the time to the page to finish reloading and building the web elements inside it.
	 * Otherwise, that can also give you another StaleElementReferenceException. 
	 */
	public boolean sorting (WebDriver d, String name, int id){
		sorting= true;
		first = null; count = 0; second = null;
		one = 0; two = 0;
		group_name = name;
		group_id = id;
		AllTests.click("table.gridsort thead tr th[selectIndex=\""+id+"\"]");
		AllTests.click("div.app_menu_cmd:contains('Refresh')");
		try{
			AllTests.wait_Element("table.gridsort tbody.blockInfo_tbody tr td[colName=\""+name+"\"]");
			List<WebElement> line_group = d.findElements(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\""+name+"\"]"));
			for (WebElement i: line_group){
				mark = count;
				count++;
				text = i.getText();
				if (text.equalsIgnoreCase("")||text.isEmpty()){
					break;
				}
				try{
					if (count ==1 ){
						one = Integer.parseInt(text);
					}
					else{
						two = Integer.parseInt(text);
	//					System.out.println (name +" = "+ two +" ; Previous "+name+" = "+one);
						if (two >= one){
							one = two;
						}
						else {
							sorting = false;
							break;
						}
					}
					
				}
				catch(NumberFormatException e){

					if (count == 1){
						first = text;
					}
					else{
						second = text;
		//				System.out.println (name +" =e "+ second +" ; Previous "+name+" =e "+first);
						if (second.compareToIgnoreCase(first)>= 0){						
							first = second;
						}
						else{
							sorting = false;
							break;
						}
					}
				}
				
			}
		}catch (StaleElementReferenceException e1){
			try {
			//	System.out.println ("Original StaleElementReferenceException");
//				Thread.sleep(2*1000);
				re_sorting (d,name, id);
			} catch (InterruptedException e2) {
				e1.printStackTrace();
			}
		}catch (WebDriverException e){
			try {
				System.out.println ("Original WebDriverException");
//				Thread.sleep(2*1000);
				re_sorting (d,name, id);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}catch (NullPointerException e1){
			try {
//				System.out.println ("Original NullPointerException");
//				d.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
				re_sorting (d,name, id);
			} catch (InterruptedException e2) {
				e1.printStackTrace();
			}
		}
		return sorting;
	}
	private void re_sorting (WebDriver d, String name, int id) throws InterruptedException{
		first = null; count = 0; second = null;
		one = 0; two = 0;
		AllTests.wait_Element("table.gridsort tbody.blockInfo_tbody tr td[colName=\""+name+"\"]");
		List<WebElement> line_group = d.findElements(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\""+name+"\"]"));
		for (WebElement i: line_group){			
			count++;
			text =i.getText();
			if (text.equalsIgnoreCase("")||text.isEmpty()){
				break;
			}
			try{
				try{
					if (count ==mark ){
						mark = count;
						one = Integer.parseInt(text);
					}
					else if (count > mark){
						two = Integer.parseInt(text);
			//			System.out.println (name +" = "+ two +" ; Previous "+name+" = "+one);
						if (two >= one){
							one = two;
						}		
						else {
							sorting = false;
							break;
						}
					}
				} catch (NumberFormatException e){
					if (count==mark){
						first =text;
					}
					
					else if (count > mark){
						second = text;
					//	System.out.println (name +" =re "+ second +" ; Previous "+name+" =re "+first);
						if (second.compareToIgnoreCase(first)>= 0){						
							first = second;
						}
						else{
							sorting=false;
							break;
						}
					}				
				}
			}
			catch (StaleElementReferenceException e1){
				if (line_group.size()==count -1 ){
					break;
				}
				mark = count;
//				Thread.sleep(2*1000);
				System.out.println ("Re_sorting StaleElementReferenceException");
				re_sorting(d, group_name, group_id);				
			} 
			catch (WebDriverException e2){
				if (line_group.size()==count -1 ){
					break;
				}
				mark = count;
//				System.out.println ("Re_sorting WebDriverException");
//				Thread.sleep(2*1000);
				re_sorting(d, group_name, group_id);
			}
			catch (NullPointerException e1){
				if (line_group.size()==count -1 ){
					break;
				}
//				System.out.println ("Re_sorting NullPointerException");
//				d.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
				re_sorting (d,group_name, group_id);
			}
		}
	}
	
	
	/*
	 * Similar to the method used above but the only difference is this is for sorting the time
	 * The time has the different format from integer and normal string. That's why I have to build a new
	 * method to check the sorting order of the time
	 */
	public boolean sorting_time (WebDriver d, String name, int id){
		String timer = null; group_name = name; group_id = id;
		AllTests.click("table.gridsort thead tr th[selectIndex=\""+id+"\"]");
		AllTests.click("div.app_menu_cmd:contains('Refresh')");
		try{
			AllTests.wait_Element("table.gridsort tbody.blockInfo_tbody tr td[colName=\""+name+"\"]");
			List<WebElement> time = d.findElements(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\""+name+"\"]"));		
			int hm = 0; int h = 0; int m = 0;
			count= 0; one = 0; two = 0;
			for (WebElement i:time){
				mark = count;
				count++;				
				timer = i.getText();
				if (timer.equalsIgnoreCase("")||timer.isEmpty()){
					break;
				}
				h = Integer.parseInt(timer.substring(0,timer.indexOf(":")));			
				m = Integer.parseInt(timer.substring(timer.indexOf(":")+1));
				hm = h*60 + m;				
				if (count ==1 ){
					one = hm;
				}
				else{
					two = hm;
					if (two >= one){
						one = two;
					}
					else {
						sorting = false;
						break;
					}
				}
			}
		}catch (WebDriverException e){
			
			try {
//				System.out.println ("Original WebDriverException");
//				Thread.sleep(2*1000);
				resorting_time(d, name, id);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} catch (NullPointerException e1){
			try {
//				System.out.println ("Original NullPointerException");
//				Thread.sleep(2*1000);
				resorting_time (d,name, id);
			} catch (InterruptedException e2) {
				e1.printStackTrace();
			}
		}
		return sorting;
	}
	private void resorting_time (WebDriver d, String name, int id) throws InterruptedException{
		one = 0; two = 0; count = 0; String timer = null; int h = 0; int m =0; int hm = 0;
		AllTests.wait_Element("table.gridsort tbody.blockInfo_tbody tr td[colName=\""+name+"\"]");
		List<WebElement> time = d.findElements(By.cssSelector("table.gridsort tbody.blockInfo_tbody tr td[colName=\""+name+"\"]"));	
		for (WebElement i: time){			
			count++;
			try{
				timer = i.getText();
				if (timer.equalsIgnoreCase("")||timer.isEmpty()){
					break;
				}
				h = Integer.parseInt(timer.substring(0,timer.indexOf(":")));			
				m = Integer.parseInt(timer.substring(timer.indexOf(":")+1));
				hm = h*60 + m;				
				if (count ==1 ){
					one = hm;
				}
				else{
					two = hm;
					if (two >= one){
						one = two;
					}
					else {
						sorting = false;
						break;
					}
				}
			}
			catch (StaleElementReferenceException e1){
				if (time.size()==count -1 ){
					break;
				}
				mark = count;
//				Thread.sleep(2*1000);
//				System.out.println ("Re_sorting StaleElementReferenceException");
				resorting_time(d, group_name , group_id);				
			} 
			catch (WebDriverException e2){
				if (time.size()==count -1 ){
					break;
				}
				mark = count;
//				System.out.println ("Re_sorting WebDriverException");
//				Thread.sleep(2*1000);
				resorting_time(d, group_name, group_id);
			}
			catch (NullPointerException e1){
				if (time.size()==count -1 ){
					break;
				}
//				System.out.println ("Original NullPointerException");
//				Thread.sleep(2*1000);
				resorting_time (d,group_name, group_id);
			}
		}
	}
}
