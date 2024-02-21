package com.salesforce.automation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.input.SwappedDataInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.dockerjava.api.command.SaveImageCmd;
import com.salesforce.base.BaseSalesForceClass;
@Listeners(com.salesforce.utility.SalesforceListenerUtility.class)

public class SalesforceRandomScenarios_Testcases extends BaseSalesForceClass {
	protected Logger randomscenarioslog=LogManager.getLogger();
	String othertxt = null;
	@Test
	public void verifyAccountHolder() {
		assertCurrentURL("https://login.salesforce.com/");
	    randomscenarioslog.info("Current URL assertion passed");

		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
	    randomscenarioslog.info("Title assertion passed");

		WebElement hometab = driver.findElement(By.id("home_Tab"));
		clickbuttonAndAssert(hometab, " Home tab  ");
	    randomscenarioslog.info("Clicked on Home tab");

		WebElement username = driver.findElement(By.xpath("//span[@class='pageType']//a"));
		String usernameLink = username.getText();
		clickbuttonAndAssert(username, " User name   ");
	    randomscenarioslog.info("Clicked on username link");

		WebElement braedcrumbUsername = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
		String usernamebreadcrumbtxt = braedcrumbUsername.getText();
		clickbuttonAndAssert(braedcrumbUsername, " User name sees in breadcrumb  ");
		assertStringsEqual(usernameLink, usernamebreadcrumbtxt);
	    randomscenarioslog.info("Clicked on breadcrumb username link");

		String currentURL1 = driver.getCurrentUrl();
		WebElement userAccountbttn = driver.findElement(By.id("userNavLabel"));
		waitForVisibilty(userAccountbttn, 40, "User account menu");
		clickbuttonAndAssert(userAccountbttn, "user account ");
	    randomscenarioslog.info("Clicked on UserAccount");

		WebElement myProfile = driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a"));
		waitForVisibilty(myProfile, 40, "User account menu");
		clickbuttonAndAssert(myProfile, "user menu ");
	    randomscenarioslog.info("Clicked on User Menu");

		String currentURL2 = driver.getCurrentUrl();
		assertStringsEqual(currentURL1, currentURL2);
		

	}
	@Test
	public void verifylastnameEdit_Variousplace() {
		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
	    randomscenarioslog.info("Title assertion passed");

		WebElement hometab = driver.findElement(By.id("home_Tab"));
		clickbuttonAndAssert(hometab, " Home tab  ");
	    randomscenarioslog.info("Clicked on Home tab");
	    
		WebElement username = driver.findElement(By.xpath("//span[@class='pageType']//a"));
		String usernameLink = username.getText();
		clickbuttonAndAssert(username, " User name   ");
	    randomscenarioslog.info("Clicked on username link");

		WebElement editBttn = driver
				.findElement(By.xpath("//a[contains(@class, 'contactInfoLaunch') and contains(@class, 'editLink')]"));
		waitForVisibilty(editBttn, 70, "Edit pen link");
		clickbuttonAndAssert(editBttn, "Edit pen link  ");
		String parentwin = driver.getWindowHandle();
		System.out.println(parentwin);
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			System.out.println(handles);

			if (handle.equals(parentwin)) {

				System.out.println(handle);
				driver.switchTo().window(handle);

			}

		}
		driver.switchTo().frame("contactInfoContentId");
		WebElement emaifield = driver.findElement(By.name("email"));
		emaifield.click();
	    randomscenarioslog.info("Clicked on Email link");

assertEquals(emaifield, driver.switchTo().activeElement());
// eliminates redundancy and makes the code more efficient.
boolean isFocusOnEmail = emaifield.equals(driver.switchTo().activeElement());

if (isFocusOnEmail) {
    randomscenarioslog.info("Focus is on the email field.");
    reportlog.logTestwithPassed("Focus is on the First namefield.");

} else {
    randomscenarioslog.error("Focus is NOT on the email field.");
	reportlog.logTestwithFailed("Focus  is not on  First namefield.");

} // input[@id='firstName']*/
		WebElement aboutTab = driver.findElement(By.xpath("//li[@id='aboutTab']/a[@href]"));
	
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", aboutTab);
	    randomscenarioslog.info("Clicked on AboutTab Link.");

		// waitForVisibilty( aboutTab, 40, "About tab " );

		// clickbuttonAndAssert( aboutTab, "About tab ");
		WebElement firstName = driver.findElement(By.id("firstName"));
		waitForVisibilty(firstName, 40, "first Name field ");
	    
