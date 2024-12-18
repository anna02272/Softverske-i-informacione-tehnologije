package Zad04Fakultet.ListAndTableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Zad04Fakultet.DataClasses.Profesor;

@SuppressWarnings({ "rawtypes", "serial", "unchecked"})
public class ProfesorTabeleModel extends AbstractTableModel {
	
	private final String[] columnNames = {"JMBG", "Prezime i Ime", "Zvanje", "Plata"};
	
	ArrayList<Profesor> lista;
	
	public ProfesorTabeleModel(ArrayList<Profesor> lista) {
		super();
		this.lista = new ArrayList<Profesor>();
		this.lista.addAll(lista);
	}

	public void setNewList(ArrayList<Profesor> lista){
		this.lista = new ArrayList<Profesor>();
		this.lista.addAll(lista);
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(row >= lista.size())
			return null;
		
		Profesor pr = lista.get(row);
		Object retVal = null;
		switch (col) {
		case 0: retVal = pr.getJMBG();
				break;
		case 1: retVal =  pr.getPrezime() + " " + pr.getIme();
				break;
		case 2: retVal =  pr.getZvanje();
				break;
		case 3: retVal =  pr.getPlata();
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
	public void addValue(Profesor element) {
		lista.add(element);
		fireTableDataChanged();
	}
	
	public void setVelueAt(Profesor element, int index) {
		lista.set(index, element);
		fireTableDataChanged();
	}
	
	public void setVelueById(Profesor element, String id) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getJMBG().equalsIgnoreCase(id)) {
				lista.set(i, element);
				break;
			}
		}
		fireTableDataChanged();
	}
	
	public void removeVelueAt(int index) {
		lista.remove(index);
		fireTableDataChanged();
	}

	public void removeVelueById(String id) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getJMBG().equalsIgnoreCase(id)) {
				lista.remove(i);
				break;
			}
		}
		fireTableDataChanged();
	}
	
	public ArrayList<Profesor> getLista() {
		return lista;
	}
}

