package com.ftn.PrviMavenVebProjekat.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;

public interface ProjekcijaService {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	Projekcija findOne(Long id); 
	List<Projekcija> findAll(); 
	Projekcija save(Projekcija projekcija); 
	List<Projekcija> save(List<Projekcija> projekcije); 
	Projekcija update(Projekcija projekcija); 
	List<Projekcija> update(List<Projekcija> projekcije);
	Projekcija delete(Long id); 
	void delete(List<Long> ids); 
	List<Projekcija> findBySala(int sala);
	List<Projekcija> findByTipProjekcije(String tipProjekcije);
	List<Projekcija> findByFilm(Film film);
	List<Projekcija> findByCenaKarte(double cenaKarte);
	List<Projekcija> findByCenaKarteFrom(double cenaKarteFrom);
	List<Projekcija> findByCenaKarteTo(double cenaKarteTo);
	List<Projekcija> findByCenaKarteFromAndCenaKarteTo(double cenaKarteFrom, double cenaKarteTo);
	List<Projekcija> findByDatumIVreme(LocalDateTime datumIVremeFrom);
	List<Projekcija> findByDatumIVremeFrom(LocalDateTime datumIVremeFrom);
	List<Projekcija> findByDatumIVremeTo(LocalDateTime datumIVremeTo);
	List<Projekcija> findByDatumIVremeFromAndDatumIVremeTo(LocalDateTime datumIVremeFrom, LocalDateTime datumIVremeTo);
}
