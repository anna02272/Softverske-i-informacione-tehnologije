package model.osobe;

import util.StringUtils;

public class Admin extends Korisnik {
	
	private Osoba osoba;
	private String email;

	public Admin(String korisnickoIme, String lozinka, Osoba osoba, String email) {
		super(korisnickoIme, lozinka);
		this.osoba = osoba;
		this.email = email;
	}

	@Override
	public String toString() {
		return super.toString() + " " + StringUtils.clean(osoba.getJmbg()) + " " + StringUtils.clean(email);
	}
	
	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
