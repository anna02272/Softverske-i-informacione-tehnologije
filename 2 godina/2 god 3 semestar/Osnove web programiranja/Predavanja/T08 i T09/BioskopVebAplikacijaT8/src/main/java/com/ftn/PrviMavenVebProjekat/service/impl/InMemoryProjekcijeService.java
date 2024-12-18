package com.ftn.PrviMavenVebProjekat.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ProjekcijaService;

@Service
public class InMemoryProjekcijeService implements ProjekcijaService {

	private Map<Long, Projekcija> projekcije = new HashMap<>();
	private long nextId = 1L;

	@Autowired
	private FilmService filmService;

	@PostConstruct
    private void iniDataForTesting() {
		save(new Projekcija(LocalDateTime.parse("2020-06-22 20:00", formatter), filmService.findOne(1L), 1, "2D", 380.00));
		save(new Projekcija(LocalDateTime.parse("2020-06-22 23:30", formatter), filmService.findOne(3L), 1, "2D", 380.00));
		save(new Projekcija(LocalDateTime.parse("2020-06-22 20:00", formatter), filmService.findOne(1L), 2, "3D", 420.00));
		save(new Projekcija(LocalDateTime.parse("2020-06-22 23:30", formatter), filmService.findOne(2L), 2, "3D", 420.00));
		save(new Projekcija(LocalDateTime.parse("2020-06-22 20:00", formatter), filmService.findOne(3L), 3, "4D", 580.00));
		save(new Projekcija(LocalDateTime.parse("2020-06-23 20:00", formatter), filmService.findOne(2L), 1, "2D", 380.00));
		save(new Projekcija(LocalDateTime.parse("2020-06-23 22:00", formatter), filmService.findOne(4L), 1, "2D", 380.00));
		save(new Projekcija(LocalDateTime.parse("2020-06-23 20:00", formatter), filmService.findOne(2L), 2, "3D", 420.00));
		save(new Projekcija(LocalDateTime.parse("2020-06-23 22:00", formatter), filmService.findOne(4L), 2, "3D", 420.00));
		save(new Projekcija(LocalDateTime.parse("2020-06-23 20:00", formatter), filmService.findOne(1L), 3, "4D", 580.00));
    }	

	@Override
	public Projekcija findOne(Long id) {
		return projekcije.get(id);
	}

	@Override
	public List<Projekcija> findAll() {
		return new ArrayList<Projekcija>(projekcije.values());
	}

	@Override
	public Projekcija save(Projekcija projekcija) {
		// u slučaju da projekcija nema id
		// tada se radi o insertovanju nove projekcije
		if (projekcija.getId() == null) {
			projekcija.setId(nextId++);		
		}
		projekcije.put(projekcija.getId(), projekcija);	
		
		return projekcija;
	}

	@Override
	public List<Projekcija> save(List<Projekcija> projekcije) {
		List<Projekcija> rezultat = new ArrayList<>();
		for (Projekcija itProjekcija: projekcije) {
			// za svaku prosleđenu projekciju pozivamo save
			// metodu koju smo već napravili i testirali -
			// ona će sepobrinuti i za dodelu ID-eva
			// ako je to potrebno
			Projekcija saved = save(itProjekcija);

			// uspešno snimljene dodajemo u listu za vraćanje
			if (saved != null) {
				rezultat.add(saved);
			}
		}

		return rezultat;
	}

	@Override
	public Projekcija update(Projekcija projekcija) {
		// u slučaju da projekcija ima id
		// tada se radi o update postojece projekcije
		projekcije.put(projekcija.getId(), projekcija);
		
		return projekcija;
	}

	@Override
	public List<Projekcija> update(List<Projekcija> projekcije) {
		List<Projekcija> rezultat = new ArrayList<>();
		for (Projekcija itProjekcija: projekcije) {
			// za svaku prosleđenu projekciju pozivamo update
			// metodu koju smo već napravili i testirali -
			Projekcija updated = update(itProjekcija);

			// uspešno azuriranje dodajemo u listu za vraćanje
			if (updated != null) {
				rezultat.add(updated);
			}
		}

		return rezultat;
	}

	@Override
	public Projekcija delete(Long id) {
		if (!projekcije.containsKey(id)) {
			throw new IllegalArgumentException("invalid id");
		}

		Projekcija projekcija = projekcije.get(id);
		if (projekcija != null) {
			projekcije.remove(id);
		}
		return projekcija;
	}
	
	@Override
	public List<Projekcija> deleteAll(Film film) {
		List<Projekcija> projekcijeZaUklanjanje = new ArrayList<>();
		for (Projekcija itProjekcija: projekcije.values()) {
			if (itProjekcija.getFilm().equals(film)) {
				projekcijeZaUklanjanje.add(itProjekcija);
			}
		}		
		List<Projekcija> rezultat = new ArrayList<>();
		for (Projekcija itProjekcija: projekcijeZaUklanjanje) {
			Projekcija uklonjenaProjekcija = projekcije.remove(itProjekcija.getId());
			rezultat.add(uklonjenaProjekcija);
		}

		return rezultat;
	}

	@Override
	public List<Projekcija> deleteAll(List<Film> filmovi) {
		List<Projekcija> rezultat = new ArrayList<>();
		for (Film itFilm: filmovi) {
			rezultat.addAll(deleteAll(itFilm));
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
	public List<Projekcija> find(LocalDateTime datumIVremeOd, LocalDateTime datumIVremeDo, Long filmId, String tip,
			Integer sala, Double cenaKarteOd, Double cenaKarteDo) {
		if (filmId == null) {
			filmId = 0L;
		}
		if (sala == null) {
			sala = 0;
		}
		if (cenaKarteOd == null) {
			cenaKarteOd = 0.0;
		}
		if (cenaKarteDo == null) {
			cenaKarteDo = Double.MAX_VALUE;
		}
		
		List<Projekcija> rezultat = new ArrayList<>();
		for (Projekcija itProjekcije: projekcije.values()) {
			if (datumIVremeOd != null && !(itProjekcije.getDatumIVreme().isAfter(datumIVremeOd) || itProjekcije.getDatumIVreme().isEqual(datumIVremeOd))) {
				continue;
			}
			if (datumIVremeDo != null && !(itProjekcije.getDatumIVreme().isBefore(datumIVremeDo) || itProjekcije.getDatumIVreme().isEqual(datumIVremeDo))) {
				continue;
			}
			if (filmId > 0 && !(itProjekcije.getFilm().getId() == filmId)) {
				continue;
			}
			if (tip != null && !tip.equals("") && !itProjekcije.getTip().equals(tip)) {
				continue;
			}
			if (sala > 0  && !(itProjekcije.getSala() == sala)) {
				continue;
			}
			if (!(itProjekcije.getCenaKarte() >= cenaKarteOd && itProjekcije.getCenaKarte() <= cenaKarteDo)) {
				continue;
			}

			rezultat.add(itProjekcije);
		}

		return rezultat;
	}

	@Override
	public List<Projekcija> findByFilmId(Long filmId) {
		if (filmId == null) {
			filmId = 0L;
		}
		
		List<Projekcija> rezultat = new ArrayList<>();
		for (Projekcija itProjekcije: projekcije.values()) {
			if (filmId > 0 && !(itProjekcije.getFilm().getId() == filmId)) {
				continue;
			}

			rezultat.add(itProjekcije);
		}

		return rezultat;
	}

}
