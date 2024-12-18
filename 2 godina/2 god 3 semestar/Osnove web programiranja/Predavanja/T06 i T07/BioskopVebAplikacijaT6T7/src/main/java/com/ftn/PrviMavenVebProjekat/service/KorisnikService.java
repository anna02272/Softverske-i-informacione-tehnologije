package com.ftn.PrviMavenVebProjekat.service;

import java.util.List;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;

public interface KorisnikService {

	Korisnik findOne(String korisnickoIme); 
	Korisnik findOne(String korisnickoIme, String korisnickaSifra);
	List<Korisnik> findAll(); 
	Korisnik save(Korisnik korisnik); 
	List<Korisnik> save(List<Korisnik> korisnici); 
	Korisnik update(Korisnik korisnik); 
	List<Korisnik> update(List<Korisnik> korisnici);
	Korisnik delete(String korisnickoIme); 
	void delete(List<String> korisnickaImena); 
	List<Korisnik> findByIme(String ime);
	List<Korisnik> findByPrezime(String prezime);
	List<Korisnik> findByPol(String pol);
	List<Korisnik> findByPlata(double plata);
	List<Korisnik> findByPlataFrom(double plataFrom);
	List<Korisnik> findByPlataTo(double plataTo);
	List<Korisnik> findByPlataFromAndPlataTo(double plataFrom, double plataTo);
	List<Korisnik> findByAdministrator(boolean administrator);
	List<Korisnik> findByUlogovan(boolean ulogovan);
}
