package unittesting.calculator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTestParametrized {
	
	
	private Calculator calculatorObject;
	
	
	/*
	 * Metoda koja se pokrece pre test metoda 
	 * Treci nacin za instanciranje objekta klase koju testiramo
	 */
	@BeforeMethod
	public void metodPreTestMetoda(){
		calculatorObject = new Calculator();
		calculatorObject.clear();
	}
	
	/*
	 * Test metoda kao parametar prima broj koji ce biti poslat metodi za sabiranje
	 * Iz Calculator klase
	 * Naziv paramentra u anotaciji @Parameters({"zaSabiranje"}) mora da se poklapa sa
	 * Nazivom koji stoji u XML fajlu
	 */
	@Test()
	@Parameters({"zaSabiranje"})
	public void testAdd(int zaSabiranje){
		int vrednostKalkulatoraPreSabiranja = calculatorObject.getResult();
		
		calculatorObject.add(zaSabiranje);
		
		int ocekivaniZbir = vrednostKalkulatoraPreSabiranja + zaSabiranje;
		int stvarniZbir = calculatorObject.getResult();
		
		assertEquals(ocekivaniZbir, stvarniZbir);
	}
	
	
	/*
	 * Test metoda kao parametar prima broj koji ce biti poslat metodi za oduzimanje
	 * Iz Calculator klase
	 * Naziv paramentra u anotaciji @Parameters({"zaOduzimanje"}) mora da se poklapa sa
	 * Nazivom koji stoji u XML fajlu
	 * Ovaj ce test pasti iz razloga sto metoda za oduzimanje iz klase Calculator nije dobro implementirana
	 */
	@Test()
	@Parameters({"zaOduzimanje"})
	public void testSub(int zaOduzimanje){
		int vrednostKalkulatoraPreOduzimanja = calculatorObject.getResult();

		calculatorObject.substract(zaOduzimanje);
		
		int ocekivanaRazlika = vrednostKalkulatoraPreOduzimanja - zaOduzimanje;
		int stvarnaRazlika = calculatorObject.getResult();
		
		assertEquals(ocekivanaRazlika, stvarnaRazlika);
	}
	
	
	/*
	 * Test metoda kao parametar prima broj koji ce biti poslat metodi za mnozenje
	 * Iz Calculator klase
	 * Naziv paramentra u anotaciji @Parameters({"zaMnozenje"}) mora da se poklapa sa
	 * Nazivom koji stoji u XML fajlu
	 * Ovaj test ce pasti iz razloga sto metoda za mnozenje nije uopste implementirana
	 */
	@Test()
	@Parameters({"zaMnozenje"})
	public void testMul(int zaMnozenje){
		int vrednostKalkulatoraPreMnozenja = calculatorObject.getResult();

		calculatorObject.multiply(zaMnozenje);
		
		int ocekivaniProizvod = vrednostKalkulatoraPreMnozenja * zaMnozenje;
		int stvarniProizvod = calculatorObject.getResult();
		
		assertEquals(ocekivaniProizvod, stvarniProizvod);
	}
	
	
	/*
	 * Test metoda kao parametar prima broj koji ce biti poslat metodi za deljenje
	 * Iz Calculator klase
	 * Naziv paramentra u anotaciji @Parameters({"zaDeljenje"}) mora da se poklapa sa
	 * Nazivom koji stoji u XML fajlu
	 */
	@Test()
	@Parameters({"zaDeljenje"})
	public void testDiv(int zaDeljenje){
		int vrednostKalkulatoraPreDeljenja = calculatorObject.getResult();

		calculatorObject.divide(zaDeljenje);
		
		int ocekivaniKolicnik = vrednostKalkulatoraPreDeljenja / zaDeljenje;
		int stvarniKolicnik = calculatorObject.getResult();
		
		assertEquals(ocekivaniKolicnik, stvarniKolicnik);
	}
	
	
	/*
	 * Test metoda kao parametar prima broj koji ce biti poslat metodi za kvadriranje
	 * Iz Calculator klase
	 * Naziv paramentra u anotaciji @Parameters({"zaKvadriranje"}) mora da se poklapa sa
	 * Nazivom koji stoji u XML fajlu
	 */
	@Test()
	@Parameters({"zaKvadriranje"})
	public void testSquare(int zaKvadriranje){
		calculatorObject.square(zaKvadriranje);

		int ocekivaniRezultat = zaKvadriranje * zaKvadriranje;
		int stvarniRezultat = calculatorObject.getResult();
		
		assertEquals(ocekivaniRezultat, stvarniRezultat);
	}
}