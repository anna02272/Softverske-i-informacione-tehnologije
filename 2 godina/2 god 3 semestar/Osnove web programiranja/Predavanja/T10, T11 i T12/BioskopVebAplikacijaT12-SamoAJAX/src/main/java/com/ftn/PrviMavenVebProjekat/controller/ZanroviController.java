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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Zanr;
import com.ftn.PrviMavenVebProjekat.service.ZanrService;

@Component
@RequestMapping(value="/Zanrovi")
public class ZanroviController {

	@Autowired
	private ZanrService zanrService;
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private LocaleResolver localeResolver;

	@GetMapping
	@ResponseBody
	public Map<String, Object> index(@RequestParam(required=false, defaultValue="") String naziv) {
		// čitanje
		List<Zanr> zanrovi = zanrService.find(naziv);

		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("zanrovi", zanrovi);
		return odgovor;
	}

	@GetMapping(value="/Details")
	@ResponseBody
	public Map<String, Object> details(@RequestParam Long id) throws IOException {
		// čitanje
		Zanr zanr = zanrService.findOne(id);

		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("zanr", zanr);
		return odgovor;
	}

	@PostMapping(value="/Create")
	@ResponseBody
	public Map<String, Object> create(@RequestParam String naziv, 
			HttpSession session, HttpServletRequest request) throws IOException {
		// autentikacija, autorizacija
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}
		Locale lokalizacija = localeResolver.resolveLocale(request);
		try {
			// validacija
			if (naziv.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("genre.name",null, lokalizacija)}, lokalizacija));
			}

			// kreiranje
			Zanr zanr = new Zanr(naziv);
			zanrService.save(zanr);

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
			@RequestParam String naziv, 
			HttpSession session, HttpServletRequest request) throws IOException {
		// autentikacija, autorizacija
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}
		Locale lokalizacija = localeResolver.resolveLocale(request);
		try {
			// validacija
			Zanr zanr = zanrService.findOne(id);
			if (zanr == null) {
				throw new Exception(messageSource.getMessage("status.message.entityNotFound",
						new Object[] {messageSource.getMessage("genre",null, lokalizacija)}, lokalizacija));
			}	
			
			if (naziv == null || naziv.equals("")) {
				throw new Exception(messageSource.getMessage("status.message.inputNotEmpty",
						new Object[] {messageSource.getMessage("genre.name",null, lokalizacija)}, lokalizacija));
			}

			// izmena
			zanr.setNaziv(naziv);
			zanrService.update(zanr);

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
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "odbijen");
			return odgovor;
		}

		// brisanje
		zanrService.delete(id);

		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		return odgovor;
	}

}
