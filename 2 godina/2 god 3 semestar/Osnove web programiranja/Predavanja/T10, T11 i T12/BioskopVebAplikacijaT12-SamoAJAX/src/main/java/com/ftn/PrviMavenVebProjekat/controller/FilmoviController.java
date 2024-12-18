package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.LocaleResolver;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.FilmStatistika;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ZanrService;

@Controller
@RequestMapping(value="/Filmovi")
public class FilmoviController implements ServletContextAware {

	public static final String STATISTIKA_FILMOVA_KEY = "statistikaFilmova";
	public static final String POSECENI_FILMOVI_ZA_KORISNIKA_KEY = "poseceniFilmoviZaKorisnika";
	
	@Autowired
	private FilmService filmService;

	@Autowired
	private ZanrService zanrService;
	
	@Autowired
	private ServletContext servletContext;

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private LocaleResolver localeResolver;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 

	@GetMapping
	@ResponseBody
	public Map<String, Object> index(
			@RequestParam(required=false) String naziv, 
			@RequestParam(required=false) Long zanrId, 
			@RequestParam(required=false) Integer trajanjeOd, 
			@RequestParam(required=false) Integer trajanjeDo)  throws IOException {
		
		//ako je input tipa text i ništa se ne unese 
		//a parametar metode Sting onda će vrednost parametra handeler metode biti "" što nije null
		if(naziv!=null && naziv.trim().equals(""))
			naziv=null;
		
		// čitanje
		List<Film> filmovi = filmService.find(naziv, zanrId, trajanjeOd, trajanjeDo);

		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("filmovi", filmovi);
		return odgovor;
	}

	@GetMapping(value="/Details")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, Object> details(@RequestParam Long id, 
			HttpSession session) throws IOException {
		// čitanje
		Film film = filmService.findOne(id);

		// obrada
		FilmStatistika statistikaFilmova = (FilmStatistika) servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
		statistikaFilmova.incrementBrojac(film);

		List<Film> poseceniFilmovi = (List<Film>) session.getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
		if (!poseceniFilmovi.contains(film)) {
			poseceniFilmovi.add(film);
		}

		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("film", film);
		return odgovor;
	}

	@GetMapping(value="/StatistikaFilmova")
	@ResponseBody
	public Map<String, Object> statistikaFilmova() throws IOException {
		// čitanje
		FilmStatistika statistikaFilmova = (FilmStatistika) servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);

		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("statistikaFilmova", statistikaFilmova);
		return odgovor;
	}

	@GetMapping(value="/PoseceniFilmovi")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, Object> poseceniFilmovi(HttpSession session) throws IOException {
		// čitanje
		List<Film> poseceniFilmovi = (List<Film>) session.getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);

		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("poseceniFilmovi", poseceniFilmovi);
		return odgovor;
	}
	
	@PostMapping(value="/Create")
	@ResponseBody
	public Map<String, Object> create(@RequestParam String naziv, @RequestParam int trajanje, @RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletRequest request) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}
		Locale lokalizacija = localeResolver.resolveLocale(request);
		try {
			
			// validacija
			if (naziv == null || naziv.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("movie.title",null, lokalizacija)}, lokalizacija));
			}
			if (trajanje < 5) {
				throw new Exception(messageSource.getMessage("status.message.inputIntegerGe",
						new Object[] {messageSource.getMessage("movie.duration",null, lokalizacija),5}, lokalizacija));
			}
			if (zanrIds == null || zanrIds.length <= 0 ) {
				throw new Exception(messageSource.getMessage("status.message.inputMustChosenAtLeastOne",
						new Object[] {messageSource.getMessage("movie.genre",null, lokalizacija)}, lokalizacija));
			}

			// kreiranje
			Film film = new Film(naziv, trajanje);
			film.setZanrovi(zanrService.find(zanrIds));
			filmService.save(film);
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "ok");	
			return odgovor;
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = messageSource.getMessage("status.message.addingNotSuccessful",null, lokalizacija);
			}
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "greska");
			odgovor.put("poruka", poruka);
			return odgovor;
		}
	}

	@PostMapping(value="/Edit")
	@ResponseBody
	public Map<String, Object> edit(@RequestParam Long id, 
			@RequestParam String naziv, @RequestParam int trajanje, @RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletRequest request) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}
		Locale lokalizacija = localeResolver.resolveLocale(request);
		try {
			// validacija
			Film film = filmService.findOne(id);
			if (film == null) {
				throw new Exception(messageSource.getMessage("status.message.entityNotFound",
						new Object[] {messageSource.getMessage("movie",null, lokalizacija)}, lokalizacija));
			}	
			if (naziv == null || naziv.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("movie.title",null, lokalizacija)}, lokalizacija));
			}
			if (trajanje < 5) {
				throw new Exception(messageSource.getMessage("status.message.inputIntegerGe",
						new Object[] {messageSource.getMessage("movie.duration",null, lokalizacija),5}, lokalizacija));
			}
			if (zanrIds == null || zanrIds.length <= 0 ) {
				throw new Exception(messageSource.getMessage("status.message.inputMustChosenAtLeastOne",
						new Object[] {messageSource.getMessage("movie.genre",null, lokalizacija)}, lokalizacija));
			}

			// izmena
			film.setNaziv(naziv);
			film.setTrajanje(trajanje);
			film.setZanrovi(zanrService.find(zanrIds));
			filmService.update(film);
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "ok");	
			return odgovor;
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = messageSource.getMessage("status.message.editingNotSuccessful",null, lokalizacija);
			}
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "greska");
			odgovor.put("poruka", poruka);
			return odgovor;
		}
	}

	@PostMapping(value="/Delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam Long id, 
			HttpSession session) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}

		// brisanje
		filmService.delete(id);
	
		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		return odgovor;
	}

}
