package aplikacija;

import kontroler.KontrolerStanice;
import model.DnevnaTemperatura;
import pogled.GlavniProzor;
import pogled.ProzorMeteoroloskeStanice;

public class Aplikacija {

	public static void main(String[] args) {
		GlavniProzor.getInstance();
//		DnevnaTemperatura dnevnaTemperatura = new DnevnaTemperatura();
//		KontrolerStanice kontroler = new KontrolerStanice(dnevnaTemperatura);
//		ProzorMeteoroloskeStanice prozor = new ProzorMeteoroloskeStanice(dnevnaTemperatura, kontroler);	
//		prozor.setVisible(true);				
	}
}
