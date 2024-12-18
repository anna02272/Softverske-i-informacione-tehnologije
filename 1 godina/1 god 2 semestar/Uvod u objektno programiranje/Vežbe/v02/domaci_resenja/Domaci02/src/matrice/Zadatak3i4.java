package matrice;

public class Zadatak3i4 {

	public static int racunanjeZbira(int[][] matrica) {
		int zbir = 0;
		for (int i = 0; i < matrica.length; i++) {
			for(int j = 0; j < matrica[i].length; j++) {
				if(i == j) {
					zbir += matrica[i][j];
				}
			}
		}
		return zbir;
	}
	
	public static int proizvodElemenata(int[][] matrica) {
		int proizvod = 1;
		for (int i = 0; i < matrica.length; i++) {
			for(int j = 0; j < matrica[i].length; j++) {
				if(i < j) {
					proizvod *= matrica[i][j];
				}
			}
		}
		return proizvod;
	}
	
	public static void main(String[] args) {
		int[][] matrica = new int[][] {
			{8, 45, -1, 0},
			{58, 2, 5, 9},
			{-4, 34, 2, 78}
		};
		System.out.println("Zbir elemenata na glavnoj dijagonali je: " + racunanjeZbira(matrica));
		System.out.println("Proizvod elemenata iznad glavne dijagonale je: " + proizvodElemenata(matrica));
	}
}