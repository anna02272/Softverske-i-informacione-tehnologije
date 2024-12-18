package Zad04Fakultet.ListAndTableModels;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Zad04Fakultet.DataClasses.Student;

@SuppressWarnings({ "rawtypes", "serial", "unchecked"})
public class StudentiTabeleModel extends AbstractTableModel {
	
	private final String[] columnNames = {"JMBG", "Prezime i Ime", "Index", "Kategorija Finansiranja", "Godina Upisa"};
	
	ArrayList<Student> listaStudenta;
	
	public StudentiTabeleModel(ArrayList<Student> lista) {
		super();
		this.listaStudenta = new ArrayList<Student>();
		this.listaStudenta.addAll(lista);
	}

	public void setNewList(ArrayList<Student> lista){
		listaStudenta = new ArrayList<Student>();
		listaStudenta.addAll(lista);
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return listaStudenta.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(row >= listaStudenta.size())
			return null;
		
		Student st = listaStudenta.get(row);
		Object retVal = null;
		switch (col) {
		case 0: retVal = st.getJMBG();
				break;
		case 1: retVal =  st.getPrezime() + " " + st.getIme();
				break;
		case 2: retVal =  st.getIndex();
				break;
		case 3: retVal =  st.getKatFin().getNaziv();
				break;
		case 4: retVal =  st.getGodinaUpisa();
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
	public void addValue(Student element) {
		listaStudenta.add(element);
		fireTableDataChanged();
	}
	
	public void setVelueAt(Student element, int index) {
		listaStudenta.set(index, element);
		fireTableDataChanged();
	}
	
	public void setVelueById(Student element, String id) {
		for (int i = 0; i < listaStudenta.size(); i++) {
			if (listaStudenta.get(i).getJMBG().equalsIgnoreCase(id)) {
				listaStudenta.set(i, element);
				break;
			}
		}
		fireTableDataChanged();
	}
	
	public void removeVelueAt(int index) {
		listaStudenta.remove(index);
		fireTableDataChanged();
	}

	public void removeVelueById(String id) {
		for (int i = 0; i < listaStudenta.size(); i++) {
			if (listaStudenta.get(i).getJMBG().equalsIgnoreCase(id)) {
				listaStudenta.remove(i);
				break;
			}
		}
		fireTableDataChanged();
	}
	
	public ArrayList<Student> getListaStudenta() {
		return listaStudenta;
	}
}
