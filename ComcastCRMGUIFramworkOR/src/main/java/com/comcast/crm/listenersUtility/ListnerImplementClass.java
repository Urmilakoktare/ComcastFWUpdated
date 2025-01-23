package com.comcast.crm.listenersUtility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

import crm.comcast.crm.basetest.BaseClass;

public class ListnerImplementClass implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public static ExtentSparkReporter spark;
	 public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", " ");
		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("C:./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Environment information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("===== ======>" + result.getMethod() + ">=====START====");
		 test = report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO, result.getMethod().getMethodName()+"===>STARTED<====");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("===== ======>" + result.getMethod() + ">=====END====");
		 test.log(Status.PASS, result.getMethod().getMethodName()+"===>COMPLETED<====");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		 String testName = result.getMethod().getMethodName();
	//	WebDriver driver=new ChromeDriver();
	//	LocalDateTime ldt = LocalDateTime.now();
    //  String timeStamp = ldt.toString().replaceAll(":", "-");

		// Take a screen shot
		// TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		// File screenshot = ts.getScreenshotAs(OutputType.FILE);
//		try {
//			FileUtils.copyFile(screenshot, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    File dest = new File("./screenshot/" + timeStamp + "test.png");

		// take a screenshot in extent report
		TakesScreenshot eDriver = (TakesScreenshot) BaseClass.sdriver;
		String filePath = eDriver.getScreenshotAs(OutputType.BASE64);

		String time = new Date().toString().replace(" ", "_").replace(":", " ");
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
		 test.log(Status.FAIL, result.getMethod().getMethodName()+"===>FAILED<====");
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
