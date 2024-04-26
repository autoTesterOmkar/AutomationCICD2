package RSA.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG1 {

	
	
	public static  ExtentReports getReportObject() {
		String Path = System.getProperty("user.dir")+ "\\reports1\\index.html\\" ;
		ExtentSparkReporter ESR = new ExtentSparkReporter(Path);
		
		ESR.config().setReportName("Web Automation Report");
		ESR.config().setDocumentTitle("Test Results");
		
		ExtentReports ER = new ExtentReports();
		ER.attachReporter(ESR);
		ER.setSystemInfo("Tester", "Omie Bhau");
		return ER; 
	}
	
	

}
