package com.salesforce.automation;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.salesforce.base.BaseSalesForceClass;


@Listeners(com.salesforce.utility.SalesforceListenerUtility.class)

public class SalesforceUserMenu_Testcases  extends BaseSalesForceClass{
	protected Logger UserMenulog=LogManager.getLogger();

	@Test
	
public void verifySelectuserMenu() throws InterruptedException {
	initialSetup();
	
	assertTitle("Home Page ~ Salesforce - Developer Edition");
	Set<String> expectedmenuList=new HashSet();
	expectedmenuList.add("My Profile");
	expectedmenuList.add("My Settings");
	expectedmenuList.add("Developer Console");
	expectedmenuList.add("Switch to Lightning Experience");
	expectedmenuList.add("Logout");
	WebElement userAccountbttn = driver.findElement(By.id("userNavLabel"));
	waitForVisibilty(userAccountbttn, 40, "User account menu");
clickbuttonAndAssert(userAccountbttn, "user menu ");	
Thread.sleep(2000);
//By dropdown  =  By.xpath("//div[@id='userNav-menuItems']/a[text()]");
List<WebElement> dropdownlist= driver.findElements(By.xpath("//div[@id='userNav-menuItems']/a[text()]"));
Set<String> actualOptions = new HashSet();
for (WebElement option : dropdownlist) {
	 actualOptions.add(option.getText());
	//myBaselog.info("The dropdown oppertunities are:  " + option.getText());

}
try {
Assert.assertEquals(actualOptions, expectedmenuList);
		
myBaselog.info("Dropdown options match the expected list options.");
reportlog.logTestwithPassed("Dropdown options match the expected list options.");

		}

		catch(Exception e) {
		myBaselog.error("Dropdown options do not  match the expected list."+e.getMessage());

		}
//List<WebElement> userMenu_dropdown=
//driver.findElements (By.xpath("//div[@id='userNav-menuItems']/a[text()]"));
//List<String> actualMenuList = new ArrayList<>();
//for (WebElement element : userMenu_dropdown) {
////    actualMenuList.add(element.getText());}

Thread.sleep(2000);
//getAllOptionsAndAssert(dropdown ,expectedmenuList);
UserMenulog.info("All assertion passed");
reportlog.logTestInfo("All assertion passed");;

}


