package RSA_Training.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RSA.pageobjects.LoginPage1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest1 {
	public WebDriver driver;
	LoginPage1 LP;
	
	

	
	public WebDriver intializedriver() throws IOException {
		Properties Prop = new Properties();
		FileInputStream FIS= new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\RSA\\resources\\GlobalData.properties"));
		Prop.load(FIS);
		String browser= Prop.getProperty("browser");
		 
		if (browser.equals("chrome")) {
		WebDriverManager.chromedriver().setup();  //WebDriverManager
		driver= new ChromeDriver();
		}else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\Automation_Backup\\Browser Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		return driver;
	}
	@BeforeMethod
	public LoginPage1 launch() throws IOException {
		driver=intializedriver();
	    LP = new LoginPage1(driver);
		return LP;
	}
	
	public List<HashMap<String, String>> getDataJSON(String Filepath) throws IOException {
		String JSONCont= FileUtils.readFileToString(new File(Filepath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(JSONCont, new TypeReference <List <HashMap<String, String>>> () {
		});
				return data;	
	}
	
	public String getScreenShot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports1//"+testcaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports1//"+testcaseName+".png";
		
	}
	
}
