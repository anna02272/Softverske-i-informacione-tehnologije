package DAO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import model.osobe.Osoba;
import util.FileUtils;
import util.StringUtils;

public class OsobaDAO {

	
	public Osoba loadOsobaByJmbG(String jmbg) {
		HashMap<String, Osoba> map = loadAllOsobaFromFile();
		return map.get(jmbg);
	}
	
	public HashMap<String, Osoba> loadAllOsobaFromFile() {
		HashMap<String, Osoba> map = new HashMap<String, Osoba>();
		try (BufferedReader in = FileUtils.getReader("Osoba")) {
			String line;
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			while ((line = in.readLine()) != null) {
				Osoba o = fromString(line);
				map.put(o.getJmbg(),o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Osoba fromString(String str) {
		//1,Pera,Perić
		String parts[] = str.split(",");
		String jmbg = parts[0];
		String ime = parts[1];
		String prezime = parts[2];
		return new Osoba(jmbg, ime, prezime);
	}
	
	public void saveAllOsobaToFile(HashMap<String, Osoba> map) {
		try (PrintWriter out = FileUtils.getPrintWriter("Osoba")) {
			for (Osoba o : map.values()) {
				out.println(toFileString(o));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toFileString(Osoba o) {
		return StringUtils.clean(o.getJmbg()) + "," + StringUtils.clean(o.getIme()) 
			+ "," + StringUtils.clean(o.getPrezime());
	}
}
