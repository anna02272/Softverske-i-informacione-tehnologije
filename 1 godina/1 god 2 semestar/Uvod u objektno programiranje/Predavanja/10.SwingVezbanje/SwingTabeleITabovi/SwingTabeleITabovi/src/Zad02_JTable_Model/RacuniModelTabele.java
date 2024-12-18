package Zad02_JTable_Model;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class RacuniModelTabele extends AbstractTableModel {
	
	private final String[] columnNames = {"Sifra", "Naziv", "U Upotrebi", "Stanje"};
	
	ArrayList<Racun> listaElemenata;
	
	public RacuniModelTabele(ArrayList<Racun> lista) {
		super();
		this.listaElemenata = new ArrayList<Racun>();
		listaElemenata.addAll(lista);
	}

	public void setNewList(ArrayList<Racun> lista){
		listaElemenata = new ArrayList<Racun>();
		listaElemenata.addAll(lista);
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return listaElemenata.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(row >= listaElemenata.size())
			return null;
		
		Racun ra = listaElemenata.get(row);
		Object retVal = null;
		switch (col) {
		case 0: retVal = ra.getIndetifikator();
				break;
		case 1: retVal =  ra.getNazivRacuna();
				break;
		case 2: retVal =  ra.isAktivan();
				break;
		case 3: retVal =  ra.getStanjeRacuna();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	
	@Override
	public boolean isCellEditable(int row, int col) {
		// prva  kolona ne moze da se menja
		// sve ostale mogu
		boolean retVal = false;
		if ( col >= 1 )
			retVal = true;
		
		return retVal;
	}
	

	@Override
	public void setValueAt(Object value, int row, int col) {
		
		Racun ra = listaElemenata.get(row);
		switch (col) {
		case 0: ra.setIndetifikator((String)value);
				break;
		case 1: ra.setNazivRacuna((String)value);
				break;
		case 2: ra.setAktivan((Boolean)value);
				break;
		case 3: ra.setStanjeRacuna((Double)value);
				break;
		default:
				System.out.println("Ne postoji kolona");
				System.exit(0);
		}
		//ovde bi bilo cuvanje u bazu da radimo sa njom
		
		fireTableCellUpdated(row, col);
	}
	
	/*** KORISNICKE METODE****/
	public void addValue(Racun element) {
		listaElemenata.add(element);
		fireTableDataChanged();
	}
	
	public void setVelue(Racun element, int index) {
		listaElemenata.set(index, element);
		fireTableDataChanged();
	}
	
	public void removeVelueAt(int index) {
		listaElemenata.remove(index);
		fireTableDataChanged();
	}

	public ArrayList<Racun> getListaElemenata() {
		return listaElemenata;
	}
}
