package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Zanr;
import com.ftn.PrviMavenVebProjekat.service.ZanrService;

@Controller
@RequestMapping(value="/Zanrovi")
public class ZanroviController {

	@Autowired
	private ZanrService zanrService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL; 

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}

	@GetMapping
	public ModelAndView index(@RequestParam(required=false) String naziv, HttpSession session) {
		//ako je input tipa text i ništa se ne unese 
		//a parametar metode Sting onda će vrednost parametra handeler metode biti "" što nije null
		if(naziv!=null && naziv.trim().equals(""))
			naziv=null;
		
		// čitanje
		List<Zanr> zanrovi = zanrService.find(naziv);

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("zanrovi");
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}

	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam Long id, HttpSession session) throws IOException {
		// čitanje
		Zanr zanr = zanrService.findOne(id);

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("zanr");
		rezultat.addObject("zanr", zanr);

		return rezultat;
	}

	@GetMapping(value="/Create")
	public String create(HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Zanrovi");
			return "zanrovi";
		}

		return "dodavanjeZanra";
	}
	
	@PostMapping(value="/Create")
	public void create(@RequestParam String naziv, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Zanrovi");
			return;
		}

		// validacija
		if (naziv.equals("")) {
			response.sendRedirect(baseURL + "Zanrovi/Create");
			return;
		}

		// kreiranje
		Zanr zanr = new Zanr(naziv);
		zanrService.save(zanr);

		response.sendRedirect(baseURL + "Zanrovi");
	}

	@PostMapping(value="/Edit")
	public void edit(@RequestParam Long id, 
			@RequestParam String naziv, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Zanrovi");
			return;
		}

		// validacija
		Zanr zanr = zanrService.findOne(id);
		if (zanr == null) {
			response.sendRedirect(baseURL + "Zanrovi");
			return;
		}	
		if (naziv == null || naziv.equals("")) {
			response.sendRedirect(baseURL + "Zanrovi/Details?id=" + id);
			return;
		}

		// izmena
		zanr.setNaziv(naziv);
		zanrService.update(zanr);

		response.sendRedirect(baseURL + "Zanrovi");
	}

	@PostMapping(value="/Delete")
	public void delete(@RequestParam Long id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Zanrovi");
			return;
		}

		// brisanje
		zanrService.delete(id);

		response.sendRedirect(baseURL + "Zanrovi");
	}

}
