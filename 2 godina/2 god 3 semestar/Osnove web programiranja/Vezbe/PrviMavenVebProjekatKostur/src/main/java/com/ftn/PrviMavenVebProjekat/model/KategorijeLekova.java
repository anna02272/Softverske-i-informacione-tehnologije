package com.ftn.PrviMavenVebProjekat.model;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KategorijeLekova {

	private Map<Long, KategorijaLeka> kategorijeLekova = new HashMap<>();
	private long nextId = 1L;

	
	public KategorijeLekova() {

		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("kategorije.txt").toURI());
			System.out.println(path.toFile().getAbsolutePath());
			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

			for (String line : lines) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				
				String[] tokens = line.split(";");
				Long id = Long.parseLong(tokens[0]);
				
				if(nextId<id)
					nextId=id;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public KategorijaLeka findOne(Long id) {
		return kategorijeLekova.get(id);
	}

	public List<KategorijaLeka> findAll() {
		return new ArrayList<KategorijaLeka>(kategorijeLekova.values());
	}

	public KategorijaLeka save(KategorijaLeka kategorijaLeka) {
		if (kategorijaLeka.getId() == null) {
			kategorijaLeka.setId(++nextId);
		}
		kategorijeLekova.put(kategorijaLeka.getId(), kategorijaLeka);
		return kategorijaLeka;
	}

	public List<KategorijaLeka> save(List<KategorijaLeka> kategorijeLekova) {
		List<KategorijaLeka> ret = new ArrayList<>();

		for (KategorijaLeka k : kategorijeLekova) {
			KategorijaLeka saved = save(k);

			if (saved != null) {
				ret.add(saved);
			}
		}
		return ret;
	}

	public KategorijaLeka delete(Long id) {
		if (!kategorijeLekova.containsKey(id)) {
			throw new IllegalArgumentException("tried to remove non existing");
		}
		KategorijaLeka kategorijaLeka = kategorijeLekova.get(id);
		if (kategorijaLeka != null) {
			kategorijeLekova.remove(id);
		}
		return kategorijaLeka;
	}

	public void delete(List<Long> ids) {
		for (Long id : ids) {
			delete(id);
		}
	}

	public List<KategorijaLeka> findByNaziv(String naziv) {
		List<KategorijaLeka> ret = new ArrayList<>();

		for (KategorijaLeka k : kategorijeLekova.values()) {
			if (naziv.startsWith(k.getNaziv())) {
				ret.add(k);
			}
		}

		return ret;
	}
}
