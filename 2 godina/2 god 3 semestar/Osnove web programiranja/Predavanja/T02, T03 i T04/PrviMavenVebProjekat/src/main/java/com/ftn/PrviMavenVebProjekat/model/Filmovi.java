package com.ftn.PrviMavenVebProjekat.model;

import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Filmovi {

	private Map<Long, Film> filmovi = new HashMap<>();
	private long nextId = 1L;

	/** Cita filmove iz datoteke i smesta ih u asocijativnu listu filmova. */
	public Filmovi() {

		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("filmovi.txt").toURI());
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
	}

	/** vraca film u odnosu na zadati id */
	public Film findOne(Long id) {
		return filmovi.get(id);
	}

	/** vraca sve filmove */
	public List<Film> findAll() {
		return new ArrayList<Film>(filmovi.values());
	}

	/** cuva podatke o filmu filmove */
	public Film save(Film film) {
		if (film.getId() == null) {
			film.setId(++nextId);
		}
		filmovi.put(film.getId(), film);
		return film;
	}

	/** cuva podatke o svim filmovima */
	public List<Film> save(List<Film> films) {
		List<Film> ret = new ArrayList<>();

		for (Film f : films) {
			// za svaku prosleđeni film pozivamo save
			// metodu koju smo već napravili i testirali -
			// ona će sepobrinuti i za dodelu ID-eva
			// ako je to potrebno
			Film saved = save(f);

			// uspešno snimljene dodajemo u listu za vraćanje
			if (saved != null) {
				ret.add(saved);
			}
		}
		return ret;
	}

	/** brise film u odnosu na zadati id */
	public Film delete(Long id) {
		if (!filmovi.containsKey(id)) {
			throw new IllegalArgumentException("tried to remove non existing film");
		}
		Film film = filmovi.get(id);
		if (film != null) {
			filmovi.remove(id);
		}
		return film;
	}

	/** brise filmove u odnosu na zadate ids */
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			// pozivamo postojeću metodu za svaki
			delete(id);
		}
	}

	/** vraca sve filmove ciji naziv zapocinje sa tekstom*/
	public List<Film> findByNaziv(String naziv) {
		List<Film> ret = new ArrayList<>();

		for (Film f : filmovi.values()) {
			if (naziv.startsWith(f.getNaziv())) {
				ret.add(f);
			}
		}

		return ret;
	}
}
