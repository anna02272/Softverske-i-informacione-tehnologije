package DAO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import model.artikli.Artikal;
import model.porudzbine.Stavka;
import util.FileUtils;

public class StavkaDAO {

	public ArrayList<Stavka> loadAllStavkaByKorpaID(int idKorpa) {
		HashMap<Integer, ArrayList<Stavka>> map = loadAllStavkaFromFile();
		return map.get(idKorpa);
	}
	
	public HashMap<Integer, ArrayList<Stavka>> loadAllStavkaFromFile() {
		HashMap<Integer, ArrayList<Stavka>> map = new HashMap<Integer, ArrayList<Stavka>>();
		try (BufferedReader in = FileUtils.getReader("KorpaStavke")) {
			String line;
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			while ((line = in.readLine()) != null) {
				int z = line.indexOf(",");
				int idKorpa = Integer.parseInt(line.substring(0,z));
				if (!map.containsKey(idKorpa)) {
					map.put(idKorpa, new ArrayList<Stavka>());
				}
				map.get(idKorpa).add(fromFileString(line));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Stavka fromFileString(String str) {
		String parts[] = str.split(",");
		int artikalId = Integer.parseInt(parts[1]);
		int kolicina = Integer.parseInt(parts[2]);
		ArtikalDAO aDAO = new ArtikalDAO();
		Artikal a = aDAO.loadArtikalById(artikalId);
		return new Stavka(a, kolicina);
	}
	
	public void saveAllStavkaToFile(HashMap<Integer, ArrayList<Stavka>> map) {
		try (PrintWriter out = FileUtils.getPrintWriter("KorpaStavke")) {
			for (Integer idKorpa : map.keySet()) {
				for (Stavka s : map.get(idKorpa)) {
					out.println(toFileString(idKorpa, s));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toFileString(int idKorpa, Stavka s) {
		return idKorpa + "," + s.getArtikal().getId() + "," + s.getKolicina();
	}
}
