package com.salesforce.automation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import com.salesforce.automation.SalesforceLogin_Testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.salesforce.base.BaseSalesForceClass;
@Listeners(com.salesforce.utility.SalesforceListenerUtility.class)

public class SalesforceLeads_Testcases extends BaseSalesForceClass {
	protected Logger leadslog=LogManager.getLogger();

	@Test
	public void Leadstablink() throws InterruptedException {
		assertCurrentURL("https://login.salesforce.com/");
		leadslog.info("Current URL assertion passed");
		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
		leadslog.info("Title assertion passed");

		WebElement leads_field = driver.findElement(By.xpath("//a[@title='Leads Tab']"));

		// Validate navigate to Leads Home page
		clickbuttonAndAssert(leads_field, "Leads");
		// WebElement useraccount =
		// driver.findElement(By.xpath("//*[@id='userNavLabel']"));
		// WebElement logout = driver.findElement(By.xpath("//a[@title='Logout']"));
		assertTitle("Leads: Home ~ Salesforce - Developer Edition");
		leadslog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");;

	}
	@Test

	public void leadsSelectView() throws InterruptedException {
		Leadstablink();
	By dropdown= By.xpath("//select[@id='fcf']");
	Set<String> expectedList=new HashSet();
	expectedList.add("All Open Leads");
	expectedList.add("My Unread Leads");
	expectedList.add("Recently Viewed Leads");
	expectedList.add("View - Custom 1");
	expectedList.add("View - Custom 2");
	expectedList.add("Today's Leads");
	

getAllOptionsAndAssert(dropdown,expectedList);
leadslog.info("Dropdown all contents matches Assertion is passed.in ");
reportlog.logTestwithPassed("Dropdown all contents matches Assertion is passed.in ");
leadslog.info("All assertion passed");
reportlog.logTestInfo("All assertion passed");;
		
	}
	
	public void logout() throws InterruptedException {
		WebElement useraccount = driver.findElement(By.xpath("//*[@id='userNavLabel']"));
		WebElement logout = driver.findElement(By.xpath("//a[@title='Logout']"));
		logoutAccount(useraccount, logout);

	}
	@Test
	public void defaultView() throws InterruptedException {
	
		Leadstablink();
		By dropdown = By.xpath("//select[@id='fcf']");
		// Resuable function in BaseSalesForceclass
		assertDefaultoption(dropdown, "Today's Leads");//Today's Leads
		//Thread.sleep(3000);
		
		// Resuable function in BaseSalesForceclass
		selectByVisibleTextAndAssert(dropdown, "My Unread Leads");
		///Thread.sleep(3000);
		/*
		 * select.selectByVisibleTextAndAssert("My Unread Leads"); WebElement
		 * actualCurrentSelected= select.getFirstSelectedOption();
		 * System.out.println("Select value is: " +select.getFirstSelectedOption());
		 * String expString="My Unread Leads";
		 */
		logout();
		driver.navigate().to("https://login.salesforce.com/");
		//Leadstablink();
		initialSetup();
		Thread.sleep(3000);
		assertTitle("Home Page ~ Salesforce - Developer Edition");
		leadslog.info("Title assertion passed");

		WebElement leads_field = driver.findElement(By.xpath("//a[@title='Leads Tab']"));

		// Validate navigate to Leads Home page
		clickbuttonAndAssert(leads_field, "Leads");
		// WebElement useraccount =
		// driver.findElement(By.xpath("//*[@id='userNavLabel']"));
		// WebElement logout = driver.findElement(By.xpath("//a[@title='Logout']"));
		assertTitle("Leads: Home ~ Salesforce - Developer Edition");
		
		// Resuable function in BaseSalesForceclass
		assertDefaultoption(dropdown, "My Unread Leads");

		WebElement gobutton = driver.findElement(By.xpath("//input[normalize-space(@value)='Go!']"));
		clickbuttonAndAssert(gobutton, "Go");

		// Resuable function in BaseSalesForceclass
		 dropdown = By.xpath("//select[@class='title']");

		assertDefaultoption(dropdown, "My Unread Leads");
		leadslog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");;
		// reusing logout from account and close function given in this class
	
	
	}
	@Test
	public void verifyListitemTodaysLeads() throws InterruptedException {
		Leadstablink();
		Thread.sleep(4000);
		By dropdown = By.xpath("//select[@id='fcf']");
		selectByVisibleTextAndAssert(dropdown, "Today's Leads");
		
Thread.sleep(2000);
		assertDefaultoption(dropdown, "Today's Leads");
		leadslog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");;

	}
	@Test
	public void verifyNewButtonOnLeads() throws InterruptedException {
		try {

			Leadstablink();
		} catch (Exception e) {
			e.getMessage();
		}
		String uuid=UUID.randomUUID().toString().replace("-", "");
		String firstName="ld_"+uuid.substring(0,3);
		String lastName=uuid.substring(0,4);
		String companyName="Comp_"+uuid.substring(0,4);

		WebElement newbuttn = driver.findElement(By.xpath("//input[normalize-space(@value)='New']"));
		clickbuttonAndAssert(newbuttn, "New ..");
		assertCurrentURL("https://dan000000cz3teag-dev-ed.develop.my.salesforce.com/00Q/e?retURL=%2F00Q%2Fo");
		WebElement firstname = driver.findElement(By.xpath("//*[@id='name_firstlea2']"));
		WebElement lastname = driver.findElement(By.xpath("//*[@id='name_lastlea2']"));
		WebElement company = driver.findElement(By.xpath("//*[@id='lea3']"));
		try {
			if (firstname != null && firstname.isDisplayed() && lastname != null && lastname.isDisplayed()
					&& company != null && company.isDisplayed()) {
				System.out.println("All required fields is not null and its is displaying");

			}

		} catch (Exception e) {
			WebElement error = driver.findElement(By.xpath("//div[@id='errorDiv_ep']"));
			System.out.println(error.getText());
		}

		elementSendTextWithAssert(firstname, firstName, "laed first name  ");
		elementSendTextWithAssert(lastname, lastName, " lead lastname  ");
		elementSendTextWithAssert(company, companyName, "lead Company data ");
		Thread.sleep(2000);
		WebElement savebuttn = driver.findElement(By.xpath("//input[normalize-space(@value)='Save']"));
		clickbuttonAndAssert(savebuttn, "Save ");
		Thread.sleep(1000);
		WebElement header = driver.findElement(By.xpath("//h2[@class='topName']"));
		
		String ExpTxt= lastName;

		String actTxt = header.getText();
	
		assertStringsEqual(actTxt , ExpTxt);
		leadslog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");;
	}
	// *[@id="name_lastlea2"]

	

}
