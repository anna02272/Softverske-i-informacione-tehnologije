package com.ftn.PrviMavenVebProjekat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.service.FilmService;

/** filmovi se prezistiraju u memoriji */

@Service
//sa @Qualifier se definiše naziv bean kandidata
@Qualifier("memorijaFilm")
public class InMemoryFilmService implements FilmService {

	private Map<Long, Film> films = new HashMap<>();
	private long nextId = 1L;

	@PostConstruct
    private void iniDataForTesting() {

		save(new Film("The Shining", 146));
		save(new Film("One Flew Over the Cuckoo's Nest", 133));
		save(new Film("The Little Shop of Horrors", 72));
    }
	
	@Override
	public Film findOne(Long id) {
		return films.get(id);
	}

	@Override
	public List<Film> findAll() {
		return new ArrayList<Film>(films.values());
	}

	@Override
	public Film save(Film film) {
		//u slučaju da film nema id
		//tada treba da mu se dodeli id
		if (film.getId() == null) {
			film.setId(nextId++);
			
		}
		films.put(film.getId(), film);
		return film;
	}

	@Override
	public List<Film> save(List<Film> films) {
		List<Film> ret = new ArrayList<>();

		for (Film f : films) {
			// za svaku prosleđeni film pozivamo save
			// metodu koju smo već napravili i testirali -
			// ona će se pobrinuti i za dodelu ID-eva
			// ako je to potrebno
			Film saved = save(f);

			// uspešno snimljene dodajemo u listu za vraćanje
			if (saved != null) {
				ret.add(saved);
			}
		}

		return ret;
	}
	
	@Override
	public Film update(Film film) {
		films.put(film.getId(), film);
		return film;
	}

	@Override
	public List<Film> update(List<Film> films) {
		List<Film> ret = new ArrayList<>();

		for (Film f : films) {
			// za svaku prosleđeni film pozivamo update
			// metodu koju smo već napravili i testirali -
			Film updated = update(f);

			// uspešno azuriranje dodajemo u listu za vraćanje
			if (updated != null) {
				ret.add(updated);
			}
		}

		return ret;
	}

	@Override
	public Film delete(Long id) {
		if (!films.containsKey(id)) {
			throw new IllegalArgumentException("tried to remove non existing film");
		}
		Film film = films.get(id);
		if (film != null) {
			films.remove(id);
		}
		return film;
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			// pozivamo postojeću metodu za svaki
			delete(id);
		}
	}

	@Override
	public List<Film> findByNaziv(String naziv) {
		List<Film> ret = new ArrayList<>();

		for (Film f : films.values()) {
			if (naziv.equals(f.getNaziv())) {
				ret.add(f);
			}
		}
		return ret;
	}

	@Override
	public List<Film> findByTrajanje(int trajanje) {
		List<Film> ret = new ArrayList<>();

		for (Film f : films.values()) {
			if (trajanje == f.getTrajanje()) {
				ret.add(f);
			}
		}
		return ret;
	}

	@Override
	public List<Film> findByTrajanjeFrom(int trajanjeFrom) {
		List<Film> ret = new ArrayList<>();

		for (Film f : films.values()) {
			if (trajanjeFrom <= f.getTrajanje()) {
				ret.add(f);
			}
		}
		return ret;
	}

	@Override
	public List<Film> findByTrajanjeTo(int trajanjeTo) {
		List<Film> ret = new ArrayList<>();

		for (Film f : films.values()) {
			if (trajanjeTo >= f.getTrajanje()) {
				ret.add(f);
			}
		}
		return ret;
	}

	@Override
	public List<Film> findByTrajanjeFromAndTrajanjeTo(int trajanjeFrom, int trajanjeTo) {
		List<Film> ret = new ArrayList<>();

		for (Film f : films.values()) {
			if (trajanjeFrom <= f.getTrajanje() && trajanjeTo >= f.getTrajanje()) {
				ret.add(f);
			}
		}
		return ret;
	}
	
	
}
