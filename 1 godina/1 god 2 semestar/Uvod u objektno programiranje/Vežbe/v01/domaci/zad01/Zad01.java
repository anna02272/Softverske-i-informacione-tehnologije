package domaci.zad01;

public class Zad01 {

	public static void main(String[] args) {
		
		int centimetri = 8;
		
		int metri = centimetri/100;
		int decimetri = (centimetri - metri * 100) / 10;
		int centimetri_final = centimetri - (decimetri * 10 + metri * 100);
		
		System.out.println("Rastojanje je " + metri + " metara, " + decimetri + " decimetara i " + centimetri_final + " centimetara.");
	}
}