package com.salesforce.automation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.sym.Name;
import com.salesforce.base.BaseSalesForceClass;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver.Unique;

public class SalesforceAccounts_Testcases extends BaseSalesForceClass {
	String uuid=UUID.randomUUID().toString().replace("-", "");

	protected Logger myaccountslog = LogManager.getLogger();

	@Test
	public void createAccount() throws InterruptedException {
		assertCurrentURL("https://login.salesforce.com/");

		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
		String accountName="Acc_"+uuid.substring(0,5);
		WebElement acctab = driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
		clickbuttonAndAssert(acctab, "Accounts ");
		WebElement newbuttn = driver.findElement(By.xpath("//input[normalize-space(@value)='New']"));
		clickbuttonAndAssert(newbuttn, "New ..");
		Thread.sleep(1000);
		assertTitle("Account Edit: New Account ~ Salesforce - Developer Edition");

//Verify Accounts page is displayed with correct <username>
		WebElement user_name = driver
				.findElement(By.xpath("//div[@id='head_1_ep']//following-sibling::div[1]//tr[1]/td[2]"));
		waitForVisibilty(user_name, 40, "Account name ");
		String strobjString = "Sudeepa Gallo";
		assertStringsText(user_name, strobjString);
		WebElement acc_name = driver.findElement(By.id("acc2"));
		elementSendTextWithAssert(acc_name, "Z200", "Account name  ");

		By type_dropdown = By.id("acc6");
		selectByValueAndAssert(type_dropdown, "Technology Partner");
		By priority_dropdown = By.id("00Nan000000KPsL");
		selectByIndexAndAssert(priority_dropdown, 1);
		Thread.sleep(1000);
		WebElement savebuttn = driver.findElement(By.xpath("//input[normalize-space(@value)='Save']"));
		clickbuttonAndAssert(savebuttn, "Save ");
		String header_nameverify = driver.findElement(By.className("topName")).getText();
		myaccountslog.info("header_nameverify" + header_nameverify);

		String acc_nameverify = driver.findElement(By.id("acc2_ileinner")).getText();

		acc_nameverify = acc_nameverify.replace("[View Hierarchy]", "");
		myaccountslog.log(null, "acc_nameverify" + acc_nameverify);
		assertStringsEqual(acc_nameverify, header_nameverify);
		myaccountslog.info("All assertions Passed");
		reportlog.logTestInfo("All assertions Passed");
	}

	@Test
	public void Createnewview() throws InterruptedException {
		assertCurrentURL("https://login.salesforce.com/");

		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
		String viewUniName="Uacc_"+uuid.substring(0,5);
		String viewAccName="Uacc_"+uuid.substring(0,4);

		WebElement acctab = driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
		clickbuttonAndAssert(acctab, "Accounts ");
		assertTitle("Accounts: Home ~ Salesforce - Developer Edition");
		WebElement newview = driver.findElement(By.xpath("//span[@class='fFooter']/a[2]"));
		waitForVisibilty(newview, 30, "New View link");
		clickbuttonAndAssert(newview, "New view link is ");

		assertTitle("Accounts: Create New View ~ Salesforce - Developer Edition");
		String acttext = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
		String exptext = "Create New View";
		assertStringsEqual(acttext, exptext);
		WebElement viewname = driver.findElement(By.id("fname"));
		// change value everttime this test run
		elementSendTextWithAssert(viewname, viewAccName, "New view ");
		WebElement viewUniqueName = driver.findElement(By.id("devname"));
		// change value evert time this test run

		elementSendTextWithAssert(viewUniqueName, viewUniName, exptext);
		WebElement hoverinfo = driver.findElement(By.className("mouseOverInfoOuter"));// class="mouseOverInfo"
		actionCall();
		mouseHover_Interaction(hoverinfo);
		WebElement savebuttn = driver
				.findElement(By.xpath("//div[@class='pbHeader']//input[normalize-space(@value)='Save']"));
		clickbuttonAndAssert(savebuttn, "Save ");
		myaccountslog.info("All assertions Passed");
		reportlog.logTestInfo("All assertions Passed");

	}

