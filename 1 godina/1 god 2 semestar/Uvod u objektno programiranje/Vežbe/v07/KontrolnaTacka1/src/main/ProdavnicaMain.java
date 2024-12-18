package main;

import java.util.ArrayList;

import artikli.Disk;
import artikli.Knjiga;
import artikli.Kompozicija;
import osobe.Pol;
import osobe.Prodavac;
import prodavnica.Prodavnica;

public class ProdavnicaMain {
	private static String DISKOVI_FAJL = "diskovi.txt";
	private static String KNJIGE_FAJL = "knjige.txt";
	private static String KOMPOZICIJE_FAJL = "kompozicije.txt";
	private static String PRODAVCI_FAJL = "prodavci.txt";
	
	
	public static void main(String[] args) {
		Prodavnica prodavnica = new Prodavnica();
		prodavnica.ucitajZaposlene(PRODAVCI_FAJL);
		prodavnica.ucitajDiskove(DISKOVI_FAJL);
		prodavnica.ucitajKnjige(KNJIGE_FAJL);
		prodavnica.ucitajKompozicije(KOMPOZICIJE_FAJL);

		System.out.println("PODACI UCITANI IZ DATOTEKA:");
		System.out.println("----------------------------------------------");
		ispisiSvePodatke(prodavnica);
		System.out.println("----------------------------------------------");
		
		System.out.println("Dodavanje test podataka...");
		Knjiga testKnjiga = new Knjiga("003", 560, "Test Knjiga", false, "Test autor", 14, false);
		prodavnica.dodajKnjigu(testKnjiga);
		
		Kompozicija testKompozicija = new Kompozicija("Test kompozicija", 84, false);
		Disk testDisk = new Disk("003", 400, "Test disk", false, "Test izvodjac", "Test zanr", new ArrayList<Kompozicija>());
		testDisk.getKompozicije().add(testKompozicija);
		prodavnica.dodajDisk(testDisk);
		
		Prodavac testProdavac = new Prodavac("Test", "Prodavac", Pol.ZENSKI, "test", "test", false);
		prodavnica.dodajProdavca(testProdavac);
		
		System.out.println("Snimanje dodanih podataka...");
		prodavnica.snimiKnjige(KNJIGE_FAJL);
		prodavnica.snimiKompozicije(KOMPOZICIJE_FAJL);
		prodavnica.snimiDiskove(DISKOVI_FAJL);
		prodavnica.snimiZaposlene(PRODAVCI_FAJL);
	}
	
	public static void ispisiSvePodatke(Prodavnica prodavnica) {
		for(Knjiga knjiga : prodavnica.getKnjige()) {
			System.out.println(knjiga + "\n");
		}
		
		for(Disk disk : prodavnica.getDiskovi()) {
			System.out.println(disk + "\n");
		}
		for(Prodavac prodavac : prodavnica.getProdavci()) {
			System.out.println(prodavac + "\n");
		}
	}
}