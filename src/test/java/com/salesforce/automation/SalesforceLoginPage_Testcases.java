package com.salesforce.automation;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.salesforce.base.BaseClass;
import com.salesforce.base.BaseSalesForceClass;
import com.salesforce.utility.Constants;
import com.salesforce.utility.PropertyUtility;

public class SalesforceLoginPage_Testcases extends BaseSalesForceClass {
	Logger mySalesforceLog = LogManager.getLogger();

	@Test(priority = 0,enabled = true	)
	public void loginPage_EmptyPassword() throws InterruptedException {
		mySalesforceLog.info("..............started loginPage_EmptyPassword...............");
		
		String expTxt = "Please enter your password.";
		assertTitle("Login | Salesforce");
		mySalesforceLog.info("Assertion passed: Actual title is equal to Expected title");
		//SoftAssert srAssert=new SoftAssert();
		String username = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "username");
		String passwrd = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "password");

		// baseURL("https://login.salesforce.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement email_field = driver.findElement(By.xpath("//*[@id='username']"));
		//elementSendTextWithAssert(email_field, "sweety123@yahoo.com", "Username");
		elementSendTextWithAssert(email_field,username, "Email");
		//System.out.println("Assertion passed: Actual title is equal to Expected title");

		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
password.sendKeys(passwrd.replaceAll(passwrd,""));				
WebElement loginButton = driver.findElement(By.id("Login"));
waitForVisibilty(loginButton, 30, "login ");
		clickbuttonAndAssert(loginButton, "login Button ");
		WebElement labelTxt = driver.findElement(By.id("error"));
		//assertStringsText(label, "Validation message", "Please enter your password.");
       // String actTxt=getText(labelTxt);
         assertStringsText(labelTxt , expTxt);
		mySalesforceLog.info("..............ended loginPage_EmptyPassword................");

	}

	@Test(priority = 3,enabled = true)
	public void login_Homepage() throws InterruptedException {
		mySalesforceLog.info("..............stated login_Homepage()...............");
initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
		mySalesforceLog.info("..............ended login_Homepage()...............");

	}

	@Test(priority = 4)
	public void rememberUser_checkbox() throws InterruptedException, IOException {
		mySalesforceLog.info("..............stated  rememberUser_checkbox..............");

		String username = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "username");
		String passwrd = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "password");
		WebElement email_field = driver.findElement(By.xpath("//*[@id='username']"));
		waitForVisibilty(email_field, 30, "email_field is");
		elementSendTextWithAssert(email_field, username, "Username");
		mySalesforceBaseLog.info("Email is entered in a Field ");

		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
		elementSendTextWithAssert(password, passwrd, "Password");
		mySalesforceBaseLog.info("Password is entered in a Field ");

		WebElement remembercheck = driver.findElement(By.id("rememberUn"));
		waitForVisibilty(remembercheck, 30, "Remember checkbox");
		clickbuttonAndAssert(remembercheck , "Remembercheck");
		WebElement loginButton = driver.findElement(By.id("Login"));
		waitForVisibilty(loginButton, 40,"Login ");
		clickbuttonAndAssert(loginButton, "login  ");
		assertTitle("Home Page ~ Salesforce - Developer Edition");
         logout();
		
       WebDriverWait wait = new WebDriverWait(driver, 30); // Wait up to 10 seconds
       WebElement textusername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='idcard-identity']")));

		assertStringsText(textusername,  "sweety123@yahoo.com");

		mySalesforceLog.info(".............ended rememberUser_checkbox..............");

	}

	@Test(priority = 1,enabled = true)
	public void testForgotPasssword() throws InterruptedException {
		mySalesforceLog.info("..............stated testForgotPasssword..............");
		WebElement forgotpass = driver.findElement(By.id("forgot_password_link"));
		forgotpass.click();

		assertTitle("Forgot Your Password | Salesforce");

		WebElement email_field = driver.findElement(By.xpath("//input[@id='un']"));
		elementSendTextWithAssert(email_field, "sweety123@yahoo.com", "Username");
		WebElement continuebutton = driver.findElement(By.xpath("//input[@id='continue']"));
		clickbuttonAndAssert(continuebutton, "Continue Button ");

		assertTitle("Check Your Email | Salesforce");
		WebElement element = driver.findElement(By.xpath("//h1[text()='Check Your Email']"));
		//assertStringsText(element, "page label text: ", "Check Your Email");
		mySalesforceLog.info("..............ended testForgotPasssword..............");

	}

	@Test(priority = 2,enabled = true)
	public void loginErrorMessage() throws InterruptedException {
		mySalesforceLog.info("..............stated loginErrorMessage..............");
SoftAssert sfAssert=new SoftAssert();
		assertTitle("Login | Salesforce");
String explabel="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		WebElement email_field = driver.findElement(By.xpath("//*[@id='username']"));

		elementSendTextWithAssert(email_field, "123@yahoo.co", "Username");
		Thread.sleep(3000);

		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
		elementSendTextWithAssert(password, "22131", "Password");

		WebElement loginButton = driver.findElement(By.id("Login"));

		clickbuttonAndAssert(loginButton, "login Button ");
		Thread.sleep(7000);
		WebElement label= driver.findElement(By.cssSelector("div#error.loginError"));
		System.out.println(label.getText());
		waitForVisibilty(label, 40, " Error label");
		Assert.assertEquals(label.getText(), explabel);
		//sfAssert.assertAll();
		// String string=labelele.getText();
		// System.out.println(string);
		//assertStringsText(label, "page label text: ",
		//		"Please check your username and password."); 

	}

}
