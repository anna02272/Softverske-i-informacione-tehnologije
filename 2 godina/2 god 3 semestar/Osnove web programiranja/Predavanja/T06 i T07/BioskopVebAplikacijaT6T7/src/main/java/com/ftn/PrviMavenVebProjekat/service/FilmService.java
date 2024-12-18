package com.ftn.PrviMavenVebProjekat.service;

import java.util.List;

import com.ftn.PrviMavenVebProjekat.model.Film;

public interface FilmService {

	Film findOne(Long id); 
	List<Film> findAll(); 
	Film save(Film film); 
	List<Film> save(List<Film> films); 
	Film update(Film film); 
	List<Film> update(List<Film> films);
	Film delete(Long id); 
	void delete(List<Long> ids); 
	List<Film> findByNaziv(String naziv);
	List<Film> findByTrajanje(int trajanje);
	List<Film> findByTrajanjeFrom(int trajanjeFrom);
	List<Film> findByTrajanjeTo(int trajanjeTo);
	List<Film> findByTrajanjeFromAndTrajanjeTo(int trajanjeFrom, int trajanjeTo);
}
