package domaci.zad02;

public class Zad02 {

	public static void main(String[] args) {
		
		int a=3, b=4, c=5;
		
		int povrsina = 2 * (a*b + a*c + b*c);
		int zapremina = a*b*c;
		
		System.out.println("Povrsina kvadra je: " + povrsina);
		System.out.println("Zapremina kvadra je: " + zapremina);
	}
}