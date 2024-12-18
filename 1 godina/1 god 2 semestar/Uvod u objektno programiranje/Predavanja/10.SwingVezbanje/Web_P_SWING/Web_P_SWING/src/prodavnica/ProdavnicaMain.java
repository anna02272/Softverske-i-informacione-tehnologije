package prodavnica;

import java.util.ArrayList;

import DAO.MuzickiCDDAO;
import model.artikli.Kompzicija;
import model.artikli.MuzickiCD;
import util.Utility;

public class ProdavnicaMain {
	
	private Prodavnica prodavnica;
	
	public static void main(String args []) {
		ProdavnicaMain prm = new ProdavnicaMain();
		prm.prodavnica = new Prodavnica();
		
		System.out.println("Ispisi sve artikle");
		prm.prodavnica.printArtikli();
		
		System.out.println("Ispisi sve osobe");
		prm.prodavnica.printOsobe();
		
		System.out.println("Ispisi sve korisnike");
		prm.prodavnica.printKorisnici();
		
		System.out.println("**********************************");
		System.out.println("Ispisi odreceni artikal \nUnesiID:");
		prm.prodavnica.printByIDArtikli(Utility.ocitajCeoBroj());
		
		System.out.println("**********************************");
		System.out.println("Unesite novi Muzicki CD");
		System.out.println("Unesite naziv:");
		String naziv = Utility.ocitajTekst();
		System.out.println("Unesite cenu:");
		double cena = Utility.ocitajRealanBroj();
		System.out.println("Unesite godinu publikovanja:");
		int godinaP = Utility.ocitajCeoBroj();
		System.out.println("Unesite broj raspolozivih CD:");
		int brRas = Utility.ocitajCeoBroj();
		System.out.println("Unesite broj prodatih CD:");
		int brProd = Utility.ocitajCeoBroj();
		System.out.println("Unesite izvodjaca:");
		String izvodjac = Utility.ocitajTekst();
		System.out.println("Unesite zanr:");
		String zanr = Utility.ocitajTekst();
		ArrayList<Kompzicija> kompozicije = new ArrayList<Kompzicija>();
		while(Utility.ocitajOdlukuOPotvrdi("uneti kompozicije") == 'Y'){
			System.out.println("Unesite redni broj kompozicije:");
			int  redBrK = Utility.ocitajCeoBroj();
			System.out.println("Unesite naziv kompozicije:");
			String nazivK = Utility.ocitajTekst();
			System.out.println("Unesite trajanje kompozicije:");
			double trajanjeK = Utility.ocitajRealanBroj();
			kompozicije.add(new Kompzicija(redBrK, nazivK, trajanjeK));
		}
		
		MuzickiCD novi = new MuzickiCD(naziv, cena, godinaP, brRas, brProd, izvodjac, zanr, kompozicije);
		prm.prodavnica.addArtikli(novi);
		
		System.out.println("Pisanje podataka artikla");
		prm.prodavnica.printArtikli();
		System.out.println("kraj aplikacije");
	}
}
