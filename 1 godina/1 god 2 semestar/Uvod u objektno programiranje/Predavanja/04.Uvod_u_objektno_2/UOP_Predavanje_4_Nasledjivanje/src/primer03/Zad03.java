package primer03;

import java.util.ArrayList;

import primer01.Tacka2D;
import primer02.*;

public class Zad03 {

	public static ArrayList<Figura> sveFigure = new ArrayList<Figura>();
	
	static void ispisiSveFigure() {
		//ispis svih figura
		for (int i = 0; i < sveFigure.size(); i++) {
			System.out.println(sveFigure.get(i).toString());
		}
	}
	
	public static void main(String[] args) {

		sveFigure.add(new Krug(new Tacka2D(0, 0), 3));
		Figura fig = sveFigure.get(0);
		
		//polimorfizam
		//klikni F3 na metodu toString ili CTRL+levi klik misa
		System.out.println("Povrsina figure je " + fig.izracunajPovrsinu());
		System.out.println("Obim figure je " + fig.izracunajObim());
		System.out.println(fig.toString());
		//ne pozivaju se izracunajPovrsinu, izracunajObim, toString metode klase Figura 
		//vec metoda klase Krug

//		fig.setRadius(2); //ne moze - iako je u pozadini objekat klase Krug
		//U prethodnom slučaju je vidljivo samo sto se nalazi u figuri
		//nama su dostupni svi atributi i metode objekta kojima se su vidljivi iz klase Figura	

		//kako da objekat konvertujemo nazad u objekat klase Krug
		if(sveFigure.get(0) instanceof Krug){
			Krug kr = (Krug) sveFigure.get(0);
			kr.setRadius(2);
			System.out.println(kr.toString());
		}
		
		
		
		//ODKOMENTARISI I ISPROBAJ
		sveFigure.add(new Pravougaonik(new Tacka2D(0, 0),new Tacka2D(5, 5)));
		fig = sveFigure.get(1);
		//polimorfizam
		//klikni F3 na metodu toString ili CTRL+levi klik misa
		System.out.println("Povrsina figure je " + fig.izracunajPovrsinu());
		System.out.println("Obim figure je " + fig.izracunajObim());
		System.out.println(fig.toString());
		//ne pozivaju se izracunajPovrsinu, izracunajObim, toString metode klase Figura 
		//vec metoda klase Rectangle
		
		
		/*
		//ODKOMENTARISI I ISPROBAJ
		fig = sveFigure.get(0); 
		Pravougaonik pra = (Pravougaonik)fig; //OVO NE SME DA SE RADI, 
		//AKO SE PRETHODNO NE PROVERI KOGE JE TIPA OBJEKAT
//		System.out.print("Tacka A kod pravougaonika " + pra.A.ispisNaEkran());
//		System.out.println("Povrsina pravougaonika je " + pra.izracunajPovrsinu());
//		System.out.println(pra.toString());
		 */
		
		//JEDINO ISPRAVNO
		if(sveFigure.get(1) instanceof Pravougaonik){
			Pravougaonik pra2 = (Pravougaonik) sveFigure.get(1);
			pra2.setA(new Tacka2D(1, 1));
			System.out.println(pra2.toString());
		}
		
		System.out.print("Program izvrsen");
	}

}
