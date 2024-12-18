package unittesting.parcijalni;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Zadatak3 {
	
	CreditCardService creditCardService = new CreditCardService();
	CreditCardDAO creditCardDAOMock = Mockito.mock(CreditCardDAO.class);
	CreditCard creditCard = new CreditCard();
	
	
	@Test
    public void testTryPINReturnsFalse() {	
    creditCard.setPin(1111);
    creditCard.setNumOfTries(3);

    Mockito.when(creditCardDAOMock.findByCreditCardNumber(Mockito.anyLong())).thenReturn(creditCard);
    
    creditCardService.setCreditCardDAO(creditCardDAOMock);
    
    boolean result = creditCardService.tryPIN(1234567890L, 5555);
    
    Assert.assertFalse(result);
    }

}
