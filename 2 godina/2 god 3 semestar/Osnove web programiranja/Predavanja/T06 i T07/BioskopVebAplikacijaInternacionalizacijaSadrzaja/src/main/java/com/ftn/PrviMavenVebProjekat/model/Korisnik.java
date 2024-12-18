package com.ftn.PrviMavenVebProjekat.model;

public class Korisnik {

	String ime="", prezime="", pol="muški", korisnickoIme="", korisnickaSifra="";
	double plata=0;
	boolean administrator = false;
	boolean ulogovan=false;
	
	public Korisnik() {
		super();
	}
 
	public Korisnik(String ime, String prezime, String pol, String korisnickoIme, String korisnickaSifra, double plata) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.korisnickoIme = korisnickoIme;
		this.korisnickaSifra = korisnickaSifra;
		this.plata = plata;
	}
	
	public Korisnik(String ime, String prezime, String pol, String korisnickoIme, String korisnickaSifra, double plata,
			boolean administrator) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.korisnickoIme = korisnickoIme;
		this.korisnickaSifra = korisnickaSifra;
		this.plata = plata;
		this.administrator = administrator;
	}

	
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getKorisnickaSifra() {
		return korisnickaSifra;
	}

	public void setKorisnickaSifra(String korisnickaSifra) {
		this.korisnickaSifra = korisnickaSifra;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public boolean isUlogovan() {
		return ulogovan;
	}

	public void setUlogovan(boolean ulogovan) {
		this.ulogovan = ulogovan;
	}
}
