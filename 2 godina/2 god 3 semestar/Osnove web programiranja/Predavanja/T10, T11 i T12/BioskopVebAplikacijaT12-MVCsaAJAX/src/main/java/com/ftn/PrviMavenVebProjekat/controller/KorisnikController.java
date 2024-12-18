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
import com.ftn.PrviMavenVebProjekat.service.KorisnikService;

@Controller
@RequestMapping(value="/Korisnici")
public class KorisnikController {

	public static final String KORISNIK_KEY = "prijavljeniKorisnik";
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL; 

	@PostConstruct
	public void init() {	
		baseURL = servletContext.getContextPath() + "/";			
	}

	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) String korisnickoIme,
			@RequestParam(required=false) String eMail,
			@RequestParam(required=false) String pol,
			@RequestParam(required=false) Boolean administrator,
			HttpSession session, HttpServletResponse response) throws IOException {		
		// autentikacija, autorzacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL);
			return null;
		}

		//ako je input tipa text i ništa se ne unese 
		//a parametar metode Sting onda će vrednost parametra handeler metode biti "" što nije null
		if(korisnickoIme!=null && korisnickoIme.trim().equals(""))
			korisnickoIme=null;
		
		if(eMail!=null && eMail.trim().equals(""))
			eMail=null;
		
		if(pol!=null && pol.trim().equals(""))
			pol=null;
		
		// čitanje
		List<Korisnik> korisnici = korisnikService.find(korisnickoIme, eMail, pol, administrator);

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("korisnici");
		rezultat.addObject("korisnici", korisnici);

		return rezultat;
	}

	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam String korisnickoIme, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da vidi druge korisnike; svaki korisnik može da vidi sebe
		if (prijavljeniKorisnik == null || (!prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme))) {
			response.sendRedirect(baseURL + "Korisnici");
			return null;
		}

		// validacija
		Korisnik korisnik = korisnikService.findOne(korisnickoIme);
		if (korisnik == null) {
			response.sendRedirect(baseURL + "Korisnici");
			return null;
		}

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("korisnik");
		rezultat.addObject("korisnik", korisnik);

		return rezultat;
	}

	@GetMapping(value="/Create")
	public String create(HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da kreira korisnike
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return "filmovi";
		}

		return "dodavanjeKorisnika";
	}
	
	@PostMapping(value="/Create")
	public void create(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			@RequestParam String eMail, @RequestParam String pol, @RequestParam(required=false) String administrator,
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da kreira korisnike
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}

		// validacija
		Korisnik postojeciKorisnik = korisnikService.findOne(korisnickoIme);
		if (postojeciKorisnik != null) {
			response.sendRedirect(baseURL + "Korisnici/Create");
			return;
		}
		if (korisnickoIme.equals("") || lozinka.equals("")) {
			response.sendRedirect(baseURL + "Korisnici/Create");
			return;
		}
		if (eMail.equals("")) {
			response.sendRedirect(baseURL + "Korisnici/Create");
			return;
		}
		if (!pol.equals("muški") && !pol.equals("ženski")) {
			response.sendRedirect(baseURL + "Korisnici/Create");
			return;
		}

		// kreiranje
		Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol, administrator != null);
		korisnikService.save(korisnik);

		response.sendRedirect(baseURL + "Korisnici");
	}

	@PostMapping(value="/Edit")
	public void edit(@RequestParam String korisnickoIme, 
			@RequestParam String lozinka, String eMail, @RequestParam String pol, @RequestParam(required=false) String administrator,
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da menja druge korisnike; svaki korisnik može da menja sebe
		if (prijavljeniKorisnik == null || (!prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme))) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}

		// validacija
		Korisnik korisnik = korisnikService.findOne(korisnickoIme);
		if (korisnik == null) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}
		if (eMail.equals("")) {
			response.sendRedirect(baseURL + "Korisnici/Edit?korisnicoIme=" + korisnickoIme);
			return;
		}
		if (!pol.equals("muški") && !pol.equals("ženski")) {
			response.sendRedirect(baseURL + "Korisnici/Edit?korisnicoIme=" + korisnickoIme);
			return;
		}

		// izmena
		if (!lozinka.equals("")) {
			korisnik.setLozinka(lozinka);
		}
		korisnik.setEMail(eMail);
		korisnik.setPol(pol);
		// privilegije može menjati samo administrator i to drugim korisnicima
		if (prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.equals(korisnik)) {
			korisnik.setAdministrator(administrator != null);
		}
		korisnikService.update(korisnik);

		// sigurnost
		if (!prijavljeniKorisnik.equals(korisnik)) {
			// TODO odjaviti korisnika
		}

		if (prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Korisnici");
		} else {
			response.sendRedirect(baseURL);
		}
	}

	@PostMapping(value="/Delete")
	public void delete(@RequestParam String korisnickoIme, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da briše korisnike, ali ne i sebe
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator() || prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme)) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}

		// brisanje
		korisnikService.delete(korisnickoIme);

		// sigurnost
		// TODO odjaviti korisnika
		
		response.sendRedirect(baseURL + "Korisnici");
	}

	@PostMapping(value="/Register")
	public ModelAndView register(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			@RequestParam String eMail, @RequestParam String pol, @RequestParam String ponovljenaLozinka,
			HttpSession session, HttpServletResponse response) throws IOException {
		try {
			// validacija
			Korisnik postojeciKorisnik = korisnikService.findOne(korisnickoIme);
			if (postojeciKorisnik != null) {
				throw new Exception("Korisničko ime već postoji!");
			}
			if (korisnickoIme.equals("") || lozinka.equals("")) {
				throw new Exception("Korisničko ime i lozinka ne smeju biti prazni!");
			}
			if (!lozinka.equals(ponovljenaLozinka)) {
				throw new Exception("Lozinke se ne podudaraju!");
			}
			if (eMail.equals("")) {
				throw new Exception("E-mail ne sme biti prazan!");
			}
			if (!pol.equals("muški") && !pol.equals("ženski")) {
				throw new Exception("Morate odabrati pol!");
			}

			// registracija
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol);
			korisnikService.save(korisnik);

			response.sendRedirect(baseURL + "prijava.html");
			return null;
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna registracija!";
			}

			// prosleđivanje
			ModelAndView rezultat = new ModelAndView("registracija");
			rezultat.addObject("poruka", poruka);

			return rezultat;
		}
	}
	
	@PostMapping(value="/Login")
	public ModelAndView postLogin(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			HttpSession session, HttpServletResponse response) throws IOException {
		try {
			// validacija
			Korisnik korisnik = korisnikService.findOne(korisnickoIme, lozinka);
			if (korisnik == null) {
				throw new Exception("Neispravno korisničko ime ili lozinka!");
			}			

			// prijava
			session.setAttribute(KorisnikController.KORISNIK_KEY, korisnik);
			
			response.sendRedirect(baseURL);
			return null;
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna prijava!";
			}
			
			// prosleđivanje
			ModelAndView rezultat = new ModelAndView("prijava");
			rezultat.addObject("poruka", poruka);

			return rezultat;
		}
	}

	@GetMapping(value="/Logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		// odjava	
		session.invalidate();
		
		response.sendRedirect(baseURL);
	}
	
}
