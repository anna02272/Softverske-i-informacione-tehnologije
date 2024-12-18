package nizovi.zadatak01;

public class Zadatak01 {

	public static void inicijalizacijaNiza() {
		int[] niz = new int[] {2, 63, 3, 15, -4};
		int zbir = 0;
		for (int i = 0; i < niz.length; i++) {
			zbir += niz[i];
		}
		System.out.println("Zbit elemenata niza je: " + zbir);
	}
	
	public static void main(String[] args) {
		inicijalizacijaNiza();
	}
}
