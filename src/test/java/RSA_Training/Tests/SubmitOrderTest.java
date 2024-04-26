package RSA_Training.Tests;


import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RSA.TestCompoenent.Retry;
import RSA.pageobjects.CartPage;
import RSA.pageobjects.LoginPage;
import RSA.pageobjects.OrderHistoryPage;
import RSA.pageobjects.PaymentPage;
import RSA.pageobjects.ProductCatalouge;

public class SubmitOrderTest extends BaseTest {
	
	
	
	   @Test (dataProvider="getData" , groups="Purchase")   //String Email, String Password, String ProdName
       public void SubmitOrder(HashMap <String, String> input) throws IOException {
	    //String ProdName ="ZARA COAT 3";
        //LoginPage LP =launchApplication();
    	ProductCatalouge PC= LP.Login(input.get("email") , input.get("password"));
    	List<WebElement> Products=PC.SelectionProducts();
    	CartPage CP= PC.AddToCart(input.get("product"));
    	CP.Cartpage();
    	boolean match =CP.ConfirmCart(input.get("product")); 
    	Assert.assertTrue(match);
    	PaymentPage PP = new PaymentPage(driver);
    	PP.SelectCountry();
    	OrderId=PP.PlaceOrder();
    	System.out.println(OrderId);
    	
    }
	   
	   @Test //(dependsOnMethods="SubmitOrder" , retryAnalyzer = Retry.class)
	   public void VerifyProduct() {
		 ProductCatalouge PC= LP.Login("omkarpawar@gmail.com", "Omkarpawar@1");
		 OrderHistoryPage OH = new OrderHistoryPage(driver);
	     boolean match= OH.VerifyOrderDisplay(OrderId);
		 Assert.assertTrue(match);
		   
	   }
	   
	   /*@DataProvider
	   public Object[][] getData() {
		   return new Object[][] {{"omkarpawar@gmail.com", "Omkarpawar@1", "ZARA COAT 3"} , {"Supriyapawar@gmail.com", "Supriyapawar@1", "ADIDAS ORIGINAL"}};
	  
	   //Another way using HashMap
	    *  HashMap <String, String> map= new HashMap <String, String>();
		   map.put("email", "omkarpawar@gmail.com");
		   map.put("password", "Omkarpawar@1");
		   map.put("product", "ZARA COAT 3");
		   
		   HashMap <String, String> map1= new HashMap <String, String>();
		   map1.put("email", "Supriyapawar@gmail.com");
		   map1.put("password", "Supriyapawar@1");
		   map1.put("product", "ADIDAS ORIGINAL");
		   return new Object[][] {{map} , {map1}};
	   
	   }*/
	   
	   @DataProvider 
	   public Object[][] getData() throws IOException{
		   
		   List<HashMap<String, String>> data =getJSONDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\RSA_Training\\Data\\Data.json");
		  
		   
		   return new Object[][] {{data.get(0)}, {data.get(1)}};
		   
	   }
	   
	   
	   
	   

}
