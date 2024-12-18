package com.ftn.PrviMavenVebProjekat.service.impl;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.service.FilmService;

/** filmovi se prezistiraju u fajl sistem */

@Service
//napomena moze biti samo jedana implementacije servisa anotirana sa @Primary
@Primary
//sa @Qualifier se definiše naziv bean kandidata
@Qualifier("fajloviFilm")
public class FileFilmService implements FilmService {

	@Value("${filmovi.pathToFile}")
	private String pathToFile;
	
    private Map<Long, Film> readFromFile() {

    	Map<Long, Film> filmovi = new HashMap<>();
    	Long nextId = 1L;
    	
    	try {
			Path path = Paths.get(pathToFile);
			System.out.println(path.toFile().getAbsolutePath());
			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

			for (String line : lines) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				String[] tokens = line.split(";");
				Long id = Long.parseLong(tokens[0]);
				String naziv = tokens[1];
				int trajanje  = Integer.parseInt(tokens[2]);

				filmovi.put(Long.parseLong(tokens[0]), new Film(id, naziv, trajanje));
				if(nextId<id)
					nextId=id;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return filmovi;
    }
    
    private Map<Long, Film> saveToFile(Map<Long, Film> filmovi) {
    	
    	Map<Long, Film> ret = new HashMap();
    	
    	try {
			Path path = Paths.get(pathToFile);
			System.out.println(path.toFile().getAbsolutePath());
			List<String> lines = new ArrayList<String>();
			
			for (Film film : filmovi.values()) {
				String line = film.getId()+";"+film.getNaziv()+";"+film.getTrajanje();
				lines.add(line);
				ret.put(film.getId(), film);
			}
			//pisanje svih redova za filmove
			Files.write(path, lines, Charset.forName("UTF-8"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ret;
    }
    
    private Long nextId(Map<Long, Film> filmovi) {
    	Long nextId = 1L;
    	for (Long id : filmovi.keySet()) {
    		if(nextId<id)
				nextId=id;
		}
    	return ++nextId;
    }
	
	@Override
	public Film findOne(Long id) {
		Map<Long, Film> filmovi = readFromFile();	
		return filmovi.get(id);
	}

	@Override
	public List<Film> findAll() {
		Map<Long, Film> filmovi = readFromFile();
		return new ArrayList<Film>(filmovi.values());
	}

	@Override
	public Film save(Film film) {
		Map<Long, Film> filmovi = readFromFile();
		Long nextId = nextId(filmovi); 
		
		//u slučaju da film nema id
		//tada treba da mu se dodeli id
		if (film.getId() == null) {
			film.setId(nextId++);
			
		}
		filmovi.put(film.getId(), film);
		saveToFile(filmovi);
		return film;
	}

	@Override
	public List<Film> save(List<Film> films) {
		
		Map<Long, Film> filmovi = readFromFile();
		Long nextId = nextId(filmovi); 
		
		List<Film> ret = new ArrayList<>();

		for (Film f : films) {
			
			//u slučaju da film nema id
			//tada treba da mu se dodeli id
			if (f.getId() == null) {
				f.setId(nextId++);
				
			}
			
			filmovi.put(f.getId(), f);
		}
		filmovi = saveToFile(filmovi);
		ret = new ArrayList<Film>(filmovi.values());

		return ret;
	}
	
	@Override
	public Film update(Film film) {
		Map<Long, Film> filmovi = readFromFile();
		
		filmovi.put(film.getId(), film);
		saveToFile(filmovi);
		return film;
	}

	@Override
	public List<Film> update(List<Film> films) {
		Map<Long, Film> filmovi = readFromFile();
		
		List<Film> ret = new ArrayList<>();
		for (Film f : films) {
			filmovi.put(f.getId(), f);
		}
		filmovi = saveToFile(filmovi);
		ret = new ArrayList<Film>(filmovi.values());

		return ret;
	}

	@Override
	public Film delete(Long id) {
		Map<Long, Film> filmovi = readFromFile();
		
		if (!filmovi.containsKey(id)) {
			throw new IllegalArgumentException("tried to remove non existing film");
		}
		
		Film film = filmovi.get(id);
		if (film != null) {
			filmovi.remove(id);
		}
		saveToFile(filmovi);
		return film;
	}

	@Override
	public void delete(List<Long> ids) {
		Map<Long, Film> filmovi = readFromFile();
		for (Long id : ids) {
			if (!filmovi.containsKey(id)) {
				throw new IllegalArgumentException("tried to remove non existing film");
			}
			
			Film film = filmovi.get(id);
			if (film != null) {
				filmovi.remove(id);
			}
		}
		
		saveToFile(filmovi);
	}

	@Override
	public List<Film> findByNaziv(String naziv) {
		Map<Long, Film> filmovi = readFromFile();
		List<Film> ret = new ArrayList<>();

		for (Film f : filmovi.values()) {
			if (naziv.equals(f.getNaziv())) {
				ret.add(f);
			}
		}
		return ret;
	}

	@Override
	public List<Film> findByTrajanje(int trajanje) {
		Map<Long, Film> filmovi = readFromFile();
		List<Film> ret = new ArrayList<>();

		for (Film f : filmovi.values()) {
			if (trajanje == f.getTrajanje()) {
				ret.add(f);
			}
		}
		return ret;
	}

	@Override
	public List<Film> findByTrajanjeFrom(int trajanjeFrom) {
		Map<Long, Film> filmovi = readFromFile();
		List<Film> ret = new ArrayList<>();

		for (Film f : filmovi.values()) {
			if (trajanjeFrom <= f.getTrajanje()) {
				ret.add(f);
			}
		}
		return ret;
	}

	@Override
	public List<Film> findByTrajanjeTo(int trajanjeTo) {
		Map<Long, Film> filmovi = readFromFile();
		List<Film> ret = new ArrayList<>();

		for (Film f : filmovi.values()) {
			if (trajanjeTo >= f.getTrajanje()) {
				ret.add(f);
			}
		}
		return ret;
	}

	@Override
	public List<Film> findByTrajanjeFromAndTrajanjeTo(int trajanjeFrom, int trajanjeTo) {
		Map<Long, Film> filmovi = readFromFile();
		List<Film> ret = new ArrayList<>();

		for (Film f : filmovi.values()) {
			if (trajanjeFrom <= f.getTrajanje() && trajanjeTo >= f.getTrajanje()) {
				ret.add(f);
			}
		}
		return ret;
	}
}
