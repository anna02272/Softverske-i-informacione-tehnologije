package GUI;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import DAO.AdminDAO;
import DAO.KorisnikDAO;
import DAO.KupacDAO;
import model.osobe.Admin;
import model.osobe.Korisnik;
import model.osobe.Kupac;
import prodavnica.Prodavnica;

public class KorisniciTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8836209936186632221L;
	private ArrayList<Korisnik> korisnici;
	KorisnikDAO dao = new KorisnikDAO();

	public KorisniciTableModel() {
		korisnici = new ArrayList<>(dao.loadAllKorisnikFromFile().values());
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return korisnici.size();
	}

	@Override
	public String getValueAt(int row, int col) {
		Korisnik k = korisnici.get(row);
		switch (col) {
		case 0:
			return k.getKorisnickoIme();
		case 1:
			return k.getLozinka();
		case 2:
			return k.getClass().toString().replace("model.osobe.", "").replace("class", "");
		default:
			return "";
		}
	}
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Korisnicko ime";
		case 1:
			return "Lozinka";
		case 2:
			return "Tip Korisnika";
		default:
			return "";
		}
	}
	
	public void add(Korisnik k) {
		
		boolean found = false;
		for (int i = 0; i < korisnici.size(); i++) {
			if(korisnici.get(i).getKorisnickoIme().equals(k.getKorisnickoIme())) {
				korisnici.set(i, k);
				found = true;
				break;
			}
		}
		if(!found)
			korisnici.add(k);
		
		fireTableDataChanged();
		
		if(k instanceof Kupac){
			KupacDAO kDAO = new KupacDAO();
			HashMap<String, Kupac> map = kDAO.loadAllKupacFromFile();
			map.put(k.getKorisnickoIme(), (Kupac)k);
			kDAO.saveAllKupacToFile(map);
		}
		else if(k instanceof Admin){
			AdminDAO aDAO = new AdminDAO();
			HashMap<String, Admin> map = aDAO.loadAllAdminFromFile();
			map.put(k.getKorisnickoIme(), (Admin)k);
			aDAO.saveAllAdminToFile(map);
		}
	}
	
	public void update(Korisnik k, int row) {
		korisnici.set(row, k);
		fireTableRowsUpdated(row, row);
		if(k instanceof Kupac){
			KupacDAO kDAO = new KupacDAO();
			HashMap<String, Kupac> map = kDAO.loadAllKupacFromFile();
			map.put(k.getKorisnickoIme(), (Kupac)k);
			kDAO.saveAllKupacToFile(map);
		}
		else if(k instanceof Admin){
			AdminDAO aDAO = new AdminDAO();
			HashMap<String, Admin> map = aDAO.loadAllAdminFromFile();
			map.put(k.getKorisnickoIme(), (Admin)k);
			aDAO.saveAllAdminToFile(map);
		}
	}
	
	public void delete(int row) {
		Korisnik k = korisnici.remove(row);
		fireTableRowsDeleted(row, row);
		if(k instanceof Kupac){
			KupacDAO kDAO = new KupacDAO();
			HashMap<String, Kupac> map = kDAO.loadAllKupacFromFile();
			map.remove(k.getKorisnickoIme());
			kDAO.saveAllKupacToFile(map);
		}
		else if(k instanceof Admin){
			AdminDAO aDAO = new AdminDAO();
			HashMap<String, Admin> map = aDAO.loadAllAdminFromFile();
			map.remove(k.getKorisnickoIme());
			aDAO.saveAllAdminToFile(map);
		}
	}
}
