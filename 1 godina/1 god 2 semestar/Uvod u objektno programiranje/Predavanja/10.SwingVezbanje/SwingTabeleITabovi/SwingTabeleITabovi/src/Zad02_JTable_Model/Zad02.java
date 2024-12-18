package Zad02_JTable_Model;

import java.util.ArrayList;

public class Zad02 {

	private static ArrayList<Racun> listaRacuna = new ArrayList<Racun>();
	
	public static void citajIzFajla(){
		listaRacuna.add(new Racun("001", "dinarski racuna Petra", true, 3200));
		listaRacuna.add(new Racun("002", "dinarski racuna Marka", false, 200));
		listaRacuna.add(new Racun("003", "devizni racuna Ivan", true, 5500));
		listaRacuna.add(new Racun("004", "devizni racuna Sonja", false, 30));
		
		listaRacuna.get(1).setStatusZapisa(false);
		listaRacuna.get(3).setStatusZapisa(false);
	}
	
	public static ArrayList<Racun> vratiListuRacuna(){
		return listaRacuna;
	}
	
	public static void main(String[] args) {
		citajIzFajla();
		JTable_Model_Dialog dialog = new JTable_Model_Dialog();
		dialog.setVisible(true);
	}
}
