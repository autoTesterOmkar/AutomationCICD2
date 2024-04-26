package RSA.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RSA.Abstract.AbstractCompoenent;

public class CartPage extends AbstractCompoenent {
	
	WebDriver driver;
	
	public CartPage (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (css="button[routerlink*='cart']")
	WebElement CartBtn;
	@FindBy (css="ul[class*=cartWrap ] h3")
	List <WebElement> CartProducts; 
	@FindBy (css="li[class=totalRow] button")
	WebElement CheckoutBtn;
	
	
	//Test is Done
	public void Cartpage() {
		CartBtn.click();
	}
	
	public Boolean ConfirmCart(String Productname) {
		List<WebElement> Ls= CartProducts;
		Boolean match=Ls.stream().anyMatch(c-> c.getText().equalsIgnoreCase(Productname));
		CheckoutBtn.click();
		return match;
	}
	
}
