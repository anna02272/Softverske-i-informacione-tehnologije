package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.FilmStatistika;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Zanr;
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
	private String baseURL;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}

	@GetMapping
	public ModelAndView index(HttpSession session)  throws IOException {	
		// čitanje
		List<Film> filmovi = filmService.findAll();
		List<Zanr> zanrovi = zanrService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("filmovi");
		rezultat.addObject("filmovi", filmovi);
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}
	
	@GetMapping(value="/Search")
	@ResponseBody
	public Map<String, Object> search(
			@RequestParam(required=false) String naziv, 
			@RequestParam(required=false) Long zanrId, 
			@RequestParam(required=false) Integer trajanjeOd, 
			@RequestParam(required=false) Integer trajanjeDo, HttpSession session)  throws IOException {
		System.out.println("Pogodio pretragu");
		
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
	@SuppressWarnings("unchecked")
	public ModelAndView details(@RequestParam Long id, 
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// čitanje
		Film film = filmService.findOne(id);
		List<Zanr> zanrovi = zanrService.findAll();

		// obrada
		FilmStatistika statistikaFilmova = (FilmStatistika) servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
		statistikaFilmova.incrementBrojac(film);

		List<Film> poseceniFilmovi = (List<Film>) session.getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
		if (!poseceniFilmovi.contains(film)) {
			poseceniFilmovi.add(film);
		}

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("film");
		rezultat.addObject("film", film);
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}

	@GetMapping(value="/Create")
	public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return null;
		}

		// čitanje
		List<Zanr> zanrovi = zanrService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("dodavanjeFilma");
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}

	@PostMapping(value="/Create")
	public void create(@RequestParam String naziv, @RequestParam int trajanje, @RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return;
		}

		// kreiranje
		Film film = new Film(naziv, trajanje);
		film.setZanrovi(zanrService.find(zanrIds));
		filmService.save(film);

		response.sendRedirect(baseURL + "Filmovi");
	}

	@PostMapping(value="/Edit")
	public void edit(@RequestParam Long id, 
			@RequestParam String naziv, @RequestParam int trajanje, @RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return;
		}

		// validacija
		Film film = filmService.findOne(id);
		if (film == null) {
			response.sendRedirect(baseURL + "Filmovi");
			return;
		}	
		if (naziv == null || naziv.equals("") || trajanje < 5) {
			response.sendRedirect(baseURL + "Filmovi/Details?id=" + id);
			return;
		}

		// izmena
		film.setNaziv(naziv);
		film.setTrajanje(trajanje);
		film.setZanrovi(zanrService.find(zanrIds));
		filmService.update(film);

		response.sendRedirect(baseURL + "Filmovi");
	}

	@PostMapping(value="/Delete")
	public void delete(@RequestParam Long id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return;
		}

		// brisanje
		filmService.delete(id);
	
		response.sendRedirect(baseURL + "Filmovi");
	}

}
