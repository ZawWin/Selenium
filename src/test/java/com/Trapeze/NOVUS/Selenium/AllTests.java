package com.Trapeze.NOVUS.Selenium;

//import java.net.URL;
import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


@RunWith(Suite.class)
@SuiteClasses({
//	LoginFailureTest.class,
//	UnauthorizedMovementTest.class,	
//	RemoteLogTest.class,
//	SendTxtMsgTest.class,
//	SendCanMsgTest.class,					// No mulitple buses to test ...just one bus
//	SendMsgFromVehicleTest.class,
//	VehicleButtonsTest.class,
//	ScheduleAdherenceTest.class,			// More info needed for the left side of the panel
//	RouteAdherenceTest.class,				// Same as above 
//	EditLinksTest.class,					
//	EmergenecyEventTest.class,
//	TalkRequestsTest.class,
//	ScheduleViolationsTest.class,
//	OtherMessagesTest.class,
//	EventQueueTest.class,
//	EditCannedMsgTest.class,
//	CannedMsgResponseGroupstest.class,
//	CannedMsgIndividualResponsetest.class,
//	CannedAnnouncementsTest.class,			// Should I test it on the mobile site too?
//	OpportunityAnnouncementTest.class,		
//	ViewCommuRosterListerTest.class,
//	Operations_TableTest.class,				//Check test-matchDetail later
//	OpenEmployeeTest.class,					
	ManageVehiclesTest.class,			
//	RemoteLogOffTest.class
	})
public class AllTests{
	
	public static WebDriver driver1;
	public static String CAD;
	public static String DCC;
	public static String MDTRouteSim;
//	private static final Logger log = Logger.getLogger(AllTests.class.getName());
	@BeforeClass	
	public static void setUpBeforeClass() throws Exception {
		
		MDTRouteSim = System.getProperty("routesim");
		
		DesiredCapabilities iecapability = DesiredCapabilities.internetExplorer();
		iecapability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);		
//		driver1 = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),iecapability);
		AllTests.driver1 = new InternetExplorerDriver(iecapability);
		AllTests.driver1.get("http://localhost:88/App/cad/dispatch/shell/main");		
		AllTests.driver1.manage().window().maximize();
		wait_Element("div.s_company");
		AllTests.CAD = AllTests.driver1.getWindowHandle();
		
		((JavascriptExecutor)AllTests.driver1).executeScript("window.open()");
		Set<String> windowHandles = AllTests.driver1.getWindowHandles();
		Assert.assertEquals(2, windowHandles.size());
		Iterator<String> iterator = windowHandles.iterator();
		while(iterator.hasNext())
		{
			String handle = iterator.next();
			if(!handle.equals(AllTests.CAD))
			{
				AllTests.DCC = handle;
			}
		}
		
	    Thread.sleep(500);
	    driver1.switchTo().window(AllTests.DCC);
		AllTests.driver1.manage().window().maximize();
		AllTests.driver1.get("http://localhost:88/App/cad/dcc2006/apta2006_main");
		wait_Element("p.vehicleDesc");
		
	    AllTests.switchToCAD();	    
 	}
	
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		AllTests.driver1.quit();
		if (RemoteLogTest.pp==null){
			
		}
		else{
		RemoteLogTest.pp.destroy();}
	}
	
	public static void switchToDCC() {
		driver1.switchTo().window(AllTests.DCC);
		((JavascriptExecutor)AllTests.driver1).executeScript("window.focus()");
		
	}
	
	public static void switchToCAD() {
		driver1.switchTo().window(AllTests.CAD);
		((JavascriptExecutor)AllTests.driver1).executeScript("window.focus()");
		wait_Element("div.s_company");
	}
	
	public static void wait_Element(final String ele){
		new WebDriverWait(driver1, 10).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver input) {
				return input.findElement(By.cssSelector(ele));
			}
		});
	}
	
	public static void click(final String ele){
		wait_Element(ele);
		WebElement a = driver1.findElement(By.cssSelector(ele));		
		if (a.isDisplayed()){
			driver1.findElement(By.cssSelector(ele)).click();
		}
		else{
			((JavascriptExecutor)driver1).executeScript("arguments[0].click()", a);
		}
	}
	
	public static void open_link(final String ele){
		if (!driver1.findElement(By.className("app_caption")).getText().equals("My Tasks")){
			click("td.s_command:contains('My Tasks')");
		}
		try{
			click(ele);
		}catch (NoSuchElementException e){
			click("div.app_menu_cmd");
			driver1.findElement(By.cssSelector("div[class=\"droplist\"][value=\"-1\"]")).click();
			click(ele);
			click("button.cmd:contains('Add'):nth-child(7)");			
			click("button.cmd:contains('Apply')");
			while (!driver1.findElement(By.cssSelector("div.app_caption")).getText().equalsIgnoreCase("My Tasks")){
				click("div[class=\"app_menu_close\"]");
			}
			click(ele);
		}
		
		if (driver1.findElements(By.cssSelector("form[id=\"frmEventRequest\"] table:last-child tbody tr td:contains('An error has occurred that is preventing your request from completing.')")).size()!=0){
			driver1.findElement(By.cssSelector("form[id=\"frmEventRequest\"] table:last-child button:contains('Abort')")).click();
			fail("An error has occurred that is preventing your request from completing.");
		}
	}
	
	public static void close_link(){
		while (!driver1.findElement(By.cssSelector("div.app_caption")).getText().equalsIgnoreCase("My Tasks")){
			click("div[class=\"app_menu_close\"]");
		}
	}
	
	public static void main(String[] args){	
		DateFormat dateFormat = new SimpleDateFormat();
		Date date = new Date();
		
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss \n");
		date = new Date();		
		
		try{
			BufferedWriter output = new BufferedWriter(new FileWriter(
					System.getProperty("log")));
			output.write(dateFormat.format(date));
			Result result = JUnitCore.runClasses(new Class[] {AllTests.class});
			
			if (result.wasSuccessful()){
				output.write("Run: " + result.getRunCount()+"  Success: "+ result.getRunCount()+"\n");
				output.write("All Tests are successful!\n");
			}
			else{
				output.write("Run: "+result.getRunCount() + "  Success: "+ (result.getRunCount()-result.getFailureCount())+
						"  Failure: "+ result.getFailureCount()+"\n");				
				for (Failure f: result.getFailures()){
					output.write( f.getTrace());
					output.write("-------------------------------------------------------------------");
				}	
			}		
			output.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

