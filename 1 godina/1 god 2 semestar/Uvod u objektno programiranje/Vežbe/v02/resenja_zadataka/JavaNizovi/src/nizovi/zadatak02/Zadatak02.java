package nizovi.zadatak02;

public class Zadatak02 {

	public static void definicijaNiza() {
		int[] niz = new int[5];
		int j = 1;
		for (int i = 0; i < niz.length; i++) {
			niz[i] = j;
			j += 10;
			System.out.println(niz[i]);
		}
	}
	
	public static void main(String[] args) {
		definicijaNiza();
	}
}
