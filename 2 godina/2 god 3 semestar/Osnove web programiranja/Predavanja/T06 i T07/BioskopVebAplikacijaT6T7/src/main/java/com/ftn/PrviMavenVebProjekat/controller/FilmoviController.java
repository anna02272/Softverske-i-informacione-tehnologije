package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.FilmBrojac;
import com.ftn.PrviMavenVebProjekat.model.FilmStatistika;
import com.ftn.PrviMavenVebProjekat.model.Filmovi;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ProjekcijaService;

@Controller
@RequestMapping(value="/Filmovi")
public class FilmoviController implements ServletContextAware /*, ApplicationContextAware*/{

	public static final String STATISTIKA_FILMOVA_KEY = "StatistikaFilmova";
	public static final String POSECENI_FILMOVI_ZA_KORISNIKA_KEY = "PoseceniFilmoviZaKorisnika";
	
	@Autowired
//	@Qualifier("memorijaFilm")
//	@Qualifier("fajloviFilm")
	private FilmService filmService;
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
//	@Autowired
//	private ApplicationContext applicationContext;
	
//	@Autowired
//	private MemorijaAplikacije memorijaAplikacije;
	
	/** pristup ServletContext */
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 
	
//	/** pristup ApplicationContext */
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//	    this.applicationContext = applicationContext;    
//	}
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {
		//Specify the base URL for all relative URLs in a document
		bURL = servletContext.getContextPath()+"/";
	}
	
	/** pribavljanje HTML stanice za prikaz svih entiteta, get zahtev */
	// GET: Filmovi
	@GetMapping
	@ResponseBody
	public String index(HttpSession session, HttpServletResponse response) throws IOException {	
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz konteksta		
		FilmStatistika statistikaFilmova = (FilmStatistika)servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika)memorijaAplikacije.get(FilmoviController.STATISTIKA_FILMOVA_KEY);
		
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) session.getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null) {
			response.sendRedirect(bURL+"login.html");
			return "";
		}
		//preuzimanje vrednosti iz sesije	
		@SuppressWarnings("unchecked")
		List<Film> poseceniFilmovi = (List<Film>) session.getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);	
		
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" +
//				"	<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\">\r\n" + 
//				"	<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\"> \r\n" + 
//				"	<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\r\n" +
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">\r\n" + 
				"	<title>Filmovi</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body> "+
				"	<div> Prijavljen je <a href=\"Korisnici/Details?korisnickoIme="+korisnik.getKorisnickoIme()+"\">"+ korisnik.getIme() +" "+ korisnik.getPrezime()+ "</a> <a href=\"PrijavaOdjava/Logout\">Odjavi se</a></li></div>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"		<table>\r\n" + 
				"			<caption>Filmovi</caption>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>r. br.</th>\r\n" + 
				"				<th>naziv</th>\r\n" + 
				"				<th>trajanje</th>\r\n" + 
				"				<th>projekcije</th>\r\n" +
				"			</tr>\r\n");
		
