package main;

import java.util.ArrayList;

import artikli.Disk;
import artikli.Knjiga;
import artikli.Kompozicija;
import gui.LoginProzor;
import osobe.Pol;
import osobe.Prodavac;
import prodavnica.Prodavnica;

public class ProdavnicaMain {
	public static String DISKOVI_FAJL = "diskovi.txt";
	public static String KNJIGE_FAJL = "knjige.txt";
	public static String KOMPOZICIJE_FAJL = "kompozicije.txt";
	public static String PRODAVCI_FAJL = "prodavci.txt";
	
	
	public static void main(String[] args) {
		Prodavnica prodavnica = new Prodavnica();
		prodavnica.ucitajZaposlene(PRODAVCI_FAJL);
		prodavnica.ucitajDiskove(DISKOVI_FAJL);
		prodavnica.ucitajKnjige(KNJIGE_FAJL);
		prodavnica.ucitajKompozicije(KOMPOZICIJE_FAJL);

		LoginProzor lp = new LoginProzor(prodavnica);
		lp.setVisible(true);
	}
}