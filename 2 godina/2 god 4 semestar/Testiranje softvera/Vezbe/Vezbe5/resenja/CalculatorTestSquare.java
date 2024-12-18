package unittesting.calculator;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTestSquare {
	
	/*
	 * Metoda za testiranje metode za kvadriranje
	 * Za treci zadatak je dovoljna samo @Test anotacija
	 */
	@Test(groups = {"odabranaMetoda"})
	public void testSquare(){
		Calculator calculatorObject = new Calculator();
		calculatorObject.clear();
		
		calculatorObject.square(3);
		
		int stvarniRezultat = calculatorObject.getResult();
		int ocekivaniRezultat = 3 * 3;
		
		assertEquals(ocekivaniRezultat, stvarniRezultat);
	}
	
	/*
	 * Metoda za kvadratni koren ima beskonacnu petlju i nikada se ne zavrsava
	 * TimeOut ogranicava vreme izvrsavanja  na 1000 ms
	 * Test ce pasti posto je beskonacno > 1000 ms
	 */
	@Test(timeOut=1000)
	public void testSquareRoot(){
		Calculator calculatorObject = new Calculator();
		calculatorObject.clear();

		calculatorObject.squareRoot(16);
		
		int stvarniRezultat = calculatorObject.getResult();
		int ocekivaniRezultat = 4;
		
		assertEquals(ocekivaniRezultat, stvarniRezultat);
	}
}