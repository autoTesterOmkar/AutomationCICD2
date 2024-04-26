package RSA.Abstract;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractCompoenent {
	
	WebDriver driver;
	
	
	public AbstractCompoenent(WebDriver driver) {
		this.driver=driver;
	}


	public void waitforElementToShow(By FindBY) {
		  WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
	  	  w.until(ExpectedConditions.visibilityOfElementLocated(FindBY));
	  }
	public void waitforWebElementToShow(WebElement ele) {
		  WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
	  	  w.until(ExpectedConditions.visibilityOf(ele));
	  }
	
	public void waitforElementTODisappear(By FindBY) {
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(FindBY)));
	}

}
