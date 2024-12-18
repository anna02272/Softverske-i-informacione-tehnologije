package DAO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import model.artikli.Kompzicija;
import model.artikli.MuzickiCD;
import util.FileUtils;
import util.StringUtils;

public class MuzickiCDDAO {

	public MuzickiCD loadMuzickiCDById(int id) {
		HashMap<Integer, MuzickiCD> map = loadAllMuzickiCDFromFile();
		return map.get(id);
	}
	
	public HashMap<Integer, MuzickiCD> loadAllMuzickiCDFromFile() {
		HashMap<Integer, MuzickiCD> map = new HashMap<Integer, MuzickiCD>();
		try (BufferedReader in = FileUtils.getReader("MuzickiCD")) {
			String line;
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			while ((line = in.readLine()) != null) {
				MuzickiCD mCD = fromString(line);
				map.put(mCD.getId(),mCD);
			}
			KompozicijaDAO kDAO = new KompozicijaDAO();
			HashMap<Integer, ArrayList<Kompzicija>> mapK = kDAO.loadAllKompzicijaFromFile();
			for (Integer idMCD : map.keySet()) {
				map.get(idMCD).setKompozicije(mapK.get(idMCD));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public MuzickiCD fromString(String str) {
		//30,Toxicity,6.99,2001,20,50,System of a Down,heavy metal
		String [] parts = str.split(",");
		int id = Integer.parseInt(parts[0]);
		String naziv = parts[1];
		double cena = Double.parseDouble(parts[2]);
		int gdP = Integer.parseInt(parts[3]);
		int brRas = Integer.parseInt(parts[4]);
		int brProd = Integer.parseInt(parts[5]);
		String izvodjac = parts[6];
		String zanr = parts[7];
		ArrayList<Kompzicija> komps = new ArrayList<>();
		return new MuzickiCD(id, naziv, cena, gdP, brRas, brProd, izvodjac, zanr, komps);
	}
	
	public void saveAllMuzickiCDToFile(HashMap<Integer, MuzickiCD> map) {
		try (PrintWriter out = FileUtils.getPrintWriter("MuzickiCD")) {
			HashMap<Integer, ArrayList<Kompzicija>> mapK = new HashMap<Integer, ArrayList<Kompzicija>>();
			for (MuzickiCD mCD : map.values()) {
				out.println(toFileString(mCD));
				mapK.put(mCD.getId(), mCD.getKompozicije());
			}
			KompozicijaDAO kDAO = new KompozicijaDAO();
			kDAO.saveAllKompozicijaToFile(mapK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toFileString(MuzickiCD mCD) {
		ArtikalDAO dao = new ArtikalDAO();
		return dao.toFileString(mCD) + "," + StringUtils.clean(mCD.getIzvodjac()) 
			+ "," + StringUtils.clean(mCD.getZanr());
	}
}
