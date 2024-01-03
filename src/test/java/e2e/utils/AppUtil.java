package e2e.utils;


public class AppUtil{
	
	public static String removeCurrency(String priceWithCurrency){
		return priceWithCurrency.replace("$", "").trim();
	}

}