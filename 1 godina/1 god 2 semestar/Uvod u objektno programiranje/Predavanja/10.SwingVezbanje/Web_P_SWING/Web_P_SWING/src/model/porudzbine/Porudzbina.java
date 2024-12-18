package model.porudzbine;

import java.util.Date;

import model.osobe.Kupac;
import util.StringUtils;

public class Porudzbina {
	private static int brojacId = 0;
	public static final double PDV = 0.25;

	private int id;
	private Kupac kupac;
	private Korpa korpa;
	private Date datumPorucivanja;
	private Date datumIsporuke;
	private double ukupnaCena;
	private double trokskoviSlanja;

	public Porudzbina(int id, Kupac kupac, Korpa korpa) {
		this.id = id;
		this.kupac = kupac;
		this.korpa = korpa;
		this.ukupnaCena = korpa.izracunajUkupnuCenu();
		this.datumPorucivanja = new Date();
		
		if(brojacId<id)
			brojacId = id;
	}

	public Porudzbina(int id, Kupac kupac, Korpa korpa, Date datumPorucivanja, Date datumIsporuke, double ukupnaCena,
			double trokskoviSlanja) {
		this.id = id;
		this.kupac = kupac;
		this.korpa = korpa;
		this.datumPorucivanja = datumPorucivanja;
		this.datumIsporuke = datumIsporuke;
		this.ukupnaCena = ukupnaCena;
		this.trokskoviSlanja = trokskoviSlanja;
		
		if(brojacId<id)
			brojacId = id;
	}
	
	@Override
	public String toString() {
		return id + " " + kupac.getKorisnickoIme() + " " + korpa.getId() + " " + StringUtils.formatDate(datumPorucivanja) + " "
				+ StringUtils.formatDate(datumIsporuke) + " " + ukupnaCena + " " + trokskoviSlanja;
	}
	
	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}

	public Date getdatumPorucivanja() {
		return datumPorucivanja;
	}

	public void setdatumPorucivanja(Date datumPorucivanja) {
		this.datumPorucivanja = datumPorucivanja;
	}

	public Date getDatumIsporuke() {
		return datumIsporuke;
	}

	public void setDatumIsporuke(Date datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}

	public Date getDatumPorucivanja() {
		return datumPorucivanja;
	}

	public void setDatumPorucivanja(Date datumPorucivanja) {
		this.datumPorucivanja = datumPorucivanja;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public double getTrokskoviSlanja() {
		return trokskoviSlanja;
	}

	public void setTrokskoviSlanja(double trokskoviSlanja) {
		this.trokskoviSlanja = trokskoviSlanja;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}
}
