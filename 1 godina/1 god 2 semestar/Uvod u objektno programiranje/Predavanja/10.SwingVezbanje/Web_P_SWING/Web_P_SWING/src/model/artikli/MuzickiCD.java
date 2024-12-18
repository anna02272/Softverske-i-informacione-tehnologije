package model.artikli;

import java.util.ArrayList;

import util.StringUtils;

public class MuzickiCD extends Artikal {

	private String izvodjac;
	private String zanr;
	private ArrayList<Kompzicija> kompozicije;

	public MuzickiCD(String naziv, double cena, int godinaProizvodnje, 
			int brojRaspolozivihPrimeraka,
			int brojProdatihPrimeraka, String izvodjac,
			String zanr) {
		super(naziv, cena, godinaProizvodnje, brojRaspolozivihPrimeraka, brojProdatihPrimeraka);
		this.izvodjac = izvodjac;
		this.zanr = zanr;
		this.kompozicije = new ArrayList<Kompzicija>();
	}
	
	public MuzickiCD(String naziv, double cena, int godinaProizvodnje, 
			int brojRaspolozivihPrimeraka,
			int brojProdatihPrimeraka, String izvodjac,
			String zanr, ArrayList<Kompzicija> kompozicije) {
		super(naziv, cena, godinaProizvodnje, brojRaspolozivihPrimeraka, brojProdatihPrimeraka);
		this.izvodjac = izvodjac;
		this.zanr = zanr;
		this.kompozicije = kompozicije;
	}
	
	public MuzickiCD(int id, String naziv, double cena, int godinaProizvodnje, 
			int brojRaspolozivihPrimeraka,
			int brojProdatihPrimeraka, String izvodjac,
			String zanr) {
		super(id, naziv, cena, godinaProizvodnje, brojRaspolozivihPrimeraka, brojProdatihPrimeraka);
		this.izvodjac = izvodjac;
		this.zanr = zanr;
		this.kompozicije = new ArrayList<Kompzicija>();
	}
	
	public MuzickiCD(int id, String naziv, double cena, int godinaProizvodnje, 
			int brojRaspolozivihPrimeraka,
			int brojProdatihPrimeraka, String izvodjac,
			String zanr, ArrayList<Kompzicija> kompozicije) {
		super(id, naziv, cena, godinaProizvodnje, brojRaspolozivihPrimeraka, brojProdatihPrimeraka);
		this.izvodjac = izvodjac;
		this.zanr = zanr;
		this.kompozicije = kompozicije;
	}

	@Override
	public String toString() {
		return super.toString() + " " + StringUtils.clean(izvodjac) + " " + StringUtils.clean(zanr);
	}
	
	public String toStringAllKompozicije() {
		StringBuilder sb = new StringBuilder();
		for (Kompzicija kompzicija : kompozicije) {
			sb.append("\t"+kompzicija.toString()+"\n");
		}
		return sb.toString();
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

	public ArrayList<Kompzicija> getKompozicije() {
		return kompozicije;
	}

	public void setKompozicije(ArrayList<Kompzicija> kompozicije) {
		this.kompozicije = kompozicije;
	}
}
