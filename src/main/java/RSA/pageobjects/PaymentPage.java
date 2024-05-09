package RSA.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSA.Abstract.AbstractCompoenent;

public class PaymentPage extends AbstractCompoenent {
	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super (driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (css="div[class*='form-group'] input")
	WebElement CountrytextBox;
	@FindBy (xpath="//section[@class='ta-results list-group ng-star-inserted']/button/span ")
	List <WebElement> Country;
	
	String MyCountry="India";
	
	@FindBy (css="a[class*='btnn']")
	WebElement PlacedOrderBtn;  
	
    @FindBy (css="label[class*='ng-star-inserted']")
	WebElement CopyOrderId; 
    
    @FindBy (xpath="//h1[text()=' Thankyou for the order. ']")
    WebElement SucessText;
	
	public void SelectCountry() {
		CountrytextBox.sendKeys("Ind");
		for ( WebElement SelectCountry : Country) {
	    	if(SelectCountry.getText().endsWith(MyCountry)){
	    		SelectCountry.click();
	    		break;
	    	}
	     }
	}
	public String PlaceOrder() {
		PlacedOrderBtn.click();
		String OrderId= CopyOrderId.getText();
		return OrderId;
	}
	
	public String SuccessMsg() {
		String Msg= SucessText.getText();
		return Msg;
	}
	
}
