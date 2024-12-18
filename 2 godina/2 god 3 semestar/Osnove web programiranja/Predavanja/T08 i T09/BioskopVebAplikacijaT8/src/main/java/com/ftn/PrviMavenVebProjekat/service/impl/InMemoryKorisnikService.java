package com.ftn.PrviMavenVebProjekat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.service.KorisnikService;

@Service
public class InMemoryKorisnikService implements KorisnikService {

	HashMap<String, Korisnik> korisnici = new HashMap<>();

	@PostConstruct
    private void iniDataForTesting() {	
		save(new Korisnik("a", "a", "a@a.com", "muški", true));
		save(new Korisnik("b", "b", "b@b.com", "ženski", false));
		save(new Korisnik("c", "c", "c@c.com", "muški", false));
    }

	@Override
	public Korisnik findOne(String korisnickoIme) {
		return korisnici.get(korisnickoIme);
	}

	@Override
	public Korisnik findOne(String korisnickoIme, String lozinka) {
		Korisnik korisnik = findOne(korisnickoIme);
		if (korisnik != null && korisnik.getLozinka().equals(lozinka))
			return korisnik;
		return null;
	}

	@Override
	public List<Korisnik> findAll() {
		return new ArrayList<Korisnik>(korisnici.values());
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		// u slučaju da bazi nema korisnik za korisnickoIme
		// tada se radi o insert novog korisnika
		if (korisnici.get(korisnik.getKorisnickoIme()) == null) {
			korisnici.put(korisnik.getKorisnickoIme(), korisnik);
		}
		
		return korisnik;
	}

	@Override
	public List<Korisnik> save(List<Korisnik> korisnici) {
		List<Korisnik> rezultat = new ArrayList<>();
		for (Korisnik itKorisnik: korisnici) {
			// za svaku prosleđenog korisnika pozivamo save
			// metodu koju smo već napravili i testirali -
			// ona će sepobrinuti i za dodelu ID-eva
			// ako je to potrebno
			Korisnik saved = save(itKorisnik);

			// uspešno snimljene dodajemo u listu za vraćanje
			if (saved != null) {
				rezultat.add(saved);
			}
		}

		return rezultat;
	}

	@Override
	public Korisnik update(Korisnik korisnik) {	
		// u slučaju da bazi postoji korisnik za korisnickoIme
		// tada se radi o update postojeceg korisnika
		if (korisnici.get(korisnik.getKorisnickoIme()) != null) {
			korisnici.put(korisnik.getKorisnickoIme(), korisnik);
		}
		return korisnik;
	}

	@Override
	public List<Korisnik> update(List<Korisnik> korisnici) {
		List<Korisnik> rezultat = new ArrayList<>();
		for (Korisnik itKorisnik: korisnici) {
			// za svaku prosleđenog korisnika pozivamo update
			// metodu koju smo već napravili i testirali -
			Korisnik updated = update(itKorisnik);

			// uspešno azuriranje dodajemo u listu za vraćanje
			if (updated != null) {
				rezultat.add(updated);
			}
		}

		return rezultat;
	}

	@Override
	public Korisnik delete(String korisnickoIme) {
		if (!korisnici.containsKey(korisnickoIme)) {
			throw new IllegalArgumentException("invalid id");
		}

		Korisnik korisnik = korisnici.get(korisnickoIme);
		if (korisnik != null) {
			korisnici.remove(korisnickoIme);
		}
		return korisnik;
	}

	@Override
	public void delete(List<String> korisnickaImena) {
		for (String itKorisnickoIme: korisnickaImena) {
			// pozivamo postojeću metodu za svaki
			delete(itKorisnickoIme);
		}
	}

	@Override
	public List<Korisnik> find(String korisnickoIme, String eMail, String pol, Boolean administrator) {
		if (korisnickoIme == null) {
			korisnickoIme = "";
		}
		if (eMail == null) {
			eMail = "";
		}
		if (administrator == null) {
			administrator = false;
		}

		List<Korisnik> rezultat = new ArrayList<>();
		for (Korisnik itKorisnik: korisnici.values()) {
			if (!itKorisnik.getKorisnickoIme().toLowerCase().contains(korisnickoIme.toLowerCase())) {
				continue;
			}
			if (!itKorisnik.getEMail().toLowerCase().contains(eMail.toLowerCase())) {
				continue;
			}
			if (pol != null && pol.equals("") && !itKorisnik.getPol().equals(pol)) {
				continue;
			}
			if (!(administrator && itKorisnik.isAdministrator())) {
				continue;
			}

			rezultat.add(itKorisnik);
		}

		return rezultat;
	}

	@Override
	public List<Korisnik> findByKorisnickoIme(String korisnickoIme) {
		if (korisnickoIme == null) {
			korisnickoIme = "";
		}

		List<Korisnik> rezultat = new ArrayList<>();
		for (Korisnik itKorisnik: korisnici.values()) {
			if (!itKorisnik.getKorisnickoIme().toLowerCase().contains(korisnickoIme.toLowerCase())) {
				continue;
			}

			rezultat.add(itKorisnik);
		}

		return rezultat;
	}

}
