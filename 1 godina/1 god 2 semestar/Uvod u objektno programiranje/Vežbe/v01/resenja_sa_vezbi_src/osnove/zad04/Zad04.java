package osnove.zad04;

public class Zad04 {

	public static void main(String[] args) {

		int godina = 1900;
		
		if(godina % 400 == 0) {
			System.out.println("Godina " + godina + ". je prestupna.");
		}else {
			if(godina % 100 == 0) {
				System.out.println("Godina " + godina + ". nije prestupna.");
			}else if(godina % 4 == 0) {
				System.out.println("Godina " + godina + ". je prestupna.");
			}else {
				System.out.println("Godina " + godina + ". nije prestupna.");
			}
		}
	}
}