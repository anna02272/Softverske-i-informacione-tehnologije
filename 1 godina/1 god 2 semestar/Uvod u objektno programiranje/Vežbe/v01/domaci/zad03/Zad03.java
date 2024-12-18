package domaci.zad03;

public class Zad03 {

	public static void main(String[] args) {

		int r = 6, h = 4;
		
		/*
		 	Ako je precnik 6, poluprecnik je 3.
		 	Sa poznatom visinom i poluprecnikom, na osnovu pitagorine teoreme
		 	mozemo dobiti duzinu osnovice.
		 */
		double s = Math.sqrt(Math.pow(r, 2) + Math.pow(h, 2));
		// I na kraju po formuli mozemo dobiti povrsinu
		double povrsina = Math.pow(r, 2) + Math.PI + r * s * Math.PI;
		System.out.println("Povrsina zadane kupe je: " + povrsina);
	}
}
