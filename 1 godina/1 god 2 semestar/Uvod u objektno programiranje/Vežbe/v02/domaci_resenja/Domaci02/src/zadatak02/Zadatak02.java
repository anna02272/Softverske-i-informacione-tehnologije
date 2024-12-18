package zadatak02;

public class Zadatak02 {

	public static void najveciNajmanji(int[] niz) {
		int najveciElement = niz[0], najmanjiElement = niz[0];
		for (int i = 0; i < niz.length; i++) {
			if(niz[i] > najveciElement) {
				najveciElement = niz[i];
			}
			if(niz[i] < najmanjiElement) {
				najmanjiElement = niz[i];
			}
		}
		System.out.println("Maksmalni element niza je: " + najveciElement);
		System.out.println("Minimalni element niza je: " + najmanjiElement);
	}
	
	public static double srednjaVrednost(int[] niz) {
		int zbir = 0;
		for (int i = 0; i < niz.length; i++) {
			zbir += niz[i];
		}
		return zbir / niz.length;
	}
	
	public static void pozitivniElementi(int[] niz, double prosek) {
		System.out.print("Pozitivni elementi veci od srednje vrednosti: ");
		for (int i = 0; i < niz.length; i++) {
			if(niz[i] > 0 && niz[i] > prosek) {
				System.out.print(niz[i] + " ");
			}
		}
	}
	
	public static void main(String[] args) {
		int[] niz = new int[] { -10, 3, 16, 1, 4, -2 };
		najveciNajmanji(niz);
		pozitivniElementi(niz, srednjaVrednost(niz));
	}
}
