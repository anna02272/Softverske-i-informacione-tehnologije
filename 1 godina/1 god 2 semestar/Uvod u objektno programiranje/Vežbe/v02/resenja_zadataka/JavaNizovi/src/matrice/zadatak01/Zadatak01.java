package matrice.zadatak01;

public class Zadatak01 {

	public static void ispisMatrice(int[][] matrica) {
		for (int i = 0; i < matrica.length; i++) {
			for(int j = 0; j < matrica[i].length; j++) {
				System.out.print(matrica[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int[][] matrica = new int[][] {
				{8, 45, -1, 0},
				{58, 2, 5, 9},
				{-4, 34, 2, 78}
		};
		ispisMatrice(matrica);
	}
}
