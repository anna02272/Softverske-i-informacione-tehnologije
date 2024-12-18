package com.ftn.PrviMavenVebProjekat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.dao.FilmDAO;
import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Zanr;
import com.ftn.PrviMavenVebProjekat.service.FilmService;

@Service
public class DatabaseFilmService implements FilmService {

	@Autowired
	private FilmDAO filmDAO;
	
	@Override
	public Film findOne(Long id) {
		return filmDAO.findOne(id);
	}

	@Override
	public List<Film> findAll() {
		return filmDAO.findAll();
	}

	@Override
	public Film save(Film film) {
		filmDAO.save(film);
		return film;
	}

	@Override
	public List<Film> save(List<Film> filmovi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film update(Film film) {
		filmDAO.update(film);
		return film;
	}

	@Override
	public List<Film> update(List<Film> filmovi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film delete(Long id) {
		Film film = findOne(id);
		if (film != null)
			filmDAO.delete(id);
		
		return film;
	}

	@Override
	public List<Film> deleteAll(Zanr zanr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(List<Long> ids) {
		// TODO Auto-generated method stub
	}
	//NACIN 1
	//U ovom metodi pretragu radimo manuelno tako što programski filtriramo kompletnu listu rezultata dobijenu sa findAll()
	//očitaju se svi filmovi iz baze pa se filtrira po uslovu
	//nije praktično imajući u vidu da u bazi može biti i 500.000 fimova
	//NACIN 2
	//ideja je koristiti select sa where delom sa se smanji ResultSet
	@Override
	public List<Film> find(String naziv, Long zanrId, Integer trajanjeOd, Integer trajanjeDo) {
		//NACIN 1
		List<Film> filmovi = filmDAO.findAll();

		// maksimalno inkluzivne vrednosti parametara ako su izostavljeni
		// filtiranje radi u Servisnom sloju - izbegavati
		if (naziv == null) {
			naziv = "";
		}
		if (zanrId == null) {
			zanrId = 0L;
		}
		if (trajanjeOd == null) {
			trajanjeOd = 0;
		}
		if (trajanjeDo == null) {
			trajanjeDo = Integer.MAX_VALUE;
		}
		
		List<Film> rezultat = new ArrayList<>();
		for (Film itFilm: filmovi) {
			// kriterijum pretrage
			if (!itFilm.getNaziv().toLowerCase().contains(naziv.toLowerCase())) {
				continue;
			}
			if (zanrId > 0) { // ako je žanr odabran
				boolean pronadjen = false;
				for (Zanr itZanr: itFilm.getZanrovi()) {
					if (itZanr.getId() == zanrId) {
						pronadjen = true;
						break;
					}
				}
				if (!pronadjen) {
					continue;
				}
			}
			if (!(itFilm.getTrajanje() >= trajanjeOd && itFilm.getTrajanje() <= trajanjeDo)) {
				continue;
			}

			rezultat.add(itFilm);
		}

		return rezultat;
		
//		//NACIN 2. ideja je koristiti select sa where delom sa se smanji ResultSet
//		return filmDAO.find(naziv, zanrId, trajanjeOd, trajanjeDo);
	}

	@Override
	public List<Film> findByZanrId(Long zanrId) {
		// TODO Auto-generated method stub
		return null;
	}

}
