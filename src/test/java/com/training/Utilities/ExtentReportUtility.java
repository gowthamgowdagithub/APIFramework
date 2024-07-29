package com.training.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.training.Base.APIHelper;

public class ExtentReportUtility {
	APIHelper apiHelper;
	public static ExtentReports report;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;
	private static ExtentReportUtility extentObject;
	
	private ExtentReportUtility() {
		
	}
	
	public static ExtentReportUtility getInstance() {
		if(extentObject==null) {
			extentObject=new ExtentReportUtility();
		}
		return extentObject;	
	}
	
	public void startExtentReport() {
		report=new ExtentReports();
		spark=new ExtentSparkReporter(Constants.Extent_Report);
		report.setSystemInfo("HostName", "GitHub");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("Username", "Gowtham Gowda");
		
		spark.config().setDocumentTitle("GitHub API Test Execution Report");
		spark.config().setReportName("GitHub API regression Test");
		spark.config().setTheme(Theme.DARK);
		report.attachReporter(spark);
	}
	
	public void startSingleTestReport(String methodName) {
		test=report.createTest(methodName);
	}
	
	public void EndReport() {
		report.flush();
	}
	
	public void logTestInfo(String text) {
		test.log(Status.INFO,text);
		test.info(text);
	}
	
	public void logTestpassed(String text) {
		test.log(Status.PASS,MarkupHelper.createLabel(text, ExtentColor.GREEN));
		test.info(text);
	}
	
	public void logTestFailed(String text) {
		test.log(Status.FAIL, MarkupHelper.createLabel(text, ExtentColor.RED));
		test.info(text);
	}
	
	public void logTestFailedWithException(Throwable e) {
		test.log(Status.FAIL,e);
		test.info(e);
		}

}
