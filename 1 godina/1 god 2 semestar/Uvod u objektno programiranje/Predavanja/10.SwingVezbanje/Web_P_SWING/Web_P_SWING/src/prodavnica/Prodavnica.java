package prodavnica;

import java.util.HashMap;

import DAO.ArtikalDAO;
import DAO.KorisnikDAO;
import DAO.KorpaDAO;
import DAO.OsobaDAO;
import DAO.PorudzbinaDAO;
import model.artikli.Artikal;
import model.artikli.MuzickiCD;
import model.osobe.Korisnik;
import model.osobe.Osoba;
import model.porudzbine.Korpa;
import model.porudzbine.Porudzbina;


public class Prodavnica {
	
	public void printArtikli() {
		ArtikalDAO aDAO = new ArtikalDAO();
		for (Artikal artikl : aDAO.loadAllArtikalFromFile().values()) {
			System.out.println(artikl.toString());				
		}
	}
	
	public void printByIDArtikli(int id) {
		Artikal artikal = findArtikalById(id);
		System.out.println(artikal.toString());
		if(artikal instanceof MuzickiCD)
			System.out.println(((MuzickiCD)artikal).toStringAllKompozicije());
	}
	
	public Artikal findArtikalById(int id) {
		ArtikalDAO aDAO = new ArtikalDAO();
		Artikal artikal = aDAO.loadArtikalById(id);
		return artikal;
	}
	
	public boolean addArtikli(Artikal a) {
		ArtikalDAO aDAO = new ArtikalDAO();
		HashMap<Integer, Artikal> map = aDAO.loadAllArtikalFromFile();
		if(map.containsKey(a.getId())){
			System.out.println("Artikal sa tim ID već postoji u evidenciji");
			return false;
		}
		map.put(a.getId(), a);
		aDAO.saveAllArtikalToFile(map);
		return true;
	}
	
	public boolean editArtikli(Artikal a) {
		ArtikalDAO aDAO = new ArtikalDAO();
		HashMap<Integer, Artikal> map = aDAO.loadAllArtikalFromFile();
		if(!map.containsKey(a.getId())){
			System.out.println("Artikal sa tim ID ne postoji u evidenciji");
			return false;
		}
		map.put(a.getId(), a);
		aDAO.saveAllArtikalToFile(map);
		return true;
	}
	
	public void printOsobe() {
		OsobaDAO dao = new OsobaDAO();
		for (Osoba obj : dao.loadAllOsobaFromFile().values()) {
			System.out.println(obj.toString());				
		}
	}
	
	public void printByJMBGOsoba(String JMBG) {
		Osoba obj = findOsobaByJMBG(JMBG);
		System.out.println(obj.toString());
	}
	
	public Osoba findOsobaByJMBG(String JMBG) {
		OsobaDAO dao = new OsobaDAO();
		Osoba obj = dao.loadOsobaByJmbG(JMBG);
		return obj;
	}
	
	public void printKorisnici() {
		KorisnikDAO dao = new KorisnikDAO();
		for (Korisnik obj : dao.loadAllKorisnikFromFile().values()) {
			System.out.println(obj.toString());				
		}
	}
	
	public void printByKorisnickoImeKorisnik(String ki) {
		Korisnik obj = findKorisnikByKorisnickoIme(ki);
		System.out.println(obj.toString());
	}
	
	public Korisnik findKorisnikByKorisnickoIme(String ki) {
		KorisnikDAO dao = new KorisnikDAO();
		Korisnik obj = dao.loadKorisnikByKorisnickoIme(ki);
		return obj;
	}
	
	public Korpa findKorpaById(int id) {
		KorpaDAO kDAO = new KorpaDAO();
		Korpa korpa = kDAO.loadKorpaById(id);
		return korpa;
	}

	public Porudzbina findPorudzbinaById(int id) {
		PorudzbinaDAO pDAO = new PorudzbinaDAO();
		Porudzbina p = pDAO.loadPorudzbinaById(id);
		return p;
	}

	public Korisnik findByKorisnickoIme(String ki) {
		KorisnikDAO kDAO = new KorisnikDAO();
		Korisnik p = kDAO.loadKorisnikByKorisnickoIme(ki);
		return p;
	}
}
