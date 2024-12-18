package model.osobe;

import util.StringUtils;

public abstract class Korisnik {

	private String korisnickoIme;
	private String lozinka;
	
	public Korisnik(String korisnickoIme, String lozinka) {
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}
	
	@Override
	public String toString() {
		return StringUtils.clean(korisnickoIme) + " " + StringUtils.clean(lozinka);
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}	
}
