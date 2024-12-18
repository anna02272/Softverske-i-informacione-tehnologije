package DAO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.porudzbine.Korpa;
import model.porudzbine.Stavka;
import util.FileUtils;
import util.StringUtils;

public class KorpaDAO {

	public Korpa loadKorpaById(int id) {
		HashMap<Integer, Korpa> map = loadAllKorpaFromFile();
		return map.get(id);
	}
	
	public HashMap<Integer, Korpa> loadAllKorpaFromFile() {
		HashMap<Integer, Korpa> map = new HashMap<Integer, Korpa>();
		try (BufferedReader in = FileUtils.getReader("Korpa")) {
			String line;
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			while ((line = in.readLine()) != null) {
				Korpa k = fromString(line);
				map.put(k.getId(),k);
			}
			StavkaDAO sDAO = new StavkaDAO();
			HashMap<Integer, ArrayList<Stavka>> mapS = sDAO.loadAllStavkaFromFile();
			for (Integer idKorpa : map.keySet()) {
				map.get(idKorpa).setStavke(mapS.get(idKorpa));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Korpa fromString(String str) {
		String parts[] = str.split(",");
		int id = Integer.parseInt(parts[0]);
		Date datumKreiranja = StringUtils.parseDate(parts[1]);
		return new Korpa(id, datumKreiranja);
	}
	
	public void saveAllKorpaToFile(HashMap<Integer, Korpa> map) {
		try (PrintWriter out = FileUtils.getPrintWriter("Korpa")) {
			HashMap<Integer, ArrayList<Stavka>> mapS = new HashMap<Integer, ArrayList<Stavka>>();
			for (Korpa k : map.values()) {
				out.println(toFileString(k));
				mapS.put(k.getId(), k.getStavke());
			}
			StavkaDAO sDAO = new StavkaDAO();
			sDAO.saveAllStavkaToFile(mapS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toFileString(Korpa k) {
		return k.getId() + "," + StringUtils.formatDate(k.getDatumKreiranja());
	}
}
