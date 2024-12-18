package zadatak01;

public class Zadatak01 {

	public static int racunajProizvod() {
		int proizvod = 1;
		for(int i=10; i<=20; i+=2) {
			proizvod *= i;
		}
		return proizvod;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Proivod parnih brojeva izmedju 10 i 20: " + racunajProizvod());
	}
}
