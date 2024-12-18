package com.ftn.PrviMavenVebProjekat.dao;

import java.util.List;

import com.ftn.PrviMavenVebProjekat.model.Film;

public interface FilmDAO {

	public Film findOne(Long id);

	public List<Film> findAll();

	public int save(Film film);

	public int update(Film film);

	public int delete(Long id);
	
	public List<Film> find(String naziv, Long zanrId, Integer trajanjeOd, Integer trajanjeDo);
	
}
