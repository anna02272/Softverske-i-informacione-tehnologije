package com.ftn.PrviMavenVebProjekat.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;

@Controller
@RequestMapping(value="/Objekti")
public class ObjektiController {
	
	
	@GetMapping
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		
		System.out.println(request.getParameter("parametar1"));
		
		request.setAttribute("tekst1", "Atribut zahteva tekst1");
		request.setAttribute("korisnik1", new Korisnik("pera", "pera", "pera@pera", "muški", true));
		map.put("korisnik2", new Korisnik("steva", "steva", "steva@steva", "muški"));
		
		response.setHeader("zaglavlje1", "Atribut zaglavlja odgovora");
		
		
		map.put("standardDate", new Date());
		map.put("localDateTime", LocalDateTime.now());
		map.put("localDate", LocalDate.now());
		map.put("timestamp", Instant.now());
		
		return "objekti";
		///?parametar1=query+parametar
	}	
}