	@Test //(dependsOnMethods = "Createnewview")
	public void editView() throws InterruptedException {
		Createnewview();
		// By accview_dropdown = By.className("title");
		// selectByIndexAndAssert(accview_dropdown, 1);
		//WebElement accview = driver.findElement(By.className("title"));
		Thread.sleep(4000);
		WebElement editbuttn = driver.findElement(By.xpath("//div[@class='filterLinks']/a[1]"));

		clickbuttonAndAssert(editbuttn, "Edit link ");

		assertTitle("Accounts: Edit View ~ Salesforce - Developer Edition");

		WebElement viewname = driver.findElement(By.id("fname"));
		elementSendTextWithAssert(viewname, "newacctView1", "New updated view name  ");
		By field_dropdown = By.id("fcol1");
		selectByVisibleTextAndAssert(field_dropdown, "Account Name");

		By operator_dropdown = By.id("fop1");
		selectByValueAndAssert(operator_dropdown, "c");
		Thread.sleep(1000);

		WebElement valuename = driver.findElement(By.id("fval1"));
		elementSendTextWithAssert(valuename, "12", "Entered value ");
		Thread.sleep(1000);

		WebElement savebuttn = driver
				.findElement(By.xpath("//div[@class='pbHeader']//input[normalize-space(@value)='Save']"));
		clickbuttonAndAssert(savebuttn, "Save ");
		assertTitle("Accounts ~ Salesforce - Developer Edition");
		WebElement selectedOption = driver.findElement(By.xpath("//option[@selected='selected']"));
		String str = selectedOption.getText();
		myaccountslog.info("All assertions Passed");
		reportlog.logTestInfo("All assertions Passed");

	}

