package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
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
	private MessageSource messageSource;
	@Autowired
	private LocaleResolver localeResolver;
	
	@GetMapping(value="PrijavljeniKorisnik")
	@ResponseBody
	public Map<String, Object> prijavljeniKorisnik(HttpSession session) {
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		
		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("prijavljeniKorisnik", prijavljeniKorisnik);	
		return odgovor;
	}
	
	@GetMapping
	@ResponseBody
	public Map<String, Object> index(
			@RequestParam(required=false) String korisnickoIme,
			@RequestParam(required=false) String eMail,
			@RequestParam(required=false) String pol,
			@RequestParam(required=false) Boolean administrator,
			HttpSession session) throws IOException {		
		
		// autentikacija, autorzacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
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

		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("korisnici", korisnici);
		return odgovor;
	}

	@GetMapping(value="/Details")
	@ResponseBody
	public Map<String, Object> details(@RequestParam String korisnickoIme, 
			HttpSession session) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da vidi druge korisnike; svaki korisnik može da vidi sebe
		if (prijavljeniKorisnik == null || (!prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme))) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}

		// čitanje
		Korisnik korisnik = korisnikService.findOne(korisnickoIme);

		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("korisnik", korisnik);
		return odgovor;
	}
	
	@PostMapping(value="/Create")
	@ResponseBody
	public Map<String, Object> create(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			@RequestParam String eMail, @RequestParam String pol, @RequestParam(required=false) String administrator,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da kreira korisnike
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}
		Locale lokalizacija = localeResolver.resolveLocale(request);
		try {
			// validacija
			Korisnik postojeciKorisnik = korisnikService.findOne(korisnickoIme);
			if (postojeciKorisnik != null) {
				throw new Exception(messageSource.getMessage("status.message.inputAlreadyTaken",
						new Object[] {messageSource.getMessage("user.username",null, lokalizacija), korisnickoIme}, lokalizacija));
			}
			if (korisnickoIme.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("user.username",null, lokalizacija)}, lokalizacija));
			}
			if (lozinka.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("user.password",null, lokalizacija)}, lokalizacija));
			}
			if (eMail.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("user.email",null, lokalizacija)}, lokalizacija));
			}
			if (!pol.equals("muški") && !pol.equals("ženski")) {
				throw new Exception(messageSource.getMessage("status.message.inputMustBeFromSet",
						new Object[] {messageSource.getMessage("user.gender",null, lokalizacija), 
								messageSource.getMessage("user.gender.manAndwoman",null, lokalizacija)}, lokalizacija));
			}
			// kreiranje
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol, administrator != null);
			korisnikService.save(korisnik);
			
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
	public Map<String, Object> edit(@RequestParam String korisnickoIme, 
			@RequestParam String lozinka, String eMail, @RequestParam String pol, @RequestParam(required=false) String administrator,
			HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da menja druge korisnike; svaki korisnik može da menja sebe
		if (prijavljeniKorisnik == null || (!prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme))) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}
		Locale lokalizacija = localeResolver.resolveLocale(request);
		try {
			// validacija
			Korisnik korisnik = korisnikService.findOne(korisnickoIme);
			if (korisnik == null) {
				throw new Exception(messageSource.getMessage("status.message.entityNotFound",
						new Object[] {messageSource.getMessage("user",null, lokalizacija)}, lokalizacija));
			}
			if (eMail.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("user.email",null, lokalizacija)}, lokalizacija));
			}
			if (!pol.equals("muški") && !pol.equals("ženski")) {
				throw new Exception(messageSource.getMessage("status.message.inputMustBeFromSet",
						new Object[] {messageSource.getMessage("user.gender",null, lokalizacija), 
								messageSource.getMessage("user.gender.manAndwoman",null, lokalizacija)}, lokalizacija));
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
			else
				throw new Exception(messageSource.getMessage("status.message.administratorRestriction",null, lokalizacija));
			
			korisnikService.update(korisnik);
	
			// sigurnost
			if (!prijavljeniKorisnik.equals(korisnik)) {
				// TODO odjaviti korisnika
			}
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "ok");	
			return odgovor;
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna izmena!";
			}
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "greska");
			odgovor.put("poruka", poruka);
			return odgovor;
		}
	}

	@PostMapping(value="/Delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam String korisnickoIme, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da briše korisnike, ali ne i sebe
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator() || prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme)) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}

		// brisanje
		korisnikService.delete(korisnickoIme);

		// sigurnost
		// TODO odjaviti korisnika
		
		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		return odgovor;
	}

	@PostMapping(value="/Register")
	@ResponseBody
	public Map<String, Object> register(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			@RequestParam String eMail, @RequestParam String pol, @RequestParam String ponovljenaLozinka,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		Locale lokalizacija = localeResolver.resolveLocale(request);
		try {
			// validacija
			Korisnik postojeciKorisnik = korisnikService.findOne(korisnickoIme);
			if (postojeciKorisnik != null) {
				throw new Exception(messageSource.getMessage("status.message.inputAlreadyTaken",
						new Object[] {messageSource.getMessage("user.username",null, lokalizacija), korisnickoIme}, lokalizacija));
			}
			if (korisnickoIme.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("user.username",null, lokalizacija)}, lokalizacija));
			}
			if (lozinka.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("user.password",null, lokalizacija)}, lokalizacija));
			}
			if (!lozinka.equals(ponovljenaLozinka)) {
				throw new Exception(messageSource.getMessage("status.message.userPasswordsNotMatch",null, lokalizacija));
			}
			if (eMail.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("user.email",null, lokalizacija)}, lokalizacija));
			}
			if (!pol.equals("muški") && !pol.equals("ženski")) {
				throw new Exception(messageSource.getMessage("status.message.inputMustBeFromSet",
						new Object[] {messageSource.getMessage("user.gender",null, lokalizacija), 
								messageSource.getMessage("user.gender.manAndwoman",null, lokalizacija)}, lokalizacija));
			}

			// registracija
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol);
			korisnikService.save(korisnik);

			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "ok");	
			return odgovor;
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = messageSource.getMessage("status.message.userRegistrationNotSuccessful",null, lokalizacija);
			}

			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "greska");
			odgovor.put("poruka", poruka);
			return odgovor;
		}
	}
	
	@PostMapping(value="/Login")
	@ResponseBody
	public Map<String, Object> postLogin(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
		Locale lokalizacija = localeResolver.resolveLocale(request);
		try {
			// validacija
			Korisnik korisnik = korisnikService.findOne(korisnickoIme, lozinka);
			if (korisnik == null) {
				throw new Exception(messageSource.getMessage("status.message.userLogginUsernameOrPassworNotValid",null, lokalizacija));
			}			

			// prijava
			session.setAttribute(KorisnikController.KORISNIK_KEY, korisnik);
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "ok");	
			return odgovor;
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = messageSource.getMessage("status.message.userLogginNotSuccessful",null, lokalizacija);
			}
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "greska");
			odgovor.put("poruka", poruka);
			return odgovor;
		}
	}

	@GetMapping(value="/Logout")
	@ResponseBody
	public Map<String, Object> logout(HttpSession session, HttpServletResponse response) throws IOException {
		// odjava	
		session.invalidate();
		
		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");	
		return odgovor;
	}
	
}
