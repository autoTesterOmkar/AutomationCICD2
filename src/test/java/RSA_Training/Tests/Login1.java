package RSA_Training.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RSA.pageobjects.LoginPage1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login1 extends BaseTest1 {
	WebDriver driver; 
	
	@Test  (dataProvider="getData")
	public void Demo( HashMap <String, String> input) throws IOException {
		LP.gotoURL();
		LP.Login(input.get("email"), input.get("password"));
		Assert.assertEquals("Incorrect email or password.", LP.ErrorMsg());
	}
	
	@Test (dependsOnMethods="Demo") 
	public void Demo2() throws InterruptedException {
		LP.gotoURL();
		LP.Login("omkarpawar@gmail.com", "Omkarpawar@1");
		//Assert.assertEquals("Incorrect email or password.", LP.ErrorMsg());
		LP.OrderHistory("6605157fa86f8f74dcaf3651");
	}
	
	@DataProvider
	public Object [][] getData() throws IOException{
		/*return new Object[][] {{"omkarpawar@gmail.com", "Omkarpawar@1"} , {"Supriyapawar@gmail.com", "Supriyapawar@1"}};
		
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("email", "omkarpawar@gmail.com");
		map.put("password", "Omkarpawar@1");
		
		HashMap<String, String> map1= new HashMap<String, String>();
		map1.put("email", "Supriyapawar@gmail.com");
		map1.put("password", "Supriyapawar@1");
		
		return new Object[][] {{map} , {map1}};*/
		
		List<HashMap<String, String>> data=getDataJSON(System.getProperty("user.dir")+"\\src\\test\\java\\RSA_Training\\Data\\Data1.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}}; 
		
		}
		
	}
	



