package model.osobe;

import java.util.ArrayList;

import model.porudzbine.Korpa;
import model.porudzbine.Porudzbina;
import util.StringUtils;

public class Kupac extends Korisnik {
	
	private Osoba osoba;
	private double stanjeNaRacunu;
	
	private ArrayList<Porudzbina> porudzbine;

	public Kupac(String korisnickoIme, String lozinka, Osoba osoba, double stanjeNaRacunu) {
		super(korisnickoIme, lozinka);
		this.osoba = osoba;
		this.stanjeNaRacunu = stanjeNaRacunu;
		this.porudzbine = new ArrayList<Porudzbina>();
	}
	
	public Kupac(String korisnickoIme, String lozinka, Osoba osoba, double stanjeNaRacunu, 
			ArrayList<Porudzbina> porudzbine) {
		super(korisnickoIme, lozinka);
		this.osoba = osoba;
		this.stanjeNaRacunu = stanjeNaRacunu;
		this.porudzbine = porudzbine;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + StringUtils.clean(osoba.getJmbg()) + " " + stanjeNaRacunu;
	}
	
	public String toStringAllPorudzbine() {
		StringBuilder sb = new StringBuilder();
		for (Porudzbina p : porudzbine) {
			sb.append("\t"+p.toString()+"\n");
		}
		return sb.toString();
	}
	
	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	public double getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}

	public void setStanjeNaRacunu(double stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}

	public ArrayList<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(ArrayList<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}
}
