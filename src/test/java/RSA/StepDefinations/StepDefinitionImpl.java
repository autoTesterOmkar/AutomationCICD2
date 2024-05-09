package RSA.StepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RSA.pageobjects.LoginPage;
import RSA.pageobjects.PaymentPage;
import RSA.pageobjects.ProductCatalouge;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinitionImpl extends RSA_Training.Tests.BaseTest{

	public LoginPage landingPage;
	public ProductCatalouge productCatalogue;
	public RSA.pageobjects.CartPage Cartpage;
	public PaymentPage Paymentpage; 
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
		//code
	}

	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.Login(username,password);
	}
	
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.SelectionProducts();
		Cartpage=productCatalogue.AddToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		Cartpage.Cartpage();

		Boolean match = Cartpage.ConfirmCart(productName);
		Assert.assertTrue(match);
		Paymentpage = new PaymentPage(driver);
		Paymentpage.SelectCountry();
		Paymentpage.PlaceOrder();
	}
	

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String FInalMsg=Paymentpage.SuccessMsg();
		Assert.assertTrue(FInalMsg.equalsIgnoreCase(string));
		driver.close();
    }
    
    
}
