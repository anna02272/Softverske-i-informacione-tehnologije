package funkcije.zadatak1;

public class Zadatak01 {

	public static double izracunajHipotenuzu(double a, double b) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
	
	// Primer za method overloading
	public static double izracunajHipotenuzu(double a) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(a, 2));
	}
	
	public static void main(String[] args) {
		double c = izracunajHipotenuzu(4, 6);
		double c2 = izracunajHipotenuzu(4);
		System.out.println("Duzina hipotenuze je: " + c);
		System.out.println("Duzina hipotenuze za jednake katete: " + c2);
	}
}