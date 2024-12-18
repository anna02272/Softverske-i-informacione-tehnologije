package DAO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.osobe.Kupac;
import model.porudzbine.Korpa;
import model.porudzbine.Porudzbina;
import util.FileUtils;
import util.StringUtils;

public class PorudzbinaDAO {

	public Porudzbina loadPorudzbinaById(int id) {
		HashMap<Integer, Porudzbina> map = loadAllPorudzbinaFromFile();
		return map.get(id);
	}
	
	public ArrayList<Porudzbina> loadPorudzbinaByKorisnickoImeKupac(String ki) {
		HashMap<Integer, Porudzbina> map = loadAllPorudzbinaFromFile();
		ArrayList<Porudzbina> temp = new ArrayList<Porudzbina>();
		for (Porudzbina porudzbina : map.values()) {
			if(porudzbina.getKupac().getKorisnickoIme().equals(ki)){
				temp.add(porudzbina);
			}
		}
		return temp;
	}
	
	public HashMap<Integer, Porudzbina> loadAllPorudzbinaFromFile() {
		HashMap<Integer, Porudzbina> map = new HashMap<Integer, Porudzbina>();
		try (BufferedReader in = FileUtils.getReader("Porudzbina")) {
			String line;
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			while ((line = in.readLine()) != null) {
				Porudzbina po = fromString(line);
				map.put(po.getId(),po);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Porudzbina fromString(String str) {
		String parts[] = str.split(",");
		int id = Integer.parseInt(parts[0]);
		String ki = parts[1];
		int idKorpe = Integer.parseInt(parts[2]);
		Date datumPorucivanja = StringUtils.parseDate(parts[3]);
		Date datumIsporuke = StringUtils.parseDate(parts[4]);
		double ukupnaCena = Double.parseDouble(parts[5]);
		double trokskoviSlanja = Double.parseDouble(parts[6]);
		KupacDAO dao = new KupacDAO();
		Kupac ku = dao.loadKupacByKorisnickoIme(ki);
		KorpaDAO kDAO = new KorpaDAO();
		Korpa k = kDAO.loadKorpaById(idKorpe);
		return new Porudzbina(id, ku, k, datumPorucivanja, datumIsporuke, ukupnaCena,
				trokskoviSlanja);
	}
	
	public void saveAllPorudzbinaToFile(HashMap<Integer, Porudzbina> map) {
		try (PrintWriter out = FileUtils.getPrintWriter("Porudzbina")) {
			for (Porudzbina p : map.values()) {
				out.println(toFileString(p));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toFileString(Porudzbina p) {
		return p.getId() + "," + p.getKupac().getKorisnickoIme() + "," + p.getKorpa().getId() + "," 
				+ StringUtils.formatDate(p.getDatumPorucivanja()) + ","
				+ StringUtils.formatDate(p.getDatumIsporuke()) 
				+ "," + p.getUkupnaCena() + "," + p.getTrokskoviSlanja();
	}
}
