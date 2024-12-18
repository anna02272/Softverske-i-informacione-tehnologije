package matrice.zadatak02;

public class Zadatak02 {

	public static void ispisGlavneDijagonale(int[][] matrica) {
		for (int i = 0; i < matrica.length; i++) {
			for(int j = 0; j < matrica[i].length; j++) {
				if(i == j) {
					System.out.print(matrica[i][j] + " ");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrica = new int[][] {
			{8, 45, -1, 0},
			{58, 2, 5, 9},
			{-4, 34, 2, 78}
		};
		System.out.println("Elementi na glavnoj dijagonali: ");
		ispisGlavneDijagonale(matrica);
	}
}
