package DAO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import model.artikli.Kompzicija;
import util.FileUtils;
import util.StringUtils;

public class KompozicijaDAO {

	public ArrayList<Kompzicija> loadAllByIdMCD(int idMCD) {
		HashMap<Integer, ArrayList<Kompzicija>> map = loadAllKompzicijaFromFile();
		return map.get(idMCD);
	}
	
	public HashMap<Integer, ArrayList<Kompzicija>> loadAllKompzicijaFromFile() {
		HashMap<Integer, ArrayList<Kompzicija>> map = new HashMap<Integer, ArrayList<Kompzicija>>();
		try (BufferedReader in = FileUtils.getReader("MuzickiCDKompozicije")) {
			String line;
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			while ((line = in.readLine()) != null) {
				//30,14,Aerials,6.11
				int z = line.indexOf(",");
				int idMCD = Integer.parseInt(line.substring(0,z));
				if (!map.containsKey(idMCD)) {
					map.put(idMCD, new ArrayList<Kompzicija>());
				}
				map.get(idMCD).add(fromFileString(line));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Kompzicija fromFileString(String str) {
		//30,14,Aerials,6.11
		String parts [] = str.split(",");
		int redBr = Integer.parseInt(parts[1]);
		String naziv = parts[2];
		double trajanje = Double.parseDouble(parts[3]);
		return new Kompzicija(redBr, naziv, trajanje);
	}
	
	public void saveAllKompozicijaToFile(HashMap<Integer, ArrayList<Kompzicija>> map) {
		try (PrintWriter out = FileUtils.getPrintWriter("MuzickiCDKompozicije")) {
			for (Integer idMCD : map.keySet()) {
				for (Kompzicija k : map.get(idMCD)) {
					out.println(toFileString(idMCD, k));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toFileString(int idMCD, Kompzicija k) {
		//nedostaje samo id Muzickog CD a to cemo naknadno dodati
		return idMCD + "," + k.getRedBr() + "," + StringUtils.clean(k.getNaziv()) + "," + k.getTrajanje();
	}
}