	@Test
	public void mergeAccount() throws InterruptedException {
		initialSetup();
		// headlessBrowser();
		WebElement acctab = driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
		clickbuttonAndAssert(acctab, "Accounts ");
		WebElement mergelink = driver.findElement(By.xpath("//div[@class='toolsContentRight']//ul/child::li[4]/span"));
		clickbuttonAndAssert(mergelink, "Merge accounts ");

		WebElement find = driver.findElement(By.id("srch"));
		WebElement findacclink = driver.findElement(By.xpath("//input[@value='Find Accounts']"));
		Set<String> validNames = new HashSet(Arrays.asList("Z100", "Z200", "Z300", "Z123"));

		List<WebElement> accnames = driver.findElements(By.xpath("//a[@class='accountMru']"));// span[@class=]
		for (WebElement accname : accnames) {

			String actualnameS = accname.getText();
			// Assert.assertEquals should be the expected value, but you're passing
			// validNames.contains("Z100"), which is a boolean expression. Instead, you
			// should check if actualnameS is contained within the validNames set
			// Assert.assertEquals(actualnameS, validNames.contains("Z100"), "Actual name "
			// + actualnameS + " does not match any valid names");
			Assert.assertTrue(validNames.contains(actualnameS),
					"Actual name " + actualnameS + "does not match any valid names");
			elementSendTextWithAssert(find, "Z100", "Valid existing account name ");
			clickbuttonAndAssert(findacclink, "Account link is ");
			break;
		}
		/*
		 * Assert.assertTrue(validNames.contains(actualnameS),"Actual name " +
		 * actualnameS + "does not match any valid names");
		 * elementSendTextWithAssert(find, "Z123", "Valid existing account name ");
		 * clickbuttonAndAssert(findacclink, "Account link is ");
		 * 
		 * Assert.assertTrue(validNames.contains(actualnameS),"Actual name " +
		 * actualnameS + "does not match any valid names");
		 * elementSendTextWithAssert(find, "Z300", "Valid existing account name ");
		 * clickbuttonAndAssert(findacclink, "Account link is "); } WebElement inputtxt
		 * = driver.findElement(By.xpath("//input[@id='srch']")); ; //check with 1
		 * letter elementSendTextWithAssert(inputtxt, "Z", "Textdata ");
		 * 
		 * String str = inputtxt.getText(); System.out.println(str); WebElement
		 * findacc_btn =
		 * driver.findElement(By.xpath("//input[@value='Find Accounts']"));
		 * waitForVisibilty(findacc_btn, 40, "Find account");
		 * clickbuttonAndAssert(findacc_btn, "Find Accounts ");
		 * 
		 * try { // Attempt to locate and interact with the element WebElement inputtxt1
		 * = driver.findElement(By.xpath("//input[@id='srch']")); ; inputtxt.click();
		 * inputtxt.clear();
		 * 
		 * WebElement hiddenElement = driver.findElement(By.id("srchbutton"));
		 * 
		 * // Scroll the element into view (JavascriptExecutor)
		 * driver).executeScript("arguments[0].scrollIntoView(true);", hiddenElement);
		 * 
		 * // Now you can interact with the element hiddenElement.click(); } catch
		 * (StaleElementReferenceException e) { // Log or print the exception message
		 * for debugging System.out.println("Stale Element Reference Exception: " +
		 * e.getMessage());
		 */
		// Re-locate the element and retry the interaction

		/*
		 * WebElement inputtxt1 = driver.findElement(By.xpath("//input[@id='srch']")); ;
		 * clickbuttonAndAssert(inputtxt1, "text area");
		 * elementSendTextWithAssert(inputtxt1, "Z1", "Textdata ");
		 * inputtxt1.sendKeys(Keys.ENTER);
		 * 
		 * // WebElement hiddenElement = driver.findElement(By.id("srchbutton"));
		 * 
		 * // Scroll the element into view // (JavascriptExecutor) //
		 * driver).executeScript("arguments[0].scrollIntoView(true);", hiddenElement);
		 * 
		 * // Now you can interact with the element // hiddenElement.click();
		 * 
		 * // WebElement findacc_btn1 =
		 * driver.findElement(By.xpath("//input[@value='Find // Accounts']")); //
		 * (JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new //
		 * Event('click'))", findacc_btn1 ); //
		 * 
		 * 
		 * //str1=str1+""; // WebElement inputtxt1 =
		 * driver.findElement(By.xpath("//input[@id='srch']"));; //
		 * inputtxt.sendKeys("Z11"); /// String str=inputtxt.getText();
		 * 
		 * 
		 * // Thread.sleep(3000); // (JavascriptExecutor)
		 * driver).executeScript("arguments[0].value = //
		 * arguments[1];", inputtxt, "Z100");
		 * 
		 * // WebElement inputtxt1 =
		 * driver.findElement(By.xpath("//input[@id='srch']"));; // inputtxt1.clear();
		 */
		List<WebElement> rows = driver.findElements(By.xpath("//tr[contains(@class,'dataRow')]"));
		System.out.println(rows.size());

		if (rows.size() > 1) {
			List<WebElement> checkboxs = driver.findElements(By.xpath("//tr[contains(@class,'dataRow')]/th"));
			int selectedCount = 0;

			for (WebElement checkbox : checkboxs) {
				Assert.assertTrue(!checkbox.isSelected());
				myaccountslog.info("Checkboxes are not selected");
				if (selectedCount < 2) {
					Thread.sleep(1000);
					checkbox.click();
					selectedCount++;
					// Add logging statements here
				} else {
					break; // Exit the loop once two checkboxes are selected
				}
				myaccountslog.info("two checkboxes are selected");
			}
		}
		// Assert.assertTrue(!checkbox.isSelected());

		/*
		 * checkbox.click(); myaccountslog.info("checkboxes are unclicked");
		 * reportlog.logTestwithPassed("checkboxes are unclicked");
		 * 
		 * 
		 * Thread.sleep(3000);
		 */

		/*
		 * for (int i = 0; i < Math.min(2, checkboxs.size()); i++) { //for (int i = 0; i
		 * < checkboxs.size(); i++) {
		 * 
		 * Thread.sleep(1000); checkboxs.get(i).click();
		 * myaccountslog.info(" two checkboxes are clicked");
		 * reportlog.logTestInfo(" two checkboxes are unclicked");
		 * 
		 * }
		 */

		WebElement next_btn1 = driver.findElement(By.xpath("//div[@class='pbTopButtons']/iNPUT[@CLASS='btn']  "));
		clickbuttonAndAssert(next_btn1, "next button ");
		Thread.sleep(2000);
		WebElement pageheader = driver.findElement(By.xpath("//div[@class='content']/h1"));
		waitForVisibilty(pageheader, 30, "Page header");
		assertStringsText(pageheader, "Merge My Accounts");
		WebElement mergeBttn = driver.findElement(By.xpath("(//input[normalize-space(@value)='Merge'])[1]"));
		waitForVisibilty(mergeBttn, 30, "Merge ");
		clickbuttonAndAssert(mergeBttn, "Merge ");
		myaccountslog.info("Accounts are merged");

		// New pop up for account merge confirmation is displayed and once the accounts
		// are merged
		switchToAlert();
		acceptAlert();
		myaccountslog.info("All assertions Passed");
		reportlog.logTestInfo("All assertions Passed");
	}

