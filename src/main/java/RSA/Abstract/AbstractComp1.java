package RSA.Abstract;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComp1 {
	
	WebDriver driver;
	
	public AbstractComp1(WebDriver driver) {
		this.driver=driver;
	}
	
	public void WaitForElementtoVisible(WebElement ele) {
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(ele));
	}
	

}
