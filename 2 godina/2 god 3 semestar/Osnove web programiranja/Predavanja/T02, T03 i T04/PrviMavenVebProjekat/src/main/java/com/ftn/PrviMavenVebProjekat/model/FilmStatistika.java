package com.ftn.PrviMavenVebProjekat.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmStatistika {

	private Map<Long, FilmBrojac> popularniFilmovi;

	public FilmStatistika() {
		super();
		popularniFilmovi = new HashMap<Long, FilmBrojac>();
	}

	public void incrementBrojac(Film film) {
		FilmBrojac fB = null;
		if(popularniFilmovi.get(film.getId())==null){
			fB = new FilmBrojac(film);
			popularniFilmovi.put(film.getId(), fB);
		}
		fB = popularniFilmovi.get(film.getId());
		fB.incrementBrojac();
	}
	
	public List<FilmBrojac> getFilmovi() {
		List<FilmBrojac> sorted = new ArrayList<FilmBrojac>(popularniFilmovi.values());
		Collections.sort(sorted, new SorterFilmova());
		return sorted;
	}
	
	public boolean isEmpty() {
		return popularniFilmovi.isEmpty();
	}
	
	private class SorterFilmova implements Comparator<FilmBrojac>{

		int direction = -1;
		
		@Override
		public int compare(FilmBrojac arg0, FilmBrojac arg1) {
			if(arg0.getBrojac()==arg1.getBrojac())
				return arg0.getFilm().getNaziv().compareTo(arg1.getFilm().getNaziv());
			
			return (arg0.getBrojac()-arg1.getBrojac())*direction;
		}
	}
}