	@Test
	public void createAccountReport() throws InterruptedException {
		assertCurrentURL("https://login.salesforce.com/");

		initialSetup();
		assertTitle("Home Page ~ Salesforce - Developer Edition");
		Thread.sleep(2000);

		WebElement acctab = driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
		clickbuttonAndAssert(acctab, "Accounts ");
		WebElement accountsact_btn = driver.findElement(By.xpath("//div[@class='lbBody']/ul/li[2]/a"));
		clickbuttonAndAssert(accountsact_btn, "Accounts with last activity > 30 days ");
		assertTitle("Unsaved Report ~ Salesforce - Developer Edition");

		Thread.sleep(2000);
		WebElement dropdownbttn = driver.findElement(By.id("ext-gen20"));
		clickbuttonAndAssert(dropdownbttn, "Date field ");
		List<WebElement> dates_dropdown = driver
				.findElements(By.xpath("//div[@id='ext-gen265']/div[@class='x-combo-list-item']"));
		dropdownChoosebyText(dates_dropdown, "Created Date", "Choose created date field ");
		WebElement Frombttn = driver.findElement(By.id("ext-gen152"));
		waitForVisibilty(Frombttn, 30, "FROM BUTTON");
		clickbuttonAndAssert(Frombttn, "calender From Date  ");
		Thread.sleep(3000);
		List<WebElement> calenderFromdates = driver
				.findElements(By.xpath("//table[@class='x-date-inner']/tbody/tr/td[@title='Today']/a"));
		for (WebElement date : calenderFromdates) {
//Assert.assertEquals(date.getAttribute("title").trim(), "Today");

			date.click();
			myaccountslog.info("current From date is selected");
			reportlog.logTestwithPassed("current From date is selected");

		}
		WebElement Tobttn = driver.findElement(By.id("ext-gen154"));
		waitForVisibilty(Tobttn, 30, "To BUTTON");

		clickbuttonAndAssert(Tobttn, "calender To Date  ");
		

	//	List<WebElement> calenderTodates = driver.findElements(
				//By.xpath("//table[@class='x-date-inner']/tbody/tr/td[@title='Today']/a"));
	//	By.xpath("//td[@class='x-date-active x-date-selected']"));
		WebElement Todaybttn = driver.findElement(By.id("ext-gen276"));
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", Todaybttn );
				
	/*	for (WebElement date : calenderTodates) {
			// Assert.assertEquals(date.getAttribute("title").trim(), "Today");

			/// if (date.getAttribute("title").trim().equals("Today")) {
waitForVisibilty(date, 30, "Today ");
			date.click();
			myaccountslog.info("current To date is selected");
			reportlog.logTestwithPassed("current To date is selected");
		}*/

		WebElement Savebttn = driver.findElement(By.id("ext-gen49"));
		clickbuttonAndAssert(Savebttn, "Save");
		// switchToAlert();
		Thread.sleep(2000);

		WebElement reportname = driver.findElement(By.id("saveReportDlg_reportNameField"));
		waitForVisibilty(reportname, 30, "Report name field");
		elementSendTextWithAssert(reportname, "Report21", "Report name ");
		String reportName = driver.findElement(By.id("saveReportDlg_reportNameField")).getAttribute("value");
		String UniReportName="Repo_"+uuid.substring(0,5);
String expectedUniReportName =UniReportName;
		WebElement reportUniquename = driver.findElement(By.id("saveReportDlg_DeveloperName"));
		waitForVisibilty(reportUniquename, 30, "Report unique name field");

		elementSendTextWithAssert(reportUniquename, UniReportName, "Unique reportUnique Name ");

		WebElement saveRunbttn = driver
				.findElement(By.xpath("//table[@id='dlgSaveAndRun']//button[@class=' x-btn-text']"));
		waitForVisibilty(saveRunbttn, 30, "Save and Run button wait ");
		Thread.sleep(3000);
		clickbuttonAndAssert(saveRunbttn, "Save and Click");
		Thread.sleep(2000);

		WebElement headertitle = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
		waitForVisibilty(headertitle, 50, "header title");
		assertStringsText(headertitle, "Report21");

		myaccountslog.info("All assertions Passed");
		reportlog.logTestInfo("All assertions Passed");

	}

	public boolean serachString(String search) {
		return search.matches("a-zA-z0-9");
	}

	// div[@class="toolsContentRight"]//ul/child::li[4]/span

}
