package RSA.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSA.Abstract.AbstractCompoenent;

public class LoginPage extends AbstractCompoenent {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id="userEmail")
	WebElement Username;
	
	@FindBy (id="userPassword")
	WebElement Password;
	
	@FindBy (id="login")
	WebElement Login;
	
	@FindBy (css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public void GotoURL() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalouge Login(String username, String password) {
		ProductCatalouge PC= new ProductCatalouge(driver);
		Username.sendKeys(username);
		Password.sendKeys(password);
		Login.click();
		return PC;
	}
	
	public String ErrorMessage() {
		waitforWebElementToShow(errorMsg);
		return errorMsg.getText();
		
	}

}
