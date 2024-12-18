//Paket kome pripada klasa se mora definisati u 
//prvoj liniji koja nije komentar.
//Hijerarhija foldera mora biti u skladu sa nazivom paketa, 
//naziv fajla mora biti u skladu sa imenom klase.
package t1.primer01;

//Ovde pocinje definicija klase, pre ovoga mogu biti 
//i import direktive koje cemo raditi nesto kasnije na
//ovom kursu.
//Takodje, znacenje za public i class cemo raditi kasnije


public class Hello {

	// Izvrsenje programa pocinje od main metode
	// Desni klik u Package Explorer view-u na klasu->Run As->Java Aplication
	// Precica: Alt + Shift + X, J
	public static void main(String[] args) {
		// Linija ispod ispisuje tekst u zagradi
		// out je staticki atribut klase System,
		// a println je metoda, ali o tome cemo
		// kasnije tokom kursa
		System.out.println("Hello world!");
	}

}
