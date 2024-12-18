package com.ftn.PrviMavenVebProjekat.service.impl;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ftn.PrviMavenVebProjekat.model.Knjiga;
import com.ftn.PrviMavenVebProjekat.service.KnjigaService;

@Service
@Qualifier("fajloviKnjiga")
public class KnjigaServiceImpl implements KnjigaService {
	
	@Value("${knjige.pathToFile}")
	private String pathToFile;
	
    private Map<Long, Knjiga> readFromFile() {

    	Map<Long, Knjiga> knjige = new HashMap<>();
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
				String registarskiBrojPrimerka = tokens[2];
				String jezik = tokens[3];
				int brStranica = Integer.parseInt(tokens[4]);
					
				knjige.put(id, new Knjiga(id, naziv, registarskiBrojPrimerka, jezik, brStranica));
				
				
				if(nextId<id)
					nextId=id;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return knjige;
    }
    
    private Map<Long, Knjiga> saveToFile(Map<Long, Knjiga> knjige) {
    	
    	Map<Long, Knjiga> ret = new HashMap<>();
    	
    	try {
			Path path = Paths.get(pathToFile);
			System.out.println(path.toFile().getAbsolutePath());
			List<String> lines = new ArrayList<>();
			
			for (Knjiga knjiga : knjige.values()) {
				String line = knjiga.toString(); // todo upis linije u fajl
				lines.add(line);
				ret.put(knjiga.getId(), knjiga);
			}
			//pisanje svih redova za filmove
			Files.write(path, lines, Charset.forName("UTF-8"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ret;
    }

    private Long nextId(Map<Long, Knjiga> knjige) {
    	Long next = 1L;
    	for(Long id : knjige.keySet()) {
    		if (next < id) {
    			next = id;
    		}
    	}
    	return ++next;
    }
    
	@Override
	public Knjiga findOne(Long id) {
		Map <Long, Knjiga> knjige = readFromFile();
		return knjige.get(id);
	}

	@Override
	public List<Knjiga> findAll() {
		Map <Long, Knjiga> knjige = readFromFile();
		return new ArrayList<>(knjige.values());
	}

	@Override
	public Knjiga save(Knjiga knjiga) {
		Map<Long, Knjiga> knjige = readFromFile();
		Long id = nextId(knjige);
		
		if(knjiga.getId() == null) {
			knjiga.setId(id);
		}
		
		knjige.put(id, knjiga);
		saveToFile(knjige);
		return knjiga;
	}

	@Override
	public Knjiga update(Knjiga knjiga) {
		Map<Long, Knjiga> knjige = readFromFile();
		
		knjige.put(knjiga.getId(), knjiga);
		saveToFile(knjige);
		
		
		return knjiga;
	}

	@Override
	public Knjiga delete(Long id) {
		Map<Long, Knjiga> knjige = readFromFile();
		if (!knjige.containsKey(id)) {
			throw new IllegalArgumentException("Pokusaj brisanja nepostojeceg objekta");
		}
		Knjiga knjiga = knjige.remove(id);
		saveToFile(knjige);
		return knjiga;
	}

}
