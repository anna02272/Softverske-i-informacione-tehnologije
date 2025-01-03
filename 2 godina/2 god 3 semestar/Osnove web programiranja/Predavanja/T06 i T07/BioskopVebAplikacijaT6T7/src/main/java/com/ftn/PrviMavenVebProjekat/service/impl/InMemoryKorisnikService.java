package com.ftn.PrviMavenVebProjekat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;
import com.ftn.PrviMavenVebProjekat.service.KorisnikService;

/** korisnici se prezistiraju u momoriji */

@Service
public class InMemoryKorisnikService implements KorisnikService {

	HashMap<String, Korisnik> korisnici = new HashMap<String, Korisnik>();

	@PostConstruct
    private void iniDataForTesting() {
		
		save(new Korisnik("Petar", "Petarović", "muški", "pera", "pera", 60000, true));
		save(new Korisnik("Steva", "Stević", "muški", "steva", "steva", 50000));
		save(new Korisnik("Jova", "Jović", "muški", "jova", "jova", 45000));
    }

	@Override
	public Korisnik findOne(String korisnickoIme) {
		return korisnici.get(korisnickoIme);
	}

	@Override
	public Korisnik findOne(String korisnickoIme, String korisnickaSifra) {
		Korisnik korisnik = findOne(korisnickoIme);
		if(korisnik!=null && korisnik.getKorisnickaSifra().equals(korisnickaSifra))
			return korisnik;
		return null;
	}

	@Override
	public List<Korisnik> findAll() {
		return new ArrayList<Korisnik>(korisnici.values());
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		//u slučaju da bazi nema korisnik za korisnickoIme
		//tada se radi o insert novog korisnika
		if (korisnici.get(korisnik.getKorisnickoIme()) == null) {
			korisnici.put(korisnik.getKorisnickoIme(), korisnik);
		}
		
		return korisnik;
	}

	@Override
	public List<Korisnik> save(List<Korisnik> korisnici) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici) {
			// za svaku prosleđenog korisnika pozivamo save
			// metodu koju smo već napravili i testirali -
			// ona će sepobrinuti i za dodelu ID-eva
			// ako je to potrebno
			Korisnik saved = save(k);

			// uspešno snimljene dodajemo u listu za vraćanje
			if (saved != null) {
				ret.add(saved);
			}
		}

		return ret;
	}

	@Override
	public Korisnik update(Korisnik korisnik) {
		
		//u slučaju da bazi postoji korisnik za korisnickoIme
		//tada se radi o update postojeceg korisnika
		if (korisnici.get(korisnik.getKorisnickoIme()) != null) {
			korisnici.put(korisnik.getKorisnickoIme(), korisnik);
		}
				
		// TODO Auto-generated method stub
		return korisnik;
	}

	@Override
	public List<Korisnik> update(List<Korisnik> korisnici) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici) {
			// za svaku prosleđenog korisnika pozivamo update
			// metodu koju smo već napravili i testirali -
			Korisnik updated = update(k);

			// uspešno azuriranje dodajemo u listu za vraćanje
			if (updated != null) {
				ret.add(updated);
			}
		}

		return ret;
	}

	@Override
	public Korisnik delete(String korisnickoIme) {
		if (!korisnici.containsKey(korisnickoIme)) {
			throw new IllegalArgumentException("tried to remove non existing projekcija");
		}
		Korisnik korisnik = korisnici.get(korisnickoIme);
		if (korisnik != null) {
			korisnici.remove(korisnickoIme);
		}
		return korisnik;
	}

	@Override
	public void delete(List<String> korisnickaImena) {
		for (String korisnickoIme : korisnickaImena) {
			// pozivamo postojeću metodu za svaki
			delete(korisnickoIme);
		}
	}

	@Override
	public List<Korisnik> findByIme(String ime) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici.values()) {
			if (ime.equals(k.getIme())) {
				ret.add(k);
			}
		}
		return ret;
	}

	@Override
	public List<Korisnik> findByPrezime(String prezime) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici.values()) {
			if (prezime.equals(k.getPrezime())) {
				ret.add(k);
			}
		}
		return ret;
	}

	@Override
	public List<Korisnik> findByPol(String pol) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici.values()) {
			if (pol.equals(k.getPol())) {
				ret.add(k);
			}
		}
		return ret;
	}

	@Override
	public List<Korisnik> findByPlata(double plata) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici.values()) {
			if (plata == k.getPlata()) {
				ret.add(k);
			}
		}
		return ret;
	}

	@Override
	public List<Korisnik> findByPlataFrom(double plataFrom) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici.values()) {
			if (plataFrom <= k.getPlata()) {
				ret.add(k);
			}
		}
		return ret;
	}

	@Override
	public List<Korisnik> findByPlataTo(double plataTo) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici.values()) {
			if (plataTo >= k.getPlata()) {
				ret.add(k);
			}
		}
		return ret;
	}

	@Override
	public List<Korisnik> findByPlataFromAndPlataTo(double plataFrom, double plataTo) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici.values()) {
			if (plataFrom <= k.getPlata() && plataTo >= k.getPlata()) {
				ret.add(k);
			}
		}
		return ret;
	}

	@Override
	public List<Korisnik> findByAdministrator(boolean administrator) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici.values()) {
			if (administrator == k.isAdministrator()) {
				ret.add(k);
			}
		}
		return ret;
	}

	@Override
	public List<Korisnik> findByUlogovan(boolean ulogovan) {
		List<Korisnik> ret = new ArrayList<>();

		for (Korisnik k : korisnici.values()) {
			if (ulogovan == k.isUlogovan()) {
				ret.add(k);
			}
		}
		return ret;
	}
	
}
