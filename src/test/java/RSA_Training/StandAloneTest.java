package RSA_Training;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
	
	
    public static void main(String [] args) throws InterruptedException {
	    String ProdName ="ZARA COAT 3";
    	WebDriverManager.chromedriver().setup();
    	WebDriver driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	driver.get("https://rahulshettyacademy.com/client/");
    	driver.findElement(By.id("userEmail")).sendKeys("omkarpawar@gmail.com");
    	driver.findElement(By.id("userPassword")).sendKeys("Omkarpawar@1");
    	driver.findElement(By.id("login")).click();
    	WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
    	
    	w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='col-lg-4']")));
    	
    	List<WebElement> Prodcuts=driver.findElements(By.cssSelector("div[class*='col-lg-4']"));
    	WebElement Prod=Prodcuts.stream().filter(s-> s.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
    	Prod.findElement(By.cssSelector("button + button")).click();
    	
        w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
    	w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ngx-spinner[class*=ng]")));
    	System.out.println("Test Done");
    	
    	Thread.sleep(5000);
    	driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
 
    	List<WebElement> CartProducts=driver.findElements(By.cssSelector("ul[class*=cartWrap ] h3"));
    	Boolean match= CartProducts.stream().anyMatch(c-> c.getText().equalsIgnoreCase(ProdName));
    	Assert.assertTrue(match);
    	driver.findElement(By.cssSelector("li[class=totalRow] button")).click();
    	
    	driver.findElement(By.cssSelector("div[class*='form-group'] input")).sendKeys("Ind");
    	List<WebElement> Country=driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button/span "));
        for ( WebElement MyCountry : Country) {
        	if(MyCountry.getText().endsWith("India")){
        		MyCountry.click();
        		break;
        	}
        }
    	driver.findElement(By.cssSelector("a[class*='btnn']")).click();
    	String OrderID=driver.findElement(By.cssSelector("label[class*='ng-star-inserted']")).getText();
    	System.out.println(OrderID);
    }

}