//		List<Film> films = filmovi.findAll();
		//preuzimanje vrednosti preko Servisnog sloja
		List<Film> films = filmService.findAll();
		for (int i=0; i < films.size(); i++) {
			retVal.append(
				"			<tr>\r\n" + 
				"				<td class=\"broj\">"+ (i+1) +"</td>\r\n" + 
				"				<td><a href=\"Filmovi/Details?id="+films.get(i).getId()+"\">" +films.get(i).getNaziv() +"</a></td>\r\n" +
				"				<td class=\"broj\">"+ films.get(i).getTrajanje() +"</td>\r\n" +
				"				<td>\r\n"+				
				"					<a href=\"Projekcije?filmID="+films.get(i).getId()+"\">projekcije</a>\r\n" + 
				"					<form action=\"Projekcije\" method=\"get\">\r\n" + 
				"						<input type=\"hidden\" name=\"filmID\" value=\""+films.get(i).getId()+"\"/>\r\n" + 
				"						<input type=\"submit\" value=\"Projekcije\"/>\r\n" + 
				"					</form>\r\n" + 
				"				</td>\r\n"+
				"			</tr>\r\n");
		}
		retVal.append(
				"		</table>\r\n");
		if(korisnik.isAdministrator()) {
			retVal.append(
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi/Create\">dodavanje filma</a></li>\r\n" + 
				"	</ul>\r\n");
		}
		
		//rad se objektom pribavljenim iz ServletContext
		if(!statistikaFilmova.isEmpty()) {
			List<FilmBrojac> popularniFilmovi = statistikaFilmova.getFilmovi();
			
			int najvisePopularan = popularniFilmovi.get(0).getBrojac();
			
			retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Popularni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
			for (int i=0; i < popularniFilmovi.size(); i++) {
				retVal.append(
				"					<li>\n\r"+
				"						<a href=\"Filmovi/Details?id="+popularniFilmovi.get(i).getFilm().getId()+"\">"
													+popularniFilmovi.get(i).getFilm().getNaziv()+ "</a><br/>\r\n"+
				"						<progress value=\""+popularniFilmovi.get(i).getBrojac()+"\" "
												+ "max=\""+najvisePopularan+"\"></progress>"+popularniFilmovi.get(i).getBrojac()+"\r\n"+
				"					</li>\r\n");
			}
			retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");			
		}
		//rad se objektom pribavljenim iz sesije
		retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Posećeni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
		if(poseceniFilmovi.isEmpty()) {
			retVal.append(
					"					<li>Nema posećenih filmova</li>\r\n");
		}
		for (int i=0; i < poseceniFilmovi.size(); i++) {
			retVal.append(
				"					<li><a href=\"Filmovi/Details?id="+poseceniFilmovi.get(i).getId()+"\">"
											+poseceniFilmovi.get(i).getNaziv()+ "</a></li>\r\n");
		}
		retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");
		
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
	}
	
	/** pribavljanje HTML stanice za prikaz određenog entiteta , get zahtev */
	// GET: Filmovi/Details?id=1
	@GetMapping(value="/Details")
	@ResponseBody
	public String details(@RequestParam Long id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {	
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz konteksta		
		FilmStatistika statistikaFilmova = (FilmStatistika)servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika)memorijaAplikacije.get(FilmoviController.STATISTIKA_FILMOVA_KEY);
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) session.getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null) {
			response.sendRedirect(bURL+"login.html");
			return "";
		}
		//preuzimanje vrednosti iz sesije
		@SuppressWarnings("unchecked")
		List<Film> poseceniFilmovi = (List<Film>) request.getSession().getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
		
