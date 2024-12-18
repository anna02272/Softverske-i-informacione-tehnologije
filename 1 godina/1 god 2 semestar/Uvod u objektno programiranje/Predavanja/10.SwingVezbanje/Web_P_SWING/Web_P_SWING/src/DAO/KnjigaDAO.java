package DAO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import model.artikli.Knjiga;
import util.FileUtils;
import util.StringUtils;

public class KnjigaDAO {

	public Knjiga loadKnjigaById(int id) {
		HashMap<Integer, Knjiga> map = loadAllKnjigaFromFile();
		return map.get(id);
	}
	
	public HashMap<Integer, Knjiga> loadAllKnjigaFromFile() {
		HashMap<Integer, Knjiga> map = new HashMap<Integer, Knjiga>();
		try (BufferedReader in = FileUtils.getReader("Knjiga")) {
			String line;
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			while ((line = in.readLine()) != null) {
				Knjiga k = fromString(line);
				map.put(k.getId(),k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	private  Knjiga fromString(String str) {
		//11,A Brief History of Time,10.19,1998,100,500,Stephen Hawking,212,Bantam
		String parts[] = str.split(",");
		int id = Integer.parseInt(parts[0]);
		String naziv = parts[1];
		double cena = Double.parseDouble(parts[2]);
		int godinaP = Integer.parseInt(parts[3]);
		int brRp = Integer.parseInt(parts[4]);
		int brPp = Integer.parseInt(parts[5]);
		String autor = parts[6];
		int brojStrana = Integer.parseInt(parts[7]);
		String izdavac = parts[8];
		return new Knjiga(id, naziv, cena, godinaP, brRp, brPp, autor, brojStrana, izdavac);
	}
	
	public void saveAllKnjigaToFile(HashMap<Integer, Knjiga> map) {
		try (PrintWriter out = FileUtils.getPrintWriter("Knjiga")) {
			for (Knjiga k : map.values()) {
				out.println(toFileString(k));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toFileString(Knjiga k) {
		return k.getId() + "," + StringUtils.clean(k.getNaziv()) + "," + k.getCena() 
				+ "," + k.getGodinaProizvodnje() + "," + k.getBrojRaspolozivihPrimeraka()
				+ "," + k.getBrojProdatihPrimeraka() + "," + StringUtils.clean(k.getAutor()) 
				+ "," + k.getBrojStrana() + "," + StringUtils.clean(k.getIzdavac());
	}
}
