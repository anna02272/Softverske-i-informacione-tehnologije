package t1.primer02;

//Ime klase je isto kao u prethodnom primeru, 
//ali to je samo SimpleName, puno ime je ime paketa + ime klase
//Trudimo se da je puno ime klase jedinstveno ne samo na nivou 
//naseg projekta, nego na globalnom nivou - pogledajte naziv paketa
public class Hello {
	public static void main(String[] args) {
		//Deklaracija lokalne varijable name koja je tipa String
		String name;
		//Dodela string literala "Dragan" varijabli name
		//ovo moze da bude i odmah prilikom deklaracije
		name = "Dragan";
		
		System.out.println("AAAA");
		//Konkatenacija stringova se vrsi pomocu operatora +
		System.out.println("HelloWorld " + name + "!");
		//Primer konkatenacije stringova koja se smesta u lokalnu promenljivu
		//pa tek onda ispisuje
		String message = "HelloWorld " + name + "!";
		System.out.println(message);
		//Specijalni karakteri se moraju eskejpovati pomocu \
		System.out.println("HelloWorld \"" + name + "\"!");
		
		int brojRacunara = 7;
		boolean imaCD = true;
		double cena = 39000.52;
		char os = 'W'; //tip instaliranog operativnog sistema
		
		
		
		System.out.println("Broj racunara je: " + brojRacunara);
		System.out.print("\tCena: " + cena + "\n\n\n");
		System.out.println("\tSa CD uredjajem?: " + imaCD);
		System.out.println("\tOznaka operativnog sistema: " + os);		
	}
}
