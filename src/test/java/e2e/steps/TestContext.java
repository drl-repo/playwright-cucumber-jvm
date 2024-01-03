package e2e.steps;

import java.lang.Thread;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import com.microsoft.playwright.*;

public class TestContext{

	protected static Playwright playwright;
   protected static Browser browser;
   protected BrowserContext context;
   protected Page page;
	
	@BeforeAll
	public static void BeforeAll(){
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
    	.setHeadless(false)
    	.setSlowMo(100));
	}

	public Browser getBrowser(){
		return browser;
	}

	public Page newPage(){
		context = browser.newContext();
		return context.newPage();
	}

	@AfterAll
	public static void AfterAll(){
		//sleep for 5 seconds for viewing result (headed mode)
		try {
      	Thread.sleep(5000);
	   } catch(InterruptedException ex) {
	   	System.out.println(ex.getMessage());
	   }
	}

}