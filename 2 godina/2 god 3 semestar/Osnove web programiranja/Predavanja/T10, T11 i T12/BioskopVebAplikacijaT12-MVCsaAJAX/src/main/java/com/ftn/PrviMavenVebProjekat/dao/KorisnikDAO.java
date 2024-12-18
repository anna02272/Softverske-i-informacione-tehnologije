package com.ftn.PrviMavenVebProjekat.dao;

import java.util.List;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;

public interface KorisnikDAO {

	public Korisnik findOne(String korisnickoIme);

	public Korisnik findOne(String korisnickoIme, String lozinka);

	public List<Korisnik> findAll();

	public List<Korisnik> find(String korisnickoIme, String eMail, String pol, Boolean administrator);
	
	public int save(Korisnik korisnik);

	public int update(Korisnik korisnik);

	public int delete(String korisnickoIme);
}