	@Test

public void MyProfile() throws InterruptedException, AWTException {
	
initialSetup();	assertTitle("Home Page ~ Salesforce - Developer Edition");
	WebElement userAccountbttn = driver.findElement(By.xpath("//div[@id='userNavButton']//div[@id='userNav-arrow']"));
	waitForVisibilty(userAccountbttn, 40, "User account menu");
	mouseHover_Interaction(userAccountbttn);
//clickbuttonAndAssert(userAccountbttn, "user menu ");	
List<WebElement> userMenu_dropdown =driver.findElements (By.xpath("//div[@id='userNav-menuItems']/a[text()]"));
for(WebElement option:userMenu_dropdown ) {
	
	if(option.getText().contains("My Profile")) {
		assertTrue(true);
		waitForVisibilty( option, 70, "My profile link" );
		new Actions(driver)
        .moveToElement(option).click()
        .perform();
		UserMenulog.info("My profile link is clicked");
		reportlog.logTestwithPassed("My profile link is clicked");
		break;
	}}
	Boolean title=driver.getPageSource().contains("Developer Edition");
	if(!title) {
		
		UserMenulog.info("current page Title not matching to the  expected ");
		reportlog.logTestwithPassed("current page Title not matching to the  expected ");
	}else {
		UserMenulog.error("current page Title is not  matching to the  expected ");
		reportlog.logTestwithFailed("current page Title not matching to the  expected ");
		

		
	}

	 	WebElement editBttn = driver.findElement(By.xpath("//a[contains(@class, 'contactInfoLaunch') and contains(@class, 'editLink')]"));
	waitForVisibilty( editBttn, 70, "Edit pen link" );
clickbuttonAndAssert( editBttn, "Edit pen link  ");
 String parentwin=  driver.getWindowHandle();
 System.out.println(parentwin);
 Set<String> handles=driver.getWindowHandles();
 for(String handle:handles) {
	 UserMenulog.info("Windows unique identifiers:  "+handles);

	 if(handle.equals(parentwin)) {
		 
		 System.out.println(handle);
		 driver.switchTo().window(handle);
		 
	 }
	 
	 
 }
driver.switchTo().frame("contactInfoContentId");
UserMenulog.info("Now switched to Frame:'ContactInfoContentId'");
reportlog.logTestInfo("Now switched to Frame:'ContactInfoContentId'");
WebElement aboutTab =  driver.findElement(By.xpath("//li[@id='aboutTab']/a[@href]"));;;
JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
jsExecutor.executeScript("arguments[0].click();", aboutTab);
UserMenulog.info("About Tab link is clicked");
	//waitForVisibilty( aboutTab, 40, "About tab " );

	//clickbuttonAndAssert( aboutTab, "About tab  ");
	WebElement lastName = driver.findElement(By.id("lastName"));
	waitForVisibilty( lastName, 40, "last Name field " );
	elementSendTextWithAssert(lastName, "Gallo", "Last Name ");	
	String nametxt=lastName.getAttribute("value");
	WebElement saveBttn = driver.findElement(By.xpath("//input[@value='Save All']"));
	//waitForVisibilty( aboutTab, 40, "About tab " );
	clickbuttonAndAssert( saveBttn, "Save All  ");
	 

//
driver.switchTo().defaultContent();
WebElement headerTitle = driver.findElement(By.id("tailBreadcrumbNode" ));
waitForVisibilty(headerTitle, 30, "HeaderTitle ");
 String	 nameHeader= headerTitle.getAttribute("title");
 String[]	 names=	 nameHeader.split("\\s+");
 String	 lastname=names[names.length-1];
 assertStringsEqual(nametxt, lastname);
 
WebElement postBttn= driver.findElement(By.xpath("//a[@title='Post']"));
	waitForVisibilty( postBttn, 40, "Post Button " );
clickbuttonAndAssert(postBttn, "Post ");
//div[@class="cke_contents cke_reset"]/iframe
//driver.switchTo().frame(0);
WebElement frame= driver.findElement(By.cssSelector("iframe.cke_wysiwyg_frame.cke_reset"));
driver.switchTo().frame(frame);
UserMenulog.info("Switched to 'frame' to enter the text" );
reportlog.logTestInfo("Switched to 'frame' to enter the text" );

WebElement textArea= driver.findElement(By.xpath("//body[contains(@class, 'chatterPublisherRTE')]"));;

//WebElement textArea= driver.findElement(By.xpath("//textarea[@id='publishereditablearea']"));
waitForVisibilty(textArea, 20, "Text Area ");
clickbuttonAndAssert(textArea, "text area clicked");
//textArea.click();

//WebElement txtinput= driver.findElement(By.xpath("//html[@dir='ltr']/body"));
//waitForclickable(textArea, 40, "Input text box");
elementSendTextWithAssert(textArea, " this looks good!", "Text area ");
String txt=textArea.getText();
UserMenulog.info("Text  message is shared successfully in post section" );
reportlog.logTestInfo("Text  message is shared  successfully in post section" );
driver.switchTo().defaultContent();
UserMenulog.info("Switched back to main page  driver using " );
reportlog.logTestInfo("Switched back to main page  driver using " );

WebElement shareBttn= driver.findElement(By.id("publishersharebutton"));
clickbuttonAndAssert(shareBttn, "Share ");
UserMenulog.info("File is shared  successfully" );
reportlog.logTestInfo("File is shared  successfully" );
WebElement feedTxt= driver.findElement(By.xpath("//div[@class='cxfeeditemtextadditional']/span/p"));
waitForVisibilty(feedTxt, 40, "Feed text ");
String feedtxt=feedTxt.getText();
assertStringsEqual(txt, feedtxt);

		
WebElement fileBttn= driver.findElement(By.id("publisherAttachContentPost"));
Thread.sleep(1000);

clickbuttonAndAssert(fileBttn, "File ");
WebDriverWait wait = new WebDriverWait(driver, 30);
WebElement uploadLink = driver.findElement(By.xpath("//div[@id='chatterFileStageOne']//table//td[2]/a[@id='chatterUploadFileAction']"));
//WebElement uploadLink = driver.findElement(By.id("chatterFile"));
wait.until(ExpectedConditions.elementToBeClickable( uploadLink));
clickbuttonAndAssert(uploadLink, "Upload ");

//uploadLink.sendKeys("/Users/nitin/Desktop/java.png");


// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='chatterUploadFileAction' and @title='Upload a file from your computer']")));
 

 // Define the function to locate the element
 
//WebElement uploadBttn= driver.findElement(By.xpath("//div//table[@class='chatterPublisherActionsContainer']//a[@id= 'chatterUploadFileAction']"));
/*By uploadBy=By.xpath("//table[@class='chatterPublisherActionsContainer']//a[@id= 'chatterUploadFileAction']");
WebDriverWait wait=new WebDriverWait(driver,40);
wait.until(ExpectedConditions.presenceOfElementLocated(uploadBy));
////if(uploadBttn.isEnabled()) {*/
//JavascriptExecutor jsExe=(JavascriptExecutor)driver;
//jsExe.executeScript("arguments[0].click();", uploadLink);
//else {
//	System.out.println("its not enables");
//}
WebElement choosefileBttn= driver.findElement(By.id("chatterFile"));
waitForVisibilty(choosefileBttn, 30, "choose file ");
int x = 270; 
int y =430; 
Thread.sleep(1000);
//int x=browserbttn.getLocation().getX();  // X-coordinate of the button
//int y=browserbttn.getLocation().getY();
//System.out.println(x +y);// X-coordinate of the button
//	System.out.println(x +y);// X-coordinate of the button

Robot rt=new Robot();
//Dimension i = driver.manage().window().getSize(); 
//System.out.println("Dimension x and y :"+i.getWidth()+" "+i.getHeight()); 
//3. Get the height and width of the screen 
//int x = (i.getWidth()/4)+20; 
//int y = (i.getHeight()/10)+50;
rt.mouseMove(x, y); 

Thread.sleep(1000);
//clickbuttonAndAssert(browserbttn, "file browser ");
rt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
rt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
rt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
rt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
//rt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//rt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
StringSelection stringSelection = new StringSelection("/Users/nitin/Desktop/java.png");
Clipboard   clip= Toolkit.getDefaultToolkit().getSystemClipboard();
clip.setContents(stringSelection, null);
Thread.sleep(2000);
rt.keyPress(KeyEvent.VK_META);  // Press Command key
rt.keyPress(KeyEvent.VK_SHIFT); // Press Shift key
rt.keyPress(KeyEvent.VK_G);     // Press G key

rt.keyRelease(KeyEvent.VK_META);  // Release Command key
rt.keyRelease(KeyEvent.VK_SHIFT); // Release Shift key
rt.keyRelease(KeyEvent.VK_G); 
Thread.sleep(2000);

rt.keyPress(KeyEvent.VK_ENTER);
rt.keyRelease(KeyEvent.VK_ENTER);
Thread.sleep(2000);
rt.keyPress(KeyEvent.VK_ENTER);
rt.keyRelease(KeyEvent.VK_ENTER);
Thread.sleep(2000);
//Move mouse to a neutral location to avoid accidental clicks

rt.mouseMove(0, 0);
Thread.sleep(1000);

// Optionally, close the dialog if it reopens
// Example: Press Escape key
rt.keyPress(KeyEvent.VK_ESCAPE);
rt.keyRelease(KeyEvent.VK_ESCAPE);


WebElement shareBttn1= driver.findElement(By.id("publishersharebutton"));
clickbuttonAndAssert(shareBttn1, "share ");
//used wait for uploading image to disappear
WebDriverWait wait1 = new WebDriverWait(driver, 50); // Wait up to 60 seconds
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("progressIcon")));