		firstName.click();
		randomscenarioslog.info("Clicked on First Name Link.");
        reportlog.logTestInfo("Clicked on First Name Link.");
        boolean isFocusOnFirstName = firstName.equals(driver.switchTo().activeElement());
assertEquals(firstName,driver.switchTo().activeElement());
        if (isFocusOnFirstName ) {
			randomscenarioslog.info("Focus is on the First namefield.");
			reportlog.logTestwithPassed("Focus is on the First namefield.");

		} else {
			randomscenarioslog.error("Focus is not on  First namefield.");
			reportlog.logTestwithFailed("Focus  is not on  First namefield.");
		}
		
		String nametxt = firstName.getAttribute("value");
		WebElement lastName = driver.findElement(By.id("lastName"));
		waitForVisibilty(lastName, 40, "last Name field ");
		elementSendTextWithAssert(lastName, "Gallo123", "last name is ");
		
		String lastNametxt = lastName.getText();
		WebElement saveBttn = driver.findElement(By.xpath("//input[@value='Save All']"));
		// waitForVisibilty( aboutTab, 40, "About tab " );
		clickbuttonAndAssert(saveBttn, "Save All  ");
		driver.switchTo().defaultContent();
		// this code below giving stale exception
		/*
		 * if(lastName.isDisplayed()) { System.out.println("Edit window is still open");
		 * }else { System.out.println("Edit window is closed");
		 * 
		 * }
		 */

		WebElement topleftheader = driver
				.findElement(By.xpath("//div[@class='chatterBreadcrumbs']//span[@id='tailBreadcrumbNode']"));
		String leftheadertxt = topleftheader.getText();
		boolean isContainsLastName=leftheadertxt.contains(lastNametxt);
assertTrue(leftheadertxt.contains(lastNametxt));
		if (isContainsLastName) {
			randomscenarioslog.info("Page Header shows the updated Last Name, at the top left hand side of the page");
			reportlog.logTestwithPassed("Page Header shows the updated Last Name, at the top left hand side of the page");

		} else {
			randomscenarioslog.error("Page Header shows the updated Last Name, at the top left hand side of the page");
			reportlog.logTestwithFailed("Page Header shows the updated Last Name, at the top left hand side of the page");

		}

