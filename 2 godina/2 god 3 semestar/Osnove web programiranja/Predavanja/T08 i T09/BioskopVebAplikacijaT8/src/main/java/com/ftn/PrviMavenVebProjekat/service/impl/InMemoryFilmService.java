package com.ftn.PrviMavenVebProjekat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Zanr;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ZanrService;

@Service
@Qualifier("memorijaFilm")
public class InMemoryFilmService implements FilmService {

	private Map<Long, Film> filmovi = new HashMap<>();
	private long nextId = 1L;
	
	@Autowired
	private ZanrService zanrService;
	
	@PostConstruct
    private void iniDataForTesting() {
		Film film;

		film = new Film("Avengers: Endgame", 182);
		film.getZanrovi().add(zanrService.findOne(1L));
		film.getZanrovi().add(zanrService.findOne(2L));
		film.getZanrovi().add(zanrService.findOne(5L));
		save(film);

		film = new Film("Life", 110);
		film.getZanrovi().add(zanrService.findOne(1L));
		film.getZanrovi().add(zanrService.findOne(4L));
		save(film);

		film = new Film("It: Chapter 2", 170);
		film.getZanrovi().add(zanrService.findOne(4L));
		save(film);

		film = new Film("Pirates of the Caribbean: Dead Men Tell No Tales", 153);
		film.getZanrovi().add(zanrService.findOne(2L));
		film.getZanrovi().add(zanrService.findOne(3L));
		film.getZanrovi().add(zanrService.findOne(5L));
		save(film);
    }
	
	@Override
	public Film findOne(Long id) {
		return filmovi.get(id);
	}

	@Override
	public List<Film> findAll() {
		return new ArrayList<Film>(filmovi.values());
	}

	@Override
	public Film save(Film film) {
		// u slučaju da film nema id
		// tada treba da mu se dodeli id
		if (film.getId() == null) {
			film.setId(nextId++);
		}
		filmovi.put(film.getId(), film);
		return film;
	}

	@Override
	public List<Film> save(List<Film> filmovi) {
		List<Film> rezultat = new ArrayList<>();
		for (Film itFilm: filmovi) {
			// za svaku prosleđeni film pozivamo save
			// metodu koju smo već napravili i testirali -
			// ona će se pobrinuti i za dodelu ID-eva
			// ako je to potrebno
			Film saved = save(itFilm);

			// uspešno snimljene dodajemo u listu za vraćanje
			if (saved != null) {
				rezultat.add(saved);
			}
		}

		return rezultat;
	}
	
	@Override
	public Film update(Film film) {
		filmovi.put(film.getId(), film);
		return film;
	}

	@Override
	public List<Film> update(List<Film> filmovi) {
		List<Film> rezultat = new ArrayList<>();
		for (Film itFilm: filmovi) {
			// za svaku prosleđeni film pozivamo update
			// metodu koju smo već napravili i testirali -
			Film updated = update(itFilm);

			// uspešno azuriranje dodajemo u listu za vraćanje
			if (updated != null) {
				rezultat.add(updated);
			}
		}

		return rezultat;
	}

	@Override
	public Film delete(Long id) {
		if (!filmovi.containsKey(id)) {
			throw new IllegalArgumentException("invalid id");
		}

		Film film = filmovi.get(id);
		if (film != null) {
			filmovi.remove(id);
		}
		
		return film;
	}

	@Override
	public List<Film> deleteAll(Zanr zanr) {
		List<Film> filmoviZaUklanjanje = new ArrayList<>();
		for (Film itFilm: filmovi.values()) {
			if (itFilm.getZanrovi().contains(zanr)) {
				filmoviZaUklanjanje.add(itFilm);
			}
		}		
		List<Film> rezultat = new ArrayList<>();
		for (Film itFilm: filmoviZaUklanjanje) {
			Film uklonjeniFilm = filmovi.remove(itFilm.getId());
			rezultat.add(uklonjeniFilm);
		}

		return rezultat;
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long itID: ids) {
			// pozivamo postojeću metodu za svaki
			delete(itID);
		}
	}

	@Override
	public List<Film> find(String naziv, Long zanrId, Integer trajanjeOd, Integer trajanjeDo) {
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
		for (Film itFilm: filmovi.values()) {
			if (!itFilm.getNaziv().toLowerCase().contains(naziv.toLowerCase())) {
				continue;
			}
			if (zanrId > 0) {
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
	}

	@Override
	public List<Film> findByZanrId(Long zanrId) {
		if (zanrId == null) {
			zanrId = 0L;
		}
		
		List<Film> rezultat = new ArrayList<>();
		for (Film itFilm: filmovi.values()) {
			if (zanrId > 0) {
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

			rezultat.add(itFilm);
		}

		return rezultat;
	}

}
