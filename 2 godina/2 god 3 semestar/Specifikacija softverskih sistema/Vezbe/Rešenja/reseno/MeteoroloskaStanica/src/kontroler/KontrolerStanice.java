package kontroler;

import model.DnevnaTemperatura;
import repozitorijum.DnevnaTemperaturaRepozitorijum;

/**
 * KontrolerStanice - klasa koja implementira kontroler deo MVC paterna
 */
public class KontrolerStanice {
	private DnevnaTemperaturaRepozitorijum dnevnaTemperaturaRepozitorijum; // model

	public KontrolerStanice(DnevnaTemperaturaRepozitorijum dnevnaTemperaturaRepozitorijum) {
		this.dnevnaTemperaturaRepozitorijum = dnevnaTemperaturaRepozitorijum;
	}

	/**
	 * unosNoveTemperature - prima ulaz od prozora (sadržaj unetog stringa), vrši
	 * provere, prevodi ga u broj i poziva metodu iz modela
	 */
	public void unosNoveTemperature(String unetaTemperatura, DnevnaTemperatura dnevnaTemperatura) {
		if (unetaTemperatura == null)
			throw new NullPointerException("bla");
		float temperatura = Float.parseFloat(unetaTemperatura); // potencijalno baca NumberFormatException
		dnevnaTemperaturaRepozitorijum.dodajTemperaturu(temperatura, dnevnaTemperatura);
	}

	public void obrisiMerenje(int index) {
		this.dnevnaTemperaturaRepozitorijum.obrisiMerenje(index);

	}

	public void dodajMerenje(DnevnaTemperatura dnevnaTemperatura) {
		dnevnaTemperaturaRepozitorijum.dodajMerenje(dnevnaTemperatura);

	}
}
