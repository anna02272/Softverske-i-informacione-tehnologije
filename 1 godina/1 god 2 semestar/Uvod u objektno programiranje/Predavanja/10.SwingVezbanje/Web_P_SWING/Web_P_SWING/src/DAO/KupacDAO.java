package DAO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import model.osobe.Kupac;
import model.osobe.Osoba;
import util.FileUtils;
import util.StringUtils;

public class KupacDAO {

	public Kupac loadKupacByKorisnickoIme(String korisnickoIme) {
		HashMap<String, Kupac> map = loadAllKupacFromFile();
		return map.get(korisnickoIme);
	}
	
	public HashMap<String, Kupac> loadAllKupacFromFile() {
		HashMap<String, Kupac> map = new HashMap<String, Kupac>();
		try (BufferedReader in = FileUtils.getReader("Kupac")) {
			String line;
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			while ((line = in.readLine()) != null) {
				Kupac k = fromString(line);
				map.put(k.getKorisnickoIme(),k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Kupac fromString(String str) {
		String parts[] = str.split(",");
		String korisnickoIme = parts[0];
		String lozinka = parts[1];
		String jmbg = parts[2];
		double stanjeNaRacunu = Double.parseDouble(parts[3]);
		OsobaDAO oDAO = new OsobaDAO();
		Osoba o = oDAO.loadOsobaByJmbG(jmbg);
		return new Kupac(korisnickoIme, lozinka, o, stanjeNaRacunu);
	}
	
	public void saveAllKupacToFile(HashMap<String, Kupac> map) {
		try (PrintWriter out = FileUtils.getPrintWriter("Kupac")) {
			for (Kupac k : map.values()) {
				out.println(toFileString(k));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toFileString(Kupac k) {
		KorisnikDAO dao = new KorisnikDAO();
		return dao.toFileString(k) + "," + 
				StringUtils.clean(k.getOsoba().getJmbg()) + "," + k.getStanjeNaRacunu();
	}
}
