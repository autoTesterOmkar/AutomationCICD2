package RSA_Training.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RSA.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	LoginPage LP;
	String OrderId;
	 
	public WebDriver intializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\RSA\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		System.out.println(browserName);
		
		if (browserName.startsWith("chrome")) {
			ChromeOptions option = new ChromeOptions(); 
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				option.addArguments("headless");
			}
		    driver = new ChromeDriver(option);
		    driver.manage().window().setSize(new Dimension(1440, 900));
		    
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\Automation_Backup\\Browser Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJSONDataToMap(String filepath) throws IOException {
		
		String JSONContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List <HashMap<String, String>> data =mapper.readValue(JSONContent, new TypeReference <List<HashMap<String, String>>>() {
		});
		
		return data;
		
		
		
	}
	
	public String getScreenShot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File (System.getProperty("user.dir")+"//reports//"+ testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testcaseName + ".png";
	}
	
	
	@BeforeMethod (alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		driver=intializeDriver();
        LP = new LoginPage(driver);
		LP.GotoURL();
		return LP;
	}

	@AfterMethod 
	public void Teardown() {
		//driver.close();
	}

	
}
