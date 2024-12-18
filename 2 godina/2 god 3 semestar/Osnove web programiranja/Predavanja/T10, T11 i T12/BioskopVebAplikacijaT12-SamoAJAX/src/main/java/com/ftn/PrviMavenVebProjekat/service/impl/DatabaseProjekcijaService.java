package com.ftn.PrviMavenVebProjekat.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.dao.ProjekcijaDAO;
import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;
import com.ftn.PrviMavenVebProjekat.service.ProjekcijaService;

@Service
public class DatabaseProjekcijaService implements ProjekcijaService {

	@Autowired
	private ProjekcijaDAO projekcijaDAO;
	
	@Override
	public Projekcija findOne(Long id) {
		return projekcijaDAO.findOne(id);
	}

	@Override
	public List<Projekcija> findAll() {
		return projekcijaDAO.findAll();
	}

	@Override
	public Projekcija save(Projekcija projekcija) {
		projekcijaDAO.save(projekcija);
		return projekcija;
	}

	@Override
	public List<Projekcija> save(List<Projekcija> projekcije) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projekcija update(Projekcija projekcija) {
		projekcijaDAO.update(projekcija);
		return projekcija;
	}

	@Override
	public List<Projekcija> update(List<Projekcija> projekcije) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projekcija delete(Long id) {
		Projekcija projekcija = findOne(id);
		if (projekcija != null) {
			projekcijaDAO.delete(id);
		}
		return projekcija;
	}

	@Override
	public List<Projekcija> deleteAll(Film film) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projekcija> deleteAll(List<Film> filmovi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(List<Long> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Projekcija> find(LocalDateTime datumIVremeOd, LocalDateTime datumIVremeDo, Long filmId, String tip,
			Integer sala, Double cenaKarteOd, Double cenaKarteDo) {
		// minimalne inkluzivne vrednosti parametara ako su izostavljeni
		//1. način bi bilo pozivanje ogovarajuće DAO metode u odnosu na broj parametara 
		//		gde bi trebalo implementirati više dao metoda tako da pokriju različite situacije
		//2. način reši sve u DAO sloju
		
		//odabran 2.
		
		return projekcijaDAO.find(datumIVremeOd, datumIVremeDo, filmId, tip, sala, cenaKarteOd, cenaKarteDo);

	}
	
	@Override
	public List<Projekcija> findVer2(LocalDateTime datumIVremeOd, LocalDateTime datumIVremeDo, Long filmId, String tip,
			Integer sala, Double cenaKarteOd, Double cenaKarteDo) {
		// maksimalno inkluzivne vrednosti parametara ako su izostavljeni
		//1. način bi bilo pozivanje ogovarajuće DAO metode u odnosu na broj parametara 
		//		gde bi trebalo implementirati više dao metoda tako da pokriju različite situacije
		//2. način reši sve u DAO sloju
		
		//odabran 2.
		
		HashMap<String, Object> mapaArgumenata = new HashMap<String,Object>();
		
		if(datumIVremeOd!=null) 
			mapaArgumenata.put("datumIVremeOd", datumIVremeOd);
		
		if(datumIVremeDo!=null) 
			mapaArgumenata.put("datumIVremeDo", datumIVremeDo);
		
		if(filmId!=null) 
			mapaArgumenata.put("filmId", filmId);
		
		if(tip!=null)
			mapaArgumenata.put("tip", tip);
		
		if(sala!=null) 
			mapaArgumenata.put("sala", sala);
		
		if(cenaKarteOd!=null)
			mapaArgumenata.put("cenaKarteOd", cenaKarteOd);
		
		if(cenaKarteDo!=null) 
			mapaArgumenata.put("cenaKarteDo", cenaKarteDo);
		
		return projekcijaDAO.find(mapaArgumenata);
	}

	@Override
	public List<Projekcija> findByFilmId(Long filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}
