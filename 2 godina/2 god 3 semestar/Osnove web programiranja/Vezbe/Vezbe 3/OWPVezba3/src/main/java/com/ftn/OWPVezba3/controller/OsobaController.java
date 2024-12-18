package com.ftn.OWPVezba3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.ftn.OWPVezba3.bean.Osoba;

@Controller
@RequestMapping(value = "/osoba")
public class OsobaController implements ServletContextAware{
	
	public static final String OSOBE_KEY = "osobe";
	
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 
	
	@PostConstruct
	public void init() {	
		List<Osoba> osobe = new ArrayList<>();
		servletContext.setAttribute(OsobaController.OSOBE_KEY,osobe);
	}
	
	@GetMapping
	public String prikazStraniceZaDodavanjeOsobe() {
		return "/dodaj-osobu.html";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "dodajOsobu")
	@ResponseBody
	public String dodajOsobu(@ModelAttribute Osoba osoba, BindingResult bind) {
		
		if (bind.hasErrors()) {
			 return "greška prilikom preuzimanja podataka";
        }
		
		List<Osoba> osobe = (List<Osoba>) servletContext.getAttribute(OsobaController.OSOBE_KEY);
		
		osobe.add(osoba);
		
		String htmlPrikaz = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Insert title here</title>\n" + 
				"</head>\n" + 
				"<body>\n";

		for(int i = 0; i < osobe.size(); ++i) {
			Osoba pOsoba = osobe.get(i);
			htmlPrikaz += "IME: " + pOsoba.getIme() + "<br>";
			htmlPrikaz += "PREZIME: " + pOsoba.getPrezime() + "<br>";
			htmlPrikaz += "<form method=\"post\" action=\"obrisiOsobu\">" + 
					"<input type=\"hidden\" name=\"id\" value=\""+ i +"\">" + 
					"<input type=\"submit\" value=\"Obriši\">" + 
					"</form>";
			htmlPrikaz += "<form method=\"get\" action=\"prikaziOsobu\">" + 
					"<input type=\"hidden\" name=\"id\" value=\""+ i +"\">" + 
					"<input type=\"submit\" value=\"Prikazi\">" + 
					"</form>";
			htmlPrikaz += "<a href = \"izmeniOsobu?id=" + i + "\">Izmeni</a></br>";
		}
		
		htmlPrikaz += "<a href = \"/osoba\">Dodaj novu osobu</a></br>";
		
		htmlPrikaz += "</body>\n" + 
				"</html>";
		return htmlPrikaz;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value="/prikaziOsobe")
	@ResponseBody
	public String prikazSveOsobe() {
		
		List<Osoba> osobe = (List<Osoba>) servletContext.getAttribute(OsobaController.OSOBE_KEY);
		
		String htmlPrikaz = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Insert title here</title>\n" + 
				"</head>\n" + 
				"<body>\n";

		for(int i = 0; i < osobe.size(); ++i) {
			Osoba osoba = osobe.get(i);
			htmlPrikaz += "IME: " + osoba.getIme() + "<br>";
			htmlPrikaz += "PREZIME: " + osoba.getPrezime() + "<br>";
			htmlPrikaz += "<form method=\"post\" action=\"obrisiOsobu\">" + 
					"<input type=\"hidden\" name=\"id\" value=\""+ i +"\">" + 
					"<input type=\"submit\" value=\"Obriši\">" + 
					"</form>";
			htmlPrikaz += "<form method=\"get\" action=\"prikaziOsobu\">" + 
					"<input type=\"hidden\" name=\"id\" value=\""+ i +"\">" + 
					"<input type=\"submit\" value=\"Prikazi\">" + 
					"</form>";
			htmlPrikaz += "<a href = \"izmeniOsobu?id=" + i + "\">Izmeni</a></br>";
		}
		htmlPrikaz += "<a href = \"/osoba\">Dodaj novu osobu</a></br>";
		htmlPrikaz += "</body>\n" + 
				"</html>";
		return htmlPrikaz;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "obrisiOsobu")
	@ResponseBody
	public String obrisiOsobu(@RequestParam int id) {	
		List<Osoba> osobe = (List<Osoba>) servletContext.getAttribute(OsobaController.OSOBE_KEY);
		osobe.remove(id);
		
		String htmlPrikaz = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Insert title here</title>\n" + 
				"</head>\n" + 
				"<body>\n";

		for(int i = 0; i < osobe.size(); ++i) {
			Osoba osoba = osobe.get(i);
			htmlPrikaz += "IME: " + osoba.getIme() + "<br>";
			htmlPrikaz += "PREZIME: " + osoba.getPrezime() + "<br>";
			htmlPrikaz += "<form method=\"post\" action=\"obrisiOsobu\">" + 
					"<input type=\"hidden\" name=\"id\" value=\""+ i +"\">" + 
					"<input type=\"submit\" value=\"Obriši\">" + 
					"</form>";
			htmlPrikaz += "<form method=\"get\" action=\"prikaziOsobu\">" + 
					"<input type=\"hidden\" name=\"id\" value=\""+ i +"\">" + 
					"<input type=\"submit\" value=\"Prikazi\">" + 
					"</form>";
			htmlPrikaz += "<a href = \"izmeniOsobu?id=" + i + "\">Izmeni</a></br>";
		}
		htmlPrikaz += "<a href = \"/osoba\">Dodaj novu osobu</a></br>";
		htmlPrikaz += "</body>\n" + 
				"</html>";
		return htmlPrikaz;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "prikaziOsobu")
	@ResponseBody
	public String prikaziOsobu(@RequestParam int id) {
		
		List<Osoba> osobe = (List<Osoba>) servletContext.getAttribute(OsobaController.OSOBE_KEY);
		
		String htmlPrikaz = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Insert title here</title>\n" + 
				"</head>\n" + 
				"<body>\n";
		Osoba osoba = osobe.get(id);
		htmlPrikaz += "IME: " + osoba.getIme() + "<br>";
		htmlPrikaz += "PREZIME: " + osoba.getPrezime() + "<br>";
		htmlPrikaz += "<a href = \"prikaziOsobe\">Prikazi sve osobe</a>";
		
		htmlPrikaz += "</body>\n" + 
				"</html>";
		return htmlPrikaz;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "izmeniOsobu")
	@ResponseBody
	public String izmeniOsobu(@RequestParam int id) {
		
		List<Osoba> osobe = (List<Osoba>) servletContext.getAttribute(OsobaController.OSOBE_KEY);
		
		Osoba osoba = osobe.get(id);
		
		String htmlPrikaz = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Insert title here</title>\n" + 
				"</head>\n" + 
				"<body>\n";
		
		htmlPrikaz += "<form action=\"izmeniOsobu\" method=\"post\">\n" + 
				"		<input type = \"text\" name= \"ime\" value=\"" + osoba.getIme() + "\" /> <br>\n" + 
				"		<input type = \"text\" name = \"prezime\" value=\"" + osoba.getPrezime() + "\"/> <br>\n" +
				"  		<input type = \"hidden\" name = \"id\" value=\"" + id + "\"/> <br>\n" +
				"		<input type = \"submit\" value = \"Izmeni\"/></br>\n" + 
				"	</form>";
		htmlPrikaz += "<a href = \"prikaziOsobe\">Prikazi sve osobe</a>";
		htmlPrikaz += "</body>\n" + 
				"</html>";
		return htmlPrikaz;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "izmeniOsobu")
	@ResponseBody
	public String izmeniOsobu(@RequestParam int id, @ModelAttribute Osoba osoba, BindingResult bind) {
		
		List<Osoba> osobe = (List<Osoba>) servletContext.getAttribute(OsobaController.OSOBE_KEY);
		
		if (bind.hasErrors()) {
			 return "greška prilikom preuzimanja podataka";
        }
		
		Osoba osobaZaIzmenu = osobe.get(id);
		osobaZaIzmenu.setIme(osoba.getIme());
		osobaZaIzmenu.setPrezime(osoba.getPrezime());
		
		
		String htmlPrikaz = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Insert title here</title>\n" + 
				"</head>\n" + 
				"<body>\n";

		for(int i = 0; i < osobe.size(); ++i) {
			Osoba pOsoba = osobe.get(i);
			htmlPrikaz += "IME: " + pOsoba.getIme() + "<br>";
			htmlPrikaz += "PREZIME: " + pOsoba.getPrezime() + "<br>";
			htmlPrikaz += "<form method=\"post\" action=\"obrisiOsobu\">" + 
					"<input type=\"hidden\" name=\"id\" value=\""+ i +"\">" + 
					"<input type=\"submit\" value=\"Obriši\">" + 
					"</form>";
			htmlPrikaz += "<form method=\"get\" action=\"prikaziOsobu\">" + 
					"<input type=\"hidden\" name=\"id\" value=\""+ i +"\">" + 
					"<input type=\"submit\" value=\"Prikazi\">" + 
					"</form>";
			htmlPrikaz += "<a href = \"izmeniOsobu?id=" + i + "\">Izmeni</a></br>";
		}
		htmlPrikaz += "<a href = \"/osoba\">Dodaj novu osobu</a></br>";
		htmlPrikaz += "</body>\n" + 
				"</html>";
		return htmlPrikaz;
	}
	
	
}
