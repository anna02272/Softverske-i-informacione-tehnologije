package com.ftn.OWPVezba3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

import com.ftn.OWPVezba3.bean.Osoba;

@Controller
@RequestMapping(value = "/prijava")
public class PrijavaController implements ServletContextAware{
	
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 
	
	@GetMapping
	public String prijava() {
		return "/login.html";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping
	public void prijava(@RequestParam String username, @RequestParam String password, HttpServletResponse response) throws ServletException, IOException {
		
		List<Osoba> osobe = (List<Osoba>) servletContext.getAttribute(OsobaController.OSOBE_KEY);
		
		// ako postoji lista proveriti da li se korisnik nalazi u listi
		boolean found = false;
		for (Osoba osoba : osobe) {
			if (osoba.getUsername().equals(username) && osoba.getPassword().equals(password)) {
				found = true;
				break;
			}
		}
		
		// ako postoji redirect na prikaz svih, ako ga ne nadjemo u listi vracamo korisnika ponovo na unos
		if (found) {
			response.sendRedirect("/osoba/prikaziOsobe");
		} else {
			response.sendRedirect("/login.html");
		}
	}

}
