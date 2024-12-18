package com.ftn.PrviMavenVebProjekat.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ftn.PrviMavenVebProjekat.model.Projekcija;

public interface ProjekcijaDAO {

	public Projekcija findOne(Long id);

	public List<Projekcija> findAll();
	
	public List<Projekcija> find(LocalDateTime datumIVremeOd, LocalDateTime datumIVremeDo, Long filmId, String tip, Integer sala, Double cenaKarteOd, Double cenaKarteDo);
	
	public List<Projekcija> find(HashMap<String, Object> mapaArgumenata);
	
	public int save(Projekcija projekcija);
	
	public int update(Projekcija projekcija);

	public int delete(Long id);
}
