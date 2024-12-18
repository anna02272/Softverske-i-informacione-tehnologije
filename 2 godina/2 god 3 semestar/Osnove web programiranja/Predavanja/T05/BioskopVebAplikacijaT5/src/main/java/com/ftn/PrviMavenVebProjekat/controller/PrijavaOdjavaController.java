package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;

@Controller
@RequestMapping("PrijavaOdjavaController")
public class PrijavaOdjavaController {
	
	public static final String ULOGOVAN_KEY = "ulogovanKorisnik";
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 

	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {	
		//Specify the base URL for all relative URLs in a document
		bURL = servletContext.getContextPath()+"/";				
	}
	
	
	@PostMapping("/LogIn")
	public void logovanje(@RequestParam String korisnickoIme, @RequestParam String korisnickaSifra,
			HttpSession session, HttpServletResponse response) throws IOException {
		//preuzimanje vrednosti iz konteksta
		HashMap<String, Korisnik> korisnici = (HashMap<String, Korisnik>)servletContext.getAttribute(KorisnikController.KORISNICI_KEY);		
		if(korisnickoIme==null || korisnickoIme.trim().equals("")) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		if(korisnickaSifra==null || korisnickaSifra.trim().equals("")) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		if(korisnici.get(korisnickoIme)==null) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		if(!korisnici.get(korisnickoIme).getKorisnickaSifra().equals(korisnickaSifra)) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		if(korisnici.get(korisnickoIme).isUlogovan()) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		if(session.getAttribute(ULOGOVAN_KEY)!=null) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		korisnici.get(korisnickoIme).setUlogovan(true);
		session.setAttribute(ULOGOVAN_KEY, korisnici.get(korisnickoIme));
		response.sendRedirect(bURL+"Filmovi");
	}
}
