package t1.primer07;

public class MojRacun {

	public static void main(String[] args) {
		
		System.out.println(args[0]);
		System.out.println("============================");
		String [] nazivi = {"hleb", "mleko", "jaja", "brasno"};
		double [] cene = {49.99, 89, 14.99, 50};
		double [] kolicine = {1, 10, 10, 2};
		
		double cenaBezPDV=0;
		double cenaSaPDV=0;
		System.out.println("red.Br. naziv kolicina cena");
		System.out.println("----------------------------");
		for (int i = 0; i < kolicine.length; i++) {
			double stavka = kolicine[i]*cene[i];
			cenaBezPDV+=stavka;
			cenaSaPDV+=stavka*1.2;
			
			System.out.println(i+1 +". "+nazivi[i]+" "+kolicine[i]+" "+cene[i]);
		}
		System.out.println("----------------------------");
		System.out.println("Cena bez PDV "+cenaBezPDV);
		System.out.println("Cena sa PDV "+cenaSaPDV);
	}

}
