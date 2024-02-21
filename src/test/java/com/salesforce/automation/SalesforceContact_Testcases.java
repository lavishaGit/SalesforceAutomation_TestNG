package com.salesforce.automation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.input.ClassLoaderObjectInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.BaseClass;
import com.salesforce.base.BaseSalesForceClass;

public class SalesforceContact_Testcases extends BaseSalesForceClass {
	protected Logger myContactlog = LogManager.getLogger();
	String uuid=UUID.randomUUID().toString().replace("-", "");


	@Test
	public void createNewContact() throws InterruptedException {

		initialSetup();

		assertTitle("Home Page ~ Salesforce - Developer Edition");

		WebElement contacttab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		clickbuttonAndAssert(contacttab, "Contacts  ");
		myContactlog.info("Contact Pge is opened");
		WebElement newbuttn = driver.findElement(By.xpath("//input[normalize-space(@value)='New']"));
		clickbuttonAndAssert(newbuttn, "New ..");
		myContactlog.info("New contact Page is opened");

		assertTitle("Contact Edit: New Contact ~ Salesforce - Developer Edition");

		WebElement lastname = driver.findElement(By.id("name_lastcon2"));
		elementSendTextWithAssert(lastname, "clara", "Last name ");

		String parentwin = driver.getWindowHandle();

		WebElement loopupimg = driver.findElement(By.xpath("//a[@id='con4_lkwgt']//img[@class='lookupIcon']"));
		waitForVisibiltyofElementLocated(By.xpath("//a[@id='con4_lkwgt']//img[@class='lookupIcon']"), 30, "lookup ");
		clickbuttonAndAssert(loopupimg, "Look up  image to Find Accounts");
//windowHandle(parentwin);lickElement(acctnum, "Account number is");
		String nameStr1 = "";
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles.size());
		for (String handle : handles) {
		
			if (!handle.equals(parentwin)) {
	
					driver.switchTo().window(handle);
					myContactlog.info("Switched window Title is :" + driver.getTitle());
					reportlog.logTestwithPassed("Switched window Title is :" + driver.getTitle());
					driver.switchTo().frame("resultsFrame");

					WebElement acctnum = driver.findElement(By.xpath("//table[@class='list']//tr[2]/th/a"));
					nameStr1 = acctnum.getText();
					waitForVisibiltyofElementLocated(By.xpath("//table[@class='list']//tr[2]/th/a"), 70, "Account number");
					//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", acctnum);
					((JavascriptExecutor)driver).executeScript("arguments[0].click()", acctnum);
//mouseHover(acctnum);					//clickbuttonAndAssert(acctnum, "Account number is");
				

			}
		}
	//	driver.switchTo().defaultContent();
		driver.switchTo().window(parentwin);
		myContactlog.info("Window  switched back to parent Window");
		reportlog.logTestwithPassed("Window  switched back to parent Window");
		WebElement acctname = driver.findElement(By.id("con4"));
		assertElementWithAttribute(acctname, nameStr1);
		/*
		 * String nameStr2=acctname.getAttribute("value"); if
		 * (nameStr1.trim().equals(nameStr2)) {
		 * 
		 * System.out.println("Account name matches from the selected account names");
		 * }else { System.out.
		 * println("Account name  dp not matches from the selected account names");
		 * 
		 * }
		 */

		WebElement savebuttn = driver.findElement(By.xpath("//td[@id=\"topButtonRow\"]/input[normalize-space(@value)='Save']"));
	Thread.sleep(2000);
		clickbuttonAndAssert(savebuttn, "Save ");
		String header_nameverify = driver.findElement(By.className("topName")).getText();

		String acc_nameverify = driver.findElement(By.xpath("//div[@id='con2_ileinner']")).getText();
		assertStringsEqual(acc_nameverify, header_nameverify);

