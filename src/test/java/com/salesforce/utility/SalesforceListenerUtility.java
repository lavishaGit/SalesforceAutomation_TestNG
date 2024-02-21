package com.salesforce.utility;

import java.awt.dnd.DropTargetDragEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.salesforce.base.BaseClass;

public class SalesforceListenerUtility extends BaseClass implements ITestListener,ISuiteListener {
	Logger ListenerLog = LogManager.getLogger();
	private static ExtentUtility report = ExtentUtility.getinstance();
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		ListenerLog.info(suite.getName()+".......Suite execution started........");
		report.startExtentReport(); 
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		ListenerLog.info(suite.getName()+".......Suite execution Finished ........");
		report.endReport();
	}

	

//This Iltestlistener listen to the events before exceuting any test methods 

	// related to the @test level

	@Override
	public void onStart(ITestContext context) {
		// context contains all the informa about the tests
		ListenerLog.info(context.getName()+".......<test> execution started........");
		report.startExtentCreateReport(context.getName()) ;// attach the extentreport to the sparkhtml
	}

	// After any <test> starts exceution
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ListenerLog.info(context.getName()+".......<test> execution completed........");
		//report.endReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ListenerLog.info(result.getMethod().getMethodName() + ".......test execution started........");
		//report.startExtentCreateReport(result.getMethod().getMethodName()+".......new Test container for report is created........");
		//report.startExtentCreateReport();
	}

//related to the @test level
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ListenerLog.info(result.getMethod().getMethodName() + ".......test execution sucess........");
		report.logTestwithPassed(result.getMethod().getMethodName() + ".......test execution sucess........");

	}
	// related to the @test level

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ListenerLog.error(result.getMethod().getMethodName() + ".......test execution completed with failure........");
		report.logTestwithFailed(
				result.getMethod().getMethodName() + ".......test execution completed with failure........");
		String filename = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String path = Constants.screenshotsFilepath + filename + ".png";
		takescreenshot(path);
		report.logTestfailwithScreenshot(path);
		report.logTestfailwithException(result.getThrowable());

	}
	// related to the @test level

	// before any <test> starts exceution this will get executed,one time excution

	
}
