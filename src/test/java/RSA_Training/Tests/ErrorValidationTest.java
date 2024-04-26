package RSA_Training.Tests;


import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import RSA.TestCompoenent.Retry;
import RSA.pageobjects.CartPage;
import RSA.pageobjects.LoginPage;
import RSA.pageobjects.PaymentPage;
import RSA.pageobjects.ProductCatalouge;

public class ErrorValidationTest extends BaseTest {
	
	   @Test
       public void ErrorValidation() throws IOException {
	    String ProdName ="ZARA COAT 3";
        //LoginPage LP =launchApplication();
    	LP.Login("omkarpawar@gmail.com", "Omkapawar@1");
        Assert.assertEquals("Incorrect email or password.", LP.ErrorMessage());
        }
	   
	   @Test 
	   public void ProductErrorValidation() {
		   String ProdName ="ZARA COAT 3";
	    	ProductCatalouge PC=LP.Login("Supriyapawar@gmail.com", "Supriyapawar@1");
	    	List<WebElement> Products=PC.SelectionProducts();
	    	CartPage CP= PC.AddToCart(ProdName);
	    	CP.Cartpage();
	    	boolean match =CP.ConfirmCart("ZARA COAT 33");
	    	Assert.assertFalse(match);
	   }

}
