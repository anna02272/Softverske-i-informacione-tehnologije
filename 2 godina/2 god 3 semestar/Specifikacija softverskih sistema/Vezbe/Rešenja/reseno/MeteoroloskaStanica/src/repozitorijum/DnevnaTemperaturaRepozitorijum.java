package repozitorijum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dogadjaj.Observer;
import dogadjaj.Publisher;
import dogadjaj.UpdateEvent;
import model.DnevnaTemperatura;

public class DnevnaTemperaturaRepozitorijum implements Publisher {

	private long generator;

	private List<DnevnaTemperatura> dnevneTemperature;
	private List<String> kolone;
	private List<Observer> posmatraci;

	public DnevnaTemperaturaRepozitorijum() {
		generator = 0;

		this.dnevneTemperature = new ArrayList<DnevnaTemperatura>();

		DnevnaTemperatura dt1 = new DnevnaTemperatura(datumPreNDana(1), 8, 19);
		DnevnaTemperatura dt2 = new DnevnaTemperatura(datumPreNDana(2), 6, 25);
		DnevnaTemperatura dt3 = new DnevnaTemperatura(datumPreNDana(3), 7, 22);

		this.dnevneTemperature.add(dt1);
		this.dnevneTemperature.add(dt2);
		this.dnevneTemperature.add(dt3);

		this.kolone = new ArrayList<String>();
		this.kolone.add("Datum");
		this.kolone.add("Min. temp.");
		this.kolone.add("Max. temp.");
	}

	private long generateId() {
		return ++generator;
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public DnevnaTemperatura getRow(int rowIndex) {
		return this.dnevneTemperature.get(rowIndex);
	}

	public List<DnevnaTemperatura> getDnevneTemperature() {
		return dnevneTemperature;
	}

	public String getValueAt(int row, int column) {
		DnevnaTemperatura dt = this.dnevneTemperature.get(row);

		String sablon = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sablon);
		String datum = simpleDateFormat.format(dt.getDatum());

		switch (column) {
		case 0:
			return datum;
		case 1:
			return Float.toString(dt.getMinVrednost());
		case 2:
			return Float.toString(dt.getMaxVrednost());
		default:
			return null;
		}
	}

	public void dodajTemperaturu(float temperatura, DnevnaTemperatura dnevnaTemperatura) {
		dnevnaTemperatura.dodajTemperaturu(temperatura);
	}

	public void obrisiMerenje(int index) {
		this.dnevneTemperature.remove(index);
		this.notifyObservers();
	}

	public void dodajMerenje(DnevnaTemperatura dnevnaTemperatura) {
		if (!dnevneTemperature.contains(dnevnaTemperatura))
			dnevneTemperature.add(dnevnaTemperatura);
		this.notifyObservers();
	}

	@Override
	public void addObserver(Observer posmatrac) {
		if (null == posmatraci)
			posmatraci = new ArrayList<Observer>();
		posmatraci.add(posmatrac);
	}

	@Override
	public void removeObserver(Observer posmatrac) {
		if (null == posmatraci)
			return;
		posmatraci.remove(posmatrac);
	}

	@Override
	public void notifyObservers() {
		for (Observer posmatrac : this.posmatraci) {
			posmatrac.updatePerformed(new UpdateEvent(new Object()));
		}
	}

	private Date datumPreNDana(int n) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -n);
		return cal.getTime();
	}

}
