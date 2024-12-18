package osnove.zad02;

public class Zad02 {

	public static void main(String[] args) {

		/*
		 	DEFINISANJE PROMENLJIVIH:
		 		Promenljive se definisu navodjenjem tipa, imena promenljive i vrednosti.
		 */
		int povrsinaKvadrata = 16;
		/*
		 	Ako imamo vise promenljivih istog tipa, one se mogu navesti odjednom, razdvojene zarezom
		 	pri cemu se tip navodi samo jednom, na pocetku reda.
		 */
		int stranicaA = 5, stranicaB = 6, osnovicaTrougla = 4, krakTrougla = 6;
		double poluprecnikKruga = 1.954905637;
		
		// Racunanje rezultata
		int obimPravougaonika = 2 * stranicaA + 2 * stranicaB;
		int povrsinaPravougaonika = stranicaA * stranicaB;
		
		double obimKruga = 2 * poluprecnikKruga * 3.14;
		double povrsinaKruga = poluprecnikKruga * poluprecnikKruga * 3.14;
		int obimTrougla = osnovicaTrougla + 2 * krakTrougla;
		
		// Ispis rezultata
		System.out.println("Obim pravougaonika je: " + obimPravougaonika);
		System.out.println("Povrsina pravougaonika je: " + povrsinaPravougaonika);
		System.out.println("Obim kruga je: " + obimKruga);
		System.out.println("Povrsina kruga je: " + povrsinaKruga);
		System.out.println("Obim trougla je: " + obimTrougla);
	}
}