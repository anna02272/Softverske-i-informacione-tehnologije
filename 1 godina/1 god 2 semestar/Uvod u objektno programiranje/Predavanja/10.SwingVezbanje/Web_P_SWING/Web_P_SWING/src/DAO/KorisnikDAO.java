package DAO;

import java.util.HashMap;

import model.osobe.Korisnik;
import util.StringUtils;

public class KorisnikDAO {

	public Korisnik loadKorisnikByKorisnickoIme(String ki) {
		return loadAllKorisnikFromFile().get(ki);
	}
	
	public HashMap<String, Korisnik> loadAllKorisnikFromFile() {
		HashMap<String, Korisnik> map = new HashMap<String, Korisnik>();
		
		AdminDAO aDAO = new AdminDAO();
		map.putAll(aDAO.loadAllAdminFromFile());
		KupacDAO kDAO = new KupacDAO();
		map.putAll(kDAO.loadAllKupacFromFile());
		return map;
	}
	
	public String toFileString(Korisnik k) {
		return StringUtils.clean(k.getKorisnickoIme()) + "," + StringUtils.clean(k.getLozinka());
	}
}
