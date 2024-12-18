package DAO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import model.osobe.Admin;
import model.osobe.Osoba;
import util.FileUtils;
import util.StringUtils;

public class AdminDAO {

	public Admin loadAdminByKorisnickoIme(String korisnickoIme) {
		HashMap<String, Admin> map = loadAllAdminFromFile();
		return map.get(korisnickoIme);
	}
	
	public HashMap<String, Admin> loadAllAdminFromFile() {
		HashMap<String, Admin> map = new HashMap<String, Admin>();
		try (BufferedReader in = FileUtils.getReader("Admin")) {
			String line;
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			while ((line = in.readLine()) != null) {
				Admin a = fromString(line);
				map.put(a.getKorisnickoIme(),a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	private Admin fromString(String str) {
		//pera,123,1,pera@prodavnica.rs
		String parts[] = str.split(",");
		String korisnickoIme = parts[0];
		String lozinka = parts[1];
		String jmbg = parts[2];
		String email = parts[3];
		OsobaDAO oDAO = new OsobaDAO();
		Osoba o = oDAO.loadOsobaByJmbG(jmbg);
		return new Admin(korisnickoIme, lozinka, o, email);
	}
	
	public void saveAllAdminToFile(HashMap<String, Admin> map) {
		try (PrintWriter out = FileUtils.getPrintWriter("Admin")) {
			for (Admin a : map.values()) {
				out.println(toFileString(a));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String toFileString(Admin a) {
		KorisnikDAO dao = new KorisnikDAO();
		return dao.toFileString(a) + "," + 
				StringUtils.clean(a.getOsoba().getJmbg()) + "," + StringUtils.clean(a.getEmail());
	}
}
