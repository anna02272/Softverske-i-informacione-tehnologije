package Zad04Fakultet.ListAndTableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Zad04Fakultet.DataClasses.Osoba;

@SuppressWarnings({ "rawtypes", "serial", "unchecked"})
public class OsobeTabeleModel extends AbstractTableModel {
	
	private final String[] columnNames = {"JMBG", "Ime", "Prezime"};
	
	ArrayList<Osoba> listaOsoba;
	
	public OsobeTabeleModel(ArrayList<Osoba> lista) {
		super();
		this.listaOsoba = new ArrayList<Osoba>();
		listaOsoba.addAll(lista);
	}

	public void setNewList(ArrayList<Osoba> lista){
		listaOsoba = new ArrayList<Osoba>();
		listaOsoba.addAll(lista);
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return listaOsoba.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(row >= listaOsoba.size())
			return null;
		
		Osoba os = listaOsoba.get(row);
		Object retVal = null;
		switch (col) {
		case 0: retVal = os.getJMBG();
				break;
		case 1: retVal =  os.getIme();
				break;
		case 2: retVal =  os.getPrezime();
				break;
		default:
				retVal = null;
		}
		
		return retVal;
	}

	/**
	 * Ako se ova metoda ne redefinise, koristi se default renderer/editor za
	 * celiju. To znaci da, ako je kolona tipa boolean, onda ce se u tabeli
	 * prikazati true/false, a ovako ce se za takav tip kolone pojaviti checkbox.
	 */
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

//	posto imamo kontrolisane vrednosti ne moze se menjati sadrzaj table
//	@Override
//	public boolean isCellEditable(int row, int col) {
//		return false;
//	}

//	posto imamo kontrolisane vrednosti ne moze se menjati sadrzaj table
//	@Override
//	public void setValueAt(Object value, int row, int col) {
//	}
	
	//moje metode ka na dalje
	public void addValue(Osoba element) {
		listaOsoba.add(element);
		fireTableDataChanged();
	}
	
	public void setVelueAt(Osoba element, int index) {
		listaOsoba.set(index, element);
		fireTableDataChanged();
	}
	
	public void setVelueById(Osoba element, String id) {
		for (int i = 0; i < listaOsoba.size(); i++) {
			if (listaOsoba.get(i).getJMBG().equalsIgnoreCase(id)) {
				listaOsoba.set(i, element);
				break;
			}
		}
		fireTableDataChanged();
	}
	
	public void removeVelueAt(int index) {
		listaOsoba.remove(index);
		fireTableDataChanged();
	}

	public void removeVelueById(String id) {
		for (int i = 0; i < listaOsoba.size(); i++) {
			if (listaOsoba.get(i).getJMBG().equalsIgnoreCase(id)) {
				listaOsoba.remove(i);
				break;
			}
		}
		fireTableDataChanged();
	}
	
	public ArrayList<Osoba> getListaOsoba() {
		return listaOsoba;
	}
}
