package com.automatio.restassured.tests;

import java.io.File;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.automation.restassured.utilities.ReadPropertiesData;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

	public RequestSpecification request;
	public Response response;
	
	static ExtentSparkReporter reporter;
	static ExtentReports reports;
	static ExtentTest test;
	
	@BeforeSuite
	public void testSetUp() {
		ReadPropertiesData.loadPropertiesFile();
		File file = new File("ReportHtml.html");
		reporter = new ExtentSparkReporter(file);
		reports = new ExtentReports();
		reports.attachReporter(reporter);
	}
	
	
	@BeforeMethod
	public void requestObjectInitilization() {
		request = RestAssured.with();
		request.baseUri("http://localhost:8080/api/");
	}
	
	@AfterMethod
	public void prepareReport(ITestResult result) {
		test = reports.createTest(result.getName());
		
		if(result.isSuccess()) {
			test.pass(response.getTime()/1000+ " sec");
		}else {
			test.fail(result.getThrowable());
		}
	}
	
	@AfterSuite
	public void flushReport() {
		reports.flush();
	}
	
}
