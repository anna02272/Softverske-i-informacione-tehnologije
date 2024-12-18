package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ProjekcijaService;

@Controller
@RequestMapping(value="/Projekcije")
public class ProjekcijeController {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
	
	@Autowired
	private ProjekcijaService projekcijaService;

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL; 

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";	
	}

	@GetMapping
	public ModelAndView Index(
			@RequestParam(required=false, defaultValue="2020-01-01") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumOd, 
			@RequestParam(required=false, defaultValue="08:00") @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalTime vremeOd, 
			@RequestParam(required=false, defaultValue="3000-01-01") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumDo, 
			@RequestParam(required=false, defaultValue="23:00") @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalTime vremeDo, 
			@RequestParam(required=false, defaultValue="0") Long filmId, 
			@RequestParam(required=false, defaultValue="0") Integer sala, 
			@RequestParam(required=false, defaultValue="") String tip, 
			@RequestParam(required=false, defaultValue="0") Double cenaKarteOd, 
			@RequestParam(required=false, defaultValue="1.7976931348623157e+308") Double cenaKarteDo, 
			HttpSession session) throws IOException {
		// čitanje
		LocalDateTime datumIVremeOd = LocalDateTime.of(datumOd, vremeOd);
		LocalDateTime datumIVremeDo = LocalDateTime.of(datumDo, vremeDo);
		List<Projekcija> projekcije = projekcijaService.find(datumIVremeOd, datumIVremeDo, filmId, tip, sala, cenaKarteOd, cenaKarteDo);
		List<Film> filmovi = filmService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("projekcije");
		rezultat.addObject("projekcije", projekcije);
		rezultat.addObject("filmovi", filmovi);

		return rezultat;
	}

	@GetMapping(value="/Details")
	public ModelAndView Details(@RequestParam Long id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// čitanje
		Projekcija projekcija = projekcijaService.findOne(id);
		if (projekcija == null) {
			response.sendRedirect(baseURL + "Projekcije");
			return null;
		}

		List<Film> filmovi = filmService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("projekcija");
		rezultat.addObject("projekcija", projekcija);
		rezultat.addObject("filmovi", filmovi);

		return rezultat;
	}

	@GetMapping(value="/Create")
	public ModelAndView Create(@RequestParam(name="filmId", required=false) Long filmId, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || prijavljeniKorisnik.isAdministrator() == false) {
			response.sendRedirect(baseURL + "prijava.html");
			return null;
		}

		// čitanje
		List<Film> filmovi = filmService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("dodavanjeProjekcije");
		rezultat.addObject("filmovi", filmovi);

		return rezultat;
	}

	@PostMapping(value="/Create")
	public void Create(
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datum, 
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalTime vreme, 
			@RequestParam Long filmId, @RequestParam(defaultValue="4D") String tip, @RequestParam int sala,
			@RequestParam double cenaKarte, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// validacija
		LocalDateTime datumIVreme = LocalDateTime.of(datum, vreme);
		
		Film film = filmService.findOne(filmId);
		if (film == null) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// kreiranje
		Projekcija projekcija = new Projekcija(datumIVreme, film, sala, tip, cenaKarte);
		projekcijaService.save(projekcija);
		
		response.sendRedirect(baseURL + "Projekcije");
	}

	@PostMapping(value="/Edit")
	public void Edit(
			@RequestParam Long id, 
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datum, 
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalTime vreme, 
			@RequestParam Long filmId, @RequestParam String tip, @RequestParam int sala,
			@RequestParam double cenaKarte, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// validacija
		Projekcija projekcija = projekcijaService.findOne(id);
		if (projekcija == null) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}
		
		LocalDateTime datumIVreme = LocalDateTime.of(datum, vreme);
		
		Film film = filmService.findOne(filmId);
		if (film == null) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// izmena
		projekcija.setDatumIVreme(datumIVreme);
		projekcija.setFilm(film);
		projekcija.setTip(tip);
		projekcija.setSala(sala);
		projekcija.setCenaKarte(cenaKarte);
		projekcijaService.update(projekcija);
	
		response.sendRedirect(baseURL + "Projekcije");
	}

	@PostMapping(value="/Delete")
	public void Delete(@RequestParam Long id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// brisanje
		projekcijaService.delete(id);

		response.sendRedirect(baseURL + "Projekcije");
	}

}
