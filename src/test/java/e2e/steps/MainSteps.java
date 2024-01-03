package e2e.steps;

import java.util.*;
import io.cucumber.java.en.*;
import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import e2e.utils.AppUtil;

public class MainSteps{

   private Browser browser;
   private BrowserContext context;
   private Page page;
   private Map<String, String> dataState = new HashMap<>();

   String url = "https://react-shopping-cart-67954.firebaseapp.com/";
   
   //Locators
   String locProductIndex			= ".sc-124al1g-2:nth-child(INDEX)";
   String locProductName 			= ".sc-124al1g-4";
   String locProductPrice 			= ".sc-124al1g-6";
   String locAddToCartButton		= "button.sc-124al1g-0";

   String locCart						= "div.sc-7th5t8-0";
   String locCartProductPrice		= ".sc-11uohgb-4 p";


   public MainSteps(TestContext testContext){
   	this.browser = testContext.getBrowser();
   	this.page = testContext.newPage();
   }

	@Given("user at homepage")
	public void user_at_homepage(){
		page.navigate(url);
	}

	@When("user add product {int} to cart")
	public void user_add_product_to_cart(Integer no){
		String locProductIndex = this.locProductIndex.replace("INDEX", String.valueOf(no));
	   Locator currentProduct = page.locator(locProductIndex);
	   String productName 	= currentProduct.locator(locProductName).textContent();
	   String productPrice 	= currentProduct.locator(locProductPrice).textContent();
	   dataState.put("productName", productName);
	   dataState.put("productPrice", productPrice);
	   currentProduct.locator(locAddToCartButton).click();
	}

	@Then("the product should be in cart")
	public void product_should_be_in_cart() {
	   Locator cart = page.locator(locCart);
		assertThat(cart).containsText(dataState.get("productName"));
	}

	@And("verify the product price in cart is correct")
	public void verify_the_product_price_in_cart_is_correct(){
	   Locator productInCart = page.locator(locCart, 
	   									new Page.LocatorOptions().setHasText(dataState.get("productName"))
	   								);
	   String priceInCart = productInCart.locator(locCartProductPrice).textContent();
	   assertEquals(
	   	AppUtil.removeCurrency(priceInCart),
	   	AppUtil.removeCurrency(dataState.get("productPrice"))
	   );
	 }

}