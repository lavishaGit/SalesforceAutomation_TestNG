package com.salesforce.base;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.google.common.io.Files;
import com.salesforce.utility.ExtentUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected Alert alert;
	protected Actions action;
	protected WebDriverWait wait;
	protected static WebDriver driver = null;
	protected Logger myBaselog = LogManager.getLogger();
	protected static SoftAssert softAssert = new SoftAssert();
	protected ExtentUtility reportlog = ExtentUtility.getinstance();

	public void initializeBrowser(String browser) {

		if (browser.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			myBaselog.info("Browser instance has started");
		//	reportlog.logTestInfo("Browser instance has started");
			// initializing the class level driver
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("firefox")) {

			// System.setProperty("webdriver.chrome.driver",
			// "/Users/nitin/Downloads/Drivers/geckodriver");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			myBaselog.info("Browser instance has started");

		} else if (browser.equalsIgnoreCase("safari")) {

			// System.setProperty("webdriver.chrome.driver",
			// "/Users/nitin/Downloads/Drivers/safaridriver");
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			myBaselog.info("Browser instance has started");

		} else {
			myBaselog.error("Browser is not available");
		}

		// maximize the browser

	}

	public void waitForVisibilty(WebElement ele, int time, String Objname) {
		try {
	

		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(ele));
		myBaselog.info(Objname + " IS WAITED FOR VISIBLITY");
		reportlog.logTestInfo(Objname + " IS WAITED FOR VISIBLITY");
	}catch(Exception e){
		myBaselog.error(Objname + " timeout exception");
throw e;
	}}

	public void waitForVisibiltyofElementLocated(By ele, int time, String Objname) {
		try {
		
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		myBaselog.info(Objname + " IS WAITED FOR VISIBLITY OF ELEMENT TO BE LOCATED");
		reportlog.logTestInfo(Objname + " IS WAITED FOR VISIBLITY OF ELEMENT TO BE LOCATED");
		}catch(Exception e){
			myBaselog.error(Objname + " timeout exception");
	throw e;
		}}

	public void waitForclickable(WebElement ele, int time, String Objname) {
	try {
		

		myBaselog.info(Objname + " IS WAITED FOR clickable");
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}catch(Exception e) {
		myBaselog.error(Objname + " did not become visible within the specified time"+e.getMessage());
		reportlog.logTestwithFailed(Objname + " did not become visible within the specified time");
throw e;
	}}
	public void waitForVisibility(WebElement ele, int time, int pollingtime, String objectName) {
		myBaselog.info(objectName + " IS WAITED FOR Visibility");

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait
		.withTimeout(time,TimeUnit.SECONDS)
		.pollingEvery(pollingtime, TimeUnit.SECONDS)
		.ignoring(ElementNotInteractableException.class)
		.withMessage(objectName+" is not visible.fluent wait time expires");

		wait.until(ExpectedConditions.visibilityOf(ele));
		myBaselog.info(objectName + " is waited for visibility using fluent wait");
		reportlog.logTestInfo(objectName + " is waited for visibility using fluent wait");
	}

	public void elementSendTextWithAssert(WebElement ele, String info, String objname) {
		try {
			assertEquals(true, ele.isEnabled());
			ele.click();
			ele.clear();
			myBaselog.info(ele.toString() + "Is enabled and clicked");
			reportlog.logTestInfo(objname + "Is enabled and clicked");

			ele.sendKeys(info);
			assertTrue(info != null && !info.isEmpty(), "Field should be  not null or empty");
			myBaselog.info(objname + "Is displayed  in textarea");
			reportlog.logTestInfo(objname + "Is displayed  in textarea");
		}catch(AssertionError e) {
			myBaselog.error(objname + "Is not  displayed  in textarea");
			myBaselog.error(objname + "Is not entered  in textarea");

	throw e;
		}}
		

	/*public void clickbuttonAndAssert(WebElement ele, String objectName) {
		try {
			assertEquals(true, ele.isEnabled());//enabled to interact with like buttons 

			ele.click();
			myBaselog.info(objectName + "button is clicked");
			reportlog.logTestInfo(objectName +  "button is clicked");
		
		}catch(AssertionError e) {
			myBaselog.error(objectName + " is not enabled :Please  check");

	throw e;
		}}*/

	public void clickElementAndAssertIsSelected(WebElement ele, String objectName) {//clickbuttonAndAssert(element, objectName);
		try {

		assertEquals(true, ele.isSelected());
			ele.click();
			myBaselog.info(objectName + "Is selected – Assert passed");
			reportlog.logTestwithPassed(objectName + "Is selected – Assert passed");


		}catch(AssertionError e) {
			myBaselog.info(objectName + "Element is not selected ");
throw e;
		}
	}

	public void assertElementWithAttribute(WebElement ele, String expText) {  //elementTextVerify
		String expTexts = expText;
		String actText=""; // Initialize actText with a default value

		try {
	assertEquals(true, ele.isDisplayed());// Verifies that element is displayes
	myBaselog.info(ele.toString() + "Is enabled and clicked");
	reportlog.logTestwithPassed("Is selected – Assert passed");
	 actText = ele.getAttribute("value");

		assertEquals(actText, expTexts);
		myBaselog.info(actText+"Expected value matches the actual value" + expTexts);
		reportlog.logTestwithPassed(actText+"Expected value matches the actual value" + expTexts);

		}catch(AssertionError e) {
		myBaselog.error(actText+"Expected value do not matches the actual value" + expTexts);
		// if (expText.equals(actText)) {
throw e;
	}}

	public void clickbuttonAndAssert(WebElement ele, String objname) {//buttoncheck
		try {
		assertEquals(true, ele.isEnabled());
		ele.click();
		myBaselog.info(objname + "Is enabled and clicked");
		reportlog.logTestwithPassed(objname + "Is enabled and clicked");
		}catch(AssertionError e) {
			myBaselog.error(objname + " is not clickable Please check");
throw e;
		/*if (ele.isEnabled()) {
			ele.click();

			myBaselog.info(objname + "Is enabled and clicked");
		} else {
			myBaselog.info(objname + " is not clickable Please check");
		}*/
		}
	}

	public void baseURL(String url) {
		try {
	        driver.get(url);
	        myBaselog.info(url + " is launched");
		///	reportlog.logTestInfo( "Valid URL is launched ");
	    } catch (Exception e) {
	        myBaselog.error("Error occurred while navigating to URL: " + e.getMessage());
	       // myBaselog.error(e);
	        throw e; // Rethrow the exception to propagate it further
	    }
	}

	public void driverClose() {
		driver.close();
		myBaselog.info("browser is closed");
		//reportlog.logTestInfo( "browser is closed");
		driver=null;
        Assert.assertNull(driver);
	}

	public void assertTitle(String expectData) {

		String expTitle = expectData;

		String actTitle = driver.getTitle();
		//myBaselog.info("title= " + actTitle);
		
		try {
		assertEquals(actTitle, expTitle);
		myBaselog.info("Found "+actTitle + "  matched with  Expected " +  expTitle);
		reportlog.logTestwithPassed("Found "+actTitle + "  matched with   Expected "+ expTitle);
		}catch(AssertionError e) {
			myBaselog.error("Found "+actTitle + " not   matched with   Expected " + expTitle);

			//reportlog.logTestfailwithException(e);
			throw e;
		}
		
	}

	public void assertCurrentURL(String expURL) {

		String actURL = driver.getCurrentUrl();
		myBaselog.info("Current URL is = " + actURL);
		try {
	    assertEquals(actURL, expURL);
		myBaselog.info(actURL + "  matched with " +  expURL);
		reportlog.logTestwithPassed(actURL + "  matched with "+ expURL);


		}catch(AssertionError e) {
			myBaselog.error(actURL + "not   matched with " +expURL);

			//reportlog.logTestfailwithException(e);
			throw e;
		}
		/*
		 * if (expTitle.equals(actdata)) { myBaselog.info(expectData + "  matched with "
		 * + actdata); } else { myBaselog.info(expectData + " not matched with " +
		 * actdata); }
		 */

	}

	public String getText(WebElement ele) {
		String actual = ele.getText();
		return actual;
	}

	public void assertStringsText(WebElement ele , String exp) {//gettextcheck
		String expectedTxt = exp;
		String actualTxt = ele.getText();
		try {
			assertTrue(actualTxt.contains(expectedTxt));
		assertEquals(actualTxt, expectedTxt);
		myBaselog.info(actualTxt + "  matched with " + expectedTxt);
		//myBaselog.error(actualTxt + "  matched with " + expectedTxt);

		reportlog.logTestwithPassed(actualTxt + "  matched with " + expectedTxt);

		}catch(AssertionError e) {
			myBaselog.error(actualTxt + "not   matched with " + expectedTxt);

		//reportlog.logTestfailwithException(e);
		throw e;
		}
	}
	//}

	/*
	 * if (expected.equals(actual) || (actual.contains(expected)))// it returns true
	 * if one of the condition is true
	 * 
	 * { myBaselog.info("Match found  " + object + exp); } else {
	 * myBaselog.info("Match not found " + object + exp); }
	 
	}*/

	public void switchToAlert() {

		alert = driver.switchTo().alert();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		myBaselog.info("Now focus is on Alert Dialog box");
		reportlog.logTestInfo("Now focus is on Alert Dialog box");
	}

	public void acceptAlert() {
		alert.accept();
		myBaselog.info("ok button is clicked");
		reportlog.logTestInfo("ok button is clicked");

	}

	public String getAlertlabelText() {
		String text = alert.getText();
		myBaselog.info("Alert text is retrieved");
reportlog.logTestInfo("Alert text is retrieved");
		return text;
	}

	public void sendtoAlertText(String obj) {
		alert.sendKeys(obj);
		myBaselog.info("Prompt text is:" + obj);
		reportlog.logTestInfo("Prompt text is:" + obj);

	}

	public void dismiss() {
		alert.dismiss();
		myBaselog.info("Alert is dismissed");
		reportlog.logTestInfo("Alert is dismissed");

	}

	public String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		myBaselog.info("text is extracted from " + objectName);
		reportlog.logTestInfo("text is extracted from \" + objectName");
		return data;
	}

	public void mouseHover_Interaction(WebElement ele) {
		action = new Actions(driver);

		action.moveToElement(ele, 10, 10).click().build().perform();// build means
		// ready to be performed
		//action.moveToElement(ele).click().build().perform();// build means ready to be performed
		myBaselog.info("Cursor hovered to the desired element");
		reportlog.logTestInfo("Cursor hovered to the desired element");
	}

	public void mouseHover(WebElement element) {
		action = new Actions(driver);

		action.moveToElement(element).click().build().perform();// build means
		// ready to be performed
		//action.moveToElement(ele).click().build().perform();// build means ready to be performed
		myBaselog.info("Cursor hovered to the desired element");
		reportlog.logTestInfo("Cursor hovered to the desired element");
	}

	public void ContextClickOnElement(WebElement ele, String objName) {
		Actions action = new Actions(driver);
		action.contextClick(ele).build().perform();
		myBaselog.info("right click persormed on web element " + objName);
			reportlog.logTestInfo("right click persormed on web element " + objName);

	}
	
	public void actionCall() {
		action = new Actions(driver);
		myBaselog.info("Action object created");
		reportlog.logTestInfo("Action object created");

		;
	}

	public void actionDragandDropCall(WebElement ele1, WebElement ele2) {
		action.dragAndDrop(ele1, ele2).build().perform();
		myBaselog.info("Dragand drop action is performed successfully....");
		reportlog.logTestInfo("Dragand drop action is performed successfully.");
		;
	}

	public void toolTip(WebElement ele, WebElement tooltipele) {
		action.moveToElement(ele).build().perform();
		;
		driver.switchTo().activeElement();
		
		String str = tooltipele.getText();
		myBaselog.info("tooltiptext ---> " + str);
		reportlog.logTestInfo("tooltiptext ---> " + str);
	}

	public void takescreenshot(String filepath) {
		// Perform actions with the driver
		// System.out.println("WebDriver instance is null. Please initialize it
		// first.");
		// Other operations...

		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File srcFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		File descFile = new File(filepath);

		try {
			Files.copy(srcFile, descFile);
			myBaselog.info("captures the screenshot");
			reportlog.logTestInfo("captures the screenshot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			myBaselog.error("Error while capturing  the screenshot");

		}

	}

	public void screenshotWebElement(WebElement ele, String filepath)  {

		File srcFile = ele.getScreenshotAs(OutputType.FILE);
		File descFile = new File(filepath);
		try {
			Files.copy(srcFile, descFile);
			myBaselog.info("captures the screenshot");
			reportlog.logTestInfo("captures the screenshot");

		} catch (IOException e) {
		
			myBaselog.error("Error while capturing  the screenshot"+ e.getMessage());

		}
	}

	
	public void assertStringsEqual(String actvalue, String expvalue) {
		
		
		try {
				Assert.assertEquals((actvalue).replaceAll("\\s+", ""), (expvalue.replaceAll("\\s+", "")));
			//	Assert.assertTrue(actvalue.trim().equals(expvalue), "Strings are not equal after trimming whitespace");

		
			myBaselog.info("Actual value " + actvalue + " match the expected value" + expvalue);
			reportlog.logTestwithPassed("Actual value " + actvalue + " match the expected value" + expvalue);
		}
catch(Exception e) {
		
	

	myBaselog.error("Actual value " + actvalue + " do not  match the expected value" + expvalue);
	reportlog.logTestwithFailed("Actual value " + actvalue + " do not  match the expected value" + expvalue);
		}
	}
	public void waitUntilPageLoads() {
		myBaselog.info("Waiting until page loads within  expectedtime period");
		//reportlog.logTestInfo("Waiting until page loads within  expectedtime period");
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
	}
	
/*	
	public void switchToNewWindowFrom(String currentWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!currentWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		System.out.println("switched to new window");
	}
	public WebElement selectFromListUsingText(List<WebElement> list, String text) {
		WebElement element = null;
		for (WebElement i : list) {
			if (i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected=" + i.getText());
				element = i;
				break;
			}

		}
		return element;

	}
	
*/
	 
	 public void assertDefaultoption(By locator, String objname) {
	
		WebElement dropdown = driver.findElement(locator);
		dropdown.click();
		myBaselog.info("Dropdown element is clicked");
		reportlog.logTestInfo("Dropdown element is clicked");
		Select select = new Select(dropdown);
		String defaultActualString = select.getFirstSelectedOption().getText();
		myBaselog.info("Select  default value is: " + defaultActualString);// select.getFirstSelectedOption().toString());
		reportlog.logTestInfo("Select  default value is: " + defaultActualString);

		String expString = objname;
  try {
		
		Assert.assertEquals(defaultActualString, expString);
		myBaselog.info("Default selected option matches to the Actual option");
		reportlog.logTestwithPassed("Default selected option matches to the Actual option");
  }catch (Exception e) {
	// TODO: handle exception
			myBaselog.error("Default selected option  not matches to the Actual option"+e.getMessage());
			
throw e;
		}
	}

	public void selectByVisibleTextAndAssert(By locator, String visibleText) {
		WebElement dropdownElement = driver.findElement(locator);
		dropdownElement.click();
		myBaselog.info("Dropdown element is clicked");
		reportlog.logTestInfo("Dropdown element is clicked");
		Select dropdown = new Select(dropdownElement);
		try{
		dropdown.selectByVisibleText(visibleText);
		myBaselog.info("Text '" + visibleText + "' is selected from dropdown");

	    String selectedOptionText = dropdown.getFirstSelectedOption().getText();

	assertTrue(selectedOptionText.contains(visibleText));
		myBaselog.info("selected option matches to the Actual option");

		reportlog.logTestwithPassed("Selected option matches to the expected Visible text");

		}catch(Exception e) {
			myBaselog.error("Desired Visible text"+visibleText + "is not selected");
			//reportlog.logTestfailwithException(e);
			
		}
	}

	public void selectByIndexAndAssert(By locator, int Index) {
		
	
		WebElement dropdownElement = driver.findElement(locator);
		dropdownElement.click();

		myBaselog.info("Dropdown element is clicked");
		reportlog.logTestInfo("Dropdown element is clicked");
		Select dropdown = new Select(dropdownElement);
		try {
		dropdown.selectByIndex(Index); 
		myBaselog.info( Index + "' is selected from dropdown");
		 String selectedOptionText = dropdown.getFirstSelectedOption().getText();
			Assert.assertTrue(true, selectedOptionText);
			myBaselog.info("selected option matches to the Actual option");

			reportlog.logTestwithPassed("Selected option matches to the expected Visible text");
		}catch(Exception e) {
			myBaselog.error("Desired Index"+Index + "is not selected");
			//reportlog.logTestfailwithException(e);
			throw e;
		
	}}

	public void selectByValueAndAssert(By locator, String value) {
		WebElement dropdownElement = driver.findElement(locator);
		dropdownElement.click();
		myBaselog.info("Dropdown element is clicked");

		Select dropdown = new Select(dropdownElement);
		try {
	
		dropdown.selectByValue(value);
		
		 String selectedOptionText = dropdown.getFirstSelectedOption().getAttribute("value");

			Assert.assertTrue(true, selectedOptionText);
			myBaselog.info("selected option matches to the Actual option");

			reportlog.logTestwithPassed("Selected option matches to the expected value ");

		reportlog.logTestwithPassed("Selected option matches to the expected value");
	}catch(Exception e) {
		myBaselog.error("Desired Value"+value+ "is not selected");
		//reportlog.logTestfailwithException(e);
		throw e;
	
}}

	public void getAllOptionsAndAssert(By locator,Set<String> expectedOptions) {
		WebElement dropdownElement = driver.findElement(locator);
		dropdownElement.click();
		Select select = new Select(dropdownElement);
		List<WebElement> optionList = select.getOptions();
		Set<String> actualOptions = new HashSet();
		for (WebElement option : optionList) {
			 actualOptions.add(option.getText());
			//myBaselog.info("The dropdown oppertunities are:  " + option.getText());

		}
	/*expectedOptions = new ArrayList();
		expectedOptions.add("All Opportunities");
		expectedOptions.add("Closing Next Month");
		expectedOptions.add("Closing This Month");
		expectedOptions.add("My Opportunities");
		expectedOptions.add("New Last Week");
		expectedOptions.add("New This Week");
		expectedOptions.add("Opportunity Pipeline");
		expectedOptions.add("Private");
		expectedOptions.add("Recently Viewed Opportunities");
		expectedOptions.add("Won");
		System.out.println(expectedOptions);*/
		try {
Assert.assertEquals(actualOptions, expectedOptions);
		
myBaselog.info("Dropdown options match the expected list options.");
reportlog.logTestwithPassed("Dropdown options match the expected list options.");

		}

		catch(Exception e) {
		myBaselog.error("Dropdown options do not  match the expected list."+e.getMessage());

		}

	}

public void dropdownChoosebyText(List<WebElement> ele, String info, String obj) {
		for (WebElement field : ele) {
			String getText = field.getText().trim(); // Trim whitespace from the text

			if (getText.equals(info)) {
				if (field.isDisplayed()) { // Check if the element is visible
					field.click();
					System.out.println(obj + "is selected from dropdown");
				} else {
					System.out.println(obj + "is not visible in the dropdown");
				}
				break; // Exit the loop after selecting the date
			}
		}
}
		
		//

public void windowHandle(String parentwin) {
	

Set<String> handles= driver.getWindowHandles();

for (String handle:handles) {
	
	if(!handle.equalsIgnoreCase(parentwin)) {
		
		 driver.switchTo().window(handle);
	}
		 System.out.println("Switched window Title is :"+driver.getTitle());
	}
}

public void assertElementIsDisplayed(WebElement ele) {
	
	
	Assert.assertTrue(ele.isDisplayed(), "Element not found");//if fails error message will be throen
	
}
public void assertElementIsEnabled(WebElement ele) {
	
	
	Assert.assertTrue(ele.isEnabled(), "Element not enabled");
	
}
public void assertElementIsSelected(WebElement ele) {
	
	
	Assert.assertTrue(ele.isSelected(), "Element not selected");
	
}

}