WebElement addLPhotolink = driver.findElement(By.xpath("//div[@id='listItem-addPhoto']/a"));
waitForVisibilty(addLPhotolink, 60, "Add a photo");
clickbuttonAndAssert(addLPhotolink, "Add a  photo");

driver.switchTo().frame("uploadPhotoContentId");
UserMenulog.info("Now switched to Frame:'uploadPhotoContentId'");
reportlog.logTestInfo("Now switched to Frame:'uploadPhotoContentId");
//Robot rt=new Robot();
int x1 = 600; 
int y1 =480; 
Thread.sleep(1000);
rt.mouseMove(x1, y1); 

Thread.sleep(1000);
//clickbuttonAndAssert(browserbttn, "file browser ");
rt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
rt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
rt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
rt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

StringSelection stringSelection2=new StringSelection("/Users/nitin/Desktop/sandwich.png");
Clipboard clip1=Toolkit.getDefaultToolkit().getSystemClipboard();
clip1.setContents(stringSelection2,null);
rt.keyPress(KeyEvent.VK_META);
rt.keyPress(KeyEvent.VK_SHIFT);rt.keyPress(KeyEvent.VK_G);
rt.keyRelease(KeyEvent.VK_META);  // Release Command key
rt.keyRelease(KeyEvent.VK_SHIFT); // Release Shift key
rt.keyRelease(KeyEvent.VK_G); 
Thread.sleep(2000);

