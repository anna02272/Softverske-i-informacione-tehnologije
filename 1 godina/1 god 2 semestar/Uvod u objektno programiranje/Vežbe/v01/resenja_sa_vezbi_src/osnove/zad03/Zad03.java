package osnove.zad03;

public class Zad03 {

	public static void main(String[] args) {
	
		int povrsinaKvadrata = 16, osnovicaTrougla = 4, krakTrougla = 6;
		
		// Racunanje rezultata
		double stranicaKvadrata = Math.sqrt(povrsinaKvadrata);
		// a - osnovica, b - krak
		double ha = Math.sqrt(Math.pow(krakTrougla, 2) - Math.pow(osnovicaTrougla, 2) / 4);
		double povrsinaTrougla = (osnovicaTrougla * ha) / 2;
		
		// Ispis rezultata
		System.out.println("Stranica kvadrata je: " + stranicaKvadrata);
		System.out.println("Povrsina trougla je: " + povrsinaTrougla);
	}
}