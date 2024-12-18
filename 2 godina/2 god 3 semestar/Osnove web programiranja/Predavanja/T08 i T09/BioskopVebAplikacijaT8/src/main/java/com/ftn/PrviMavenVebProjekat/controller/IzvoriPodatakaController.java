package com.ftn.PrviMavenVebProjekat.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ftn.PrviMavenVebProjekat.bean.SecondConfiguration.MemorijaAplikacije;
import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;

@Controller
@RequestMapping(value="/IzvoriPodataka")
public class IzvoriPodatakaController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private MemorijaAplikacije memorijaAplikacije;
	
	@PostConstruct
	public void init() {
		
		if(memorijaAplikacije.get("film1")==null) {
			Film f = new Film(1001L, "FilmSpringBean", 101);
			memorijaAplikacije.put("film1", f);
		}
		
		if(servletContext.getAttribute("film2")==null) {
			Film f = new Film(1002L, "FilmServletContext", 102);
			servletContext.setAttribute("film2", f);
		}
	}
	
	
//	@GetMapping
//	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap map) {
//		
//		if(session.getAttribute("film3")==null) {
//			Film f = new Film(1003L, "FilmSession", 103);
//			session.setAttribute("film3", f);
//		}
//		
//		ModelAndView rezultat = new ModelAndView("izvoriPodataka");
//	    rezultat.addObject("film4", new Film(1004L, "FilmModel", 104));
//	    
//		map.put("film5", new Film(1005L, "FilmModel", 105));
//		
//		return rezultat;
//	}	
	
	@GetMapping
	public String index(HttpSession session, RedirectAttributes ra , ModelMap map) {
		
		if(session.getAttribute("film3")==null) {
			Film f = new Film(1003L, "FilmSession", 103);
			session.setAttribute("film3", f);
		}

	    map.put("film4", new Film(1004L, "FilmModel", 104));
		map.put("film5", new Film(1005L, "FilmModel", 105));
		
		return "izvoriPodataka";
	}	
}
