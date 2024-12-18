package primer02;

import primer01.Tacka2D;

public class Zad02 {

	public static void main(String[] args) {

		Krug kr = new Krug(new Tacka2D(0, 0), 3);
		
		
		System.out.println("Povrsina kruga je " + kr.izracunajPovrsinu());
		System.out.println("Obim kruga je " + kr.izracunajObim());
		System.out.println(kr.toString());
		
		//apstraktna klasa ne moze se instancirati
//		Figura fig = new Figura();
//		Figura fig = new Krug(new Tacka2D(0, 0), 3); 
//		System.out.println("-----Povrsina figure je " + fig.izracunajPovrsinu());
		
		
		Pravougaonik pra = new Pravougaonik(new Tacka2D(0, 0),new Tacka2D(5, 5));
		System.out.println("Povrsina pravougaonika je " + pra.izracunajPovrsinu());
		System.out.println("Obim pravougaonika je " + pra.izracunajObim());
		System.out.println(pra.toString());
		
		
//		fig = pra; 
//		System.out.println("-----Povrsina figure je " + fig.izracunajPovrsinu());
		
		System.out.print("Program izvrsen");
	}

}