//		Film film = filmovi.findOne(id);
		//preuzimanje vrednosti preko Servisnog sloja
		Film film = filmService.findOne(id);
		if(film!=null) {
			statistikaFilmova.incrementBrojac(film);
			if(!poseceniFilmovi.contains(film))
				poseceniFilmovi.add(film);
		}
		else {
			response.sendRedirect(bURL+"Filmovi");
			return "";
		}
		
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
//				"	<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\">\r\n" + 
//				"	<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\"> \r\n" + 
//				"	<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\r\n" +
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<base href=\""+bURL+"\">\r\n" +
				"	<title>Film</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n" +
				"	<div> Prijavljen je <a href=\"Korisnici/Details?korisnickoIme="+korisnik.getKorisnickoIme()+"\">"+ korisnik.getIme() +" "+ korisnik.getPrezime()+ "</a> <a href=\"PrijavaOdjava/Logout\">Odjavi se</a></li></div>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"	</ul>\r\n" + 				
				"	<form method=\"post\" action=\"Filmovi/Edit\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\""+film.getId()+"\">\r\n" + 
				"		<table>\r\n" + 
				"			<caption>Film</caption>\r\n" + 
				"			<tr><th>naziv:</th><td><input type=\"text\" "+
				 				"value=\""+film.getNaziv()+"\" name=\"naziv\"/></td></tr>\r\n" + 
				"			<tr><th>trajanje:</th><td><input type=\"number\" min=\"5\" "+
				 				"value=\""+film.getTrajanje()+"\" name=\"trajanje\"/></td></tr>\r\n");
		if(korisnik.isAdministrator()) {
			retVal.append(
				"			<tr><th></th><td><input type=\"submit\" value=\"Izmeni\" /></td></tr>\r\n");
		}
		retVal.append(
				"		</table>\r\n" + 
				"	</form>\r\n" + 
				"	<br/>\r\n");
		
		if(korisnik.isAdministrator()) {
			retVal.append(
				"	<form method=\"post\" action=\"Filmovi/Delete\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\""+film.getId()+"\">\r\n" + 
				"		<table>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Obriši\"></td></tr>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n");
		}
		if(!statistikaFilmova.isEmpty()) {
			List<FilmBrojac> popularniFilmovi = statistikaFilmova.getFilmovi();
			
			int najvisePopularan = popularniFilmovi.get(0).getBrojac();
			
			retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Popularni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
			for (int i=0; i < popularniFilmovi.size(); i++) {
				retVal.append(
				"					<li>\n\r"+
				"						<a href=\"Filmovi/Details?id="+popularniFilmovi.get(i).getFilm().getId()+"\">"
													+popularniFilmovi.get(i).getFilm().getNaziv()+ "</a><br/>\r\n"+
				"						<progress value=\""+popularniFilmovi.get(i).getBrojac()+"\" "
												+ "max=\""+najvisePopularan+"\"></progress>"+popularniFilmovi.get(i).getBrojac()+"\r\n"+
				"					</li>\r\n");
			}
			retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");			
		}
		
		//rad se objektom pribavljenim iz sesije
		retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Posećeni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
		if(poseceniFilmovi.isEmpty()) {
			retVal.append(
					"					<li>Nema posećenih filmova</li>\r\n");
		}
		for (int i=0; i < poseceniFilmovi.size(); i++) {
			retVal.append(
				"					<li><a href=\"Filmovi/Details?id="+poseceniFilmovi.get(i).getId()+"\">"
											+poseceniFilmovi.get(i).getNaziv()+ "</a></li>\r\n");
		}
		retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");
				
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
	}
	
	/** pribavljanje HTML stanice za unos novog entiteta, get zahtev */
	// GET: Filmovi/Create
	@GetMapping(value="/Create")
	@ResponseBody
	public String create(HttpSession session, HttpServletResponse response) throws IOException {
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz konteksta
		FilmStatistika statistikaFilmova = (FilmStatistika)servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika)memorijaAplikacije.get(FilmoviController.STATISTIKA_FILMOVA_KEY);
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) session.getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"login.html");
			return "";
		}
		//preuzimanje vrednosti iz sesije
		@SuppressWarnings("unchecked")
		List<Film> poseceniFilmovi = (List<Film>) session.getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
		
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
//				"	<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\">\r\n" + 
//				"	<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\"> \r\n" + 
//				"	<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\r\n" +
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<base href=\""+bURL+"\">\r\n" + 
				"	<title>Film kreiranje</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<div> Prijavljen je <a href=\"Korisnici/Details?korisnickoIme="+korisnik.getKorisnickoIme()+"\">"+ korisnik.getIme() +" "+ korisnik.getPrezime()+ "</a> <a href=\"PrijavaOdjava/Logout\">Odjavi se</a></li></div>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"	<form method=\"post\" action=\"Filmovi/Create\">\r\n" + 
				"		<table>\r\n" + 
				"			<caption>Film</caption>\r\n" + 
				"			<tr><th>naziv:</th><td><input type=\"text\" value=\"\" name=\"naziv\"/></td></tr>\r\n" + 
				"			<tr><th>trajanje:</th><td><input type=\"number\" min=\"5\" "+
				 					"value=\"5\" name=\"trajanje\"/></td></tr>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Dodaj\" /></td>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n" +
				"	<br/>\r\n");
		if(!statistikaFilmova.isEmpty()) {
			List<FilmBrojac> popularniFilmovi = statistikaFilmova.getFilmovi();
			
			int najvisePopularan = popularniFilmovi.get(0).getBrojac();
			
			retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Popularni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
			for (int i=0; i < popularniFilmovi.size(); i++) {
				retVal.append(
				"					<li>\n\r"+
				"						<a href=\"Filmovi/Details?id="+popularniFilmovi.get(i).getFilm().getId()+"\">"
													+popularniFilmovi.get(i).getFilm().getNaziv()+ "</a><br/>\r\n"+
				"						<progress value=\""+popularniFilmovi.get(i).getBrojac()+"\" "
												+ "max=\""+najvisePopularan+"\"></progress>"+popularniFilmovi.get(i).getBrojac()+"\r\n"+
				"					</li>\r\n");
			}
			retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");			
		}
		
		//rad se objektom pribavljenim iz sesije
		retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Posećeni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
		if(poseceniFilmovi.isEmpty()) {
			retVal.append(
					"					<li>Nema posećenih filmova</li>\r\n");
		}
		for (int i=0; i < poseceniFilmovi.size(); i++) {
			retVal.append(
				"					<li><a href=\"Filmovi/Details?id="+poseceniFilmovi.get(i).getId()+"\">"
											+poseceniFilmovi.get(i).getNaziv()+ "</a></li>\r\n");
		}
		retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");
		
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
	}
	
	/** obrada podataka forme za unos novog entiteta, post zahtev */
	// POST: Filmovi/Create
	@PostMapping(value="/Create")
	public void create(@RequestParam String naziv, @RequestParam int trajanje, HttpSession session, HttpServletResponse response) throws IOException {		
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) session.getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		
		//preuzimanje vrednosti iz konteksta
		Film film = new Film(naziv, trajanje);
		
		//cuvanje vrednosti preko Servisnog sloja
		Film saved = filmService.save(film);
		response.sendRedirect(bURL+"Filmovi");
	}

	/** obrada podataka forme za izmenu postojećeg entiteta, post zahtev */
	// POST: Filmovi/Edit
	@PostMapping(value="/Edit")
	public void edit(@ModelAttribute Film filmEdited , BindingResult result, HttpSession session, HttpServletResponse response) throws IOException {	
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		if (result.hasErrors()) {
			//"greška prilikom preuzimanja podataka<br/>";
        }
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) session.getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"login.html");
		}
		
		//preuzimanje vrednosti preko Servisnog sloja
		Film film = filmService.findOne(filmEdited.getId());
		if(film!=null) {
			if(filmEdited.getNaziv()!=null && !filmEdited.getNaziv().trim().equals(""))
				film.setNaziv(filmEdited.getNaziv());
			if(filmEdited.getTrajanje() > 0)
				film.setTrajanje(filmEdited.getTrajanje());
		}
		
		//azuriranje vrednosti preko Servisnog sloja
		Film updated = filmService.update(film);
		response.sendRedirect(bURL+"Filmovi");
	}
	
	/** obrada podataka forme za brisanje postojećeg entiteta, post zahtev */
	// POST: Filmovi/Delete
	@PostMapping(value="/Delete")
	public void delete(@RequestParam Long id, HttpSession session, HttpServletResponse response) throws IOException {
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) session.getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"login.html");
		}
		
		//brisanje vrednosti preko Servisnog sloja
		Film deleted = filmService.delete(id);
		
		//ako se ukloni film moraju se obrisati i sve projekcije tog filma
		List<Projekcija> listaZaBrisanje = projekcijaService.findByFilm(deleted);		
		for (Projekcija projekcija : listaZaBrisanje) {
			projekcijaService.delete(projekcija.getId());
		}
		
		response.sendRedirect(bURL+"Filmovi");
	}
}
