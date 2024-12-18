package model.osobe;

import util.StringUtils;

public class Osoba {
	
	private String jmbg;
	private String ime;
	private String prezime;

	public Osoba(String jmbg, String ime, String prezime) {
		this.jmbg = jmbg;
		this.ime = ime;
		this.prezime = prezime;
	}
	
	@Override
	public String toString() {
		return StringUtils.clean(jmbg) + " " + StringUtils.clean(ime) + " " + StringUtils.clean(prezime);
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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
}
