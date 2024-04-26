package RSA.TestCompoenent;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RSA.resources.ExtentReportsNG1;
import RSA_Training.Tests.BaseTest1;

public class Listeners1 extends BaseTest1 implements ITestListener {
	ExtentTest test;
	
	ExtentReports extent= ExtentReportsNG1.getReportObject();
	
	public void onTestStart(ITestResult result) {
		extent.createTest(result.getMethod().getMethodName());
		
	}
	
	public void onTestSuccess() {
		test.log(Status.PASS, "Test is passed");
	}
	
	public void OnTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		
		
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		String Filepath=null;
		try {
			Filepath=getScreenShot(result.getMethod().getMethodName() , driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName());
	}
	
	public void onFinish (ITestContext Context) {
		extent.flush();
	}

	
	
}
