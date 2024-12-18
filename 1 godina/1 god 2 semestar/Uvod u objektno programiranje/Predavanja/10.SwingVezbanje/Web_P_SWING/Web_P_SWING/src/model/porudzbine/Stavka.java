package model.porudzbine;

import model.artikli.Artikal;

public class Stavka {
	
	private Artikal artikal;
	private int kolicina;

	public Stavka(Artikal artikal, int kolicina) {
		super();
		this.artikal = artikal;
		this.kolicina = kolicina;
	}
	
	@Override
	public String toString() {
		return artikal.getId() + " " + kolicina;
	}
	
	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
}
