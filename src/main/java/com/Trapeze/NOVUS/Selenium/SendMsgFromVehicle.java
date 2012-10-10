package com.Trapeze.NOVUS.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SendMsgFromVehicle {

	public void sendMsgBtn(WebDriver d){
		AllTests.click("div.app_body button.cmdVehicleBig_1:nth-child(5)");
	}
	
	public String[] sendEmergencyMsg(WebDriver d) throws InterruptedException{		
		String[] info = new String[2];
		info[0] = "Priority Canned Message";
		AllTests.click("button.canned1:contains(Emergency)");
		AllTests.wait_Element("div.listbox div");
		WebElement alarm = d.findElement(By.cssSelector("div.listbox div"));
		info[1] = alarm.getText();
		alarm.click();
		AllTests.click("button.gobtn");
		Thread.sleep(1500);
		AllTests.wait_Element("div.app_body button.cmdVehicleSmall:nth-child(4):contains('Log Off')");
		return info;
	}
	
	public String[] sendOperatorMsg(WebDriver d) throws InterruptedException{
		String[] info = new String[2];
		info[0] = "Canned Message";
		AllTests.click("button.canned2:contains(Operator)");
		AllTests.wait_Element("div.listbox div:last-child");
		WebElement alarm = d.findElement(By.cssSelector("div.listbox div:last-child"));
		info[1] = alarm.getText();
		alarm.click();
		AllTests.click("button.gobtn");
		Thread.sleep(1500);
		AllTests.wait_Element("div.app_body button.cmdVehicleSmall:nth-child(4):contains('Log Off')");
		return info;
	}
	
	public String[] sendServiceMsg(WebDriver d) throws InterruptedException{
		String[] info = new String[2];
		info[0] = "Canned Message";
		AllTests.click("button.canned3:contains(Service)");
		AllTests.wait_Element("div.listbox div");
		WebElement alarm = d.findElement(By.cssSelector("div.listbox div"));
		info[1] = alarm.getText();
		alarm.click();
		AllTests.click("button.gobtn");
		Thread.sleep(1500);
		AllTests.wait_Element("div.app_body button.cmdVehicleSmall:nth-child(4):contains('Log Off')");
		return info;
	}
	
	public String[] sendFreeText(WebDriver d) throws InterruptedException{
		String[] info = new String[2];
		info[0] = "Free Text Msg";
		AllTests.click("button.canned1:contains(Free Text)");
		
		info[1] = "TRAPEZE 123";
		d.findElement(By.cssSelector("button.keypadNum:contains(T)")).click();
		d.findElement(By.cssSelector("button.keypadNum:contains(R)")).click();
		d.findElement(By.cssSelector("button.keypadNum:contains(A)")).click();
		d.findElement(By.cssSelector("button.keypadNum:contains(P)")).click();
		d.findElement(By.cssSelector("button.keypadNum:contains(E)")).click();
		d.findElement(By.cssSelector("button.keypadNum:contains(Z)")).click();
		d.findElement(By.cssSelector("button.keypadNum:contains(E)")).click();
		d.findElement(By.cssSelector("button.listFrame:contains(Space)")).click();
		d.findElement(By.cssSelector("button.keypadNum:contains(1)")).click();
		d.findElement(By.cssSelector("button.keypadNum:contains(2)")).click();
		d.findElement(By.cssSelector("button.keypadNum:contains(3)")).click();
		
		AllTests.click("button.gobtn");
		Thread.sleep(1500);
		AllTests.wait_Element("div.app_body button.cmdVehicleSmall:nth-child(4):contains('Log Off')");
		return info;
	}
}
