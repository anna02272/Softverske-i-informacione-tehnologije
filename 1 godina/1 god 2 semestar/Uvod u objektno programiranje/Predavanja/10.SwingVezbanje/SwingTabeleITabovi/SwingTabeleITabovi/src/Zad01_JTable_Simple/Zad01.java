package Zad01_JTable_Simple;

import java.util.ArrayList;

public class Zad01 {

	public static ArrayList<Racun> ocitajPodatkeIzBaze(){
		ArrayList<Racun> listaRacuna = new ArrayList<Racun>();
		listaRacuna.add(new Racun("001", "dinarski racuna Petra", true, 3200));
		listaRacuna.add(new Racun("002", "dinarski racuna Marka", false, 200));
		listaRacuna.add(new Racun("003", "devizni racuna Ivan", true, 5500));
		listaRacuna.add(new Racun("004", "devizni racuna Sonja", false, 30));
		
		return listaRacuna;
	}
	public static void main(String[] args) {
		ArrayList<Racun> racuni = ocitajPodatkeIzBaze();
		JTable_Simple_Dialog dialog = new JTable_Simple_Dialog(racuni);
		dialog.setVisible(true);
	}
}
