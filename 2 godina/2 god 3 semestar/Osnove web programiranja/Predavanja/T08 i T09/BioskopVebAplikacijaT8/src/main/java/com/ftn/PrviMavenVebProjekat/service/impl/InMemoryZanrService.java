package com.ftn.PrviMavenVebProjekat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.model.Zanr;
import com.ftn.PrviMavenVebProjekat.service.ZanrService;

@Service
public class InMemoryZanrService implements ZanrService {

	private Map<Long, Zanr> zanrovi = new HashMap<>();
	private long nextId = 1L;

	@PostConstruct
    private void iniDataForTesting() {
		save(new Zanr("naučna fantastika"));
		save(new Zanr("akcija"));
		save(new Zanr("komedija"));
		save(new Zanr("horor"));
		save(new Zanr("avantura"));
    }
	
	@Override
	public Zanr findOne(Long id) {
		return zanrovi.get(id);
	}

	@Override
	public List<Zanr> findAll() {
		return new ArrayList<>(zanrovi.values());
	}
	
	@Override
	public List<Zanr> find(Long[] ids) {
		if (ids == null) {
			return new ArrayList<Zanr>();
		}
		List<Zanr> rezultat = new ArrayList<>();
		for (Long itId: ids) {
			Zanr zanr = findOne(itId);
			rezultat.add(zanr);
		}

		return rezultat;
	}

	@Override
	public Zanr save(Zanr zanr) {
		if (zanr.getId() == null) {
			zanr.setId(nextId++);
			
		}
		zanrovi.put(zanr.getId(), zanr);
		return zanr;
	}

	@Override
	public List<Zanr> save(List<Zanr> zanrovi) {
		List<Zanr> rezultat = new ArrayList<>();
		for (Zanr itZanr: zanrovi) {
			Zanr saved = save(itZanr);

			if (saved != null) {
				rezultat.add(saved);
			}
		}

		return rezultat;
	}

	@Override
	public Zanr update(Zanr zanr) {
		zanrovi.put(zanr.getId(), zanr);
		return zanr;
	}

	@Override
	public List<Zanr> update(List<Zanr> zanrovi) {
		List<Zanr> rezultat = new ArrayList<>();
		for (Zanr itZanr: zanrovi) {
			Zanr updated = update(itZanr);

			if (updated != null) {
				rezultat.add(updated);
			}
		}

		return rezultat;
	}

	@Override
	public Zanr delete(Long id) {
		if (!zanrovi.containsKey(id)) {
			throw new IllegalArgumentException("invalid id");
		}

		Zanr zanr = zanrovi.get(id);
		if (zanr != null) {
			zanrovi.remove(id);
		}
		return zanr;
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long itID: ids) {
			delete(itID);
		}
	}

	@Override
	public List<Zanr> find(String naziv) {
		if (naziv == null) {
			naziv = "";
		}

		List<Zanr> rezultat = new ArrayList<>();
		for (Zanr itZanr: zanrovi.values()) {
			if (!itZanr.getNaziv().toLowerCase().contains(naziv.toLowerCase())) {
				continue;
			}

			rezultat.add(itZanr);
		}
		return rezultat;
	}

}
