package DAO;

import java.util.HashMap;

import model.artikli.Artikal;
import model.artikli.Knjiga;
import model.artikli.MuzickiCD;
import util.StringUtils;

public class ArtikalDAO {

	public Artikal loadArtikalById(int id) {
		return loadAllArtikalFromFile().get(id);
	}
	
	public HashMap<Integer, Artikal> loadAllArtikalFromFile() {
		HashMap<Integer, Artikal> map = new HashMap<Integer, Artikal>();
		
		MuzickiCDDAO mDAO = new MuzickiCDDAO();
		map.putAll(mDAO.loadAllMuzickiCDFromFile());
		KnjigaDAO kDAO = new KnjigaDAO();
		map.putAll(kDAO.loadAllKnjigaFromFile());
		
		return map;
	}
	
	public void saveAllArtikalToFile(HashMap<Integer, Artikal> map) {
		HashMap<Integer, MuzickiCD> mapMuzickiCD = new HashMap<Integer, MuzickiCD>();
		HashMap<Integer, Knjiga> mapKnjiga = new HashMap<Integer, Knjiga>();
		
		for (Artikal a : map.values()) {
			if (a instanceof MuzickiCD) {
				mapMuzickiCD.put(a.getId(), (MuzickiCD)a);
			} else if (a instanceof Knjiga){
				mapKnjiga.put(a.getId(), (Knjiga)a);
			}
		}
		
		MuzickiCDDAO mDAO = new MuzickiCDDAO();
		mDAO.saveAllMuzickiCDToFile(mapMuzickiCD);
		KnjigaDAO kDAO = new KnjigaDAO();
		kDAO.saveAllKnjigaToFile(mapKnjiga);
	}
	
	public String toFileString(Artikal obj) {
		return obj.getId() + "," + StringUtils.clean(obj.getNaziv()) + "," + 
				obj.getCena() + "," + obj.getGodinaProizvodnje()
				+ "," + obj.getBrojRaspolozivihPrimeraka()+ "," + obj.getBrojProdatihPrimeraka();
	}
}