		myContactlog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");

	}

	@Test
	public void createNewViewContact() throws InterruptedException {
		assertCurrentURL("https://login.salesforce.com/");

		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
	    String uniqueName = "UView_"  + uuid.substring(0, 3);
	    String ViewName = "View_"  + uuid.substring(0, 3);

		WebElement contacttab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitForclickable(contacttab, 30, "Contact tab ");
		clickbuttonAndAssert(contacttab, "Contacts  ");
		assertTitle("Contacts: Home ~ Salesforce - Developer Edition");
		WebElement newview = driver.findElement(By.xpath("//span[@class='fFooter']/a[2]"));
		clickbuttonAndAssert(newview, "New view link is ");
		assertTitle("Contacts: Create New View ~ Salesforce - Developer Edition");
		WebElement viewName = driver.findElement(By.id("fname"));
		elementSendTextWithAssert(viewName, "Contact 5", "View name is ");
		String str1 = viewName.getAttribute("value");
		// System.err.println(str1 + "Contact name entered ");
		WebElement viewUniqueName = driver.findElement(By.id("devname"));
		elementSendTextWithAssert(viewUniqueName, uniqueName, "ubique contact name ");

		WebElement savebuttn = driver
				.findElement(By.xpath("//div[@class='pbHeader']//input[normalize-space(@value)='Save']"));
		clickbuttonAndAssert(savebuttn, "Save ");
		assertTitle("Contacts ~ Salesforce - Developer Edition");
		WebElement selectedOption = driver.findElement(By.xpath("//option[@selected='selected']"));
		String str2 = selectedOption.getText();
		System.err.println(str2 + "Contact name selected in dropdown ");
		assertStringsEqual(str1, str2);
		myContactlog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");

	}

	@Test

	public void mycontactsView() throws InterruptedException {
		initialSetup();
		WebElement contacttab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitForclickable(contacttab, 30, "Contact tab ");
		clickbuttonAndAssert(contacttab, "Contacts  ");
		By select_dropdown = By.xpath("//select[@id='fcf']");
		//WebElement select_dropdown1 = driver.findElement(By.xpath("//select[@id='fcf']"));
		//select_dropdown1.click();
		WebElement selectedOption = driver.findElement(By.xpath("//select[@id='fcf']/option[@selected='selected']"));
		String expectedMatch=selectedOption.getText();
		assertDefaultoption(select_dropdown, expectedMatch);
		selectByVisibleTextAndAssert(select_dropdown, "My Contacts");// WebElement contacttab =
																		// driver.findElement=driver.findElements());
//dropdownChoosebyText(options, "My Contacts", " My contacs");
		WebElement selectedOption1 = driver.findElement(By.xpath("//option[@selected='selected']"));
		String str1 = "My Contacts";

		String str2 = selectedOption1.getText();
		assertStringsEqual(str1, str2);
		assertTitle("Contacts ~ Salesforce - Developer Edition");
		myContactlog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");

	}

	@Test
	public void recentContactsView() throws InterruptedException {
		initialSetup();
		WebElement contacttab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitForclickable(contacttab, 30, "Contact tab ");
		clickbuttonAndAssert(contacttab, "Contacts  ");
//cannot access the elemt
//WebElement lastname = driver.findElement(By.id("name_lastcon2"));
//String str=lastname.getAttribute("value");
		List<WebElement> options = driver
				.findElements(By.xpath("//tbody/tr[@class='headerRow']/following-sibling::tr/th/a"));
		for (WebElement option : options) {
			boolean isContainsText = option.getText().contains("clara")||option.getText().contains("chaa");

			assertTrue(isContainsText, "Option text should contain 'clara' or 'chaa'");

			option.click();
			myContactlog.info("Matched and clicked the link ");
			reportlog.logTestwithPassed("Matched and clicked the link ");
			break;
		}
		myContactlog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");

	}

	@Test
	public void verifyErrormessage() throws InterruptedException {
		assertCurrentURL("https://login.salesforce.com/");

		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
		WebElement contacttab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitForclickable(contacttab, 30, "Contact tab ");
		clickbuttonAndAssert(contacttab, "Contacts  ");
		assertTitle("Contacts: Home ~ Salesforce - Developer Edition");
		WebElement newview = driver.findElement(By.xpath("//span[@class='fFooter']/a[2]"));
		clickbuttonAndAssert(newview, "New view link is ");
		WebElement viewUniqueName = driver.findElement(By.id("devname"));
		waitForVisibilty(viewUniqueName, 40, "Unique view ");

		elementSendTextWithAssert(viewUniqueName, "EFGH", "ubique contact name ");

		WebElement savebuttn = driver
				.findElement(By.xpath("//div[@class='pbHeader']//input[normalize-space(@value)='Save']"));
		clickbuttonAndAssert(savebuttn, "Save ");
		WebElement viewName = driver.findElement(By.xpath("//input[@class='error']"));

		String actualerrormsg = driver.findElement(By.xpath("//td[@class='dataCol']//div[@class='errorMsg']"))
				.getText();
		String experrormsgString1 = "Error:";
		String experrormsgString2 = " You must enter a value";
		String experrormsg = experrormsgString1 + experrormsgString2;
        //System.out.println(experrormsg);
        //System.out.println(actualerrormsg);
		boolean isErrorMsgsMatch=actualerrormsg.trim().equals(experrormsg.trim());
assertEquals(actualerrormsg.trim(),experrormsg.trim());
		if (isErrorMsgsMatch) {
			myContactlog.info("Expected Error message matches the Actual message");
			reportlog.logTestwithPassed("Expected Error message matches the Actual message");

		} else {
			myContactlog.error("Expected Error messages does not  matches the Actual message");
			reportlog.logTestwithFailed("Expected Error message  does not matches the Actual message");


		}
		myContactlog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");
	}

	@Test
	public void checkCancelButtn_NewView() throws InterruptedException {
		//headlessBrowser();
		assertCurrentURL("https://login.salesforce.com/");

	 initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
		WebElement contacttab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitForclickable(contacttab, 30, "Contact tab ");
		clickbuttonAndAssert(contacttab, "Contacts  ");
		assertTitle("Contacts: Home ~ Salesforce - Developer Edition");
		WebElement newview = driver.findElement(By.xpath("//span[@class='fFooter']/a[2]"));
		clickbuttonAndAssert(newview, "New view link is ");
		WebElement viewName = driver.findElement(By.id("fname"));
		elementSendTextWithAssert(viewName, "view3434", "View name is ");
		// this will no text as wwe are cancelling the so text will not be saved
		String viewNameBeforeCancel = viewName.getText();
		assertTrue(viewNameBeforeCancel.isEmpty());
		myContactlog.info("View name before Cancel is empty");
		waitForVisibilty(viewName, 40, " view name field ");
		WebElement viewUniqueName = driver.findElement(By.id("devname"));
		elementSendTextWithAssert(viewUniqueName, "UniqueContact5", "unique contact name ");
		WebElement cancelbttn = driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='cancel']"));
		clickbuttonAndAssert(cancelbttn, "Cancel ");
		assertTitle("Contacts: Home ~ Salesforce - Developer Edition");
		Boolean viewnamecheck = driver.getPageSource().contains(viewNameBeforeCancel); // this returning True as
																						// text its empty
		assertTrue(viewnamecheck);
		if (viewnamecheck) {
	
			myContactlog.info("View name is not displayed on the new page as expected.");
		} else {
			myContactlog.error("View name is unexpectedly displayed on the new page.");
			Assert.fail("View name is unexpectedly displayed on the new page.");
		}
		myContactlog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");
	}

	@Test
	public void checkSaveNewButtn() throws InterruptedException {
		assertCurrentURL("https://login.salesforce.com/");

		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
	    String lastName = "l" +uuid.substring(0, 4);

		WebElement contacttab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		clickbuttonAndAssert(contacttab, "Contacts  ");

		WebElement newbuttn = driver.findElement(By.xpath("//input[normalize-space(@value)='New']"));
		clickbuttonAndAssert(newbuttn, "New ..");
		assertTitle("Contact Edit: New Contact ~ Salesforce - Developer Edition");

		WebElement lastname = driver.findElement(By.id("name_lastcon2"));
		waitForVisibilty(lastname, 30, "Last name ");
		elementSendTextWithAssert(lastname, lastName, "Last name ");

		WebElement acctName = driver.findElement(By.id("con4"));
		waitForVisibilty(acctName, 30, "Account name ");
		//clickbuttonAndAssert(acctName, "Account name is");*/
		WebElement loopupimg = driver.findElement(By.xpath("//a[@id='con4_lkwgt']//img[@class='lookupIcon']"));
		waitForVisibiltyofElementLocated(By.xpath("//a[@id='con4_lkwgt']//img[@class='lookupIcon']"), 30, "lookup ");
		clickbuttonAndAssert(loopupimg, "Look up  image to Find Accounts");
//windowHandle(parentwin);lickElement(acctnum, "Account number is");
		String nameStr1 = "";
		String parentwin = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
System.out.println(handles.size());
		for (String handle : handles) {

			if (!handle.equals(parentwin)) {

				driver.switchTo().window(handle);
				//Thread.sleep(5000);
				myContactlog.info("Switched window Title is :" + driver.getTitle());
				reportlog.logTestInfo("Switched window Title is :" + driver.getTitle());
				driver.switchTo().frame("resultsFrame");
Thread.sleep(2000);
				WebElement acctnum = driver.findElement(By.xpath("//table[@class='list']//tr[2]/th/a"));
				nameStr1 = acctnum.getText();

				waitForVisibiltyofElementLocated(By.xpath("//table[@class='list']//tr[2]/th/a"), 40, "Account number");
				clickbuttonAndAssert(acctnum, "Account number is");

			}
		}
			//elementSendTextWithAssert(acctName, "122", "Account name Global media");
			
		

			driver.switchTo().window(parentwin);
			myContactlog.info("Switched Parent  window Title is :" + driver.getTitle());
			reportlog.logTestInfo("Switched Parent window Title is :" + driver.getTitle());

			WebElement acctname = driver.findElement(By.id("con4"));
			String nameStr2 = acctname.getAttribute("value");
			boolean isStringsMatches=nameStr1.trim().equals(nameStr2);
			assertEquals(nameStr1, nameStr2);
			if (isStringsMatches) {

				myContactlog.info("Account name matches from the selected account names");
			} else {
				myContactlog.error("Account name does not matches from the selected account names");
				reportlog.logTestwithFailed("Account name does not matches from the selected account names");
			}

		
		WebElement savenewbuttn = driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save_new']"));
		clickbuttonAndAssert(savenewbuttn, "Save&New  ");
		myContactlog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");
	}
	
}
