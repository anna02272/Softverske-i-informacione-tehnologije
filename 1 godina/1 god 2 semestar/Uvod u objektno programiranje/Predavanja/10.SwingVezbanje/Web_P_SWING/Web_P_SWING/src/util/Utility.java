package util;

import java.util.Scanner;

public class Utility {

	static Scanner sc = new Scanner(System.in);
	
	//citanje promenljive String
	public static String ocitajTekst(){
		String tekst = "";
		while(tekst == null || tekst.equals(""))
			tekst = sc.nextLine();
		
		return tekst;
	}
		
	//citanje promenljive integer
	public static int ocitajCeoBroj(){
		int ceoBroj = 0;
		boolean notRead = true;
		do {
			if (sc.hasNextInt()) {
				ceoBroj = sc.nextInt();
				notRead = false;
			} else {
				System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			}
			sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
		} while (notRead);
		return ceoBroj;
	}
		
	//citanje promenljive double
	public static float ocitajRealanBroj(){
		float realanBroj = 0;
		boolean notRead = true;
		do {
			if (sc.hasNextFloat()) {
				realanBroj = sc.nextFloat();
				notRead = false;
			} else {
				System.out.println("GRESKA - Pogresno unsesena vrednost za realan broj, pokusajte ponovo: ");
			}
			sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak testa posle broja
		} while (notRead);
		return realanBroj;
	}
		
	//citanje promenljive char
	public static char ocitajKarakter(){
		char karakter = ' ';
		boolean notRead = true;
		do {
			String text = sc.next();
			if(text.length()==1){
				karakter = text.charAt(0);
				notRead = false;
			}
			else {
				System.out.println("GRESKA - Pogresno unsesena vrednost za karakter, pokusajte ponovo: ");
			}
			sc.nextLine();//cisti sve sa ulaza sto nije broj ili ostatak testa posle karaktera
		} while (notRead);
		return karakter;
	}
	
	//citanje promenljive char
	public static char ocitajOdlukuOPotvrdi(String tekst){
		System.out.println("Da li zelite " + tekst + " [Y/N]:");
		char odluka = ' ';
		while( !(odluka == 'Y' || odluka == 'N') ){
			odluka = ocitajKarakter();
			if (!(odluka == 'Y' || odluka == 'N')) {
				System.out.println("Opcije su Y ili N");
			}
		}
		return odluka;
	}
}
