package unittesting.calculator;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
/*
 * NAPOMENA: svaki od zadataka je mogao biti resen na vise nacina,
 * dati primeri pokazuju samo neke od potencijalnih resenja
*/
public class CalculatorTestAddSub {
	
	
	private Calculator calculatorObject = new Calculator();
	
	
	/*
	 * Metoda koja se pokrece pre svakog od testova iz ove klase i postavlja vrednost rezultata na 0
	 * Ovo je potrebno jer radimo sa globalnom promenljivom calculatorObject (nacin 1)
	 */
	@BeforeMethod
	public void methodThatRunsBeforeEveryOtherMethodInThisClass() {
		calculatorObject.clear();
	}
	
	/*
	 * U result promenljivoj se nalazi vrednost 0, jer je predhodno izvrsena metoda methodThatRunsBeforeEveryOtherMethodInThisClass
	 * Sabiramo 5 na vec postojecu vrednost
	 * I tvrdimo kako je ocekivana vrednost (5) jednaka stvarnoj vrednosti 
	 * Za prvi zadatak je dovoljna samo @Test anotacija, bez groups atributa
	 */
	@Test(groups = {"odabranaMetoda"})
	public void testAdd(){
		calculatorObject.add(5);
		int stvarnaVrednost = calculatorObject.getResult();
		assertEquals(5, stvarnaVrednost);
	}
	
	
	/*
	 * U result promenljivoj se nalazi vrednost 0, jer je predhodno izvrsena metoda methodThatRunsBeforeEveryOtherMethodInThisClass
	 * Oduzimamo 5 od postajece vrednosti
	 * Tvrdimo da je netacno da je stvarna vrednost jednaka sa ocekivanom (-5)
	 * Zato sto metoda za oduzimanje ima gresku u implementaciji
	 */
	@Test
	public void testSub(){
		calculatorObject.substract(5);
		int stvarnaVrednost = calculatorObject.getResult();
		assertFalse(stvarnaVrednost == -5);
	}
	
	
	/*
	 * Deo 5og zadatka
	 * Metoda koja ce se pokretati pre svih testova
	 * Koji pripadaju grupi sa nazivom odabranaMetoda
	 */
	@BeforeGroups(groups = {"odabranaMetoda"})
	public void preGrupeOdabranihMetoda(){
		System.out.println("Sada će se pokrenuti svi testovi iz grupe ODABRANIH METODA.");
	}
	
	
	/*
	 * Deo 5og zadatka
	 * Metoda koja ce se pokretati pre svih testova
	 * Koji pripadaju grupi sa nazivom odabranaMetoda
	 */
	@AfterGroups(groups = {"odabranaMetoda"})
	public void posleGrupeOdabranihMetoda(){
		System.out.println("Svi testovi iz grupe ODABRANIH METODA su izvršeni.");
	}
}