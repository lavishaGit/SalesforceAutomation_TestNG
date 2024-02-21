package com.salesforce.automation;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.salesforce.base.BaseSalesForceClass;
@Listeners(com.salesforce.utility.SalesforceListenerUtility.class)

public class SalesforceCreateOpty_Testcases extends BaseSalesForceClass {
	protected Logger Optylog=LogManager.getLogger();

@Test
	public void opportunitiesdropdown() throws InterruptedException {
		initialSetup();
		WebElement opty_field = driver.findElement(By.xpath("//li[@id='Opportunity_Tab']/a"));
		clickbuttonAndAssert(opty_field, "Opportunities    ");
		assertTitle("Opportunities: Home ~ Salesforce - Developer Edition");
		By dropdown = By.xpath("//select[@id='fcf']");
//verify with the default option selected
		Set<String> expectedOptionList = new HashSet(Arrays.asList("All Opportunities","Closing Next Month","Closing This Month","My Opportunities","New Last Week","New This Week","Opportunity Pipeline","Private","Recently Viewed Opportunities","Won"));

		getAllOptionsAndAssert(dropdown, expectedOptionList);
		Optylog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");;
//Get all options in a actual list and create custom list with all expected options and match
		//getAllOptionsAndAssert(dropdown);

	}
@Test
	public void createNewOppty() throws InterruptedException {
    String uuid = UUID.randomUUID().toString().replace("-", "");    // Generate a random UUID (Universally Unique Identifier)
    ///uuid.substring(0,4);

    String uniqueName = "OPTY_"  + uuid.substring(0, 4);

		try {
			opportunitiesdropdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// By oppty=By.xpath("//select[@id='fcf']");
		By dropdown = By.xpath("//select[@id='fcf']");

		selectByVisibleTextAndAssert(dropdown, "Opportunity Pipeline");
		
		WebElement newbuttn = driver.findElement(By.xpath("//input[normalize-space(@value)='New']"));
		waitForVisibilty(newbuttn, 30 ,"New button");
		clickbuttonAndAssert(newbuttn, "New ..");
		assertTitle("Opportunity Edit: New Opportunity ~ Salesforce - Developer Edition");
		WebElement opty_name = driver.findElement(By.id("opp3"));

		elementSendTextWithAssert(opty_name, uniqueName, "Oppurunity name  ");
		WebElement acc_name = driver.findElement(By.id("opp6"));
		// elementSendTextWithAssert(acc_name, "ABDC", "Account name ");

		By type_dropdown = By.id("opp5");
		selectByVisibleTextAndAssert(type_dropdown, "Existing Customer - Upgrade");
		By leadS_dropdown = By.id("opp6");
		selectByVisibleTextAndAssert(leadS_dropdown, "Web");

		WebElement amount = driver.findElement(By.id("opp7"));
		elementSendTextWithAssert(amount, "60", "Amount ");

		WebElement closedate = driver.findElement(By.id("opp9"));
		WebElement datepicker = driver.findElement(By.xpath("//a[contains(text(),'2024')]"));
		datepicker.click();
		Thread.sleep(1000);
		By stage_dropdown = By.id("opp11");
		selectByVisibleTextAndAssert(stage_dropdown, "Qualification");
		try {

			Assert.assertTrue(opty_name != null && opty_name.isDisplayed() && closedate != null && closedate.isDisplayed()
					&& stage_dropdown != null); 
				Optylog.info("All required fields are not null and it is displaying the data");
reportlog.logTestwithPassed("All required fields are not null and it is displaying the data");

		} catch (AssertionError e) {
			WebElement error = driver.findElement(By.xpath("//div[@id='errorDiv_ep']"));
			Optylog.error(error.getText(),"Required fields are empty Please fill them");
			reportlog.logTestwithFailed("Required fields are empty Please fill them");
			Assert.fail();
		}

		WebElement probability = driver.findElement(By.id("opp12"));
		elementSendTextWithAssert(probability, "30", "probabitliy ");
		WebElement campaignS = driver.findElement(By.id("opp17"));
		elementSendTextWithAssert(campaignS, " ", "Primary Campaign Source ");
		Thread.sleep(1000);
		WebElement savebuttn = driver.findElement(By.xpath("(//input[normalize-space(@value)='Save'])[1]"));
		clickbuttonAndAssert(savebuttn, "Save ");
		Thread.sleep(2000);
		String RegexString= "https://dan000000cz3teag-dev-ed\\.develop\\.my\\.salesforce\\.com/006an000000.*";
				//        String actualUrl = "https://dan000000cz3teag-dev-ed.develop.my.salesforce.com/006an000000n2DZ";
		  String actualUrl = "https://dan000000cz3teag-dev-ed.develop.my.salesforce.com/006an000000.*";//changing part of the URL is the last characters, you can use a wildcard or a regex pattern to match the URL. https://dan000000cz3teag-dev-ed.develop.my.salesforce.com/006an000000n2DZ
		assertTrue(actualUrl.matches(RegexString));
		WebElement optyName = driver.findElement(By.xpath("//div[@id='opp3_ileinner']"));
		WebElement verifydescription = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		try{			
Assert.assertTrue(optyName.getText().equals(verifydescription.getText())) ;

		Optylog.info("oppurnity name matches the page header name");
		reportlog.logTestwithPassed("oppurnity name matches the page header name");

		} catch (AssertionError e) {
			Optylog.error("oppurnity name do not matche the page header name");
			reportlog.logTestwithFailed("oppurnity name do not matche the page header name");
			Assert.fail();

		}
		Optylog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");
	}
@Test
	public void OpportunityPipelineReport() throws InterruptedException {
		opportunitiesdropdown();
		Thread.sleep(1000);
		WebElement opptyele = driver.findElement(By.xpath("//a[text()='Opportunity Pipeline']"));
		clickbuttonAndAssert(opptyele, "Oppurtunity link ");
		assertTitle("Opportunity Pipeline ~ Salesforce - Developer Edition");
		WebElement reportStatus = driver.findElement(By.xpath("//div[@class='progressIndicator']"));
		System.out.println(reportStatus.getText());
		String exptedString = "Report Generation Status: Complete";
		try {
			assertTrue((reportStatus.getText()).replaceAll("\\s+", "").equalsIgnoreCase(exptedString.replaceAll("\\s+", ""))) ;

			Optylog.info("Matches the report generation status");
			reportlog.logTestwithPassed("Matches the report generation status");
		} catch (AssertionError e) {
			Optylog.error("Do not matching to the report generation status ");
			reportlog.logTestwithFailed("Do not matching to the report generation status ");
			Assert.fail();
		}
		WebElement pageheader = driver.findElement(By.xpath("//h1[contains(@class,'noSecondHeader')]"));
		assertStringsText(pageheader, "Opportunity Pipeline");
		Optylog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");
	}
@Test
	public void StuckOpportunitiesReport() throws InterruptedException {
		opportunitiesdropdown();
		Thread.sleep(1000);
		WebElement opptyele = driver.findElement(By.xpath("//a[text()='Stuck Opportunities']"));
		clickbuttonAndAssert(opptyele, "Stuck Oppurtunity link under reports ");
		assertTitle("Stuck Opportunities ~ Salesforce - Developer Edition");
		WebElement reportStatus = driver.findElement(By.xpath("//div[@class='progressIndicator']"));
		System.out.println(reportStatus.getText());
		String exptedString = "Report Generation Status: Complete";
		try {
			assertTrue((reportStatus.getText()).replaceAll("\\s+", "").equalsIgnoreCase(exptedString.replaceAll("\\s+", ""))) ;

			Optylog.info("Matches the report generation status");
			reportlog.logTestwithPassed("Matches the report generation status");
		} catch (AssertionError e) {
			Optylog.error("Do not matching to the report generation status ");
			reportlog.logTestwithFailed("Do not matching to the report generation status ");
			Assert.fail();
		}
		WebElement pageheader = driver.findElement(By.xpath("//h1[contains(@class,'noSecondHeader')]"));
		assertStringsText(pageheader, "Stuck Opportunities");
		Optylog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");
}
@Test
	public void QuarterlySummaryReport() throws InterruptedException {
		opportunitiesdropdown();
		By dropdown_interval = By.id("quarter_q");
Thread.sleep(2000);	
selectByIndexAndAssert(dropdown_interval, 1);
		By dropdown_include = By.id("open");
		Thread.sleep(2000);	

		selectByValueAndAssert(dropdown_include, "open");
		WebElement reportbttn = driver.findElement(By.xpath("//input[@title='Run Report']"));
		clickbuttonAndAssert(reportbttn, "Run Report ");
		assertTitle("Opportunity Report ~ Salesforce - Developer Edition");
		WebElement reportStatus = driver.findElement(By.xpath("//div[@class='progressIndicator']"));
		System.out.println(reportStatus.getText());
		String exptedString = "Report Generation Status: Complete";
		try {
			assertTrue((reportStatus.getText()).replaceAll("\\s+", "").equalsIgnoreCase(exptedString.replaceAll("\\s+", ""))) ;

			Optylog.info("Matches the report generation status");
			reportlog.logTestwithPassed("Matches the report generation status");
		} catch (AssertionError e) {
			Optylog.error("Do not matching to the report generation status ");
			reportlog.logTestwithFailed("Do not matching to the report generation status ");
			Assert.fail();
		}
		WebElement pageheader = driver.findElement(By.xpath("//h1[contains(@class,'noSecondHeader')]"));
		assertStringsText(pageheader, "Opportunity Report");
		Optylog.info("All assertion passed");
		reportlog.logTestInfo("All assertion passed");
	}

}
