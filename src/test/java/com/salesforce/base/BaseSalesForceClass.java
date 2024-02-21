package com.salesforce.base;

import java.awt.HeadlessException;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicEncryptionKey;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.salesforce.utility.*;
//import com.salesforce.*;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.salesforce.utility.SalesforceListenerUtility.class)

public class BaseSalesForceClass extends BaseClass {
	
	protected Logger mySalesforceBaseLog=LogManager.getLogger();
	// protected Alert alert;
	// protected WebDriver driver=null;
	@Parameters({"browser"})

	@BeforeMethod
	
	public void setUpBeforeMethod(@Optional("chrome") String name) {
		//BaseFirebasetlog.info(".........BeforeMethod setUpBeforeMethod executed---------------");
		initializeBrowser(name);
		String url = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "url");
		baseURL(url);
		waitUntilPageLoads();
	}
	
	@AfterMethod
	public void tearDownAfterTestMethod() {
		driverClose();
		//BaseFirebasetlog.info("******tearDownAfterTestMethod executed***********");
	}
	public void initialSetup() {
		
		driver.manage().window().maximize();
		//titleCheck("Login | Salesforce");
		String username = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "username");
		String passwrd = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "password");
		WebElement email_field = driver.findElement(By.xpath("//*[@id='username']"));
		waitForVisibilty(email_field, 30, "email_field is");
		elementSendTextWithAssert(email_field, username, "Username");
		mySalesforceBaseLog.info("Email is entered in a Field ");

		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
		elementSendTextWithAssert(password, passwrd, "Password");
		mySalesforceBaseLog.info("Password is entered in a Field ");

		WebElement loginButton = driver.findElement(By.id("Login"));
		waitForVisibilty(loginButton, 40,"Login ");
		clickbuttonAndAssert(loginButton, "login  ");
		mySalesforceBaseLog.info("Successfully logged to the Home page");
		reportlog.logTestwithPassed("Successfully logged in to Home page");

		// WebElement message_okbttn=
		// driver.findElement(By.xpath("//a[@class='continue']"));
		// if(message_okbttn.isDisplayed()) {
		// message_okbttn.click();}

	}

	public void headlessBrowser()  {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		mySalesforceBaseLog.info("Headless Excution started");

		assertTitle("Login | Salesforce");
		baseURL("https://login.salesforce.com");
waitUntilPageLoads();	

		WebElement email_field = driver.findElement(By.xpath("//*[@id='username']"));
		waitForVisibilty(email_field, 30, "email_field is");

		elementSendTextWithAssert(email_field, "sweety123@yahoo.com", "Username");
		mySalesforceBaseLog.info("Email is entered in a Field ");

		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
		elementSendTextWithAssert(password, "Lavisha_2212", "Password");

		WebElement loginButton = driver.findElement(By.id("Login"));
		clickbuttonAndAssert(loginButton, "login  ");
		mySalesforceBaseLog.info("Successfully logged in a headless mode to Home page");
reportlog.logTestwithPassed("Successfully logged in a headless mode to Home page");
	}


	

	public   void logout() throws InterruptedException {
		WebElement useraccount = this.driver.findElement(By.xpath("//*[@id='userNavLabel']"));
		WebElement logout =this. driver.findElement(By.xpath("//a[@title='Logout']"));
		logoutAccount(useraccount, logout);
		
	}


	public void logoutAccount(WebElement accountele, WebElement logoutele) throws InterruptedException {
		Thread.sleep(3000);
		// actionCall();
		// mouseHover_Interaction(accountele);
		accountele.click();
		clickbuttonAndAssert(logoutele, "Logout ");

	}

}
