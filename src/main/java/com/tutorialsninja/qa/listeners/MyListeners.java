package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.util.MyExtentReport;
import com.tutorialsninja.qa.util.TestUtil;

import io.qameta.allure.Allure;

public class MyListeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	Allure allureReport;
	@Override
	public void onStart(ITestContext context) {
		//Invoking the extent report
		extentReport = MyExtentReport.generateExtentReport();
		System.out.println("Test Execution is started");
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		// testname = result.getName();
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " Started Execution");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + " got successfully executed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destinationPath = TestUtil.captureScreenshot(driver, result.getName());
		Allure.addAttachment("Screenshot", destinationPath);
		extentTest.addScreenCaptureFromPath(destinationPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " Test is Failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " Test is Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Execution is finished");
		
		//extentReport.flush() method is used to ensure that all the information is written in the report
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			//Opens the default browser and open the extent report
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
