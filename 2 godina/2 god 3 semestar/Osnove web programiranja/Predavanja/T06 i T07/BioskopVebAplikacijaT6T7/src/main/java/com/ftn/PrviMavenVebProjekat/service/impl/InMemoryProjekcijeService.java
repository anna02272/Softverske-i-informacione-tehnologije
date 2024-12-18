package com.ftn.PrviMavenVebProjekat.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ProjekcijaService;

/** projekcije se prezistiraju u momoriji */
@Service
public class InMemoryProjekcijeService implements ProjekcijaService {

	private Map<Long, Projekcija> projekcije = new HashMap<>();
	private long nextId = 1L;

	@Autowired
	@Qualifier("memorijaFilm")
	private FilmService filmService;

	@PostConstruct
    private void iniDataForTesting() {

		save(new Projekcija(LocalDateTime.parse("2020-03-20 14:00", formatter),
				filmService.findOne(1L), 1, "2D", 380.00));
		save(new Projekcija(LocalDateTime.parse("2020-03-20 14:00", formatter),
				filmService.findOne(1L), 2, "3D", 420.00));
		save(new Projekcija(LocalDateTime.parse("2020-03-21 10:00", formatter),
				filmService.findOne(2L), 1, "2D", 380.00));
		save(new Projekcija(LocalDateTime.parse("2020-03-21 18:00", formatter),
				filmService.findOne(1L), 3, "4D", 580.00));
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
		//u slučaju da projekcija nema id
		//tada se radi o insertovanju nove projekcije
		if (projekcija.getId() == null) {
			projekcija.setId(nextId++);
			
		}
		projekcije.put(projekcija.getId(), projekcija);	
		
		return projekcija;
	}

	@Override
	public List<Projekcija> save(List<Projekcija> projekcije) {
		List<Projekcija> ret = new ArrayList<>();

		for (Projekcija p : projekcije) {
			// za svaku prosleđenu projekciju pozivamo save
			// metodu koju smo već napravili i testirali -
			// ona će sepobrinuti i za dodelu ID-eva
			// ako je to potrebno
			Projekcija saved = save(p);

			// uspešno snimljene dodajemo u listu za vraćanje
			if (saved != null) {
				ret.add(saved);
			}
		}

		return ret;
	}

	@Override
	public Projekcija update(Projekcija projekcija) {

		//u slučaju da projekcija ima id
		//tada se radi o update postojece projekcije
		projekcije.put(projekcija.getId(), projekcija);
		
		return projekcija;
	}

	@Override
	public List<Projekcija> update(List<Projekcija> projekcije) {
		List<Projekcija> ret = new ArrayList<>();

		for (Projekcija p : projekcije) {
			// za svaku prosleđenu projekciju pozivamo update
			// metodu koju smo već napravili i testirali -
			Projekcija updated = update(p);

			// uspešno azuriranje dodajemo u listu za vraćanje
			if (updated != null) {
				ret.add(updated);
			}
		}

		return ret;
	}

	@Override
	public Projekcija delete(Long id) {
		if (!projekcije.containsKey(id)) {
			throw new IllegalArgumentException("tried to remove non existing projekcija");
		}
		Projekcija projekcija = projekcije.get(id);
		if (projekcija != null) {
			projekcije.remove(id);
		}
		return projekcija;
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			// pozivamo postojeću metodu za svaki
			delete(id);
		}
	}

	@Override
	public List<Projekcija> findBySala(int sala) {
		List<Projekcija> ret = new ArrayList<>();

		for (Projekcija p : projekcije.values()) {
			if (sala == p.getSala()) {
				ret.add(p);
			}
		}
		return ret;
	}

	@Override
	public List<Projekcija> findByTipProjekcije(String tipProjekcije) {
		List<Projekcija> ret = new ArrayList<>();

		for (Projekcija p : projekcije.values()) {
			if (tipProjekcije.equals(p.getTipProjekcije())) {
				ret.add(p);
			}
		}
		return ret;
	}

	@Override
	public List<Projekcija> findByFilm(Film film) {
		List<Projekcija> ret = new ArrayList<>();
		
		for (Projekcija p : projekcije.values()) {
			if (film.equals(p.getFilm())) {
				ret.add(p);
			}
		}
		return ret;
	}

	
	@Override
	public List<Projekcija> findByCenaKarte(double cenaKarte) {
		List<Projekcija> ret = new ArrayList<>();
		
		for (Projekcija p : projekcije.values()) {
			if (cenaKarte == p.getCenaKarte()) {
				ret.add(p);
			}
		}
		return ret;
	}

	@Override
	public List<Projekcija> findByCenaKarteFrom(double cenaKarteFrom) {
		List<Projekcija> ret = new ArrayList<>();
		
		for (Projekcija p : projekcije.values()) {
			if (cenaKarteFrom <= p.getCenaKarte()) {
				ret.add(p);
			}
		}
		return ret;
	}

	@Override
	public List<Projekcija> findByCenaKarteTo(double cenaKarteTo) {
		List<Projekcija> ret = new ArrayList<>();
		
		for (Projekcija p : projekcije.values()) {
			if (cenaKarteTo >= p.getCenaKarte()) {
				ret.add(p);
			}
		}
		return ret;
	}

	@Override
	public List<Projekcija> findByCenaKarteFromAndCenaKarteTo(double cenaKarteFrom, double cenaKarteTo) {
		List<Projekcija> ret = new ArrayList<>();
		
		for (Projekcija p : projekcije.values()) {
			if (cenaKarteFrom <= p.getCenaKarte() && cenaKarteTo >= p.getCenaKarte()) {
				ret.add(p);
			}
		}
		return ret;
	}

	@Override
	public List<Projekcija> findByDatumIVreme(LocalDateTime datumIVreme) {
		List<Projekcija> ret = new ArrayList<>();
		
		for (Projekcija p : projekcije.values()) {
			if (datumIVreme.isEqual(p.getDatumIVreme())) {
				ret.add(p);
			}
		}
		return ret;
	}
	
	@Override
	public List<Projekcija> findByDatumIVremeFrom(LocalDateTime datumIVremeFrom) {
		List<Projekcija> ret = new ArrayList<>();
		
		for (Projekcija p : projekcije.values()) {
			if (datumIVremeFrom.isBefore(p.getDatumIVreme())) {
				ret.add(p);
			}
		}
		return ret;
	}

	@Override
	public List<Projekcija> findByDatumIVremeTo(LocalDateTime datumIVremeTo) {
		List<Projekcija> ret = new ArrayList<>();
		
		for (Projekcija p : projekcije.values()) {
			if (datumIVremeTo.isAfter(p.getDatumIVreme())) {
				ret.add(p);
			}
		}
		return ret;
	}

	@Override
	public List<Projekcija> findByDatumIVremeFromAndDatumIVremeTo(LocalDateTime datumIVremeFrom,
			LocalDateTime datumIVremeTo) {
		List<Projekcija> ret = new ArrayList<>();
		
		for (Projekcija p : projekcije.values()) {
			if (datumIVremeFrom.isBefore(p.getDatumIVreme()) && datumIVremeTo.isAfter(p.getDatumIVreme())) {
				ret.add(p);
			}
		}
		return ret;
	}
}
