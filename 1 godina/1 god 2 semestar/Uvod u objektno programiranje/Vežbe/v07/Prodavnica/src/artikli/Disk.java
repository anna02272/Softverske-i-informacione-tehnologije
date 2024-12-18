package artikli;

import java.util.ArrayList;

public class Disk extends Artikal {

	private String izvodjac;
	private String zanr;
	private ArrayList<Kompozicija> kompozicije;
	
	public Disk() {
		this.izvodjac = "";
		this.zanr = "";
		this.kompozicije = new ArrayList<Kompozicija>();
	}
	
	public Disk(String identifikacioniKod, double cena, String naziv, boolean obrisan, String izvodjac, String zanr,
			ArrayList<Kompozicija> kompozicije) {
		super(identifikacioniKod, cena, naziv, obrisan);
		this.izvodjac = izvodjac;
		this.zanr = zanr;
		this.kompozicije = kompozicije;
	}

	public String getIzvodjac() {
		return izvodjac;
	}

	public void setIzvodjac(String izvodjac) {
		this.izvodjac = izvodjac;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public ArrayList<Kompozicija> getKompozicije() {
		return kompozicije;
	}

	public void setKompozicije(ArrayList<Kompozicija> kompozicije) {
		this.kompozicije = kompozicije;
	}

	@Override
	public String toString() {
		String s = "DISK " + super.toString() + 
				"\nIzvodjac: " + this.izvodjac +
				"\nZanr: " + this.zanr;
		for (Kompozicija kompozicija : kompozicije) {
			s += "\n" + kompozicija;
		}
		return s;
	}
	
}