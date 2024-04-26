package RSA.TestCompoenent;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RSA.resources.ExtentReportsNG;
import RSA_Training.Tests.BaseTest;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	
	ExtentReports extent=ExtentReportsNG.getReportObject();
	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extendTest.set(test);
	}
	
	public void onTestFailure(ITestResult result) {
		extendTest.get().fail(result.getThrowable());
		String Filepath=null;
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			/*here actually you have to get WebDriver so to get it use of varaible result which contains all information of test 
			so we getTestClass() means you go to Testng.xml file of that in that we get Class name then getRealClass() means you go to actualclass and search 
			for field driver because driver we declared outside of method and inside class then when got driver then getInstance of driver and pass it to 
			getScreenshot menthod.*/
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		try {
			Filepath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extendTest.get().addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName()); 
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	public void onTestSuccess(ITestResult result) {
		extendTest.get().log(Status.PASS, "Test is Passed"); 
		
	}
    public void onTestSkipped(ITestResult result) {
		
	}
	
	public void onFinish (ITestContext Context) {
		extent.flush();
	}
	

}
