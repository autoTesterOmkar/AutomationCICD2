package RSA.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSA.Abstract.AbstractCompoenent;

public class OrderHistoryPage extends AbstractCompoenent {
	WebDriver driver;
	
	@FindBy (css="button[routerlink='/dashboard/myorders']")
	WebElement OrderHistoryBtn;
	
	@FindBy (css=" tbody tr th")
	List <WebElement> OrderID;
	
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
	
	public boolean VerifyOrderDisplay(String OrderId) {
		 boolean match = false;
		OrderHistoryBtn.click();
		//boolean match =OrderID.stream().anyMatch(s-> s.getText().contains(OrderId));
		//boolean match=OrderID.stream().anyMatch(s-> s.getText().split(" ")[1].startsWith(OrderId));
		for (int i=1; i<=OrderID.size(); i++) {
			String OrderIdgetText=driver.findElement(By.xpath("//tbody/tr/th["+i+"]")).getText();
			String MainOrderNo=OrderId.split(" ")[1];
			
			 if (OrderIdgetText.startsWith(MainOrderNo)) {
				 System.out.println(OrderIdgetText);
				 match = true;
				 break;
			 }
			 //return match;
		}
		
		return match;
	}
	
	
	

	

}
