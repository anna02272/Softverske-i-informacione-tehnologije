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
	List<Projekcija> deleteAll(Film film);
	List<Projekcija> deleteAll(List<Film> filmovi);
	void delete(List<Long> ids);
	List<Projekcija> find(LocalDateTime datumIVremeOd, LocalDateTime datumIVremeDo, Long filmId, String tip, Integer sala, Double cenaKarteOd, Double cenaKarteDo);
	List<Projekcija> findByFilmId(Long filmId);

}
