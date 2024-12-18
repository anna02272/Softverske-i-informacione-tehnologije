package funkcije.zadatak2;

public class Zadatak02 {

	public static double izracunajX1(double a, double b, double c) {
		double x1 = -b + Math.sqrt(Math.pow(b, 2) - 4*a*c) / (2*a);
		return x1;
	}
	
	public static double izracunajX2(double a, double b, double c) {
		double x2 = -b - Math.sqrt(Math.pow(b, 2) - 4*a*c) / (2*a);
		return x2;
	}
	
	public static void main(String[] args) {
		double a = 1, b = -4, c = -320;
		double x1 = izracunajX1(a, b, c);
		double x2 = izracunajX2(a, b, c);
		System.out.println("X1 = " + x1);
		System.out.println("X2 = " + x2);
	}
}