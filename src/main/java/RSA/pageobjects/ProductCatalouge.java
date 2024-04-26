package RSA.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSA.Abstract.AbstractCompoenent;

public class ProductCatalouge extends AbstractCompoenent  {
	
	  WebDriver driver;
	
      public ProductCatalouge(WebDriver driver){
	  super(driver);
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
      }
  
     @FindBy (css="div[class*='col-lg-4']")
     List <WebElement> Products;
  
      By Productswait = By.cssSelector("div[class*='col-lg-4']"); //here we use By because we have to pass to Explicitwait they asked By as an argument
      By toastMsg = By.id("toast-container");
      By spinner = By.cssSelector("ngx-spinner[class*=ng]");
  
  
      public List<WebElement> SelectionProducts() {
	  waitforElementToShow(Productswait);
	  return Products;
      }
      
      public WebElement getProductByName(String Productname) {
    	  WebElement Prod=Products.stream().filter(s-> s.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst().orElse(null);
    	  return Prod;
      }
      public CartPage AddToCart(String Productname) {
    	  WebElement Prod=getProductByName(Productname);
    	  Prod.findElement(By.cssSelector("button + button")).click();	
    	  waitforElementToShow(toastMsg);
    	  waitforElementTODisappear(spinner);
    	  CartPage CP = new CartPage(driver);
    	  return CP;
      }
      
      
      
}
