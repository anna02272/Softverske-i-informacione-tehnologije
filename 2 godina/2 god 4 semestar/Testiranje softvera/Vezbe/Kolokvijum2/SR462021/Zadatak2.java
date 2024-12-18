package unittesting.parcijalni;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;



public class Zadatak2 {

	
	
	 CreditCardService creditCardService = new CreditCardService();
	 
	  @Test(dataProvider = "provider", dataProviderClass = KlasaDataProvider.class)
	    public void testTransaction(double balanceOnAccount, double amount,
	            double minimum, String expectedStatus){
	        
	        String result = creditCardService.transaction(balanceOnAccount, amount, minimum);
	        
	        Assert.assertEquals(result, expectedStatus);
	    }
	
	
}
