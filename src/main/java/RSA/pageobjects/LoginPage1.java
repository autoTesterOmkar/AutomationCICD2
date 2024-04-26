package RSA.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSA.Abstract.AbstractComp1;

public class LoginPage1 extends AbstractComp1{
	
	WebDriver driver;
	
	public LoginPage1(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy (id="userEmail")
	 WebElement UserID;
	@FindBy (id="userPassword")
	WebElement Password;
	@FindBy (id="login")
	WebElement Login;
	@FindBy (css="[class*='flyInOut']")
	WebElement errorMsg;
	@FindBy (css="button[routerlink='/dashboard/myorders']")
	WebElement HistoryOrders;
	@FindBy (xpath="//tbody/tr/th")
	List<WebElement> Orders;
	
	public void gotoURL() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public void Login(String username, String password) {
		UserID.sendKeys(username);
		Password.sendKeys(password);
		Login.click();
	}
	
	public String ErrorMsg() {
		WaitForElementtoVisible(errorMsg);
		String ErrMsg =errorMsg.getText();
		return ErrMsg;
		}
	
	public boolean OrderHistory(String OrderID) throws InterruptedException {
		WaitForElementtoVisible(HistoryOrders);
		HistoryOrders.click();
		Thread.sleep(2000);
		boolean match=Orders.stream().anyMatch(Order -> Order.getText().equals(OrderID));
		return match;
	}
	
	

}
