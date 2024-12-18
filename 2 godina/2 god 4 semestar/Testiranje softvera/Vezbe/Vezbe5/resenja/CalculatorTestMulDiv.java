package unittesting.calculator;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

/*
 * Ukoliko ne zelimo da koristimo @BeforeMethod kao u prvom zadatku, drugi nacin jeste da svaka
 * od test metoda instancira svoj Calculator objekat 
 *
 */
public class CalculatorTestMulDiv {
	
	/*
	 * Meotda za testiranje metode za mnozenje
	 * @Test(enabled=false) oznacava da se ovaj test metod nece izvrsiti
	 * Dodajemo prvo 3 na rezultat jer ce mnozenje sa 0 uvek dati 0
	 * Nakon toga pomnozim rezultat koji se nalazi u objektu calculatorObject sa 5
	 * Na kraju tvrdim kako su mi ocekivani i stvarni proizvod jednaki
	 */
	@Test(enabled=false)
	public void testMul(){
		Calculator calculatorObject = new Calculator();
		calculatorObject.clear();
		
		calculatorObject.add(3);
		calculatorObject.multiply(5);
		
		int stvarniProizvod = calculatorObject.getResult();
		int ocekivaniProizvod = 3*5;
		
		assertEquals(ocekivaniProizvod, stvarniProizvod);
	}
	
	
	/*
	 * Metoda za testiranje metode za deljenje
	 * @Test je isto kao da pise @Test(enable = true)
	 * 
	 */
	@Test
	public void testDiv(){
		Calculator calculatorObject = new Calculator();
		calculatorObject.clear();

		calculatorObject.add(10);
		calculatorObject.divide(2);

		int stvarniKolicnik = calculatorObject.getResult();
		int ocekivaniKolicnik = 10/2;
		
		assertEquals(ocekivaniKolicnik, stvarniKolicnik);
	}
	
	
	/*
	 * Metoda za testiranje metode za deljenje sa 0
	 * @Test(expectedExceptions = java.lang.ArithmeticException.class) oznacava da ocekujem ArithmeticException izuzetak
	 * Ukoliko delim broj sa 0, te ce ovaj test proci ukoliko metoda  divide baci ovaj izuzetak
	 * Ukoliko metoda divide ne baci izuzetak ili baci neki drugi izuzetak test nece proci 
	 */
	@Test(expectedExceptions = java.lang.ArithmeticException.class)
	public void testDivByZero(){
		Calculator calculatorObject = new Calculator();
		calculatorObject.clear();

		calculatorObject.add(10);
		calculatorObject.divide(0);
	}
}