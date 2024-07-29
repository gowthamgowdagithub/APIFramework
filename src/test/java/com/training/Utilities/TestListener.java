package com.training.Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class TestListener implements ITestListener{
	public static ExtentReportUtility extentReport=ExtentReportUtility.getInstance();
	public void onTestStart(ITestResult result) {
		extentReport.startSingleTestReport(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		extentReport.logTestpassed(result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		extentReport.logTestFailed(result.getMethod().getMethodName());
		extentReport.logTestFailedWithException(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		extentReport.logTestInfo("The testcase is skipped"+ result.getName());
	}

	public void onStart(ITestContext context) {
		extentReport.startSingleTestReport(context.getName());
	}

	public void onFinish(ITestContext context) {
		extentReport.EndReport();
	}
	

}
