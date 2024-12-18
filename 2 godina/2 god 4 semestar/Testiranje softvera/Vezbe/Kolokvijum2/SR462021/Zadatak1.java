package unittesting.parcijalni;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.Assert;
import org.testng.annotations.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Zadatak1 {
	
	CreditCardService creditCardService = new CreditCardService();
	
	@Test
	public void testCalculateAmount1(){
		
		double balanceOnAccount = 3000.0;
		double amount = 1500.0;
		boolean payout = true;
		
		double result = creditCardService.calculateAmount(balanceOnAccount, amount, payout);
		
		Assert.assertEquals(result, 1500.0);
	}

	@Test(enabled = false)
	public void testCalculateAmount2(){
		
		double balanceOnAccount = 3000.0;
		double amount = 1500.0;
		boolean payout = false;
		
		double result = creditCardService.calculateAmount(balanceOnAccount, amount, payout);
		
		Assert.assertEquals(result, 4500.0);
		
	}
}