rt.keyPress(KeyEvent.VK_ENTER);
rt.keyRelease(KeyEvent.VK_ENTER);
Thread.sleep(2000);
rt.keyPress(KeyEvent.VK_ENTER);
rt.keyRelease(KeyEvent.VK_ENTER);
Thread.sleep(2000);
WebElement uploadBttn= driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn"));
waitForVisibilty(uploadBttn, 30, "Save ");
clickbuttonAndAssert(uploadBttn, "Save ");
WebElement uploadImg= driver.findElement(By.xpath("//form[@id='j_id0:waitingForm']//img"));//img[@src="/img/loading32.gif"]
WebDriverWait wait2=new WebDriverWait(driver, 80);
wait2.until(ExpectedConditions.invisibilityOf(uploadImg));
UserMenulog.info("Robot class used to simulate keyboard and mouse actions for the purpose of clicking on file folder and uploading the photo ");
reportlog.logTestInfo("Robot class used to  simulate keyboard and mouse actions for the purpose of clicking on file folder and uploading the photo ");
//WebElement uploadFile = driver.findElement(By.xpath("//*[@id=j_id0:uploadFileForm:uploadInputFile]"));//*[@id="j_id0:uploadFileForm:uploadInputFile"]
//waitForVisibilty(uploadFile, 60, "upload  photo");
UserMenulog.info("All assertion passed");
reportlog.logTestInfo("All assertion passed");;

		/*Actions actions = new Actions(driver);
        actions.moveToElement(imageElement, 100, 100) // Move to a starting point
               .clickAndHold() // Click and hold the mouse button
               .moveByOffset(200, 200) // Move the mouse to define the cropping region
               .release() // Release the mouse button
               .perform(); // Perform the actions
		*/
}
	@Test