		WebElement topRightheader = driver
				.findElement(By.xpath("//div[@id='userNavButton']//span[@class='menuButtonLabel']"));
		String Rightheadertxt = getText(topleftheader);
		boolean isContainslastname=Rightheadertxt.contains(lastNametxt);
		assertTrue(Rightheadertxt.contains(lastNametxt));
		if (isContainslastname) {
			randomscenarioslog.info("menu button shows the updated Last Name, at the top right hand side of the page");
			reportlog.logTestwithPassed("menu button shows the updated Last Name, at the top right hand side of the page");
		} else {
			randomscenarioslog.error("menu button  do not shows the updated Last Name, at the top right hand side of the page");
			reportlog.logTestwithFailed("menu button  do not shows the updated Last Name, at the top right hand side of the page");
		}
	

	}
	@Test
	public void verifytabcustomization() throws InterruptedException {

		initialSetup();
		WebElement add_tab = driver.findElement(By.xpath("//li[@id='AllTab_Tab']/a[@href]"));
		clickbuttonAndAssert(add_tab, "Add tab link ");
		assertTitle("All Tabs ~ Salesforce - Developer Edition");
		WebElement customize = driver.findElement(By.xpath("//input[@value='Customize My Tabs']"));
		clickbuttonAndAssert(customize, "Customize my tab ");
		assertTitle("Customize My Tabs ~ Salesforce - Developer Edition");
		WebElement availabledropdown = driver.findElement(By.id("duel_select_0"));
		Select select1 = new Select(availabledropdown);
		/*
		 * select1.selectByValueAndAssert("Campaign"); WebElement add_arrow =
		 * driver.findElement(By.className("rightArrowIcon")); clickbuttonAndAssert(add_arrow,
		 * " left Arrow "); WebElement savebttn =
		 * driver.findElement(By.xpath("//input[@value=\" Save \"]"));
		 * 
		 * clickbuttonAndAssert(savebttn, "Save ");
		 */
		List<WebElement> alloptions = select1.getOptions();
		for (WebElement option : alloptions) {
			// System.out.println(option.getText());
//Thread.sleep(6000);
boolean isCampaignsExists=option.getText().trim().startsWith("Campaigns");

			if (isCampaignsExists) {
				assertTrue(isCampaignsExists);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
				randomscenarioslog.info("Campaign option is present in Available tab");
				reportlog.logTestwithPassed("Campaign option is present in Available tab");
				
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
				option.click();
				randomscenarioslog.info(" Campaign  option is clicked");
				reportlog.logTestwithPassed(" Campaign  option is clicked");

				
				WebElement add_arrow = driver.findElement(By.className("rightArrowIcon"));
				clickbuttonAndAssert(add_arrow, " left Arrow ");
				
				WebElement savebttn = driver.findElement(By.xpath("//input[@value=\" Save \"]"));
				clickbuttonAndAssert(savebttn, "Save ");
				break;

			} else {
				randomscenarioslog.error(" Campaign option is  not present in Available tab");
				// break;
			}
		}
		WebElement selectedtabs_dropdown = driver.findElement(By.id("duel_select_1"));
		waitForVisibility(selectedtabs_dropdown, 40,2, "Selected tab ");
		Select select = new Select(selectedtabs_dropdown);
		// String actualstr = select.getFirstSelectedOption().getText();
		select.selectByValue("Campaign");

		WebElement remove_arrow = driver.findElement(By.className("leftArrowIcon"));
		clickbuttonAndAssert(remove_arrow, " left Arrow ");

		WebElement savebttn = driver.findElement(By.xpath("//input[@value=\" Save \"]"));
		waitForVisibilty(savebttn, 30, "Save button button");
		clickbuttonAndAssert(savebttn, "Save ");
		Thread.sleep(1000);

		logout();
		driver.navigate().to("https://login.salesforce.com/");

		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		WebElement email_field = driver.findElement(By.id("username"));
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(email_field));
		elementSendTextWithAssert(email_field, "sweety123@yahoo.com", "Username");

		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
		elementSendTextWithAssert(password, "Lavisha_2212", "Password");

		WebElement loginButton = driver.findElement(By.id("Login"));
		clickbuttonAndAssert(loginButton, "login  ");
		List<WebElement> tabBar = driver.findElements(By.xpath("/ul[@id='tabBar']//li"));
		for (WebElement bar : tabBar) {
			assertTrue(bar.getText().equals("Campaigns"));
			if (bar.getText().equals("Campaigns")) {

				randomscenarioslog.info("Campaign option is present in Available tab");
				reportlog.logTestwithPassed("Campaign option is present in Available tab");
			} else {
				randomscenarioslog.error(" Campaign option is not  present in Tab Bar");

			}
		}
	}
	@Test
	public void blockingAnEventCalender() throws InterruptedException {
		assertCurrentURL("https://login.salesforce.com/");

		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");

		WebElement hometab = driver.findElement(By.id("home_Tab"));
		waitForVisibilty(hometab, 70, "hometab ");
		clickbuttonAndAssert(hometab, " Home tab  ");
	    randomscenarioslog.info("Clicked on Home tab");

		WebElement datelinkb = driver.findElement(By.xpath("//div[@class='content']//span/a"));
		waitForVisibilty(datelinkb, 30, "Dtae time ");
		clickbuttonAndAssert(datelinkb, "DateTime link");
	    randomscenarioslog.info("Clicked on DateTime link");
		String expectedTitle ="Calendar for Sudeepa Gallo";
		String actualTitle =driver.getTitle();
assertTrue(actualTitle.contains(expectedTitle),"Title is not matching ");
		//

		WebElement headertitle = driver.findElement(By.xpath("//div[@class='content']/h1"));
		String headertxtString = headertitle.getText();
		String exptxtString = "Calendar for Sudeepa Gallo\\d* - Day View"; // Regular expression allowing for any number of digits after "Gallo"
		assertTrue(headertxtString.matches(exptxtString));
		List<WebElement> timeTable = driver.findElements(By.xpath("//td[@class='fixedTable']//div/a"));
		for (WebElement time : timeTable) {
			randomscenarioslog.info(time.getText());
			if (time.getText().contains("8:00 PM")) {
			    assertTrue(true);
			    randomscenarioslog.info( "Time text contains '8:00 PM'");
			    reportlog.logTestwithPassed("Time text contains '8:00 PM'");
			    waitForclickable(time, 30, "Time link ");
			    time.click();
			    break;
			    // Your method call
			} else {

			    randomscenarioslog.info( "Time text does not contains '8:00 PM'");
			    reportlog.logTestwithFailed("Time text does not contain '8:00 PM'");
			}
		/*	if (time.getText().contains("8:00 PM")) {
				waitForclickable(time, 30, "");
				time.click();
				
				break;

			} else {
				System.out.println(" expected Time  is not  present in Time table ");

			}*/
		}
		String parentwin = driver.getWindowHandle();

		assertTitle("Calendar: New Event ~ Salesforce - Developer Edition");
		try {
			WebElement inputsubject = driver.findElement(By.xpath("//input[@id='evt5']"));
			String subjecttxt = getTextFromElement(inputsubject, "Subject input ");
			// assertStringsEqual(othertxt,subjecttxt);
			 randomscenarioslog.info("subject text" + subjecttxt);
			Thread.sleep(2000);
			WebElement endFeild = driver.findElement(By.id("EndDateTime_time"));
			clickbuttonAndAssert(endFeild, "field is");
			Thread.sleep(2000);

			List<WebElement> times = driver.findElements(By.id("//div[@id='simpleTimePicker']//div"));
			for (WebElement time : times) {
			
				if (time.getText().equals("9:00 PM")) {
					assertTrue(true);
					randomscenarioslog.info( "Time text contains '9:00 PM'");
				    reportlog.logTestwithPassed("Time text contains '9:00 PM'");
					waitForclickable(time, 30, "Time link ");
				    time.click();
				    break;

				} else {
					// assertTrue(false);
					    randomscenarioslog.info( "Time text does not contains '9:00 PM'");
					    reportlog.logTestwithFailed("Time text does not contain '9:00 PM'");
					}
				
			}
		} catch (NoSuchWindowException e) {

			 randomscenarioslog.info("No such window");
			 reportlog.logTestwithFailed("No such window");
			 throw e;
		}

		WebElement subjectIcon = driver.findElement(By.xpath("(//td[@class='dataCol col02'])[2]//a"));
		waitForVisibilty(subjectIcon, 40, "Subject icon is ");
		clickbuttonAndAssert(subjectIcon, "Subject icon");
		Thread.sleep(7000);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		//System.out.println(parentwin);

		Set<String> handles = driver.getWindowHandles();
		System.out.println("Windows size is " + driver.getWindowHandles().size());
	//	System.out.println(handles);
		for (String handle : handles) {
			System.out.println("Window handle: " + handle);

			if (!handle.equals(parentwin)) {
				try {
					driver.switchTo().window(handle);
					String title = driver.getTitle();
					 randomscenarioslog.info("Current window title is  "+title);
					 boolean isTitleEqual=title.equals("ComboBox");
					 assertEquals(title, "ComboBox");
					if (isTitleEqual) {

						WebElement otherslink = driver.findElement(By.xpath("//ul/li[5]/a"));

						waitForVisibilty(otherslink, 30, "Others link ");
						othertxt = getTextFromElement(otherslink, "Others link text ");
						System.out.println(othertxt);
						// Actions action = new Actions(driver);
						// action.moveToElement(otherslink).doubleClick().build().perform();
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", otherslink);
						 randomscenarioslog.info( "Other link is clicked");
					    reportlog.logTestwithPassed( "Other link is clicked");;
						////
//break;
					}
				} catch (NoSuchWindowException e) {
					 randomscenarioslog.info("Window with handle '" + handle + "' is not available.");
					// driver.switchTo().window("this is child handle"+handle);
					 throw e;

				}
			}
		}
		/*
		 * driver.switchTo().defaultContent();
		 * 
		 * Set<String> windowHandles = driver.getWindowHandles();
		 * System.out.println("Window Handles: " + windowHandles);
		 * 
		 * for (String handle : windowHandles) { try { driver.switchTo().window(handle);
		 * // Perform actions on the current window // Example: Print the title of the
		 * current window System.out.println("Current Window Title: " +
		 * driver.getTitle()); } catch (NoSuchWindowException e) { // Handle the case
		 * where the window is not available System.out.println("Window with handle '" +
		 * handle + "' is not available."); // Add logic to handle the situation, such
		 * as refreshing the page or navigating back // Example:
		 * driver.navigate().back(); } }
		 * 
		 */
		driver.switchTo().window(parentwin);
		String title = driver.getTitle();
		System.out.println(title);
		if (!title.equals("ComboBox")) {
			WebElement savebttn = driver.findElement(By.xpath("//input[@value=\" Save \"]"));
			waitForVisibilty(savebttn, 30, "Save button button");

			clickbuttonAndAssert(savebttn, "Save ");
		}
		List<WebElement> contents = driver
				.findElements(By.xpath("//div[@class='multiLineEventBlock dragContentPointer']//a/span"));

		for (WebElement content : contents) {

			String text = content.getText();
			 boolean isTitleEqual=(text.equalsIgnoreCase(othertxt));
assertEquals(text, othertxt);
			if (isTitleEqual) {
				 randomscenarioslog.info("Other' event  matches the Subject combo text");
				    reportlog.logTestwithPassed( "Other' event  matches the Subject combo text");;

			}else {
				randomscenarioslog.info("Other' event does not  matches the Subject combo text");
				 reportlog.logTestwithFailed("Other' event does not  matches the Subject combo text");
			}
		
		}
	}
		@Test
	public void weeklyrecurranceCalender() throws InterruptedException {
			assertCurrentURL("https://login.salesforce.com/");
		    randomscenarioslog.info("Current URL assertion passed");

		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
		WebElement hometab = driver.findElement(By.id("home_Tab"));
		waitForVisibilty(hometab, 70, "hometab ");
		clickbuttonAndAssert(hometab, " Home tab  ");
	    randomscenarioslog.info("Clicked on Home tab");

		WebElement datelinkb = driver.findElement(By.xpath("//div[@class='content']//span/a"));
		waitForVisibilty(datelinkb, 30, "Dtae time ");
		clickbuttonAndAssert(datelinkb, "DateTime link");
	    randomscenarioslog.info("Clicked on DateTime link");

		assertTitle("Calendar for Sudeepa Gallo123 ~ Salesforce - Developer Edition");
		//

		WebElement headertitle = driver.findElement(By.xpath("//div[@class='content']/h1"));
		String headertxtString = headertitle.getText();
		String exptxtString = "Calendar for Sudeepa Gallo123 - Day View";
		assertStringsEqual(headertxtString, exptxtString);
		List<WebElement> timeTable = driver.findElements(By.xpath("//td[@class='fixedTable']//div/a"));
		for (WebElement time : timeTable) {
			//System.out.println(time.getText());
			if (time.getText().equals("4:00 PM")) {
				assertTrue(true);
				randomscenarioslog.info( "Time text contains '4:00 PM'");
			    reportlog.logTestwithPassed("Time text contains '4:00 PM'");
				waitForclickable(time, 50, "Time link ");
			    time.click();
			    break;

			} else {
				
				    randomscenarioslog.info( "Time text does not contains '4:00 PM'");
				    reportlog.logTestwithFailed("Time text does not contain '4:00 PM'");
				}
			
		}
		String parentwin = driver.getWindowHandle();

		assertTitle("Calendar: New Event ~ Salesforce - Developer Edition");
		try {
			WebElement inputsubject = driver.findElement(By.xpath("//input[@id='evt5']"));
			String subjecttxt = getTextFromElement(inputsubject, "Subject input ");
			// assertStringsEqual(othertxt,subjecttxt);
			System.out.println("subject text" + subjecttxt);
			Thread.sleep(2000);
			WebElement endFeild = driver.findElement(By.id("EndDateTime_time"));
			clickbuttonAndAssert(endFeild, "End field is");
			Thread.sleep(2000);

			List<WebElement> times = driver.findElements(By.xpath("//div[@id='simpleTimePicker']//div"));
			for (WebElement time : times) {
				if (time.getText().equals("7:00 PM")) {
					assertTrue(true);
					randomscenarioslog.info( "Time text contains '7:00 PM'");
				    reportlog.logTestwithPassed("Time text contains '7:00 PM'");
					waitForclickable(time, 50, "Time link ");
				    time.click();
				    break;

				} else {
					
					    randomscenarioslog.info( "Time text does not contains '07:00 PMPM'");
					    reportlog.logTestwithFailed("Time text does not contain '7:00 PMPM'");
					}
				
			}
		} catch (NoSuchWindowException e) {

			 randomscenarioslog.info("No such window");
			throw e;
		}

		WebElement subjectIcon = driver.findElement(By.xpath("(//td[@class='dataCol col02'])[2]//a"));
		waitForVisibilty(subjectIcon, 40, "Subject icon is ");
		clickbuttonAndAssert(subjectIcon, "Subject icon");
		Thread.sleep(7000);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		//wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		//System.out.println(parentwin);

		Set<String> handles = driver.getWindowHandles();
		System.out.println("Windows size is " + driver.getWindowHandles().size());
		System.out.println(handles);
		for (String handle : handles) {
			//System.out.println("Window handle: " + handle);

			if (!handle.equals(parentwin)) {
				try {
					driver.switchTo().window(handle);
					String title = driver.getTitle();
					randomscenarioslog.info(title);
					if (title.equals("ComboBox")) {

						WebElement otherslink = driver.findElement(By.xpath("//ul/li[5]/a"));
						waitForVisibilty(otherslink, 30, "Others link ");
						othertxt = getTextFromElement(otherslink, "Others link text ");
						System.out.println(othertxt);
						// Actions action = new Actions(driver);
						// action.moveToElement(otherslink).doubleClick().build().perform();
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", otherslink);
						 randomscenarioslog.info("Other' link is clicked");
						    reportlog.logTestwithPassed("Other' link is clicked");;

						////
//break;
					}
				} catch (NoSuchWindowException e) {
					 randomscenarioslog.info("Window with handle '" + handle + "' is not available.");
					// driver.switchTo().window("this is child handle"+handle);
					throw e;

				}

				
			}
		}

		driver.switchTo().window(parentwin);

		WebElement recurrencechkbox = driver.findElement(By.id("IsRecurrence"));
		waitForVisibilty(recurrencechkbox, 30, "Recurreance checkbox");
		clickbuttonAndAssert(recurrencechkbox, "Checkbox  ");
		WebElement weeklyRadiobtn = driver.findElement(By.id("rectypeftw"));
		clickbuttonAndAssert(weeklyRadiobtn, "Weekly radio");

		List<WebElement> days = driver.findElements(By.xpath("//div[@id='w']//div[2]//input"));
		for (WebElement day : days) {
			if (day.isSelected()) {
				//assertTrue(true);
				 randomscenarioslog.info(day + " is selected ");
				 reportlog.logTestwithPassed(day + " is selected ");
				 break;
			} else {

				 randomscenarioslog.error("No day selected Please select");

			}
		}
		Thread.sleep(5000);
		WebElement endfield = driver.findElement(By.id("RecurrenceEndDateOnly"));
		waitForVisibilty(endfield, 30, "End field");
		;
		;
		clickbuttonAndAssert(endfield, "EndField ");
		LocalDate currentDate = LocalDate.now();
		LocalDate endDate = currentDate.plusWeeks(2);

// Format the date in the required format (e.g., MM/dd/yyyy)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d");

		String formattedEndDate = endDate.format(formatter);
boolean dateFound=false;
//if(!dateFound)
//{
if(!formattedEndDate.contains("0")) {
	//try {
		Thread.sleep(3000);
// Locate and select the end date in the date picker
		WebElement endDatePicker = driver
				.findElement(By.xpath("//div[@class='datePicker']//td[contains(text(), '" + formattedEndDate + "')]"));
randomscenarioslog.info(endDatePicker.getText());
endDatePicker.click();
		//dateFound = true;
		
}else {
///}catch (NoSuchElementException e) {
	Thread.sleep(3000);

	DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("d");
	String formattedEndDate1 = endDate.format(formatter12);
	Thread.sleep(5000);
	WebElement endDatePickerNextMonth = driver
			.findElement(By.xpath("//img[contains(@title, 'Next')]"));
	waitForclickable(endDatePickerNextMonth, 50, "Next Month" );
	Thread.sleep(3000);

	endDatePickerNextMonth.click();

	WebElement endDatePicker = driver.findElement(By.xpath("//div[@class='datePicker']//td[contains(text(), '" + formattedEndDate1 + "')]"));
	waitForclickable(endDatePicker, 50, "date pick" );
	Thread.sleep(3000);

    endDatePicker.click();


	}
		String title = driver.getTitle();
		System.out.println(title);
		if (!title.equals("ComboBox")) {
			WebElement savebttn = driver.findElement(By.xpath("//input[@value=\" Save \"]"));
			waitForVisibilty(savebttn, 30, "Save button button");
			clickbuttonAndAssert(savebttn, "Save ");
		}
		WebElement monthView = driver.findElement(By.className("monthViewIcon"));
		waitForVisibilty(monthView, 40, "Month view");
		clickbuttonAndAssert(monthView, "Month View ");
		WebElement headerPage = driver.findElement(By.xpath("(//h1)[2]"));
		String headertxt = getTextFromElement(headerPage, "Header text");
		String headerExptxt = "Calendar for Sudeepa Gallo123 - Month View";
		assertStringsEqual(headertxt, headerExptxt);
	
	}

	
}