public void mySettingOption() throws InterruptedException, AWTException {
	
	
initialSetup();
	
	assertTitle("Home Page ~ Salesforce - Developer Edition");
	WebElement userAccountbttn = driver.findElement(By.id("userNavLabel"));
	waitForVisibilty(userAccountbttn, 40, "User account menu");
clickbuttonAndAssert(userAccountbttn, "user menu ");
List<WebElement> userMenu_dropdown =driver.findElements (By.xpath("//div[@id='userNav-menuItems']/a[text()]"));
for(WebElement option:userMenu_dropdown ) {
	//System.out.println(option.getText());
	if(option.getText().contains("My Settings")) {
		waitForVisibilty( option, 70, "My profile link" );
		new Actions(driver)
        .moveToElement(option).click()
        .perform();
		break;
	}}
	Boolean title=driver.getPageSource().contains("Developer Edition");
	if(!title) {
		
		UserMenulog.error("current page Title not matching to the  expected ");
		reportlog.logTestwithFailed("current page Title not matching to the  expected ");
	}else {
		UserMenulog.info("current page Title is  matching to the  expected ");
		reportlog.logTestwithPassed("current page Title is  matching to the  expected ");
	}

	List<WebElement> container=driver.findElements (By.xpath("//div[@id='AutoNumber5']/child::div/a"));
	for(WebElement tab:container) {
		tab.getText().equals("Personal");
		clickbuttonAndAssert(tab, "Personal is ");
		
	}
	//WebElement personalbttn = driver.findElement(By.xpath("//div[@id='PersonalInfo']/a"));
//	waitForVisibilty(personalbttn, 40, "Personal option");
//	clickbuttonAndAssert(personalbttn, "uPersonal Info tab	 ");
	WebElement pageheader = driver.findElement(By.xpath("//div[@class='content']/h1"));
	String textact=  pageheader.getText();
	String textaexp="Hello, Sudeepa Gallo!";
	assertStringsEqual(textact, textaexp);
	
	List<WebElement> personal=driver.findElements (By.xpath("//div[@id='PersonalInfo']//div[@class='childContainer']//div//a"));
	for(WebElement option:personal ) {
		//System.out.println(option.getText());
		if(option.getText().contains("Login History")) {
			waitForVisibilty( option, 30, "Login History link" );
clickbuttonAndAssert(option, "Login History selected");		
		//	new Actions(driver)
	     //   .moveToElement(option).click().build()
	     //   .perform();
			break;
		}}

	List<WebElement> tabledata=driver.findElements (By.xpath("//table[@class='list']//tr"));
	if(tabledata.isEmpty()) {
		UserMenulog.error("There is no Login history data available atthis moment");
		reportlog.logTestwithFailed("There is no Login history data available atthis moment");
	}
	else {		UserMenulog.info(" Login history data is available ");
reportlog.logTestInfo(" Login history data is available ");
		
	}
	WebElement showmore = driver.findElement(By.xpath("//div[@class='pShowMore']/a"));
	String acttext = showmore.getText();
	String exptext = "Download login history for last six months, including logins from outside the website, such as API logins (Excel .csv file) »";
	assertStringsEqual(acttext, exptext);
	WebElement downloadCsvlink = driver.findElement(By.xpath("//div[@class='pShowMore']/a	"));
	downloadCsvlink.click();
	String filename="/Users/nitin/Downloads/LoginHistory1707755477006.csv";
	String file_directory = "/Users/nitin/Downloads";
	
	//verify from Downloads Directory is this file exists
	File folder = new File(file_directory);
	File[] files=new File(folder.getPath()).listFiles();
	for(File file:files) {
		if(file.getName().equals(filename)) { 
			UserMenulog.info("--Verified: File : " + filename + " Has Been Download.");
		}else {
			continue;
		}
	}
	
	
	
			List<WebElement> container1=driver.findElements (By.xpath("//div[@id='AutoNumber5']/child::div/a"));

			for(WebElement option:container1 ) {
				//System.out.println(option.getText());
				if(option.getText().contains("Display & Layout")) {
					waitForVisibilty( option, 30, "display and layout link" );
		//clickbuttonAndAssert(option, "Option selected");		
					new Actions(driver)
			        .moveToElement(option).click()
			        .perform();
					UserMenulog.info(" display and layout link is clicked");
					reportlog.logTestInfo("display and layout link is clicked");
					break;
				}}
			
			WebElement customizeMyTabs = driver.findElement(By.xpath("//div[@id='DisplayAndLayout']//div[1]/a"));
			clickbuttonAndAssert(customizeMyTabs, "customizeMyTabs ");
		By selectoption=By.id("p4");
selectByVisibleTextAndAssert(selectoption, "Salesforce Chatter");

WebElement dropdown = driver.findElement(By.id("duel_select_0"));



((JavascriptExecutor) driver).executeScript("arguments[0].value='Reports';", dropdown);

//By availableTabs=By.id("duel_select_0");
//
WebElement addBttn = driver.findElement(By.xpath("//div[@class='zen-mbs text']/a[1]"));
waitForVisibilty(addBttn, 30, " addbutton ");
clickbuttonAndAssert(addBttn, "Add Button " );
WebElement savebttn = driver.findElement(By.xpath("//input[@value=\" Save \"]"));
waitForVisibilty(savebttn, 30, "save button ");
clickbuttonAndAssert(savebttn, "Save ");
List<String> expectedstrElements=Arrays.asList("Chatter","Reports");
List<WebElement> tabbar=driver.findElements (By.xpath("//ul[@id='tabBar']/li"));
for(WebElement tab:tabbar)
{
	if(expectedstrElements.contains(tab.getText())){
		assertTrue(true);
		UserMenulog.info("All expected tabs are present.");
		reportlog.logTestInfo("All expected tabs are present.");

	}else {
		UserMenulog.error("Not all expected tabs are present.");
reportlog.logTestwithFailed("Not all expected tabs are present.");
	}
	
}

	List<WebElement> container2=driver.findElements (By.xpath("//div[@id='AutoNumber5']/child::div/a"));


for(WebElement option:container2 ) {
	System.out.println(option.getText());
	if(option.getText().contains("Email")) {
		assertTrue(true);
		waitForVisibilty( option, 30, "Email link" );
		new Actions(driver)
        .moveToElement(option).click()
        .perform();
		UserMenulog.info("Email link is clicked");
		reportlog.logTestInfo("Email link is clicked");
		break;
	}}
WebElement editSettingBttn = driver.findElement(By.xpath("//div[@id='EmailSetup']//div[@class='setupLeaf'][1]//a"));
waitForVisibilty(editSettingBttn, 30, "Edit Setiing  ");
clickbuttonAndAssert(editSettingBttn, "Edit Setting  ");


WebElement nameInput = driver.findElement(By.id("sender_name"));
waitForVisibilty(nameInput , 30, "Edit Setiing  ");
elementSendTextWithAssert(nameInput, "Sandeep GalloNew", "Nmae filed is ");
WebElement email = driver.findElement(By.id("sender_email"));
waitForVisibilty(email , 30, "Email  ");
elementSendTextWithAssert(email, "sudeep_1234@yahoo.com", "email field  is ");
WebElement checkBox = driver.findElement(By.id("auto_bcc1"));
waitForVisibilty(checkBox  , 30, "check box   ");
if(checkBox.isSelected()){
	System.out.println("BCC emails is already selectied as Yes");
}else {
	checkBox.click();
}
WebElement savebttn1 = driver.findElement(By.xpath("//input[normalize-space(@value)='Save']"));
waitForVisibilty(savebttn1,30, "save button ");
 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",savebttn1);
clickbuttonAndAssert(savebttn1, "Save ");
Thread.sleep(1000);
List<WebElement> container3=driver.findElements (By.xpath("//div[@id='AutoNumber5']/child::div/a"));


for(WebElement option:container3 ) {
	//System.out.println(option.getText());
	if(option.getText().contains("Calendar & Reminders")) {
		assertTrue(true);
		waitForVisibilty( option, 50, "Calendar & Reminders link" );
		new Actions(driver)
        .moveToElement(option).click()
        .perform();
		UserMenulog.info("Calendar & Reminders is clicked");
		reportlog.logTestInfo("Calendar & Reminders is clicked");
		break;
	}}
/*WebElement calReminderbttn = driver.findElement(By.xpath("//div[@id='CalendarAndReminders']/a"));
waitForVisibilty(calReminderbttn, 40, "Calender Reminder button ");
clickbuttonAndAssert(calReminderbttn, "Calender and Reminder ");
*/
WebElement activityReminder = driver.findElement(By.xpath("//a[@id='Reminders_font']"));
waitForVisibilty(activityReminder, 50, "Activity Reminder button ");
clickbuttonAndAssert(activityReminder, "Activity Reminder  ");

WebElement testReminder = driver.findElement(By.id("testbtn"));
waitForVisibilty(testReminder, 80, " Open Test Reminder button ");
clickbuttonAndAssert(testReminder, "TestReminder  ");

//this  below didnt work as its not a child window

/*String parentwin=driver.getWindowHandle();
System.out.println(parentwin);
Set<String>handles=driver.getWindowHandles();
System.out.println(handles);
for(String handle:handles) {
if(!handle.equals(parentwin)) {
	driver.switchTo().window("this is child handle"+handle);
	System.out.println(handle);
	Thread.sleep(3000);
	WebElement dismissbttn = driver.findElement(By.id("dismiss_all"));
	
	waitForVisibilty(dismissbttn, 30, "Dismiss all button ");
	 ((JavascriptExecutor)driver).executeScript("arguments[0].click();",dismissbttn);

	clickbuttonAndAssert(dismissbttn, "Dismiss  ");
		
}}*/
Thread.sleep(5000);
Robot rt=new Robot();
int x = 130; 
int y =365; 
Thread.sleep(1000);
rt.mouseMove(x, y);
rt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
rt.mousePress(InputEvent.BUTTON1_DOWN_MASK);

rt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
rt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
UserMenulog.info("Robot class used to  mouse action to dismiss all button ");
reportlog.logTestInfo("Robot class used to  mouse action to dismiss all button ");
UserMenulog.info("All assertion passed");
reportlog.logTestInfo("All assertion passed");;
//WebElement uploadFile = driver.findElement(By.xpath("//*[@id=j_id0:uploadFileForm:uploadInputFile]"));//*[@id="j_id0:uploadFileForm:uploadInputFile"
//this below code not working its saying invalidelement so i went with Robot 

/*WebElement dismissbttn = driver.findElement(By.cssSelector("input#dismiss_all.btn"));
waitForVisibilty(dismissbttn, 30, "Dismiss all button ");
 ((JavascriptExecutor)driver).executeScript("arguments[0].click();",dismissbttn);
//clickbuttonAndAssert(dismissbttn, "Dismiss  ");
*/

}
	
	@Test
public void developerConsole() throws AWTException, InterruptedException {
	initialSetup();	assertTitle("Home Page ~ Salesforce - Developer Edition");
	WebElement userAccountbttn = driver.findElement(By.xpath("//div[@id='userNavButton']//div[@id='userNav-arrow']"));
	waitForVisibilty(userAccountbttn, 40, "User account menu");
	mouseHover_Interaction(userAccountbttn);
//clickbuttonAndAssert(userAccountbttn, "user menu ");	
List<WebElement> userMenu_dropdown =driver.findElements (By.xpath("//div[@id='userNav-menuItems']/a[text()]"));
for(WebElement option:userMenu_dropdown ) {
	System.out.println(option.getText());
	if(option.getText().contains("Developer Console")) {
		waitForVisibilty( option, 70, "Developer console  link" );
		new Actions(driver)
        .moveToElement(option).click()
        .perform();
		UserMenulog.info("Developer console  linkis clicked");
		reportlog.logTestInfo("Developer console  linkis clicked");
		break;
	}}

/*

//these below appraoches will not work as slenium not able to handle the browser window ui parts like x-expand buttons 

//((JavascriptExecutor) driver).executeScript("window.close();");
Actions actions = new Actions(driver);
//Move the mouse to the top-right corner of the window (approximate coordinates)
actions.moveByOffset(100,100);
//Perform a click action
actions.click().build().perform();   */

Robot rt=new Robot();
int x1 = 10; 
int y1 =40; 
Thread.sleep(1000);
rt.mouseMove(x1, y1); 

Thread.sleep(1000);
//clickbuttonAndAssert(browserbttn, "file browser ");
rt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
rt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
rt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
rt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
Thread.sleep(1000);
 
UserMenulog.info("Robot class used to  mouse action to dismiss all button ");
reportlog.logTestInfo("Robot class used to  mouse action to dismiss all button ");

UserMenulog.info("All assertion passed");
reportlog.logTestInfo("All assertion passed");;
}
	@Test
public void logoutOption() throws InterruptedException {
	
	initialSetup();	
	assertTitle("Home Page ~ Salesforce - Developer Edition");
	WebElement userAccountbttn = driver.findElement(By.xpath("//div[@id='userNavButton']//div[@id='userNav-arrow']"));
	waitForVisibilty(userAccountbttn, 40, "User account menu");
	mouseHover_Interaction(userAccountbttn);
//clickbuttonAndAssert(userAccountbttn, "user menu ");	
List<WebElement> userMenu_dropdown =driver.findElements (By.xpath("//div[@id='userNav-menuItems']/a[text()]"));
for(WebElement option:userMenu_dropdown ) {
	System.out.println(option.getText());
	if(option.getText().contains("Logout")) {
		waitForVisibilty( option, 70, "Logout link" );
		new Actions(driver)
        .moveToElement(option).click()
        .perform();
		UserMenulog.info("Logout link is clicked");
		reportlog.logTestInfo("Logout link is clicked");
		
	}
	}
Thread.sleep(2000);
//waitUntilPageLoads();
	assertTitle("Login | Salesforce");
	UserMenulog.info("All assertion passed");
	reportlog.logTestInfo("All assertion passed");;
	
	
}

	
}